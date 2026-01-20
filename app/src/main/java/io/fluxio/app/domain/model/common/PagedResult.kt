package io.fluxio.app.domain.model.common

data class PagedResult<T>(
    val items: List<T>,
    val nextPageToken: PageToken?
)
