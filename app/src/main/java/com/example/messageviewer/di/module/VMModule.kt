package com.example.messageviewer.di.module

import androidx.lifecycle.ViewModel
import com.example.messageviewer.di.ViewModelKey
import com.example.messageviewer.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class VMModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun homeViewModel(viewModel: HomeViewModel): ViewModel
}
