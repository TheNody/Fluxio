package io.fluxio.app.presentation.ui.screens.home.state

import io.fluxio.app.domain.model.video.Video

data class HomeUiState(
    val isLoading: Boolean = false,
    val videos: List<Video> = emptyList(),
    val errorMessage: String? = null
)
