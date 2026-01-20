package io.fluxio.app.domain.usecase.kiosk

import io.fluxio.app.domain.repository.kiosk.KioskRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ObserveSelectedKioskIdUseCase @Inject constructor(
    private val kioskRepository: KioskRepository
) {
    operator fun invoke(): Flow<String?> {
        return kioskRepository.selectedKioskId()
    }
}
