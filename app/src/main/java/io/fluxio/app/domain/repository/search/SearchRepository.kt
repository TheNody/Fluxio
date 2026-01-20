package io.fluxio.app.domain.repository.search

import io.fluxio.app.domain.model.common.PagedResult
import io.fluxio.app.domain.model.search.SearchItem

interface SearchRepository {
    suspend fun search(query: String): PagedResult<SearchItem>
}
