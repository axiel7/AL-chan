package com.zen.alchan

import android.app.Application
import com.zen.alchan.data.datasource.AuthDataSource
import com.zen.alchan.data.datasource.AuthDataSourceImpl
import com.zen.alchan.data.localstorage.*
import com.zen.alchan.data.network.ApolloHandler
import com.zen.alchan.data.network.HeaderInterceptor
import com.zen.alchan.data.network.HeaderInterceptorImpl
import com.zen.alchan.data.repository.AuthRepository
import com.zen.alchan.data.repository.AuthRepositoryImpl
import com.zen.alchan.data.repository.ProfileRepository
import com.zen.alchan.data.repository.ProfileRepositoryImpl
import com.zen.alchan.helper.Constant
import com.zen.alchan.ui.MainViewModel
import com.zen.alchan.ui.auth.LoginViewModel
import com.zen.alchan.ui.base.BaseViewModel
import com.zen.alchan.ui.auth.SplashViewModel
import com.zen.alchan.ui.profile.ProfileViewModel
import com.zen.alchan.ui.profile.bio.BioViewModel
import com.zen.alchan.ui.profile.settings.app.AppSettingsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class AlchanApplication : Application() {

    private val appModules = module {
        single<LocalStorage> { LocalStorageImpl(this@AlchanApplication.applicationContext, Constant.SHARED_PREFERENCES_NAME) }
        single<AppSettingsManager> { AppSettingsManagerImpl(get()) }
        single<UserManager> { UserManagerImpl(get()) }

        single<HeaderInterceptor> { HeaderInterceptorImpl(get()) }
        single { ApolloHandler(get()) }

        single<AuthDataSource> { AuthDataSourceImpl(get()) }
        single<AuthRepository> { AuthRepositoryImpl(get(), get(), get()) }
        viewModel { BaseViewModel(get()) }
        viewModel { SplashViewModel(get()) }
        viewModel { LoginViewModel(get()) }
        viewModel { MainViewModel(get()) }

        single<ProfileRepository> { ProfileRepositoryImpl(get(), get(), get()) }
        viewModel { ProfileViewModel(get()) }
        viewModel { BioViewModel(get()) }
        viewModel { AppSettingsViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AlchanApplication)
            modules(appModules)
        }
    }
}