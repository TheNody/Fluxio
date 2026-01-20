package io.fluxio.app.data.repository.kiosk

import io.fluxio.app.data.local.kiosk.KioskPreferencesDataSource
import io.fluxio.app.data.mapper.kiosk.toDomainKiosk
import io.fluxio.app.data.remote.newpipe.NewPipeRemoteDataSource
import io.fluxio.app.domain.model.kiosk.Kiosk
import io.fluxio.app.domain.repository.kiosk.KioskRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class KioskRepositoryImpl @Inject constructor(
    private val remoteDataSource: NewPipeRemoteDataSource,
    private val preferencesDataSource: KioskPreferencesDataSource
) : KioskRepository {
    override fun selectedKioskId(): Flow<String?> {
        return preferencesDataSource.selectedKioskId
    }

    override suspend fun setSelectedKioskId(kioskId: String) {
        preferencesDataSource.setSelectedKioskId(kioskId)
    }

    override suspend fun getAvailableKiosks(): List<Kiosk> {
        return remoteDataSource.getAvailableKioskIds()
            .map { it.toDomainKiosk() }
    }

    override suspend fun getResolvedKioskId(): String {
        val available = remoteDataSource.getAvailableKioskIds()
        val selected = preferencesDataSource.selectedKioskId.first()
        if (!selected.isNullOrBlank() && available.contains(selected)) {
            return selected
        }
        val defaultId = remoteDataSource.getDefaultKioskId()
        if (!defaultId.isNullOrBlank() && available.contains(defaultId)) {
            return defaultId
        }
        return available.firstOrNull() ?: error("No kiosks available")
    }
}
