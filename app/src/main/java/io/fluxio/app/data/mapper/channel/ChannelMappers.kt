package io.fluxio.app.data.mapper.channel

import io.fluxio.app.data.mapper.common.bestThumbnail
import io.fluxio.app.domain.model.channel.Channel
import org.schabi.newpipe.extractor.channel.ChannelInfo
import org.schabi.newpipe.extractor.channel.ChannelInfoItem

fun ChannelInfo.toDomainChannel(): Channel {
    return Channel(
        id = id,
        url = url,
        name = name,
        avatar = avatars.bestThumbnail(),
        banner = banners.bestThumbnail(),
        subscriberCount = subscriberCount.toNullableCount(),
        description = description?.takeIf { it.isNotBlank() },
        isVerified = isVerified
    )
}

fun ChannelInfoItem.toDomainChannel(): Channel {
    return Channel(
        id = url,
        url = url,
        name = name,
        avatar = thumbnails.bestThumbnail(),
        banner = null,
        subscriberCount = subscriberCount.toNullableCount(),
        description = description?.takeIf { it.isNotBlank() },
        isVerified = isVerified
    )
}

private fun Long.toNullableCount(): Long? {
    return if (this >= 0) this else null
}
