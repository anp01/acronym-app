package com.example.acronym.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.acronym.models.WordResponse
import com.example.acronym.others.Resource
import com.example.acronym.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    private val _wordListResponse = MutableLiveData<Resource<WordResponse>>()
    val acronymWord = MutableLiveData<String>()
    val wordListResponse: LiveData<Resource<WordResponse>>
        get() = _wordListResponse

    /**
     * fetch word list from api
     */
    fun fetchWordListResponse() {
        viewModelScope.launch {
            _wordListResponse.postValue(Resource.Loading())
            try {
                val response = repository.fetchWordList(acronymWord.value.toString())
                if (response.isSuccessful)
                    _wordListResponse.postValue(Resource.Success(response.body()))
                else
                    _wordListResponse.postValue(Resource.Error("Something went wrong, Please try again."))

            } catch (e: Exception) {
                _wordListResponse.postValue(Resource.Error(e.localizedMessage))
            }
        }
    }

    /**
     * reset api response variable and set results based on acronymWord data length
     */
    fun resetResponseData() {
        val message = if (acronymWord.value?.isNotEmpty() == true)
            "Please press search button to get the data."
        else
            "Please enter search keywords."
        _wordListResponse.postValue(Resource.Error(message))
    }
}