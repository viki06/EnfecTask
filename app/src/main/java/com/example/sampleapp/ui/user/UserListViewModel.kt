package com.example.sampleapp.ui.user

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
class UserListViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _userData : MutableLiveData<MutableList<Model.User>> by lazy { MutableLiveData() }
    val userData = _userData

    private val _isLoading : MutableLiveData<Boolean> by lazy { MutableLiveData() }
    val isLoading = _isLoading

    private val _errorFeedback: MutableLiveData<String> by lazy { MutableLiveData() }
    val errorFeedback = _errorFeedback

    fun getUserData(){

        viewModelScope.launch(Dispatchers.IO) {

            _isLoading.postValue(true)

            try {

                _userData.postValue(repository.getUserData())

                _isLoading.postValue(false)

            }catch (throwable :Throwable){

                _isLoading.postValue(false)

                _errorFeedback.postValue(throwable.message)

            }

        }

    }



}