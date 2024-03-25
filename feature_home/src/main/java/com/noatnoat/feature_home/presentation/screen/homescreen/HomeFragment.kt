package com.noatnoat.feature_home.presentation.screen.homescreen

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.ComposeView
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.noatnoat.feature_base.presentation.activity.BaseFragment
import com.noatnoat.feature_base.presentation.composable.ExclusiveMoviesLabel
import com.noatnoat.feature_base.presentation.composable.RemoteImageFromUrl
import com.noatnoat.feature_base.presentation.composable.WatchedMoviesLabel
import com.noatnoat.feature_base.presentation.composable.compose_colors.BasicColors
import com.noatnoat.feature_home.domain.model.MovieCard
import com.noatnoat.movieapp.home.R
import org.koin.androidx.navigation.koinNavGraphViewModel


class HomeFragment : BaseFragment() {
    private val homeViewModel: HomeViewModel by koinNavGraphViewModel(R.id.feature_home_nav)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {
                MovieListScreen(homeViewModel)

//                homeViewModel.searchMovies(query)
//                GroupedList()
//                MyTextField()
            }
        }
    }

}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onExecuteSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    Box(modifier = Modifier
        .fillMaxSize()
        .pointerInput(Unit) {
            detectTapGestures(onTap = { focusManager.clearFocus() })
        }) {
        TextField(
            value = query,
            onValueChange = { onQueryChanged(it) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = { onExecuteSearch() }
            ),
            textStyle = TextStyle(fontSize = 12.sp),
            singleLine = true,
            placeholder = { Text("Search...") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    modifier = Modifier.clickable {  }
                )
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    start = 8.dp,
                    top = 4.dp,
                    end = 8.dp,
                    bottom = 4.dp
                ),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Black,
            )
        )
    }
}



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieListScreen(homeViewModel: HomeViewModel) {
    val movieList by homeViewModel.movieList.observeAsState()
    val topMovies by homeViewModel.topMovies.observeAsState()
    val topChineseMovies by homeViewModel.topChineseMovies.observeAsState()
    val topKoreanMovies by homeViewModel.topKoreanMovies.observeAsState()
    val isLoading by homeViewModel.isLoading.observeAsState(true)

    var selectedTabIndex by remember { mutableStateOf(0) }

    val tabList = listOf(
        stringResource(id = R.string.new_movies),
        stringResource(id = R.string.single_movies),
        stringResource(id = R.string.series_movies),
        stringResource(id = R.string.tv_shows),
        stringResource(id = R.string.animation),
        stringResource(id = R.string.exclusive_movies)

    )

    LazyColumn(modifier = Modifier
        .background(BasicColors.backgroundColor)) {

        item {
            var query by remember { mutableStateOf("") }


            CustomTopBar(
                query = query,
                onQueryChanged = { newQuery -> query = newQuery },
                onExecuteSearch = {
                },
                homeViewModel,
                isLoading
            )


        }
        stickyHeader {

            LaunchedEffect(selectedTabIndex) {
                homeViewModel.updateMovieListBasedOnTab(selectedTabIndex)
            }

            MovieTabRow(
                tabList = tabList,
                selectedTabIndex = selectedTabIndex,
                onTabSelected = { index -> selectedTabIndex = index }
            )
        }




        item {
            Column {
//                Text(text = tabList[selectedTabIndex] + " " + stringResource(id = R.string.watched_movies), color = Color.White)

//                LazyRow {
//                    items(movieList ?: emptyList()) { movieCard ->
//                        CardMovie(
//                            movieCard = movieCard,
//                            homeViewModel = homeViewModel,
//                            modifier = Modifier
//                                .height(200.dp)
//                                .width(120.dp),
//                            isLoading
//                        )
//                    }
//                }

                Text(text = tabList[selectedTabIndex] + " " + stringResource(id = R.string.top10_movies), color = Color.White, fontSize = 28.sp, modifier = Modifier.padding(12.dp))
                LazyRow {
                    items(topMovies ?: emptyList()) { movieCard ->
                        CardMovie(
                            movieCard = movieCard,
                            homeViewModel = homeViewModel,
                            modifier = Modifier
                                .height(200.dp)
                                .width(120.dp),
                            isLoading
                        )
                    }
                }

                Text(text = tabList[selectedTabIndex] + " " + stringResource(id = R.string.chinese_movies), color = Color.White, fontSize = 28.sp,  modifier = Modifier.padding(12.dp))
                LazyRow {
                    items(topChineseMovies ?: emptyList()) { movieCard ->
                        CardMovie(
                            movieCard = movieCard,
                            homeViewModel = homeViewModel,
                            modifier = Modifier
                                .height(200.dp)
                                .width(120.dp),
                            isLoading
                        )
                    }
                }

                Text(text = tabList[selectedTabIndex] + " " + stringResource(id = R.string.korean_movies), color = Color.White, fontSize = 28.sp,  modifier = Modifier.padding(12.dp))
                LazyRow {
                    items(topKoreanMovies ?: emptyList()) { movieCard ->
                        CardMovie(
                            movieCard = movieCard,
                            homeViewModel = homeViewModel,
                            modifier = Modifier
                                .height(200.dp)
                                .width(120.dp),
                            isLoading
                        )
                    }
                }


                Text(text = tabList[selectedTabIndex] + " " + stringResource(id = R.string.recommended_movies), color = Color.White, fontSize = 28.sp, modifier = Modifier.padding(12.dp))
                LazyRow {
                    val shuffledMovies = topKoreanMovies?.shuffled() ?: emptyList()
                    items(shuffledMovies) { movieCard ->
                        CardMovie(
                            movieCard = movieCard,
                            homeViewModel = homeViewModel,
                            modifier = Modifier
                                .height(200.dp)
                                .width(120.dp),
                            isLoading
                        )
                    }
                }

                Text(text = tabList[selectedTabIndex] + " " + stringResource(id = R.string.exclusive_movies), color = Color.White, fontSize = 28.sp, modifier = Modifier.padding(12.dp))
                LazyRow {
                    val shuffledMovies = topChineseMovies?.shuffled() ?: emptyList()
                    items(shuffledMovies) { movieCard ->
                        CardMovie(
                            movieCard = movieCard,
                            homeViewModel = homeViewModel,
                            modifier = Modifier
                                .height(200.dp)
                                .width(120.dp),
                            isLoading
                        )
                    }
                }

            }
        }

    }
}


