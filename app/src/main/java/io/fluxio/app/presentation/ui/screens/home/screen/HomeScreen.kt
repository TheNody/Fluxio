package io.fluxio.app.presentation.ui.screens.home.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.fluxio.app.domain.model.common.Thumbnail
import io.fluxio.app.presentation.ui.components.VideoCard
import io.fluxio.app.presentation.ui.screens.home.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    when {
        state.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        state.errorMessage != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = state.errorMessage ?: "Error",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        else -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(state.videos) { video ->
                    VideoCard(
                        title = video.title,
                        channelName = video.channelName ?: "Unknown channel",
                        viewsText = formatViews(video.viewCount),
                        publishedText = video.publishedText ?: "",
                        thumbnailModel = video.thumbnails.bestUrl(),
                        avatarModel = video.channelAvatar?.url ?: "",
                        overflowIconRes = android.R.drawable.ic_menu_more,
                        onOverflowClick = {},
                        onClick = {},
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
            }
        }
    }
}

private fun List<Thumbnail>.bestUrl(): String {
    return maxWithOrNull(compareBy({ it.height ?: -1 }, { it.width ?: -1 }))?.url.orEmpty()
}

private fun formatViews(viewCount: Long?): String {
    if (viewCount == null) {
        return "No views"
    }
    return when {
        viewCount >= 1_000_000_000 -> "${viewCount / 1_000_000_000}B views"
        viewCount >= 1_000_000 -> "${viewCount / 1_000_000}M views"
        viewCount >= 1_000 -> "${viewCount / 1_000}K views"
        else -> "$viewCount views"
    }
}
