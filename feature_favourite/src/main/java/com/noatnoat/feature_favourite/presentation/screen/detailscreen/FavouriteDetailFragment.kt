package com.noatnoat.feature_favourite.presentation.screen.detailscreen

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerControlView
import androidx.media3.ui.PlayerView
import androidx.navigation.fragment.navArgs
import coil.compose.rememberImagePainter
import com.noatnoat.movieapp.favourite.R
import org.koin.androidx.navigation.koinNavGraphViewModel

class FavouriteDetailFragment : Fragment() {
    private val detailViewModel: FavouriteDetailViewModel by koinNavGraphViewModel(R.id.feature_favorite_nav)
    private lateinit var player: Player

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val args: FavouriteDetailFragmentArgs by navArgs()

        detailViewModel.getMovieDetail(args)

        return ComposeView(requireContext()).apply {
            setContent {
                val context = LocalContext.current
                player = ExoPlayer.Builder(context).build()


                MoviePlayerView(player, detailViewModel)

            }
        }
    }
    override fun onPause() {
        super.onPause()
        player.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }

    override fun onResume() {
        super.onResume()
        player.play()
    }
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

fun Context.setScreenOrientation(orientation: Int) {
    val activity = this.findActivity() ?: return
    activity.requestedOrientation = orientation
    if (orientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
        hideSystemUi()
    } else {
        showSystemUi()
    }

}

fun Context.hideSystemUi() {
    val activity = this.findActivity() ?: return
    val window = activity.window ?: return
    WindowCompat.setDecorFitsSystemWindows(window, false)
    WindowInsetsControllerCompat(window, window.decorView).let { controller ->
        controller.hide(WindowInsetsCompat.Type.systemBars())
        controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}

fun Context.showSystemUi() {
    val activity = this.findActivity() ?: return
    val window = activity.window ?: return
    WindowCompat.setDecorFitsSystemWindows(window, true)
    WindowInsetsControllerCompat(
        window,
        window.decorView
    ).show(WindowInsetsCompat.Type.systemBars())
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@androidx.annotation.OptIn(UnstableApi::class) @Composable
fun MoviePlayerView(player2: Player, detailViewModel: FavouriteDetailViewModel) {
    val context = LocalContext.current
    val playerView = remember { PlayerView(context) }
    playerView.player = player2

    val controlView = PlayerControlView(context)
    playerView.useController = true
    playerView.addView(controlView)

    val currentVideoLink by detailViewModel.currentVideoLink.observeAsState()
    val movieDetailState by detailViewModel.favouriteMovieDetail.observeAsState()


    Log.d("abc", "MoviePlayerView: ${detailViewModel.favouriteMovieDetail.value?.id}")
    DisposableEffect(currentVideoLink) {
        val mediaItem = currentVideoLink?.let { MediaItem.fromUri(it) }
        if (mediaItem != null) {
            player2.setMediaItem(mediaItem)
        }
        player2.prepare()

        onDispose {
        }
    }
    var isFullScreen by remember { mutableStateOf(false) }
    if (isFullScreen) {
        BoxWithConstraints {
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = {  PlayerView(context).apply {
                    player = player2
                    setFullscreenButtonClickListener {
                        isFullScreen = !isFullScreen
                        with(context) {
                            setScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                        }
                    }
                } },
                update = { view ->
                    view.player = player2
                }
            )
        }
    } else {
        Column(modifier = Modifier.background(Color.Black)
        ) {
            AndroidView(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                factory = {  PlayerView(context).apply {
                    player = player2
                    setFullscreenButtonClickListener {
                        isFullScreen = !isFullScreen
                        with(context) {
                            setScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

                        }
                    }
                } },
                update = { view ->
                    view.player = player2
                }
            )


            LazyColumn(modifier = Modifier
                .padding(14.dp)
                .background(Color.Black)) {
                item {
                    movieDetailState?.let { detail ->
                        Text(
                            text = "${detail.name}",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(16.dp)
                        )

                        DetailRow("Số tập", "${detail.episodeCurrent}/${detail.episodeTotal}")
                        detail.content?.let { DetailRow("Nội dung", it) }
                        DetailRow("Thể loại", detail.category?.joinToString(", ") ?: "")
                        detail.country?.let { DetailRow("Quốc gia", it) }
                        detail.year?.let { DetailRow("Năm", it.toString()) }
                        detail.view?.let { DetailRow("Lượt xem", it.toString()) }
                        detail.lang?.let { DetailRow("Ngôn ngữ", it) }
                    }
                }

                item {
                    movieDetailState?.let { ListItemWithButtons(detailViewModel, it.id) }
                }

                item {
                    MovieDetailScreen(detailViewModel)

                }


            }


        }
    }


}


@Composable
fun DetailRow(title: String, content: String) {
    Row(modifier = Modifier.padding(8.dp)) {
        Text(
            text = "$title: ",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.width(100.dp)
        )
        Text(
            text = content,
            color = Color.White,
        )
    }
}

@Composable
fun ListItemWithButtons(detailViewModel: FavouriteDetailViewModel, movieId: String) {
    val isSaved by detailViewModel.isSaved.observeAsState(false)

    LaunchedEffect(movieId) {
        detailViewModel.isMovieInFavouriteList(movieId)
    }

    Row {
        IconButton(onClick = {
            detailViewModel.toggleFavouriteStatus(movieId)
            if (isSaved) {
                detailViewModel.removeFromFavouriteList(movieId)
            } else {
                detailViewModel.addToFavouriteList(movieId)
            }
        }) {
            Icon(
                painter = rememberImagePainter(data = R.drawable.baseline_bookmark_added_24),
                contentDescription = "",
                tint = if (isSaved) Color.Yellow else Color.White,
                modifier = Modifier.size(32.dp)
            )
        }

        IconButton(onClick = {
        }) {
            Icon(
                painter = rememberImagePainter(data = R.drawable.baseline_ios_share_24),
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
        }


        IconButton(onClick = {
        }) {
            Icon(
                painter = rememberImagePainter(data = R.drawable.baseline_download_24),
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}



@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MovieDetailScreen(detailViewModel: FavouriteDetailViewModel) {
    val movieDetailState by detailViewModel.favouriteMovieDetail.observeAsState()
    var currentEpisode by remember { mutableStateOf(0) }

    movieDetailState?.let { detail ->
        val episodeCount = detail.serverData?.size

        FlowRow {
            for (i in 1..episodeCount!!) {
                val buttonColors = if (i - 1 == currentEpisode) {
                    ButtonDefaults.buttonColors(containerColor = Color.Red, contentColor = Color.White)
                } else {
                    ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black)
                }
                Button(
                    onClick = {
                        detailViewModel.setVideoLink(detail.serverData[i - 1])
                        currentEpisode = i - 1
                    },
                    colors = buttonColors,
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(1.dp))
                ) {
                    Text(text = "Tập ${i}")
                }
            }
        }
    }
}

