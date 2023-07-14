package com.caperezh.baubaptest.di

import com.caperezh.baubaptest.data.model.repository.LoginRepository
import com.caperezh.baubaptest.data.model.repository.LoginRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class LoginModule {

    @Binds
    abstract fun provideLoginRepositoryImp(
        implementation: LoginRepositoryImpl
    ): LoginRepository
}