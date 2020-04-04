package com.zen.alchan.helper.utils

import android.content.Context
import android.net.Uri
import android.util.TypedValue
import com.zen.alchan.R
import com.zen.alchan.helper.Constant
import com.zen.alchan.helper.enums.AppColorTheme
import com.zen.alchan.helper.pojo.ColorPalette
import type.MediaType
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import kotlin.math.max


object AndroidUtility {

    fun getResValueFromRefAttr(context: Context?, attrResId: Int): Int {
        val typedValue = TypedValue()
        context?.theme?.resolveAttribute(attrResId, typedValue, true)
        return typedValue.data
    }

    fun getAppColorThemeRes(appColorTheme: AppColorTheme?): Int {
        return when (appColorTheme ?: Constant.DEFAULT_THEME) {
            AppColorTheme.YELLOW -> R.style.AppTheme_ThemeYellow
            AppColorTheme.GREEN -> R.style.AppTheme_ThemeGreen
            AppColorTheme.BLUE -> R.style.AppTheme_ThemeBlue
            AppColorTheme.PINK -> R.style.AppTheme_ThemePink
            AppColorTheme.RED -> R.style.AppTheme_ThemeRed
        }
    }

    fun getColorPalette(appColorTheme: AppColorTheme?): ColorPalette {
        return when (appColorTheme ?: Constant.DEFAULT_THEME) {
            AppColorTheme.YELLOW -> ColorPalette(R.color.yellow, R.color.cyan, R.color.magentaDark)
            AppColorTheme.GREEN -> ColorPalette(R.color.green, R.color.lavender, R.color.brown)
            AppColorTheme.BLUE -> ColorPalette(R.color.blue, R.color.cream, R.color.gold)
            AppColorTheme.PINK -> ColorPalette(R.color.pink, R.color.sunshine, R.color.jade)
            AppColorTheme.RED -> ColorPalette(R.color.red, R.color.aloevera, R.color.purple)
        }
    }

    fun getSmileyFromScore(score: Double?): Int? {
        return when (score) {
            1.0 -> R.drawable.ic_sad
            2.0 -> R.drawable.ic_neutral
            3.0 -> R.drawable.ic_happy
            else -> R.drawable.ic_puzzled
        }
    }

    fun saveUriToFolder(context: Context?, uri: Uri, mediaType: MediaType, action: () -> Unit) {
        var inputStream: InputStream? = null
        var outputStream: FileOutputStream? = null

        try {
            val targetFolder = File(context?.getExternalFilesDir(null)?.path)

            if (!targetFolder.exists()) {
                targetFolder.mkdirs()
            }

            val targetFile = File(
                context?.getExternalFilesDir(null),
                if (mediaType == MediaType.ANIME) Constant.ANIME_LIST_BACKGROUND_FILENAME else Constant.MANGA_LIST_BACKGROUND_FILENAME
            )

            if (targetFile.exists()) {
                targetFile.delete()
                targetFile.createNewFile()
            }

            inputStream = context?.contentResolver?.openInputStream(uri)!!
            outputStream = FileOutputStream(targetFile, false)

            val data = ByteArray(1024)
            var bytesRead: Int
            while (inputStream.read(data).also { bytesRead = it } > 0) {
                outputStream.write(data.copyOfRange(0, max(0, bytesRead)))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            inputStream?.close()
            outputStream?.close()

            action()
        }
    }

    fun getImageFileFromFolder(context: Context?, mediaType: MediaType): File {
        return File(
            context?.getExternalFilesDir(null),
            if (mediaType == MediaType.ANIME) Constant.ANIME_LIST_BACKGROUND_FILENAME else Constant.MANGA_LIST_BACKGROUND_FILENAME
        )
    }
}