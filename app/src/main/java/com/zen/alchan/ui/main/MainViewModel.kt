package com.zen.alchan.ui.main

import android.view.WindowInsets
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessaging
import com.zen.alchan.data.repository.AppSettingsRepository
import com.zen.alchan.data.repository.AuthRepository
import com.zen.alchan.data.repository.MediaListRepository
import com.zen.alchan.data.repository.UserRepository
import com.zen.alchan.helper.enums.AppColorTheme
import com.zen.alchan.helper.pojo.InitialPadding

class MainViewModel(private val appSettingsRepository: AppSettingsRepository,
                    private val userRepository: UserRepository
) : ViewModel() {

    val appColorThemeLiveData by lazy {
        appSettingsRepository.appColorThemeLiveData
    }

    val listOrAniListSettingsChanged by lazy {
        userRepository.listOrAniListSettingsChanged
    }

    val sessionResponse by lazy {
        userRepository.sessionResponse
    }

    val notificationCount by lazy {
        userRepository.notificationCount
    }

    val appColorTheme: AppColorTheme?
        get() = appSettingsRepository.appSettings.appTheme

    fun checkSession() {
        userRepository.checkSession()
    }

    fun getNotificationCount() {
        userRepository.getNotificationCount()
    }

    fun clearStorage() {
        appSettingsRepository.clearStorage()
    }

    fun sendFirebaseToken(token: String?) {
        if (!token.isNullOrBlank()) {
            userRepository.sendFirebaseToken(token)
        }
    }
}