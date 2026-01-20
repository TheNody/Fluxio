package io.fluxio.app.data.mapper.common

import io.fluxio.app.domain.model.common.PageToken
import org.schabi.newpipe.extractor.Page

fun Page?.toPageToken(): PageToken? {
    if (this == null || !Page.isValid(this)) {
        return null
    }
    return PageToken(
        url = url,
        id = id,
        ids = ids,
        cookies = cookies,
        body = body
    )
}

fun PageToken.toPage(): Page {
    return Page(url, id, ids, cookies, body)
}
