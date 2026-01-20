package io.fluxio.app.data.repository.video

import io.fluxio.app.data.mapper.common.toPageToken
import io.fluxio.app.data.mapper.video.toDomainVideo
import io.fluxio.app.data.mapper.video.toDomainVideoDetails
import io.fluxio.app.data.remote.newpipe.NewPipeRemoteDataSource
import io.fluxio.app.domain.model.common.PageToken
import io.fluxio.app.domain.model.common.PagedResult
import io.fluxio.app.domain.model.video.Video
import io.fluxio.app.domain.model.video.VideoDetails
import io.fluxio.app.domain.repository.video.VideoRepository
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
    private val remoteDataSource: NewPipeRemoteDataSource
) : VideoRepository {
    override suspend fun getKioskVideos(kioskId: String): PagedResult<Video> {
        val page = remoteDataSource.getKioskItems(kioskId)
        return PagedResult(
            items = page.items.map { it.toDomainVideo() },
            nextPageToken = page.nextPage.toPageToken()
        )
    }

    override suspend fun getMoreKioskVideos(
        kioskId: String,
        pageToken: PageToken
    ): PagedResult<Video> {
        val page = remoteDataSource.getMoreKioskItems(kioskId, pageToken)
        return PagedResult(
            items = page.items.map { it.toDomainVideo() },
            nextPageToken = page.nextPage.toPageToken()
        )
    }

    override suspend fun getStreamInfo(url: String): VideoDetails {
        return remoteDataSource.getStreamInfo(url).toDomainVideoDetails()
    }
}
