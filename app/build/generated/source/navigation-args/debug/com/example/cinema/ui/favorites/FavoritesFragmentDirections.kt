package com.example.cinema.ui.favorites

import android.os.Bundle
import androidx.navigation.NavDirections
import com.example.cinema.R
import kotlin.Int
import kotlin.String

public class FavoritesFragmentDirections private constructor() {
  private data class ActionFavoritesFragmentToMovieDetailFragment(
    public val movieId: Int,
    public val movieTitle: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_favoritesFragment_to_movieDetailFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putInt("movieId", this.movieId)
        result.putString("movieTitle", this.movieTitle)
        return result
      }
  }

  public companion object {
    public fun actionFavoritesFragmentToMovieDetailFragment(movieId: Int, movieTitle: String):
        NavDirections = ActionFavoritesFragmentToMovieDetailFragment(movieId, movieTitle)
  }
}
