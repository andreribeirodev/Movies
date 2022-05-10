package com.andreribeiro.nttfilmes.view.activities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.andreribeiro.nttfilmes.databinding.ActivityFilmeBinding
import com.andreribeiro.nttfilmes.model.FilmePopularWeekly
import com.andreribeiro.nttfilmes.model.FilmeResponse
import com.andreribeiro.nttfilmes.repository.FilmeRepository
import com.andreribeiro.nttfilmes.util.NetworkUtils
import com.andreribeiro.nttfilmes.view.adapter.AdapterFilmeList
import com.andreribeiro.nttfilmes.viewmodel.FilmeViewModel
import com.andreribeiro.nttfilmes.viewmodel.FilmeViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
//import com.andreribeiro.nttfilmes.viewmodel.FilmeViewModelFactory
import retrofit2.Response
import retrofit2.await

class FilmeListActivity : AppCompatActivity() {

    private val filmeClient by lazy {
        NetworkUtils.filmeClient
    }
    private val binding: ActivityFilmeBinding by lazy {
        ActivityFilmeBinding.inflate(layoutInflater)
    }

    private lateinit var filmeAdapter: AdapterFilmeList
    private val filmeRepository = FilmeRepository(filmeClient)
    private val filmeFactory = FilmeViewModelFactory(filmeRepository)
    private val filmeViewModel by viewModels<FilmeViewModel> { filmeFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.rvFilmesTrending.layoutManager = LinearLayoutManager(this)

        //getFilmesSemCoroutines()
        //getFilmesComCoroutines()
        getFilmesTrendsComViewModel()

    }

    private fun getFilmesTrendsComViewModel() {
        filmeViewModel.getFilmesTrendsFromRetrofit()
        filmeViewModel.filmes.observe(this) { filmes -> setupAdapter(filmes) }
    }

    private fun getFilmesComCoroutines() {
        lifecycleScope.launch {
            var listaDeFilmes: List<FilmePopularWeekly>
            withContext(Dispatchers.IO) {
                val filmes = filmeClient.getFilmes().await().listFilmes
                listaDeFilmes = filmes
            }
            setupAdapter(listaDeFilmes)
        }
    }

    private fun getFilmesSemCoroutines() {
        filmeClient.getFilmes().enqueue(object : retrofit2.Callback<FilmeResponse> {
            override fun onResponse(call: retrofit2.Call<FilmeResponse>, response: Response<FilmeResponse>) {
                val res = response.body()
                val filmes = res!!.listFilmes
                setupAdapter(filmes)
            }
            override fun onFailure(call: retrofit2.Call<FilmeResponse>, t: Throwable) {
                println("Não deu mesmo poxa.. !!")
            }
        })
    }

    private fun setupAdapter(filmes: List<FilmePopularWeekly>) {
        filmeAdapter = AdapterFilmeList(this, filmes)
        binding.rvFilmesTrending.adapter = filmeAdapter
    }
}







