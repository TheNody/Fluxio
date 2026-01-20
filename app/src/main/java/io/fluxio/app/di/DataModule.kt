package io.fluxio.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.fluxio.app.data.local.kiosk.KioskPreferencesDataSource
import io.fluxio.app.data.remote.newpipe.NewPipeRemoteDataSource
import io.fluxio.app.data.remote.newpipe.NewPipeRemoteDataSourceImpl
import io.fluxio.app.data.repository.channel.ChannelRepositoryImpl
import io.fluxio.app.data.repository.comment.CommentsRepositoryImpl
import io.fluxio.app.data.repository.kiosk.KioskRepositoryImpl
import io.fluxio.app.data.repository.search.SearchRepositoryImpl
import io.fluxio.app.data.repository.video.VideoRepositoryImpl
import io.fluxio.app.domain.repository.channel.ChannelRepository
import io.fluxio.app.domain.repository.comment.CommentsRepository
import io.fluxio.app.domain.repository.kiosk.KioskRepository
import io.fluxio.app.domain.repository.search.SearchRepository
import io.fluxio.app.domain.repository.video.VideoRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideNewPipeRemoteDataSource(): NewPipeRemoteDataSource {
        return NewPipeRemoteDataSourceImpl()
    }

    @Provides
    @Singleton
    fun provideVideoRepository(
        remoteDataSource: NewPipeRemoteDataSource
    ): VideoRepository {
        return VideoRepositoryImpl(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideSearchRepository(
        remoteDataSource: NewPipeRemoteDataSource
    ): SearchRepository {
        return SearchRepositoryImpl(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideChannelRepository(
        remoteDataSource: NewPipeRemoteDataSource
    ): ChannelRepository {
        return ChannelRepositoryImpl(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideCommentsRepository(
        remoteDataSource: NewPipeRemoteDataSource
    ): CommentsRepository {
        return CommentsRepositoryImpl(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideKioskRepository(
        remoteDataSource: NewPipeRemoteDataSource,
        preferencesDataSource: KioskPreferencesDataSource
    ): KioskRepository {
        return KioskRepositoryImpl(remoteDataSource, preferencesDataSource)
    }
}
