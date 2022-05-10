package com.andreribeiro.nttfilmes.view.adapter
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andreribeiro.nttfilmes.databinding.FilmeItemBinding
import com.andreribeiro.nttfilmes.model.FilmePopularWeekly
import com.andreribeiro.nttfilmes.view.activities.FilmeDetailsActivity
import com.andreribeiro.nttfilmes.view.activities.FilmeListActivity
import com.andreribeiro.nttfilmes.util.Constants.Companion.BASE_IMAGE_SMALL
import com.bumptech.glide.Glide

class AdapterFilmeList(private val context: FilmeListActivity, private val listFilmes: List<FilmePopularWeekly>)
    : RecyclerView.Adapter<AdapterFilmeList.FilmeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmeViewHolder {
        val binding = FilmeItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return FilmeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmeViewHolder, position: Int) {
        holder.tituloFilme.text = listFilmes[position].titulo
        holder.dataFilme.text = listFilmes[position].dataLancamento

        Glide.with(context)
            .load("$BASE_IMAGE_SMALL/${listFilmes[position].posterUrl}")
            .into(holder.posterFilme)
    }

    override fun getItemCount(): Int = listFilmes.size

    inner class FilmeViewHolder(private val binding: FilmeItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val tituloFilme = binding.textViewTitulo
        val posterFilme = binding.imageViewPoster
        val dataFilme = binding.textViewDataLancamento

        val itemView = binding.root.apply {
            setOnClickListener{
                val intent = Intent(context, FilmeDetailsActivity::class.java).apply {
                    putExtra("id",listFilmes[adapterPosition].id)
                }
                context.startActivity(intent)

            }
        }
    }
}

