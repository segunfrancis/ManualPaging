package com.segunfrancis.manualpaging.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.segunfrancis.manualpaging.data.repository.IManualPagingRepository
import com.segunfrancis.manualpaging.ui.model.UiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: IManualPagingRepository) : ViewModel() {

    private val _uiState = MutableLiveData<MainUiState>()
    val uiState: LiveData<MainUiState> = _uiState

    private val _response = MutableLiveData<MutableList<UiResponse>>(mutableListOf())
    val response: LiveData<MutableList<UiResponse>> = _response

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _uiState.postValue(MainUiState.Error(throwable))
        Timber.e(throwable)
    }

    private var currentPage: Int = 1

    init {
        getMovies { }
    }

    fun getMovies(scrollPosition: (position: Int?) -> Unit) {
        _uiState.postValue(MainUiState.Loading)
        viewModelScope.launch(exceptionHandler) {
            kotlin.runCatching {
                val baseResponse = repository.getMovies(page = currentPage)
                currentPage = if (baseResponse.page < baseResponse.total_pages) {
                    baseResponse.page + 1
                } else {
                    baseResponse.total_pages
                }
                baseResponse.results.map {
                    UiResponse(
                        id = it.id,
                        title = it.title,
                        overview = it.overview
                    )
                }
            }.fold(onSuccess = {
                _uiState.postValue(MainUiState.Success)
                if (_response.value?.isEmpty() == true) {
                    _response.postValue(it.toMutableList())
                    scrollPosition.invoke(null)
                } else {
                    val currentList = _response.value
                    scrollPosition.invoke(currentList?.size?.minus(1))
                    currentList?.addAll(it)
                    val mainList = currentList?.toSet()
                    _response.postValue(mainList?.toMutableList())
                }
            },
                onFailure = { _uiState.postValue(MainUiState.Error(it)) }
            )
        }
    }
}

sealed class MainUiState {
    object Loading : MainUiState()
    object Success : MainUiState()
    data class Error(val error: Throwable) : MainUiState()
}
