package com.trivia.questions.views.dialog

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.DialogFragment
import com.trivia.questions.R
import com.trivia.questions.utils.application.showToast


abstract class BaseDialog(): DialogFragment() {


    @SuppressLint("ResourceAsColor")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle);
        val builder = AlertDialog.Builder(requireContext(), R.style.DialogStyle)
        builder.setCancelable(false)
        val inflater = requireActivity().layoutInflater
        val dialog = inflater.inflate(getLayoutId(), null)
        initViews(dialog)
        builder.setView(dialog)
        return builder.create()
    }






    abstract fun initViews(view: View)

    abstract fun getLayoutId():Int

    override fun onStart() {
        super.onStart()
        val window = dialog?.window
        val windowParams = window?.attributes
        windowParams?.dimAmount = 0.8f
        window?.attributes = windowParams
    }




}