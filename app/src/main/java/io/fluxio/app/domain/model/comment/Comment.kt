package io.fluxio.app.domain.model.comment

import io.fluxio.app.domain.model.common.Thumbnail

data class Comment(
    val id: String,
    val authorName: String?,
    val authorUrl: String?,
    val authorAvatar: Thumbnail?,
    val text: String,
    val likeCount: Int?,
    val publishedText: String?,
    val isPinned: Boolean,
    val isAuthorVerified: Boolean
)
