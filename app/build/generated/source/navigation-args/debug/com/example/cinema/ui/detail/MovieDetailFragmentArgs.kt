package com.example.cinema.ui.detail

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Int
import kotlin.String
import kotlin.jvm.JvmStatic

public data class MovieDetailFragmentArgs(
  public val movieId: Int,
  public val movieTitle: String,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putInt("movieId", this.movieId)
    result.putString("movieTitle", this.movieTitle)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("movieId", this.movieId)
    result.set("movieTitle", this.movieTitle)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): MovieDetailFragmentArgs {
      bundle.setClassLoader(MovieDetailFragmentArgs::class.java.classLoader)
      val __movieId : Int
      if (bundle.containsKey("movieId")) {
        __movieId = bundle.getInt("movieId")
      } else {
        throw IllegalArgumentException("Required argument \"movieId\" is missing and does not have an android:defaultValue")
      }
      val __movieTitle : String?
      if (bundle.containsKey("movieTitle")) {
        __movieTitle = bundle.getString("movieTitle")
        if (__movieTitle == null) {
          throw IllegalArgumentException("Argument \"movieTitle\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"movieTitle\" is missing and does not have an android:defaultValue")
      }
      return MovieDetailFragmentArgs(__movieId, __movieTitle)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): MovieDetailFragmentArgs {
      val __movieId : Int?
      if (savedStateHandle.contains("movieId")) {
        __movieId = savedStateHandle["movieId"]
        if (__movieId == null) {
          throw IllegalArgumentException("Argument \"movieId\" of type integer does not support null values")
        }
      } else {
        throw IllegalArgumentException("Required argument \"movieId\" is missing and does not have an android:defaultValue")
      }
      val __movieTitle : String?
      if (savedStateHandle.contains("movieTitle")) {
        __movieTitle = savedStateHandle["movieTitle"]
        if (__movieTitle == null) {
          throw IllegalArgumentException("Argument \"movieTitle\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"movieTitle\" is missing and does not have an android:defaultValue")
      }
      return MovieDetailFragmentArgs(__movieId, __movieTitle)
    }
  }
}
