package io.fluxio.app.data.mapper.video

import io.fluxio.app.data.mapper.common.bestThumbnail
import io.fluxio.app.data.mapper.common.toThumbnails
import io.fluxio.app.domain.model.video.Video
import io.fluxio.app.domain.model.video.VideoDetails
import org.schabi.newpipe.extractor.stream.StreamInfo
import org.schabi.newpipe.extractor.stream.StreamInfoItem

fun StreamInfoItem.toDomainVideo(): Video {
    return Video(
        id = url,
        url = url,
        title = name,
        channelName = uploaderName?.takeIf { it.isNotBlank() },
        channelUrl = uploaderUrl?.takeIf { it.isNotBlank() },
        channelAvatar = uploaderAvatars.bestThumbnail(),
        thumbnails = thumbnails.toThumbnails(),
        viewCount = viewCount.toNullableCount(),
        durationSec = duration.toNullableCount(),
        publishedText = textualUploadDate?.takeIf { it.isNotBlank() },
        isShort = isShortFormContent
    )
}

fun StreamInfo.toDomainVideoDetails(): VideoDetails {
    val descriptionText = description?.content?.takeIf { it.isNotBlank() }
    return VideoDetails(
        id = id,
        url = url,
        title = name,
        description = descriptionText,
        channelName = uploaderName.takeIf { it.isNotBlank() },
        channelUrl = uploaderUrl.takeIf { it.isNotBlank() },
        channelAvatar = uploaderAvatars.bestThumbnail(),
        viewCount = viewCount.toNullableCount(),
        likeCount = likeCount.toNullableCount(),
        durationSec = duration.toNullableCount(),
        publishedText = textualUploadDate?.takeIf { it.isNotBlank() },
        thumbnails = thumbnails.toThumbnails(),
        tags = tags,
        isShort = isShortFormContent
    )
}

private fun Long.toNullableCount(): Long? {
    return if (this >= 0) this else null
}
