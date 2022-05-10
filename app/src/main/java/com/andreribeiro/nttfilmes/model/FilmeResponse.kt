package com.andreribeiro.nttfilmes.model
import com.google.gson.annotations.SerializedName

data class FilmeResponse(
    val page:Int,
    @SerializedName("results")
    val listFilmes: List<FilmePopularWeekly>
)