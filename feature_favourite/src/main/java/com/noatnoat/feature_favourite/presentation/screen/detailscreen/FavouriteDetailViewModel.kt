package com.noatnoat.feature_favourite.presentation.screen.detailscreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noatnoat.feature_favourite.domain.model.FavouriteMovieDetail
import com.noatnoat.feature_favourite.domain.usecase.AddMovieToFavouriteList
import com.noatnoat.feature_favourite.domain.usecase.CheckMovieInFavouriteList
import com.noatnoat.feature_favourite.domain.usecase.DeleteMoviesFromFavouriteList
import com.noatnoat.feature_favourite.domain.usecase.GetFavouriteMovieDetailUsecase
import kotlinx.coroutines.launch

class FavouriteDetailViewModel(
    private val getMovieDetailsUseCase: GetFavouriteMovieDetailUsecase,
    private val addMovieToFavouriteList: AddMovieToFavouriteList,
    private val deleteMoviesFromFavouriteList: DeleteMoviesFromFavouriteList,
    private val checkMovieInFavouriteList: CheckMovieInFavouriteList
    ) : ViewModel() {

    val favouriteMovieDetail = MutableLiveData<FavouriteMovieDetail>()
    val currentVideoLink = MutableLiveData<String>()
    val isSaved = MutableLiveData<Boolean>()


    fun getMovieDetail(args: FavouriteDetailFragmentArgs) {
        viewModelScope.launch {
            val movieDetails = getMovieDetailsUseCase(args.idMovie)
            favouriteMovieDetail.value = movieDetails
            currentVideoLink.value = favouriteMovieDetail.value!!.serverData?.get(0)
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