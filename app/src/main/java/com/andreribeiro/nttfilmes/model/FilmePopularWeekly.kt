package com.andreribeiro.nttfilmes.model
import com.google.gson.annotations.SerializedName

data class FilmePopularWeekly(
    @SerializedName("id")
    val id:Int?,
    @SerializedName("title")
    val titulo: String?,
    @SerializedName("release_date")
    val dataLancamento: String?,
    @SerializedName("poster_path")
    val posterUrl: String?,
)

fun mockFilme() = listOf(
    FilmePopularWeekly(
        id = 1,
        titulo = "ALTO DA COMPADECIDA",
        dataLancamento = "22-10-1994",
        posterUrl = ""
    )

)