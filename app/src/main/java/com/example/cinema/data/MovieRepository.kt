package com.example.cinema.data

object MovieRepository {
    private val movies = listOf(
        Movie(1, "The Matrix", 1999, "Sci-Fi"),
        Movie(2, "Inception", 2010, "Sci-Fi"),
        Movie(3, "Interstellar", 2014, "Sci-Fi"),
        Movie(4, "The Dark Knight", 2008, "Action"),
        Movie(5, "Gladiator", 2000, "Drama"),
        Movie(6, "Spirited Away", 2001, "Animation"),
        Movie(7, "Parasite", 2019, "Thriller"),
        Movie(8, "Whiplash", 2014, "Drama"),
        Movie(9, "La La Land", 2016, "Musical"),
        Movie(10, "Mad Max: Fury Road", 2015, "Action")
    )

    private val favorites = linkedSetOf<Int>()

    fun getMovies(): List<Movie> = movies

    fun getMovie(id: Int): Movie? = movies.find { it.id == id }

    fun isFavorite(id: Int): Boolean = favorites.contains(id)

    fun toggleFavorite(id: Int) {
        if (!favorites.add(id)) favorites.remove(id)
    }

    fun getFavoriteMovies(): List<Movie> = movies.filter { favorites.contains(it.id) }
}
