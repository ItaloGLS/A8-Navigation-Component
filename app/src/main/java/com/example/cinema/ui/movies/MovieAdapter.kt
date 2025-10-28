package com.example.cinema.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema.data.Movie
import com.example.cinema.databinding.ItemMovieBinding

class MovieAdapter(
    private val items: List<Movie>,
    private val onClick: (Movie) -> Unit,
    private val onToggleFavorite: (Movie) -> Unit,
    private val isFavorite: (Int) -> Boolean,
) : RecyclerView.Adapter<MovieAdapter.VH>() {

    class VH(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val movie = items[position]
        with(holder.binding) {
            title.text = movie.title
            subtitle.text = "${movie.genre} • ${movie.year}"
            favorite.text = if (isFavorite(movie.id)) "★" else "☆"
            root.setOnClickListener { onClick(movie) }
            favorite.setOnClickListener { onToggleFavorite(movie) }
        }
    }
}
