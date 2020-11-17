package com.trivia.questions.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trivia.questions.utils.generalUtils.OneShotEvent
import com.trivia.questions.models.dataModels.utilityModels.BaseResponse
import com.trivia.questions.utils.networkUtils.ResultWrapper


open class BaseAndroidViewModel() : ViewModel() {


    val snackbarMessage = MutableLiveData<OneShotEvent<String>>()
    val progressBar = MutableLiveData<OneShotEvent<Boolean>>()


    protected fun showSnackbarMessage(message: String) {
        snackbarMessage.value = OneShotEvent(message)
    }

    protected fun showNetworkError() {
        snackbarMessage.value = OneShotEvent("Something went wrong \n Please try again later")
    }

    protected fun handleErrorType(error: ResultWrapper<Any>) {
        when (error) {
            is ResultWrapper.NetworkError ->
                showNetworkError()
            is ResultWrapper.GenericError ->
                showSnackbarMessage("" + error.error?.message)
        }
    }

    protected fun isSuccess(result: BaseResponse): Boolean {
        if (result.response_code == 0) {
            return true
        } else {
            showSnackbarMessage("Error")
            return false
        }
    }


    protected fun showProgressBar(show: Boolean) {
        progressBar.value = OneShotEvent(show)
    }


}