package io.fluxio.app.domain.usecase.kiosk

import io.fluxio.app.domain.model.kiosk.Kiosk
import io.fluxio.app.domain.repository.kiosk.KioskRepository
import javax.inject.Inject

class GetAvailableKiosksUseCase @Inject constructor(
    private val kioskRepository: KioskRepository
) {
    suspend operator fun invoke(): List<Kiosk> {
        return kioskRepository.getAvailableKiosks()
    }
}
