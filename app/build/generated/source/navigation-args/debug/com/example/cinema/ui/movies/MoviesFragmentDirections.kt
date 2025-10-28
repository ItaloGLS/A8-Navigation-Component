package com.example.cinema.ui.movies

import android.os.Bundle
import androidx.navigation.NavDirections
import com.example.cinema.R
import kotlin.Int
import kotlin.String

public class MoviesFragmentDirections private constructor() {
  private data class ActionMoviesFragmentToMovieDetailFragment(
    public val movieId: Int,
    public val movieTitle: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_moviesFragment_to_movieDetailFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putInt("movieId", this.movieId)
        result.putString("movieTitle", this.movieTitle)
        return result
      }
  }

  public companion object {
    public fun actionMoviesFragmentToMovieDetailFragment(movieId: Int, movieTitle: String):
        NavDirections = ActionMoviesFragmentToMovieDetailFragment(movieId, movieTitle)
  }
}
