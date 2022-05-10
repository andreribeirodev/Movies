package com.andreribeiro.nttfilmes.client

import com.andreribeiro.nttfilmes.model.FilmeDetailsResponse
import com.andreribeiro.nttfilmes.model.FilmeResponse
import com.andreribeiro.nttfilmes.util.Constants.Companion.ENDPOINT_DETAILS
import com.andreribeiro.nttfilmes.util.Constants.Companion.ENDPOINT_TRENDING
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface iFilmeClient {

    @GET(ENDPOINT_TRENDING)
    fun getFilmes(): Call<FilmeResponse>

    @GET(ENDPOINT_DETAILS)
    fun getFilmeDetails(@Path("movie_id") movieId: Int): Call<FilmeDetailsResponse>
}