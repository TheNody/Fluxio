package io.fluxio.app.domain.repository.channel

import io.fluxio.app.domain.model.channel.Channel

interface ChannelRepository {
    suspend fun getChannel(idOrUrl: String): Channel
}
