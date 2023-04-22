package com.example.calc

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class AlertCalcDialogCustom : DialogFragment(){
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Неверный формат данных!")
                .setMessage("Необходимо: число1 знак число2")
                .setCancelable(true)
                .setPositiveButton("ОК") {
                        dialog,_ ->  dialog.cancel()
                }
                .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}