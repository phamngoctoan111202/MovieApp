package com.noatnoat.feature_base.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.noatnoat.feature_base.presentation.composable.compose_colors.BasicColors
import com.noatnoat.movieapp.base.R

@Composable
fun ExclusiveMoviesLabel(
    modifier: Modifier = Modifier,
    textSize: TextUnit = 11.sp,
    boxWidth: Dp = 60.dp,
    boxHeight: Dp = 22.dp,
    cornerRadius: Dp = 8.dp
) {
    Box(
        modifier = modifier
            .width(boxWidth)
            .height(boxHeight)
            .background(color = BasicColors.primaryColor, shape = RoundedCornerShape(cornerRadius)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(id = R.string.exclusive), color = BasicColors.textColor, fontSize = textSize)
    }
}



@Composable
fun WatchedMoviesLabel(
    modifier: Modifier = Modifier,
    textSize: TextUnit = 11.sp,
    boxWidth: Dp = 45.dp,
    boxHeight: Dp = 22.dp,
    cornerRadius: Dp = 8.dp
) {
    Box(
        modifier = modifier
            .width(boxWidth)
            .height(boxHeight)
            .background(color = BasicColors.primaryColor, shape = RoundedCornerShape(cornerRadius)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(id = R.string.watched_movies), color = BasicColors.textColor, fontSize = textSize)
    }
}


@Composable
fun RecommendedMoviesLabel(
    modifier: Modifier = Modifier,
    textSize: TextUnit = 11.sp,
    boxWidth: Dp = 45.dp,
    boxHeight: Dp = 22.dp,
    cornerRadius: Dp = 8.dp
) {
    Box(
        modifier = modifier
            .width(boxWidth)
            .height(boxHeight)
            .background(color = BasicColors.primaryColor, shape = RoundedCornerShape(cornerRadius)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(id = R.string.recommended_movies), color = BasicColors.textColor, fontSize = textSize)
    }
}




@Composable
fun Top10MoviesParamLabel(
    modifier: Modifier = Modifier,
    textSize: TextUnit = 11.sp,
    boxWidth: Dp = 23.dp,
    boxHeight: Dp = 34.dp,
    cornerRadius: Dp = 8.dp
) {
    Box(
        modifier = modifier
            .width(boxWidth)
            .height(boxHeight)
            .background(color = BasicColors.primaryColor, shape = RoundedCornerShape(cornerRadius)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.top10_movies),
            color = BasicColors.textColor,
            fontSize = textSize,
            modifier = Modifier.fillMaxSize(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun FavouriteMoviesLabel(
    modifier: Modifier = Modifier,
    textSize: TextUnit = 11.sp,
    boxWidth: Dp = 45.dp,
    boxHeight: Dp = 22.dp,
    cornerRadius: Dp = 8.dp
) {
    Box(
        modifier = modifier
            .width(boxWidth)
            .height(boxHeight)
            .background(color = BasicColors.primaryColor, shape = RoundedCornerShape(cornerRadius)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(id = R.string.favourite_movies), color = BasicColors.textColor, fontSize = textSize)
    }
}