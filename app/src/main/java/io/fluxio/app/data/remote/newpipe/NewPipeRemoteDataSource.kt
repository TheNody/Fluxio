package io.fluxio.app.data.remote.newpipe

import io.fluxio.app.domain.model.common.PageToken
import org.schabi.newpipe.extractor.InfoItem
import org.schabi.newpipe.extractor.channel.ChannelInfo
import org.schabi.newpipe.extractor.comments.CommentsInfoItem
import org.schabi.newpipe.extractor.stream.StreamInfo
import org.schabi.newpipe.extractor.stream.StreamInfoItem

interface NewPipeRemoteDataSource {
    suspend fun getKioskItems(kioskId: String): NewPipePage<StreamInfoItem>
    suspend fun getMoreKioskItems(kioskId: String, pageToken: PageToken): NewPipePage<StreamInfoItem>
    suspend fun getAvailableKioskIds(): List<String>
    suspend fun getDefaultKioskId(): String?
    suspend fun getStreamInfo(url: String): StreamInfo
    suspend fun search(query: String): NewPipePage<InfoItem>
    suspend fun getChannel(idOrUrl: String): ChannelInfo
    suspend fun getComments(url: String): NewPipePage<CommentsInfoItem>
}
