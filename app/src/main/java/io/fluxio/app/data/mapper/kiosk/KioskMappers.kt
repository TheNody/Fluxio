package io.fluxio.app.data.mapper.kiosk

import io.fluxio.app.domain.model.kiosk.Kiosk

fun String.toDomainKiosk(): Kiosk {
    return Kiosk(
        id = this,
        title = toDisplayTitle()
    )
}

private fun String.toDisplayTitle(): String {
    return split('_', ' ')
        .filter { it.isNotBlank() }
        .joinToString(" ") { word ->
            word.lowercase().replaceFirstChar { it.titlecase() }
        }
}
