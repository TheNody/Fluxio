package io.fluxio.app.data.repository.channel

import io.fluxio.app.data.mapper.channel.toDomainChannel
import io.fluxio.app.data.remote.newpipe.NewPipeRemoteDataSource
import io.fluxio.app.domain.model.channel.Channel
import io.fluxio.app.domain.repository.channel.ChannelRepository
import javax.inject.Inject

class ChannelRepositoryImpl @Inject constructor(
    private val remoteDataSource: NewPipeRemoteDataSource
) : ChannelRepository {
    override suspend fun getChannel(idOrUrl: String): Channel {
        return remoteDataSource.getChannel(idOrUrl).toDomainChannel()
    }
}
