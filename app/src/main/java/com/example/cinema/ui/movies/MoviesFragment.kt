package com.example.cinema.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cinema.ui.movies.MoviesFragmentDirections
import com.example.cinema.databinding.FragmentMoviesBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinema.data.MovieRepository
import androidx.recyclerview.widget.DividerItemDecoration

class MoviesFragment : Fragment() {
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movies = MovieRepository.getMovies()
        adapter = MovieAdapter(
            items = movies,
            onClick = { movie ->
                val action = MoviesFragmentDirections.actionMoviesFragmentToMovieDetailFragment(
                    movieId = movie.id,
                    movieTitle = movie.title
                )
                findNavController().navigate(action)
            },
            onToggleFavorite = { movie ->
                MovieRepository.toggleFavorite(movie.id)
                val index = movies.indexOfFirst { it.id == movie.id }
                if (index != -1) adapter.notifyItemChanged(index)
            },
            isFavorite = { id -> MovieRepository.isFavorite(id) }
        )
        val lm = LinearLayoutManager(requireContext())
        binding.recyclerMovies.layoutManager = lm
        binding.recyclerMovies.adapter = adapter
        binding.recyclerMovies.setHasFixedSize(true)
        binding.recyclerMovies.addItemDecoration(DividerItemDecoration(requireContext(), lm.orientation))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
