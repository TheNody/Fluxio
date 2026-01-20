package io.fluxio.app.domain.model.search

import io.fluxio.app.domain.model.channel.Channel
import io.fluxio.app.domain.model.common.Thumbnail
import io.fluxio.app.domain.model.playlist.Playlist
import io.fluxio.app.domain.model.video.Video

sealed interface SearchItem {
    val id: String
    val url: String
    val title: String
    val thumbnails: List<Thumbnail>
}

data class SearchVideo(
    val video: Video
) : SearchItem {
    override val id: String = video.id
    override val url: String = video.url
    override val title: String = video.title
    override val thumbnails: List<Thumbnail> = video.thumbnails
}

data class SearchChannel(
    val channel: Channel
) : SearchItem {
    override val id: String = channel.id
    override val url: String = channel.url
    override val title: String = channel.name
    override val thumbnails: List<Thumbnail> = listOfNotNull(channel.avatar)
}

data class SearchPlaylist(
    val playlist: Playlist
) : SearchItem {
    override val id: String = playlist.id
    override val url: String = playlist.url
    override val title: String = playlist.title
    override val thumbnails: List<Thumbnail> = playlist.thumbnails
}
