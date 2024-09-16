package com.example.sampleapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleapp.model.Model
import com.example.sampleapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _isLoading: MutableLiveData<Boolean> by lazy { MutableLiveData() }
    val isLoading = _isLoading

    private val _errorFeedback: MutableLiveData<String> by lazy { MutableLiveData() }
    val errorFeedback = _errorFeedback

    private val _imageList: MutableLiveData<MutableList<Model.ImageData>> by lazy { MutableLiveData() }
    val imageList = _imageList

    fun getImageData() {

        _isLoading.postValue(true)

        try {

            viewModelScope.launch(Dispatchers.IO) {
                processData(repository.getImageData())
            }

        } catch (throwable: Throwable) {
            processError(throwable)
        }

    }

    private fun processError(throwable: Throwable) {

        _isLoading.postValue(false)

        _errorFeedback.postValue(throwable.message ?: "Please try again")

    }

    private fun processData(imageData: MutableList<Model.ImageData>) {

        _isLoading.postValue(false)

        _imageList.postValue(imageData)

    }


}