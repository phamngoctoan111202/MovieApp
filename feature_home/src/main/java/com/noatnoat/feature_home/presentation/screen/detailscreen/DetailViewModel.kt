package com.noatnoat.feature_home.presentation.screen.detailscreen


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.noatnoat.feature_home.domain.model.MovieDetail
import com.noatnoat.feature_home.domain.usecase.AddMovieToFavouriteList
import com.noatnoat.feature_home.domain.usecase.CheckMovieInFavouriteList
import com.noatnoat.feature_home.domain.usecase.DeleteMoviesFromFavouriteList
import com.noatnoat.feature_home.domain.usecase.GetMovieDetailsUseCase
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val addMovieToFavouriteList: AddMovieToFavouriteList,
    private val deleteMoviesFromFavouriteList: DeleteMoviesFromFavouriteList,
    private val checkMovieInFavouriteList: CheckMovieInFavouriteList
    ) : ViewModel() {

    val movieDetail = MutableLiveData<MovieDetail>()
    val currentVideoLink = MutableLiveData<String>()
    val isSaved = MutableLiveData<Boolean>()


    fun getMovieDetail(args: DetailFragmentArgs) {
        viewModelScope.launch {
            val movieDetails = getMovieDetailsUseCase(args.idMovie)
            movieDetail.value = movieDetails
            currentVideoLink.value = movieDetail.value!!.serverData?.get(0)
        }


    }

    fun setVideoLink(link: String) {
        currentVideoLink.value = link
    }


    fun addToFavouriteList(movieId: String) {
        viewModelScope.launch {
            addMovieToFavouriteList(movieId)
        }
    }

    fun removeFromFavouriteList(movieId: String) {
        viewModelScope.launch {
            deleteMoviesFromFavouriteList(movieId)
        }
    }

    suspend fun isMovieInFavouriteList(movieId: String) {
        isSaved.value = checkMovieInFavouriteList(movieId)
    }

    fun toggleFavouriteStatus(movieId: String) {
        viewModelScope.launch {
            val isFavourite = checkMovieInFavouriteList(movieId)
            isSaved.value = !isFavourite
        }
    }
}


