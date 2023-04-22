package com.example.calc

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class AlertDialogCustom : DialogFragment(){
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Внимание!")
                .setMessage("Данное разрешение не поддерживается!")
                .setCancelable(true)
                .setPositiveButton("ОК") {
                        dialog,_ -> it.finishAffinity()
                }
                .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}