package io.fluxio.app.data.repository.comment

import io.fluxio.app.data.mapper.comment.toDomainComment
import io.fluxio.app.data.mapper.common.toPageToken
import io.fluxio.app.data.remote.newpipe.NewPipeRemoteDataSource
import io.fluxio.app.domain.model.comment.Comment
import io.fluxio.app.domain.model.common.PagedResult
import io.fluxio.app.domain.repository.comment.CommentsRepository
import javax.inject.Inject

class CommentsRepositoryImpl @Inject constructor(
    private val remoteDataSource: NewPipeRemoteDataSource
) : CommentsRepository {
    override suspend fun getComments(url: String): PagedResult<Comment> {
        val page = remoteDataSource.getComments(url)
        return PagedResult(
            items = page.items.map { it.toDomainComment() },
            nextPageToken = page.nextPage.toPageToken()
        )
    }
}
