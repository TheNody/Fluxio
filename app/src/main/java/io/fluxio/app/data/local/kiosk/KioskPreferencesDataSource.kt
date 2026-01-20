package io.fluxio.app.data.local.kiosk

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.kioskDataStore by preferencesDataStore(name = "kiosk_preferences")

@Singleton
class KioskPreferencesDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val dataStore = context.kioskDataStore

    val selectedKioskId: Flow<String?> = dataStore.data.map { preferences ->
        preferences[SELECTED_KIOSK_ID]
    }

    suspend fun setSelectedKioskId(kioskId: String) {
        dataStore.edit { preferences ->
            preferences[SELECTED_KIOSK_ID] = kioskId
        }
    }

    private companion object {
        val SELECTED_KIOSK_ID = stringPreferencesKey("selected_kiosk_id")
    }
}
