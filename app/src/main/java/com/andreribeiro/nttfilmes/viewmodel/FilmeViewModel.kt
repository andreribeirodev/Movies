package com.andreribeiro.nttfilmes.viewmodel
import androidx.lifecycle.*
import com.andreribeiro.nttfilmes.model.FilmePopularWeekly
import com.andreribeiro.nttfilmes.repository.iFilmeRepository
import kotlinx.coroutines.launch
import retrofit2.await

class FilmeViewModel(
    private val filmeRepository: iFilmeRepository
) : ViewModel() {

    private val _filmes = MutableLiveData<List<FilmePopularWeekly>>()
    val filmes: LiveData<List<FilmePopularWeekly>> = _filmes

    fun getFilmesTrendsFromRetrofit() {
        viewModelScope.launch {
            val listaFilmeFromApi = filmeRepository.getFilmesTrendsFromApi().await().listFilmes
            _filmes.value = listaFilmeFromApi
        }
    }
}

class FilmeViewModelFactory(private val repository: iFilmeRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FilmeViewModel(repository) as T
    }
}
