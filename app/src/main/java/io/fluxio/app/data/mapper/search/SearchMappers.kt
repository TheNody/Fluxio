package io.fluxio.app.data.mapper.search

import io.fluxio.app.data.mapper.channel.toDomainChannel
import io.fluxio.app.data.mapper.common.toThumbnails
import io.fluxio.app.data.mapper.video.toDomainVideo
import io.fluxio.app.domain.model.playlist.Playlist
import io.fluxio.app.domain.model.search.SearchChannel
import io.fluxio.app.domain.model.search.SearchItem
import io.fluxio.app.domain.model.search.SearchPlaylist
import io.fluxio.app.domain.model.search.SearchVideo
import org.schabi.newpipe.extractor.InfoItem
import org.schabi.newpipe.extractor.channel.ChannelInfoItem
import org.schabi.newpipe.extractor.playlist.PlaylistInfoItem
import org.schabi.newpipe.extractor.stream.StreamInfoItem

fun InfoItem.toDomainSearchItem(): SearchItem? {
    return when (this) {
        is StreamInfoItem -> SearchVideo(toDomainVideo())
        is ChannelInfoItem -> SearchChannel(toDomainChannel())
        is PlaylistInfoItem -> SearchPlaylist(toDomainPlaylist())
        else -> null
    }
}

fun PlaylistInfoItem.toDomainPlaylist(): Playlist {
    return Playlist(
        id = url,
        url = url,
        title = name,
        thumbnails = thumbnails.toThumbnails(),
        uploaderName = uploaderName?.takeIf { it.isNotBlank() },
        uploaderUrl = uploaderUrl?.takeIf { it.isNotBlank() },
        streamCount = streamCount.takeIf { it >= 0 }
    )
}
