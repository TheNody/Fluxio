package io.fluxio.app.di

import android.content.Context
import coil.ImageLoader
import coil.request.CachePolicy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.fluxio.app.core.localization.LocalizationProvider
import io.fluxio.app.core.localization.SystemLocalizationProvider
import io.fluxio.app.data.remote.newpipe.OkHttpDownloader
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import org.schabi.newpipe.extractor.downloader.Downloader

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideDownloader(client: OkHttpClient): Downloader {
        return OkHttpDownloader(client)
    }

    @Provides
    @Singleton
    fun provideLocalizationProvider(): LocalizationProvider {
        return SystemLocalizationProvider()
    }

    @Provides
    @Singleton
    fun provideImageLoader(
        @ApplicationContext context: Context,
        client: OkHttpClient
    ): ImageLoader {
        return ImageLoader.Builder(context)
            .okHttpClient(client)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.ENABLED)
            .build()
    }
}
