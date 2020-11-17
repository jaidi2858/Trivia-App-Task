package com.trivia.questions.views.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class ConfirmationDialog(isHideNoButton:Boolean,message: String,title_message:String, confirmListener: ConfirmationListener) :
    DialogFragment() {

    var message: String = ""
    var titleMessage: String = ""
    var isHideNoButton: Boolean = false
    var btnText: String = "Ok"
    var negativeButtonText = "No"
    var positiveButtonText = "Yes"
    var confirmListener: ConfirmationListener? = null


    init {
        this.message = message
        this.titleMessage = title_message
        this.confirmListener = confirmListener
        this.isHideNoButton = isHideNoButton
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)

            if(isHideNoButton)
            {
                positiveButtonText = "Ok"
            }
            else{
                positiveButtonText = "Yes"
            }
            builder
                .setTitle(titleMessage)
                .setMessage(message)
                .setPositiveButton(positiveButtonText) { dialog, id ->
                    dialog.dismiss()
                    confirmListener!!.isConfirmed(true)
                }
                .setCancelable(false)
            if(!isHideNoButton){
                builder.setNegativeButton(negativeButtonText) { dialog, id ->
                    dialog.dismiss()
                    confirmListener!!.isConfirmed(false)
                }
            }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }


    interface ConfirmationListener {
        fun isConfirmed(isConfirmed: Boolean)

    }
}