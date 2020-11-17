package com.trivia.questions.models.source.repository

import android.content.Context
import com.trivia.questions.models.source.serverConnection.ApiService
import com.trivia.questions.models.source.serverConnection.RetrofitClientInstance
import com.trivia.questions.utils.generalUtils.ErrorUtils

import com.google.gson.Gson
import com.trivia.questions.utils.networkUtils.isOnline
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


open class BaseRepository(ctx: Context,var retroInstance: RetrofitClientInstance) {

    var context: Context
    val dispatcher: CoroutineDispatcher = Dispatchers.IO
    var userId: String = ""

    var gson = Gson()

    init {
        context = ctx

    }


    fun getApiService(): ApiService {
        return retroInstance.getService()
    }




}