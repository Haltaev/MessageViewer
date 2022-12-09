package com.example.messageviewer

import android.app.Application
import com.example.messageviewer.di.component.AppComponent
import com.example.messageviewer.di.component.DaggerAppComponent
import com.example.messageviewer.di.module.AppModule

class App : Application() {

    companion object {
        private lateinit var component: AppComponent
        private lateinit var app: App

        fun getComponent(): AppComponent {
            return component
        }

        fun buildAppComponent() {
            component = DaggerAppComponent.builder()
                .appModule(AppModule(app))
                .build()
        }
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        buildAppComponent()
    }
}