@Composable
fun MovieTabRow(
    tabList: List<String>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = modifier,
        edgePadding = 0.dp,
        containerColor = Color.Black,
        contentColor = Color.White
    ) {
        tabList.forEachIndexed { index, title ->
            Tab(
                text = { Text(title) },
                selected = selectedTabIndex == index,
                onClick = { onTabSelected(index) }
            )
        }
    }
}


@Composable
fun CustomTopBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onExecuteSearch: () -> Unit,
    homeViewModel: HomeViewModel,
    isLoading: Boolean
) {

    val movieCard1 = MovieCard("65c463045540d59e3fe7a164", "", "https://img.ophim12.cc/uploads/movies/dem-nguoc-ba-giay-de-yeu-em-thumb.jpg")
    val movieCard2 = MovieCard("65cf6dae5540d59e3ff410e7", "", "https://img.ophim12.cc/uploads/movies/nam-cuoc-hen-ho-thumb.jpg")
    val movieCard3 = MovieCard("65265e1caab68118a3aac403", "", "https://img.ophim12.cc/uploads/movies/tin-hieu-con-tim-s6-thumb.jpg")
    val movieCard4 = MovieCard("65488bb4aa7d07ce5c7262c7", "", "https://img.ophim12.cc/uploads/movies/phan-boi-tnk-thumb.jpg")
    val movieCard5 = MovieCard("65a1023531d8a2b99ea1825a", "", "https://img.ophim12.cc/uploads/movies/hoa-no-ve-dem-thumb.jpg")
    val movieCard6 = MovieCard("65d6e239e5f40e2ffe5d3d7c", "", "https://img.ophim12.cc/uploads/movies/nang-tho-cua-miller-thumb.jpg")

    BoxWithConstraints {
        val screenWidth = maxWidth

        Box {
            LazyRow() {
                item {
                    CardMovie(
                        movieCard = movieCard1,
                        homeViewModel = homeViewModel,
                        modifier = Modifier
                            .width(screenWidth)
                            .graphicsLayer(alpha = 0.6f),
                        isLoading
                    )
                }
                item {
                    CardMovie(
                        movieCard = movieCard2,
                        homeViewModel = homeViewModel,
                        modifier = Modifier
                            .width(screenWidth)
                            .graphicsLayer(alpha = 0.6f),
                        isLoading
                    )
                }

                item {
                    CardMovie(
                        movieCard = movieCard3,
                        homeViewModel = homeViewModel,
                        modifier = Modifier
                            .width(screenWidth)
                            .graphicsLayer(alpha = 0.6f),
                        isLoading
                    )
                }
                item {
                    CardMovie(
                        movieCard = movieCard4,
                        homeViewModel = homeViewModel,
                        modifier = Modifier
                            .width(screenWidth)
                            .graphicsLayer(alpha = 0.6f),
                        isLoading
                    )
                }

                item {
                    CardMovie(
                        movieCard = movieCard5,
                        homeViewModel = homeViewModel,
                        modifier = Modifier
                            .width(screenWidth)
                            .graphicsLayer(alpha = 0.6f),
                        isLoading
                    )
                }

                item {
                    CardMovie(
                        movieCard = movieCard6,
                        homeViewModel = homeViewModel,
                        modifier = Modifier
                            .width(screenWidth)
                            .graphicsLayer(alpha = 0.6f),
                        isLoading
                    )
                }
            }

            Column {
                SearchBar(
                    query = query,
                    onQueryChanged = onQueryChanged,
                    onExecuteSearch = onExecuteSearch,
                    modifier = Modifier.fillMaxWidth() // SearchBar chiếm 80% chiều ngang
                )
            }

            FloatingActionButton(
                onClick = { /* Handle click */ },
                contentColor = Color.White,
                modifier = Modifier.align(Alignment.Center),
                shape = CircleShape,
                containerColor = Color.Red
            ) {
                Icon(Icons.Filled.PlayArrow, contentDescription = "Play video")
            }


        }
    }
}



@Composable
fun CardMovie(
    movieCard: MovieCard? = null,
    homeViewModel: HomeViewModel,
    modifier: Modifier = Modifier,
    isLoading: Boolean = true,
) {
    Box {
        Card(
            modifier
                .clickable(onClick = { movieCard?.let { homeViewModel.onMovieClick(it) } })
                .padding(8.dp)
                .aspectRatio(0.6f),
            shape = RoundedCornerShape(4.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
        ) {
            Column {
                if (isLoading || movieCard == null) {
                    Box(Modifier.fillMaxSize()) {
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    }
                } else {
                    RemoteImageFromUrl(
                        movieCard.thumbUrl,
                        Modifier
                            .weight(4f)
                    )
                    if (movieCard.name.isNotEmpty()) {
                        Text(
                            text = movieCard.name,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
//        WatchedMoviesLabel(
//            modifier = Modifier
//                .align(Alignment.TopEnd)
//                .padding(0.dp)
//        )
    }
}








