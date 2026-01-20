package io.fluxio.app.core.localization

import org.schabi.newpipe.extractor.localization.ContentCountry
import org.schabi.newpipe.extractor.localization.Localization

interface LocalizationProvider {
    fun getLocalization(): Localization
    fun getContentCountry(): ContentCountry
}
