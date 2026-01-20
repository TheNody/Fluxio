package io.fluxio.app.data.remote.newpipe

import io.fluxio.app.data.mapper.common.toPage
import io.fluxio.app.domain.model.common.PageToken
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.schabi.newpipe.extractor.InfoItem
import org.schabi.newpipe.extractor.ServiceList
import org.schabi.newpipe.extractor.channel.ChannelInfo
import org.schabi.newpipe.extractor.comments.CommentsInfo
import org.schabi.newpipe.extractor.comments.CommentsInfoItem
import org.schabi.newpipe.extractor.exceptions.ExtractionException
import org.schabi.newpipe.extractor.kiosk.KioskInfo
import org.schabi.newpipe.extractor.kiosk.KioskList
import org.schabi.newpipe.extractor.search.SearchInfo
import org.schabi.newpipe.extractor.stream.StreamInfo
import org.schabi.newpipe.extractor.stream.StreamInfoItem

class NewPipeRemoteDataSourceImpl @Inject constructor() : NewPipeRemoteDataSource {
    private val service = ServiceList.YouTube

    override suspend fun getKioskItems(kioskId: String): NewPipePage<StreamInfoItem> {
        return withContext(Dispatchers.IO) {
            val kioskList = service.getKioskList()
            val resolvedId = resolveKioskId(kioskList, kioskId)
            val url = kioskList.getListLinkHandlerFactoryByType(resolvedId)
                .fromId(resolvedId)
                .url
            val info = KioskInfo.getInfo(service, url)
            NewPipePage(info.relatedItems, info.nextPage)
        }
    }

    override suspend fun getMoreKioskItems(
        kioskId: String,
        pageToken: PageToken
    ): NewPipePage<StreamInfoItem> {
        return withContext(Dispatchers.IO) {
            val kioskList = service.getKioskList()
            val resolvedId = resolveKioskId(kioskList, kioskId)
            val url = kioskList.getListLinkHandlerFactoryByType(resolvedId)
                .fromId(resolvedId)
                .url
            val page = pageToken.toPage()
            val itemsPage = KioskInfo.getMoreItems(service, url, page)
            NewPipePage(itemsPage.items, itemsPage.nextPage)
        }
    }

    override suspend fun getAvailableKioskIds(): List<String> {
        return withContext(Dispatchers.IO) {
            service.getKioskList().availableKiosks.toList()
        }
    }

    override suspend fun getDefaultKioskId(): String? {
        return withContext(Dispatchers.IO) {
            service.getKioskList().defaultKioskId?.takeIf { it.isNotBlank() }
        }
    }

    override suspend fun getStreamInfo(url: String): StreamInfo {
        return withContext(Dispatchers.IO) {
            StreamInfo.getInfo(service, url)
        }
    }

    override suspend fun search(query: String): NewPipePage<InfoItem> {
        return withContext(Dispatchers.IO) {
            val queryHandler = service.searchQHFactory.fromQuery(query)
            val info = SearchInfo.getInfo(service, queryHandler)
            NewPipePage(info.relatedItems, info.nextPage)
        }
    }

    override suspend fun getChannel(idOrUrl: String): ChannelInfo {
        return withContext(Dispatchers.IO) {
            val channelFactory = service.channelLHFactory
                ?: throw ExtractionException("Channel link handler is not available")
            val url = if (channelFactory.acceptUrl(idOrUrl)) {
                idOrUrl
            } else {
                channelFactory.fromId(idOrUrl).url
            }
            ChannelInfo.getInfo(service, url)
        }
    }

    override suspend fun getComments(url: String): NewPipePage<CommentsInfoItem> {
        return withContext(Dispatchers.IO) {
            val info = CommentsInfo.getInfo(service, url)
            if (info == null) {
                NewPipePage(emptyList(), null)
            } else {
                NewPipePage(info.relatedItems, info.nextPage)
            }
        }
    }

    private fun resolveKioskId(kioskList: KioskList, kioskId: String?): String {
        val available = kioskList.availableKiosks
        if (!kioskId.isNullOrBlank() && available.contains(kioskId)) {
            return kioskId
        }
        val defaultId = kioskList.defaultKioskId
        if (!defaultId.isNullOrBlank() && available.contains(defaultId)) {
            return defaultId
        }
        return available.firstOrNull() ?: throw ExtractionException("No kiosks available")
    }
}
