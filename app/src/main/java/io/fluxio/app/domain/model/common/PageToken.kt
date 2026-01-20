package io.fluxio.app.domain.model.common

data class PageToken(
    val url: String? = null,
    val id: String? = null,
    val ids: List<String>? = null,
    val cookies: Map<String, String>? = null,
    val body: ByteArray? = null
)
