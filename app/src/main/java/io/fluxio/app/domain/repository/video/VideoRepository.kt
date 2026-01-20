package io.fluxio.app.domain.repository.video

import io.fluxio.app.domain.model.common.PageToken
import io.fluxio.app.domain.model.common.PagedResult
import io.fluxio.app.domain.model.video.Video
import io.fluxio.app.domain.model.video.VideoDetails

interface VideoRepository {
    suspend fun getKioskVideos(kioskId: String): PagedResult<Video>
    suspend fun getMoreKioskVideos(kioskId: String, pageToken: PageToken): PagedResult<Video>
    suspend fun getStreamInfo(url: String): VideoDetails
}
