package com.noatnoat.feature_favourite.presentation.screen.favouritescreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.noatnoat.feature_base.presentation.composable.FavouriteMoviesLabel
import com.noatnoat.feature_base.presentation.composable.RemoteImageFromUrl
import com.noatnoat.feature_base.presentation.composable.WatchedMoviesLabel
import com.noatnoat.feature_base.presentation.composable.compose_colors.BasicColors
import com.noatnoat.feature_favourite.domain.model.FavouriteMovieCard
import com.noatnoat.movieapp.favourite.R
import org.koin.androidx.navigation.koinNavGraphViewModel

class FavouriteFragment : Fragment() {
    private val favouriteViewModel: FavouriteViewModel by koinNavGraphViewModel(R.id.feature_favorite_nav)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        favouriteViewModel.getFavouriteMovies()


        return ComposeView(requireContext()).apply {
            setContent {
                FavouriteMoviesComposable(favouriteViewModel)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavouriteMoviesComposable(favouriteViewModel : FavouriteViewModel) {
    val favouriteMovies by favouriteViewModel.favouriteMoviesList.observeAsState(listOf())
    if (favouriteMovies.isEmpty()) {
        Box(Modifier.fillMaxSize().background(BasicColors.backgroundColor), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(modifier = Modifier.background(BasicColors.backgroundColor)) {
            stickyHeader {
                Text(
                    text = stringResource(id = R.string.favourite_fragment_title),
                    style = TextStyle(color = BasicColors.textColor, fontSize = 24.sp),
                    modifier = Modifier
                        .background(BasicColors.backgroundColor)
                        .fillMaxWidth()
                        .padding(vertical = 24.dp, horizontal = 16.dp)
                        .shadow(16.dp)
                )
            }

            items(favouriteMovies.chunked(3)) { rowMovies ->
                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                    for (movie in rowMovies) {
                        CardMovie(
                            favouriteMoviesModel = movie,
                            favouriteViewModel = favouriteViewModel,
                            modifier = Modifier
                                .height(200.dp)
                                .width(120.dp)
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun CardMovie(
    favouriteMoviesModel: FavouriteMovieCard? = null,
    favouriteViewModel: FavouriteViewModel,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
) {
    Box {
        Card(
            modifier
                .clickable(onClick = { favouriteMoviesModel?.let { favouriteViewModel.onMovieClick(it) } })
                .padding(8.dp)
                .aspectRatio(0.6f),
            shape = RoundedCornerShape(4.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
        ) {
            Column {
                if (isLoading || favouriteMoviesModel == null) {
                    Box(Modifier.fillMaxSize()) {
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    }
                } else {
                    RemoteImageFromUrl(
                        favouriteMoviesModel.thumbUrl,
                        Modifier
                            .weight(4f)
                    )
                    if (favouriteMoviesModel.name.isNotEmpty()) {
                        Text(
                            text = favouriteMoviesModel.name,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
        FavouriteMoviesLabel(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(0.dp)
        )

    }
}


