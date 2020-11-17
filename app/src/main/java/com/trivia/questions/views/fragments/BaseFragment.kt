package com.trivia.questions.views.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.trivia.questions.R
import com.trivia.questions.utils.application.showAlertDialog
import com.trivia.questions.utils.generalUtils.DialogUtils
import com.trivia.questions.viewModels.BaseAndroidViewModel
import com.trivia.questions.views.activities.MainActivity
import kotlinx.android.synthetic.main.activity_main.*


abstract class BaseFragment(resId: Int) : Fragment(resId) {

    lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialog = DialogUtils.getProgressDialog(requireContext())
    }


    abstract fun initViews()

    abstract fun attachViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        attachViewModel()


    }


    fun showProgressDialog(show: Boolean) {
        if (dialog != null && show) {
            if (!dialog.isShowing)
                dialog.apply {
                    show()
                }
        } else if (dialog != null && !show) {
            if (dialog.isShowing)
                dialog.dismiss()
        }
    }





    fun navigateToFragment(navDirections: NavDirections) {
        val navController =
            Navigation.findNavController(activity as MainActivity, R.id.nav_host_fragment)
        navController.navigate(navDirections)
    }


    protected fun setupGeneralViewModel(generalViewModel: BaseAndroidViewModel) {

        with(generalViewModel)
        {
            snackbarMessage.observe(viewLifecycleOwner, Observer {
                it.getContentIfNotHandled()?.let { showAlertDialog(it) }
            })

            progressBar.observe(viewLifecycleOwner, Observer {
                it.getContentIfNotHandled()?.let { showProgressDialog(it) }
            })
        }
    }


    protected fun onBackPress() {
        requireActivity().onBackPressed()
    }

    protected fun setToolbarTitle(
        title: String,
        isBackHide: Boolean, isHideExitButton:Boolean
    ) {
        (requireActivity() as MainActivity).setToolbarTitle(title, isBackHide, isHideExitButton)
    }
    protected fun setToolbarIconClick(){
        (activity as MainActivity).ivBack?.setOnClickListener {
            onBackPress()
        }
    }


}
