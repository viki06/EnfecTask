package com.example.sampleapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.sampleapp.model.Model
import com.example.sampleapp.repository.Repository
import com.example.sampleapp.ui.MainActivityViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock

class MainActivityViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var viewModel: MainActivityViewModel

    private lateinit var repository: Repository

    @Before
    fun setUp() {

        Dispatchers.setMain(testDispatcher)

        repository = mock(Repository::class.java)

        viewModel = MainActivityViewModel(repository)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getImageData - success response updates imageList`() = runTest {

        val dummyImageData = mutableListOf(Model.ImageData(url= "image_url"))

        `when`(repository.getImageData()).thenReturn(dummyImageData)

        val imageListObserver = mock<Observer<MutableList<Model.ImageData>>>()

        val isLoadingObserver = mock<Observer<Boolean>>()

        viewModel.imageList.observeForever(imageListObserver)

        viewModel.isLoading.observeForever(isLoadingObserver)

        viewModel.getImageData()

        verify(isLoadingObserver).onChanged(true)

        verify(isLoadingObserver).onChanged(false)

        verify(imageListObserver).onChanged(dummyImageData)

        viewModel.imageList.removeObserver(imageListObserver)

        viewModel.isLoading.removeObserver(isLoadingObserver)

    }

    @Test
    fun `getImageData - error response updates errorFeedback`() = runTest {

        val exception = RuntimeException("Error message")

        `when`(repository.getImageData()).thenThrow(exception)

        val errorObserver = mock<Observer<String>>()

        val isLoadingObserver = mock<Observer<Boolean>>()

        viewModel.errorFeedback.observeForever(errorObserver)

        viewModel.isLoading.observeForever(isLoadingObserver)

        viewModel.getImageData()

        verify(isLoadingObserver).onChanged(true)

        verify(isLoadingObserver).onChanged(false)

        verify(errorObserver).onChanged("Error message")

        viewModel.errorFeedback.removeObserver(errorObserver)

        viewModel.isLoading.removeObserver(isLoadingObserver)

    }
}