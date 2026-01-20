package io.fluxio.app.data.mapper.comment

import io.fluxio.app.data.mapper.common.bestThumbnail
import io.fluxio.app.domain.model.comment.Comment
import org.schabi.newpipe.extractor.comments.CommentsInfoItem

fun CommentsInfoItem.toDomainComment(): Comment {
    return Comment(
        id = commentId ?: url,
        authorName = uploaderName?.takeIf { it.isNotBlank() },
        authorUrl = uploaderUrl?.takeIf { it.isNotBlank() },
        authorAvatar = uploaderAvatars.bestThumbnail(),
        text = commentText.content,
        likeCount = likeCount.takeIf { it >= 0 },
        publishedText = textualUploadDate?.takeIf { it.isNotBlank() },
        isPinned = isPinned,
        isAuthorVerified = isUploaderVerified
    )
}
