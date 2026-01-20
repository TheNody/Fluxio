package io.fluxio.app.domain.model.video

import io.fluxio.app.domain.model.common.Thumbnail

data class VideoDetails(
    val id: String,
    val url: String,
    val title: String,
    val description: String?,
    val channelName: String?,
    val channelUrl: String?,
    val channelAvatar: Thumbnail?,
    val viewCount: Long?,
    val likeCount: Long?,
    val durationSec: Long?,
    val publishedText: String?,
    val thumbnails: List<Thumbnail>,
    val tags: List<String>,
    val isShort: Boolean
)
