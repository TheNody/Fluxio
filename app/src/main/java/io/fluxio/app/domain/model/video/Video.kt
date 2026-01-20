package io.fluxio.app.domain.model.video

import io.fluxio.app.domain.model.common.Thumbnail

data class Video(
    val id: String,
    val url: String,
    val title: String,
    val channelName: String?,
    val channelUrl: String?,
    val channelAvatar: Thumbnail?,
    val thumbnails: List<Thumbnail>,
    val viewCount: Long?,
    val durationSec: Long?,
    val publishedText: String?,
    val isShort: Boolean
)
