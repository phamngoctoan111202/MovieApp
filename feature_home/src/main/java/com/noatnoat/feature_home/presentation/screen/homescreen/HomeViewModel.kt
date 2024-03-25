package com.noatnoat.feature_home.presentation.screen.homescreen

import com.noatnoat.feature_base.presentation.nav.NavManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noatnoat.feature_home.domain.model.MovieCard
import com.noatnoat.feature_home.domain.usecase.GetMoviesByGenreUseCase
import kotlinx.coroutines.launch


class HomeViewModel(
    private val getMoviesByGenreUseCase: GetMoviesByGenreUseCase,
    private val navManager: NavManager
) : ViewModel() {

    internal val movieList: MutableLiveData<List<MovieCard>> = MutableLiveData()
    internal val watchedMovies: MutableLiveData<List<MovieCard>> = MutableLiveData()
    internal val topMovies: MutableLiveData<List<MovieCard>> = MutableLiveData()
    internal val topChineseMovies: MutableLiveData<List<MovieCard>> = MutableLiveData()
    internal val topKoreanMovies: MutableLiveData<List<MovieCard>> = MutableLiveData()
    internal val recommendedMovies: MutableLiveData<List<MovieCard>> = MutableLiveData()
    internal val userFavoriteMovies: MutableLiveData<List<MovieCard>> = MutableLiveData()
    internal val trendingMovies : MutableLiveData<List<MovieCard>> = MutableLiveData()


    internal val isLoading: MutableLiveData<Boolean> = MutableLiveData(true)


    fun updateMovieListBasedOnTab(tabIndex: Int) {
        when (tabIndex) {
            0 -> getNewMoviesInYear2024()
            1 -> getSingleMovies()
            2 -> getSeriesMovies()
            3 -> getTvShows()
            4 -> getAnimatedMovies()
            5 -> getExclusiveMovies()
            // Add more cases as needed
        }
    }

    fun getAllMovies() {
        viewModelScope.launch {
            try {
                val movies = getMoviesByGenreUseCase.invoke(null, null, null, null,null, null, null )
                movieList.value = movies
            } catch (e: Exception) {
            }
        }
    }


    private fun getAnimatedMovies() {
        viewModelScope.launch {
            try {
                isLoading.value = true
                val topMoviesResult = getMoviesByGenreUseCase.invoke("hoathinh", null, null, null, null, null, null)
                val topChineseMoviesResult = getMoviesByGenreUseCase.invoke("hoathinh", null, null, "Trung Quốc", null, null, null)
                val topKoreanMoviesResult = getMoviesByGenreUseCase.invoke("hoathinh", null, null, "Hàn Quốc", null, null, null)

                topMovies.value = topMoviesResult
                topChineseMovies.value = topChineseMoviesResult
                topKoreanMovies.value = topKoreanMoviesResult
                isLoading.value = false

            } catch (e: Exception) {
                isLoading.value = false
            }
        }
    }

    private fun getSingleMovies() {
        viewModelScope.launch {
            try {
                isLoading.value = true

                val topMoviesResult = getMoviesByGenreUseCase.invoke("single", null, null, null, null, null, null)
                val topChineseMoviesResult = getMoviesByGenreUseCase.invoke("single", null, null, "Trung Quốc", null, null, null)
                val topKoreanMoviesResult = getMoviesByGenreUseCase.invoke("single", null, null, "Hàn Quốc", null, null, null)

                topMovies.value = topMoviesResult
                topChineseMovies.value = topChineseMoviesResult
                topKoreanMovies.value = topKoreanMoviesResult

                isLoading.value = false
            } catch (e: Exception) {
                isLoading.value = false

            }
        }
    }

    private fun getSeriesMovies() {
        viewModelScope.launch {
            try {
                isLoading.value = true

                val topMoviesResult = getMoviesByGenreUseCase.invoke("series", null, null, null, null, null, null)
                val topChineseMoviesResult = getMoviesByGenreUseCase.invoke("series", null, null, "Trung Quốc", null, null, null)
                val topKoreanMoviesResult = getMoviesByGenreUseCase.invoke("series", null, null, "Hàn Quốc", null, null, null)

                topMovies.value = topMoviesResult
                topChineseMovies.value = topChineseMoviesResult
                topKoreanMovies.value = topKoreanMoviesResult

                isLoading.value = false
            } catch (e: Exception) {
                isLoading.value = false
            }
        }
    }

    private fun getTvShows() {
        viewModelScope.launch {
            try {
                isLoading.value = true

                val topMoviesResult = getMoviesByGenreUseCase.invoke("tvshows", null, null, null, null, null, null)
                val topChineseMoviesResult = getMoviesByGenreUseCase.invoke("tvshows", null, null, "Trung Quốc", null, null, null)
                val topKoreanMoviesResult = getMoviesByGenreUseCase.invoke("tvshows", null, null, "Hàn Quốc", null, null, null)

                topMovies.value = topMoviesResult
                topChineseMovies.value = topChineseMoviesResult
                topKoreanMovies.value = topKoreanMoviesResult

                isLoading.value = false

            } catch (e: Exception) {
                isLoading.value = false
            }
        }
    }

    private fun getNewMoviesInYear2024() {
        viewModelScope.launch {
            try {
                isLoading.value = true

                val topMoviesResult = getMoviesByGenreUseCase.invoke(null, 2024, null, null, null, null, null)
                val topChineseMoviesResult = getMoviesByGenreUseCase.invoke(null, 2024, null, "Trung Quốc", null, null, null)
                val topKoreanMoviesResult = getMoviesByGenreUseCase.invoke(null, 2024, null, "Hàn Quốc", null, null, null)

                topMovies.value = topMoviesResult
                topChineseMovies.value = topChineseMoviesResult
                topKoreanMovies.value = topKoreanMoviesResult

                isLoading.value = false
            } catch (e: Exception) {
                isLoading.value = false
            }
        }
    }

    private fun getExclusiveMovies() {
        viewModelScope.launch {
            try {
                isLoading.value = true

                val topMoviesResult = getMoviesByGenreUseCase.invoke(null, null, null, null, true, null, null)
                val topChineseMoviesResult = getMoviesByGenreUseCase.invoke(null, null, null, "Trung Quốc", true, null, null)
                val topKoreanMoviesResult = getMoviesByGenreUseCase.invoke(null, null, null, "Hàn Quốc", true, null, null)

                topMovies.value = topMoviesResult
                topChineseMovies.value = topChineseMoviesResult
                topKoreanMovies.value = topKoreanMoviesResult

                isLoading.value = false
            } catch (e: Exception) {
                isLoading.value = false
            }
        }
    }


    fun getActionMovies() {
        viewModelScope.launch {
            try {
                val movies = getMoviesByGenreUseCase.invoke(null, null, "Hành Động", null, null, null, null)
                movieList.value = movies
            } catch (e: Exception) {
            }
        }
    }

    fun onMovieClick(movieCard: MovieCard) {
        val navDirections = HomeFragmentDirections.actionHomeFragmentToDetailFragment(idMovie = movieCard.id)
        navManager.navigate(navDirections)
    }



}
