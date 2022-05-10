package com.andreribeiro.nttfilmes.model
import com.google.gson.annotations.SerializedName

data class FilmeDetailsResponse(
    @SerializedName("title")
    val titulo: String?,
    @SerializedName("backdrop_path")
    val capaUrl: String?,
    @SerializedName("release_date")
    val launchDate: String?,
    @SerializedName("poster_path")
    val posterUrl: String?,
    @SerializedName("vote_average")
    val rating: Float?,
    @SerializedName("overview")
    val visaoGeral: String?,
    @SerializedName("genres")
    val generos:List<Genero>
)

data class Genero(
    val id: Int,
    val name: String
)