package io.fluxio.app.data.repository.search

import io.fluxio.app.data.mapper.common.toPageToken
import io.fluxio.app.data.mapper.search.toDomainSearchItem
import io.fluxio.app.data.remote.newpipe.NewPipeRemoteDataSource
import io.fluxio.app.domain.model.common.PagedResult
import io.fluxio.app.domain.model.search.SearchItem
import io.fluxio.app.domain.repository.search.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val remoteDataSource: NewPipeRemoteDataSource
) : SearchRepository {
    override suspend fun search(query: String): PagedResult<SearchItem> {
        val page = remoteDataSource.search(query)
        val items = page.items.mapNotNull { it.toDomainSearchItem() }
        return PagedResult(
            items = items,
            nextPageToken = page.nextPage.toPageToken()
        )
    }
}
