package com.example.calc

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class AlertDialogCustom : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Внимание!")
                .setMessage("Вы уверены, что хотите выйти?")
                .setCancelable(true)
                .setPositiveButton("Да") { dialog, _ ->
                    it.finishAffinity()
                }
                .setNegativeButton("Нет") { dialog, _ ->
                    dialog.cancel()
                }
                .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}