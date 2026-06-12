package com.example.droidssh.di

import com.example.droidssh.service.ssh.SshManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SshModule {

    @Provides
    @Singleton
    fun provideSshManager(): SshManager {
        return SshManager()
    }
}
