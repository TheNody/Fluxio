package io.fluxio.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.fluxio.app.core.localization.LocalizationProvider
import javax.inject.Inject
import org.schabi.newpipe.extractor.NewPipe
import org.schabi.newpipe.extractor.downloader.Downloader

@HiltAndroidApp
class FluxioApp : Application() {
    @Inject
    lateinit var downloader: Downloader

    @Inject
    lateinit var localizationProvider: LocalizationProvider

    override fun onCreate() {
        super.onCreate()
        val localization = localizationProvider.getLocalization()
        val contentCountry = localizationProvider.getContentCountry()
        NewPipe.init(downloader, localization, contentCountry)
    }
}
