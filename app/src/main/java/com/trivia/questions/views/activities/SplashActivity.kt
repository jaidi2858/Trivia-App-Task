package com.trivia.questions.views.activities

import android.os.Handler
import com.trivia.questions.R

class SplashActivity : BaseActivity() {
    companion object {
        val SPLASH_DELAY: Long = 3000
    }

    override fun initViews() {
        Handler().postDelayed({
            gotoMainActivity()
        }, SPLASH_DELAY)
    }

    override fun getLayoutId(): Int = R.layout.activity_splash

}
