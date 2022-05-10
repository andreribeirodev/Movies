package com.andreribeiro.nttfilmes.repository

import com.andreribeiro.nttfilmes.client.iFilmeClient
import com.andreribeiro.nttfilmes.model.FilmeDetailsResponse
import com.andreribeiro.nttfilmes.model.FilmePopularWeekly
import com.andreribeiro.nttfilmes.model.FilmeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.await

class FilmeRepository(private val filmeClient: iFilmeClient) : iFilmeRepository {

    override suspend fun getFilmesTrendsFromApi(): Call<FilmeResponse> {
        return withContext(Dispatchers.IO) {
            filmeClient.getFilmes()
        }
    }

    override suspend fun getFilmesTrendsDetailsFromApi(movieID: Int): Call<FilmeDetailsResponse> {
        return withContext(Dispatchers.IO) {
            filmeClient.getFilmeDetails(movieId = movieID)
        }
    }

}
