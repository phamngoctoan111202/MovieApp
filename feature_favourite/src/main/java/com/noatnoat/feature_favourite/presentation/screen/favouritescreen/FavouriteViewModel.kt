package com.noatnoat.feature_favourite.presentation.screen.favouritescreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noatnoat.feature_base.presentation.nav.NavManager
import com.noatnoat.feature_favourite.domain.model.FavouriteMovieCard
import com.noatnoat.feature_favourite.domain.usecase.GetFavouriteMoviesUsecase
import kotlinx.coroutines.launch

class FavouriteViewModel(
    private val getFavouriteMoviesUsecase: GetFavouriteMoviesUsecase,
    private val navManager: NavManager
) : ViewModel() {
    internal val favouriteMoviesList: MutableLiveData<List<FavouriteMovieCard>> = MutableLiveData()

    fun getFavouriteMovies() {
        viewModelScope.launch {
            val movies = getFavouriteMoviesUsecase.invoke()
            favouriteMoviesList.value = movies
        }
    }

    fun onMovieClick(favouriteMoviesModel: FavouriteMovieCard) {
        val navDirections = FavouriteFragmentDirections.actionFavouriteFragmentToFavouriteDetailFragment(idMovie = favouriteMoviesModel.id)
        navManager.navigate(navDirections)
    }
}
