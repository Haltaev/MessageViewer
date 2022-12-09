package com.example.messageviewer.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.messageviewer.common.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}