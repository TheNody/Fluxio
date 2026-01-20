package io.fluxio.app.data.mapper.common

import io.fluxio.app.domain.model.common.Thumbnail
import org.schabi.newpipe.extractor.Image

fun List<Image>.toThumbnails(): List<Thumbnail> {
    return map { image ->
        Thumbnail(
            url = image.url,
            width = image.width.takeIf { it != Image.WIDTH_UNKNOWN },
            height = image.height.takeIf { it != Image.HEIGHT_UNKNOWN }
        )
    }
}

fun List<Image>.bestThumbnail(): Thumbnail? {
    val thumbnails = toThumbnails()
    if (thumbnails.isEmpty()) {
        return null
    }
    return thumbnails.maxWithOrNull(compareBy({ it.height ?: -1 }, { it.width ?: -1 }))
}
