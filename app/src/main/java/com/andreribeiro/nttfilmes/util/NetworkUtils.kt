package com.andreribeiro.nttfilmes.util

import com.andreribeiro.nttfilmes.client.iFilmeClient
import com.andreribeiro.nttfilmes.util.Constants.Companion.BASE_URL_FILMES
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkUtils {
    companion object {
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL_FILMES)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        val filmeClient: iFilmeClient by lazy {
            retrofit.create(iFilmeClient::class.java)
        }
    }
}
