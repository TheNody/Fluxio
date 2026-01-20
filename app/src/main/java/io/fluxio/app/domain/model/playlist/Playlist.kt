package io.fluxio.app.domain.model.playlist

import io.fluxio.app.domain.model.common.Thumbnail

data class Playlist(
    val id: String,
    val url: String,
    val title: String,
    val thumbnails: List<Thumbnail>,
    val uploaderName: String?,
    val uploaderUrl: String?,
    val streamCount: Long?
)
