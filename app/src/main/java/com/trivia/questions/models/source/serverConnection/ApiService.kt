package com.trivia.questions.models.source.serverConnection

import com.trivia.questions.models.dataModels.responseModels.questionsListResponseModel.GetAllQuestionsListResponse
import retrofit2.http.*

interface ApiService {


    @GET("api.php")
    suspend fun getAllQuestions(
        @Query("amount") amount: Int?,
        @Query("category") category: String?,
        @Query("difficulty") difficulty: String?,
        @Query("type") type: String?
    ): GetAllQuestionsListResponse
}