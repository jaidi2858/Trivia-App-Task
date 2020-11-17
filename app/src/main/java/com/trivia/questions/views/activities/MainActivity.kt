package com.trivia.questions.views.activities

import com.trivia.questions.R
import com.trivia.questions.utils.application.gone
import com.trivia.questions.utils.application.visible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initViews() {

    }

    fun setToolbarTitle(
        title: String,
        backHide: Boolean = false,
        hideExitButton: Boolean
    ) {
        tvToolbarName.text = title
        if(backHide)
        {
            ivBack.visible()
            ivExit.gone()
        }
        else if(hideExitButton)
        {
            ivExit.visible()
            ivBack.gone()
        }
        else{
            ivBack.gone()
            ivExit.gone()
        }
    }
}
