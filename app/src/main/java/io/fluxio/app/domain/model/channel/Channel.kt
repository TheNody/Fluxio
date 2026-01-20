package io.fluxio.app.domain.model.channel

import io.fluxio.app.domain.model.common.Thumbnail

data class Channel(
    val id: String,
    val url: String,
    val name: String,
    val avatar: Thumbnail?,
    val banner: Thumbnail?,
    val subscriberCount: Long?,
    val description: String?,
    val isVerified: Boolean
)
