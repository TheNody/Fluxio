package io.fluxio.app.data.remote.newpipe

import org.schabi.newpipe.extractor.Page

data class NewPipePage<T>(
    val items: List<T>,
    val nextPage: Page?
)
