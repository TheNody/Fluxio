package io.fluxio.app.core.localization

import java.util.Locale
import javax.inject.Inject
import org.schabi.newpipe.extractor.localization.ContentCountry
import org.schabi.newpipe.extractor.localization.Localization

class SystemLocalizationProvider @Inject constructor() : LocalizationProvider {
    override fun getLocalization(): Localization {
        val locale = Locale.getDefault()
        val language = locale.language
        val country = locale.country

        return when {
            language.isNullOrEmpty() -> Localization.DEFAULT
            country.isNullOrEmpty() -> Localization(language)
            else -> Localization(language, country)
        }
    }

    override fun getContentCountry(): ContentCountry {
        val country = Locale.getDefault().country
        return if (country.isNullOrEmpty()) {
            ContentCountry.DEFAULT
        } else {
            ContentCountry(country)
        }
    }
}
