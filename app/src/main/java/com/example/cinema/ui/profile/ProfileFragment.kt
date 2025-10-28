package com.example.cinema.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cinema.databinding.FragmentProfileBinding
import com.example.cinema.data.MovieRepository

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val count = MovieRepository.getFavoriteMovies().size
        binding.textFavoritesCount.text = "Favoritos: $count"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
