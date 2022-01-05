package com.segunfrancis.manualpaging

import com.segunfrancis.manualpaging.data.repository.IManualPagingRepository
import com.segunfrancis.manualpaging.data.repository.ManualPagingRepository
import com.segunfrancis.manualpaging.di.ApiClient
import com.segunfrancis.manualpaging.util.mockEngine
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RepositoryTest {

    private lateinit var testApiClient: ApiClient
    private lateinit var repository: IManualPagingRepository

    @Before
    fun setup() {
        testApiClient = ApiClient(mockEngine)
        repository = ManualPagingRepository(testApiClient.client)
    }

    @Test
    fun `test api response is not null`() {
        runBlocking {
            val actual = repository.getMovies(1)
            Assert.assertNotNull(actual)
        }
    }

    @Test
    fun `test api response has correct page number`() {
        runBlocking {
            val actual = repository.getMovies(1)
            Assert.assertEquals(1, actual.page)
        }
    }
}
