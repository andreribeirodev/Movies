package com.andreribeiro.nttfilmes.viewmodel

import androidx.lifecycle.*
import com.andreribeiro.nttfilmes.model.FilmeDetailsResponse
import com.andreribeiro.nttfilmes.repository.iFilmeRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.await

class FilmeDetailsViewModel(
    private val filmeRepository: iFilmeRepository
) : ViewModel() {

    private val _filmeDetails = MutableLiveData<FilmeDetailsResponse>()
    val filmeDetails: LiveData<FilmeDetailsResponse> = _filmeDetails

    fun getFilmeDetailsFromApi(movieID: Int) {
        viewModelScope.launch {
            val details = filmeRepository.getFilmesTrendsDetailsFromApi(movieID = movieID).await()
            _filmeDetails.value = details
        }
    }
}

class FilmeDetailsViewModelFactory(private val repository: iFilmeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FilmeDetailsViewModel(repository) as T
    }
}