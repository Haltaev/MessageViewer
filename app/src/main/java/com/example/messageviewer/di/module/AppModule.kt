package com.example.messageviewer.di.module

import android.content.Context
import com.example.messageviewer.App
import com.example.messageviewer.repository.messages.MessagesRepository
import com.example.messageviewer.repository.messages.MessagesRepositoryImpl
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: App) {
    @Provides
    internal fun provideContext(): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        return Gson()
    }
}

@Module
abstract class ApplicationModuleBinds {

    @Singleton
    @Binds
    abstract fun bindMessagesRepository(repo: MessagesRepositoryImpl): MessagesRepository
}
