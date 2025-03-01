package com.kotleters.mobile.di

import android.content.Context
import com.kotleters.mobile.feature.auth.data.UserAuthRepositoryImpl
import com.kotleters.mobile.feature.auth.domain.UserAuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideUserAuthRepository(
        @ApplicationContext context: Context
    ): UserAuthRepository =
        UserAuthRepositoryImpl(context)
}