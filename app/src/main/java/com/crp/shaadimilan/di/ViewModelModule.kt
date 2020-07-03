package com.crp.shaadimilan.di

import com.crp.shaadimilan.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        UserViewModel(get())
    }
}