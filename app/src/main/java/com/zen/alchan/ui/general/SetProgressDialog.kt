package com.zen.alchan.ui.general

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.google.gson.Gson
import com.zen.alchan.R
import com.zen.alchan.data.response.MediaList
import kotlinx.android.synthetic.main.dialog_set_progress.*
import kotlinx.android.synthetic.main.dialog_set_progress.view.*

class SetProgressDialog : DialogFragment() {

    interface SetProgressListener {
        fun passProgress(newProgress: Int)
    }

    private var listener: SetProgressListener? = null

    companion object {
        const val BUNDLE_CURRENT_PROGRESS = "currentProgress"
        const val BUNDLE_TOTAL_EPISODES = "totalEpisodes"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity!!)
        val dialogView = activity!!.layoutInflater.inflate(R.layout.dialog_set_progress, setProgressDialogLayout, false)

        val currentProgress = arguments?.getInt(BUNDLE_CURRENT_PROGRESS)
        var totalEpisodes = arguments?.getInt(BUNDLE_TOTAL_EPISODES)
        if (totalEpisodes == 0) { totalEpisodes = null }

        dialogView.currentProgressText.text = currentProgress.toString()
        dialogView.episodeCountText.text = "/ ${if (totalEpisodes != null) totalEpisodes.toString() else "?"}"

        builder.setTitle(R.string.set_progress)
        builder.setPositiveButton(R.string.set) { _, _ ->
            if (dialogView.setProgressField.text.isNullOrBlank()) {
                dismiss()
                return@setPositiveButton
            }

            var newProgress = dialogView.setProgressField.text.toString().toInt()

            if (newProgress > UShort.MAX_VALUE.toInt()) {
                newProgress = UShort.MAX_VALUE.toInt()
            }

            if (totalEpisodes != null && newProgress > totalEpisodes) {
                newProgress = totalEpisodes
            }

            listener?.passProgress(newProgress)
        }
        builder.setNegativeButton(R.string.cancel, null)
        builder.setView(dialogView)

        if (listener == null) {
            dismiss()
        }

        return builder.create()
    }

    fun setListener(setProgressListener: SetProgressListener) {
        listener = setProgressListener
    }
}