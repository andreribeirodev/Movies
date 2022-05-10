package com.andreribeiro.nttfilmes.view.activities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.andreribeiro.nttfilmes.databinding.ActivityDetailsFilmeBinding
import com.andreribeiro.nttfilmes.model.FilmeDetailsResponse
import com.andreribeiro.nttfilmes.repository.FilmeRepository
import com.andreribeiro.nttfilmes.util.Constants
import com.andreribeiro.nttfilmes.util.Constants.Companion.BASE_IMAGE_LARGE
import com.andreribeiro.nttfilmes.util.NetworkUtils
import com.andreribeiro.nttfilmes.viewmodel.FilmeDetailsViewModel
import com.andreribeiro.nttfilmes.viewmodel.FilmeDetailsViewModelFactory
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.*

class FilmeDetailsActivity : AppCompatActivity() {

    private val binding: ActivityDetailsFilmeBinding by lazy {
        ActivityDetailsFilmeBinding.inflate(layoutInflater)
    }
    private val filmeClient by lazy {
        NetworkUtils.filmeClient
    }
    private val filmeID by lazy {
        intent.getIntExtra("id", 0)
    }

    private val filmeRepository = FilmeRepository(filmeClient)
    private val filmeDetailsFactory = FilmeDetailsViewModelFactory(filmeRepository)
    private val filmeDetailsViewModel by viewModels<FilmeDetailsViewModel> { filmeDetailsFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //getFilmesDetailsSemCoroutines()
        //getFilmesDetailsComCoroutines()
        getFilmesDetailsComViewModel()
    }

    private fun getFilmesDetailsComViewModel() {
        filmeDetailsViewModel.getFilmeDetailsFromApi(filmeID)
        filmeDetailsViewModel.filmeDetails.observe(this) { filmeDetails -> setupUi(filmeDetails) }
    }

    private fun setupUi(filmeDetails: FilmeDetailsResponse) {
        Glide.with(this@FilmeDetailsActivity)
            .load("${BASE_IMAGE_LARGE}${filmeDetails.capaUrl}")
            .centerCrop()
            .into(binding.imageViewBanner)

        Glide.with(this@FilmeDetailsActivity)
            .load("${Constants.BASE_IMAGE_SMALL}/${filmeDetails.posterUrl}")
            .into(binding.imageViewSecondPoster)

        binding.textViewDateLaunch.text = filmeDetails.launchDate
        binding.textViewTituloFilme.text = filmeDetails.titulo
        binding.textViewOverview.text = filmeDetails.visaoGeral
        binding.rateBarFilme.rating = filmeDetails.rating!! / 2
    }

    private fun getFilmesDetailsSemCoroutines() {
        filmeClient.getFilmeDetails(filmeID).enqueue(object : Callback<FilmeDetailsResponse> {
            override fun onResponse(
                call: Call<FilmeDetailsResponse>,
                response: Response<FilmeDetailsResponse>
            ) {
                val filmeDetails = response.body()
                // Configura a UI:  Não configurei, só para demostrar as chamadas de formas diferentes.
            }

            override fun onFailure(call: Call<FilmeDetailsResponse>, t: Throwable) {
                println("Não deu meu nobre.. hahaha")
            }
        })
    }

    private fun getFilmesDetailsComCoroutines() {
        lifecycleScope.launch {
            var filmeDetalhes: FilmeDetailsResponse
            withContext(Dispatchers.IO) {
            val filmeDetails = filmeClient.getFilmeDetails(filmeID).await()
                filmeDetalhes = filmeDetails
            }
            // continua configuração...: Exemplo de implementação com Coroutine.
            binding.textViewDateLaunch.text = filmeDetalhes.launchDate
            binding.textViewTituloFilme.text = filmeDetalhes.titulo
        }
    }
}