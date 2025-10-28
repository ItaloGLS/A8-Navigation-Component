package com.example.cinema.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinema.data.MovieRepository
import com.example.cinema.databinding.FragmentFavoritesBinding
import com.example.cinema.ui.movies.MovieAdapter
import com.example.cinema.ui.favorites.FavoritesFragmentDirections

class FavoritesFragment : Fragment() {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val favorites = MovieRepository.getFavoriteMovies().toMutableList()
        adapter = MovieAdapter(
            items = favorites,
            onClick = { movie ->
                val action = FavoritesFragmentDirections.actionFavoritesFragmentToMovieDetailFragment(
                    movieId = movie.id,
                    movieTitle = movie.title
                )
                findNavController().navigate(action)
            },
            onToggleFavorite = { movie ->
                MovieRepository.toggleFavorite(movie.id)
                val index = favorites.indexOfFirst { it.id == movie.id }
                if (index != -1) {
                    favorites.removeAt(index)
                    adapter.notifyItemRemoved(index)
                }
            },
            isFavorite = { id -> MovieRepository.isFavorite(id) }
        )
        binding.recyclerFavorites.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerFavorites.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
