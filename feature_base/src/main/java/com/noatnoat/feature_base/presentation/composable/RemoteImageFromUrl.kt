package com.noatnoat.feature_base.presentation.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberImagePainter

@Composable
fun RemoteImageFromUrl(imageUrl: String, modifier: Modifier = Modifier) {
    val painter: Painter = rememberImagePainter(
        data = imageUrl,
        builder = {
        }
    )

    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxWidth()
    )
}
