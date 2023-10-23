package com.imen.tppremiereapplication

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel(  savedStateHandle: SavedStateHandle) : ViewModel() {
    val movies = MutableStateFlow<List<Movie>>(listOf())
    val movie = MutableStateFlow<MovieDetail>(MovieDetail())
    val tvShows = MutableStateFlow<List<TvShow>>(listOf())
    val actors = MutableStateFlow<List<Actor>>(listOf())

    val apiKey = "91b96427e6bd332e5170be1040a81847"

    fun getMovies() {
        viewModelScope.launch {
            movies.value = api.lastMovies(apiKey).results
            Log.v("xxx", "taille:" + movies.value.size)

        }
    }

    fun getMovie(id:Int) {
        viewModelScope.launch {
           movie.value = api.getMovie(id,apiKey);
        }
    }

    fun getTVShows() {
        viewModelScope.launch {
            tvShows.value = api.lastTVShow(apiKey).results
        }
    }

    fun getActors() {
        viewModelScope.launch {
            actors.value = api.getActors(apiKey).results
        }
    }

    fun findMovies(query: String) {
        viewModelScope.launch {
            movies.value = api.searchMovies(query, apiKey).results
            Log.v("xxx", "taille:" + movies.value.size)
            Log.v("xxx", "query:" + query)
        }
    }
}