package com.example.messageviewer.di.component

import com.example.messageviewer.di.module.*
import com.example.messageviewer.home.HomeFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        VMModule::class,
        ApplicationModuleBinds::class,
    ]
)
interface AppComponent {
    fun inject(fragment: HomeFragment)
}