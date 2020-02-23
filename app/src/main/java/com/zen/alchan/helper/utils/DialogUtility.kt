package com.zen.alchan.helper.utils

import android.content.Context
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object DialogUtility {

    fun showToast(context: Context?, message: String?, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, message, length).show()
    }

    fun showToast(context: Context?, message: Int, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, context?.getString(message), length).show()
    }

    fun showOptionDialog(
        context: Context?,
        title: Int,
        message: Int,
        positiveButton: Int,
        positiveAction: () -> Unit,
        negativeButton: Int,
        negativeAction: () -> Unit
    ) {
        MaterialAlertDialogBuilder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveButton) { _, _ -> positiveAction() }
            .setNegativeButton(negativeButton) { _, _ -> negativeAction() }
            .show()
    }

    fun showInfoDialog(context: Context?, message: Int) {
        MaterialAlertDialogBuilder(context)
            .setMessage(message)
            .show()
    }
}