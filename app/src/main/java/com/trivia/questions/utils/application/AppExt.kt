package com.trivia.questions.utils.application


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import  com.trivia.questions.views.adapters.BaseAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.trivia.questions.R
import com.trivia.questions.views.dialog.AlertMessageDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*



fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}
inline fun RecyclerView.setAnyAdapter(
    adapter: BaseAdapter,
    isGridLayoutManager: Boolean = false,
    count: Int = 0
) {
    if (isGridLayoutManager) {
        this.layoutManager = GridLayoutManager(context, count)
    } else {
        this.layoutManager = LinearLayoutManager(context)
    }
    this.adapter = adapter
}





fun ImageView.loadImage(resourceID: Int) {
    Glide.with(context)
        .load(resourceID)
        .into(this)
}






fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}



fun EditText.string(): String {
    return this.text.toString().trim()
}








fun Activity.openActivity(clazz: Class<out Activity>) {
    startActivity(Intent(this, clazz))
}

fun Activity.openActivityWithExist(clazz: Class<out Activity>) {
    startActivity(Intent(this, clazz))
    this.finish()
}


fun Activity.openActivityForResult(clazz: Class<out Activity>, requestCode: Int) {
    startActivityForResult(Intent(this, clazz), requestCode)
}


fun Fragment.showAlertDialog(msg: String) {
    var newMessage = msg
    if (newMessage.isEmpty()) {
        newMessage = "Unable to process your request \nPlease try again later !!"
    }
    AlertMessageDialog.newInstance(newMessage)
        .show(requireActivity().supportFragmentManager, AlertMessageDialog.TAG)
}

fun FragmentActivity.showAlertDialog(msg: String) {
    var newMessage = msg
    if (newMessage.isEmpty()) {
        newMessage = "Unable to process your request \nPlease try again later !!"
    }
    AlertMessageDialog.newInstance(newMessage).show(supportFragmentManager, AlertMessageDialog.TAG)
}






fun View.showSnackBar(message: String) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    snackbar.show()
}

fun Activity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.showToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}












fun Spinner.observe(onSelectedPosition: (Int) -> Unit) {
    this.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
            parentView: AdapterView<*>,
            selectedItemView: View,
            position: Int,
            id: Long
        ) {

            onSelectedPosition.invoke(position)
        }

        override fun onNothingSelected(parentView: AdapterView<*>) {

        }

    })
}
