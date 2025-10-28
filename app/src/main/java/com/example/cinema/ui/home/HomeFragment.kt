package com.example.cinema.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinema.data.MovieRepository
import com.example.cinema.databinding.FragmentHomeBinding
import com.example.cinema.ui.movies.MovieAdapter
import com.example.cinema.ui.home.HomeFragmentDirections

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val highlights = MovieRepository.getMovies().take(3)
        adapter = MovieAdapter(
            items = highlights,
            onClick = { movie ->
                val action = HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(
                    movieId = movie.id,
                    movieTitle = movie.title
                )
                findNavController().navigate(action)
            },
            onToggleFavorite = { movie ->
                MovieRepository.toggleFavorite(movie.id)
                val index = highlights.indexOfFirst { it.id == movie.id }
                if (index != -1) adapter.notifyItemChanged(index)
            },
            isFavorite = { id -> MovieRepository.isFavorite(id) }
        )
        val lm = LinearLayoutManager(requireContext())
        binding.recyclerHighlights.layoutManager = lm
        binding.recyclerHighlights.adapter = adapter
        binding.recyclerHighlights.setHasFixedSize(true)
        binding.recyclerHighlights.addItemDecoration(DividerItemDecoration(requireContext(), lm.orientation))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
