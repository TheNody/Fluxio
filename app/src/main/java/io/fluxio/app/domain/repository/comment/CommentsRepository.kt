package io.fluxio.app.domain.repository.comment

import io.fluxio.app.domain.model.comment.Comment
import io.fluxio.app.domain.model.common.PagedResult

interface CommentsRepository {
    suspend fun getComments(url: String): PagedResult<Comment>
}
