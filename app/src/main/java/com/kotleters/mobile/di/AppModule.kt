package com.kotleters.mobile.di

import android.content.Context
import com.kotleters.mobile.common.photo.data.PhotoRepositoryImpl
import com.kotleters.mobile.common.photo.domain.PhotoRepository
import com.kotleters.mobile.feature.auth.data.UserAuthRepositoryImpl
import com.kotleters.mobile.feature.auth.domain.UserAuthRepository
import com.kotleters.mobile.feature.client.data.ClientGenerateQRRepositoryImpl
import com.kotleters.mobile.feature.client.data.ClientRepositoryImpl
import com.kotleters.mobile.feature.client.domain.ClientGenerateQRRepository
import com.kotleters.mobile.feature.client.domain.ClientRepository
import com.kotleters.mobile.feature.company.data.CompanyRepositoryImpl
import com.kotleters.mobile.feature.company.domain.CompanyRepository
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

    @Provides
    @Singleton
    fun provideClientRepository(
        @ApplicationContext context: Context
    ): ClientRepository = ClientRepositoryImpl(
        context = context,
        userAuthRepository = UserAuthRepositoryImpl(context)
    )

    @Provides
    @Singleton
    fun provideCompanyRepository(
        @ApplicationContext context: Context
    ): CompanyRepository =
        CompanyRepositoryImpl(
            context = context,
            userAuthRepository = UserAuthRepositoryImpl(context)
        )

    @Provides
    @Singleton
    fun provideQRCodeRepository(
        @ApplicationContext context: Context
    ): ClientGenerateQRRepository =
        ClientGenerateQRRepositoryImpl(
            context = context,
            userAuthRepository = UserAuthRepositoryImpl(context)
        )

    @Provides
    @Singleton
    fun providePhotoRepository(
        @ApplicationContext context: Context
    ): PhotoRepository =
        PhotoRepositoryImpl(
            context = context,
            userAuthRepository = UserAuthRepositoryImpl(context)
        )
}