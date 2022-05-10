package com.andreribeiro.nttfilmes.util

class Constants {
    companion object{
        const val BASE_URL_FILMES = "https://api.themoviedb.org/3/"
        private const val API_KEY = "?api_key=9a5fc4034822caf62e96c1da132582a0&page=1&language=pt-BR"
        private const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/"

        private const val IMAGE_SIZE_W500 = "w500"
        private const val IMAGE_SIZE_W185 = "w185"
        private const val IMAGE_SIZE_W154 = "w154"

        const val BASE_IMAGE_LARGE = BASE_IMAGE_URL + IMAGE_SIZE_W500
        const val BASE_IMAGE_MEDIUM = BASE_IMAGE_URL + IMAGE_SIZE_W185
        const val BASE_IMAGE_SMALL = BASE_IMAGE_URL + IMAGE_SIZE_W154

        const val ENDPOINT_TRENDING = "trending/movie/week$API_KEY"
        const val ENDPOINT_DETAILS = "movie/{movie_id}$API_KEY"
    }
}