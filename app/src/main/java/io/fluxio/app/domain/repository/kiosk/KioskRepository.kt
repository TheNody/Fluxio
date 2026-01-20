package io.fluxio.app.domain.repository.kiosk

import io.fluxio.app.domain.model.kiosk.Kiosk
import kotlinx.coroutines.flow.Flow

interface KioskRepository {
    fun selectedKioskId(): Flow<String?>
    suspend fun setSelectedKioskId(kioskId: String)
    suspend fun getAvailableKiosks(): List<Kiosk>
    suspend fun getResolvedKioskId(): String
}
