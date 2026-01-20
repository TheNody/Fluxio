package io.fluxio.app.data.remote.newpipe

import javax.inject.Inject
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.toRequestBody
import org.schabi.newpipe.extractor.downloader.Downloader
import org.schabi.newpipe.extractor.downloader.Request
import org.schabi.newpipe.extractor.downloader.Response
import org.schabi.newpipe.extractor.exceptions.ReCaptchaException

class OkHttpDownloader @Inject constructor(
    private val client: OkHttpClient
) : Downloader() {

    override fun execute(request: Request): Response {
        val method = request.httpMethod()
        val requestBody = when (method) {
            "POST", "PUT", "PATCH" -> (request.dataToSend() ?: ByteArray(0)).toRequestBody()
            else -> null
        }

        val requestBuilder = okhttp3.Request.Builder()
            .method(method, requestBody)
            .url(request.url())
            .addHeader("User-Agent", USER_AGENT)

        request.headers().forEach { (headerName, headerValueList) ->
            requestBuilder.removeHeader(headerName)
            headerValueList.forEach { headerValue ->
                requestBuilder.addHeader(headerName, headerValue)
            }
        }

        client.newCall(requestBuilder.build()).execute().use { response ->
            if (response.code == 429) {
                throw ReCaptchaException("reCaptcha Challenge requested", request.url())
            }

            val responseBody = response.body.string()
            return Response(
                response.code,
                response.message,
                response.headers.toMultimap(),
                responseBody,
                response.request.url.toString()
            )
        }
    }

    companion object {
        private const val USER_AGENT =
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:140.0) Gecko/20100101 Firefox/140.0"
    }
}
