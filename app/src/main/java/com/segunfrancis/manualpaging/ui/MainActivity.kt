package com.segunfrancis.manualpaging.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.segunfrancis.manualpaging.databinding.ActivityMainBinding
import com.segunfrancis.manualpaging.ui.model.UiResponse
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val movieAdapter: MainAdapter by lazy { MainAdapter() }
    private var recyclerViewState: Parcelable? = null
    private var scrollPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        setupAdapter()
    }

    private fun setupObservers() {
        viewModel.uiState.observe(this) { state ->
            when (state) {
                MainUiState.Loading -> handleLoading()
                MainUiState.Success -> {
                    viewModel.response.observe(this) {
                        handleSuccess(it)
                    }
                }
                is MainUiState.Error -> handleError(state.error.localizedMessage)
            }
        }
    }

    private fun handleSuccess(data: List<UiResponse>) = with(binding) {
        progressIndicator.isGone = true
        recyclerViewState = movieRecyclerView.layoutManager?.onSaveInstanceState()
        movieAdapter.submitList(data)
        movieRecyclerView.layoutManager?.onRestoreInstanceState(recyclerViewState)
    }

    private fun handleLoading() = with(binding) {
        progressIndicator.isVisible = true
    }

    private fun handleError(errorMessage: String?) = with(binding) {
        progressIndicator.isGone = true
        Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_SHORT).show()
        Timber.d(errorMessage)
    }

    private fun setupAdapter() = with(binding) {
        movieRecyclerView.apply {
            val linearLayoutManager = LinearLayoutManager(this@MainActivity)
            layoutManager = linearLayoutManager
            adapter = movieAdapter
            addItemDecoration(DividerItemDecoration(this@MainActivity, RecyclerView.VERTICAL))
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (!recyclerView.canScrollVertically(1) && dy > 0) {
                        viewModel.getMovies { position ->
                            if (position != null) {
                                scrollPosition = position
                            }
                        }
                    }
                }
            })

            movieAdapter.registerAdapterDataObserver(adapterDataObserver)
        }
    }

    private val adapterDataObserver = object : RecyclerView.AdapterDataObserver() {
        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            binding.movieRecyclerView.scrollToPosition(scrollPosition)
        }
    }

    override fun onDestroy() {
        movieAdapter.unregisterAdapterDataObserver(adapterDataObserver)
        super.onDestroy()
    }
}
