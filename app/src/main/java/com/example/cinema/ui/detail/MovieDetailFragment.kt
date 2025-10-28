package com.example.cinema.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.cinema.databinding.FragmentMovieDetailBinding
import com.example.cinema.data.MovieRepository

class MovieDetailFragment : Fragment() {
    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    private val args: MovieDetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        render()
        binding.buttonFavorite.setOnClickListener {
            MovieRepository.toggleFavorite(args.movieId)
            render()
        }
    }

    private fun render() {
        val movie = MovieRepository.getMovie(args.movieId)
        if (movie != null) {
            binding.textViewTitle.text = movie.title
            binding.textViewSubtitle.text = "${movie.genre} • ${movie.year}"
            binding.buttonFavorite.text = if (MovieRepository.isFavorite(movie.id)) "Remover dos favoritos" else "Adicionar aos favoritos"
        } else {
            binding.textViewTitle.text = args.movieTitle
            binding.textViewSubtitle.text = ""
            binding.buttonFavorite.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
