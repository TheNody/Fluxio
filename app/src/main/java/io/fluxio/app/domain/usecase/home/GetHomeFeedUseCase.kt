package io.fluxio.app.domain.usecase.home

import io.fluxio.app.domain.model.common.PagedResult
import io.fluxio.app.domain.model.video.Video
import io.fluxio.app.domain.repository.kiosk.KioskRepository
import io.fluxio.app.domain.repository.video.VideoRepository
import javax.inject.Inject

class GetHomeFeedUseCase @Inject constructor(
    private val kioskRepository: KioskRepository,
    private val videoRepository: VideoRepository
) {
    suspend operator fun invoke(): PagedResult<Video> {
        val kioskId = kioskRepository.getResolvedKioskId()
        return videoRepository.getKioskVideos(kioskId)
    }
}
