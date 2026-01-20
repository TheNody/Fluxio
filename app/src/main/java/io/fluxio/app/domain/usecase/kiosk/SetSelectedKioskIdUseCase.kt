package io.fluxio.app.domain.usecase.kiosk

import io.fluxio.app.domain.repository.kiosk.KioskRepository
import javax.inject.Inject

class SetSelectedKioskIdUseCase @Inject constructor(
    private val kioskRepository: KioskRepository
) {
    suspend operator fun invoke(kioskId: String) {
        kioskRepository.setSelectedKioskId(kioskId)
    }
}
