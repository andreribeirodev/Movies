package com.andreribeiro.nttfilmes.repository
import com.andreribeiro.nttfilmes.model.FilmePopularWeekly
import com.andreribeiro.nttfilmes.model.FilmeDetailsResponse
import com.andreribeiro.nttfilmes.model.FilmeResponse
import retrofit2.Call

interface iFilmeRepository {

    suspend fun getFilmesTrendsFromApi() : Call<FilmeResponse>

    suspend fun getFilmesTrendsDetailsFromApi(movieID: Int) : Call<FilmeDetailsResponse>
}