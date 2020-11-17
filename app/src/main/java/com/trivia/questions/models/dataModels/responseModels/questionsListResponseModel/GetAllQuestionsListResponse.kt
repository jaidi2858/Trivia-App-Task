package com.trivia.questions.models.dataModels.responseModels.questionsListResponseModel

import com.trivia.questions.models.dataModels.utilityModels.BaseResponse
import java.io.Serializable

data class GetAllQuestionsListResponse(
    val results: ArrayList<Result>
): BaseResponse(), Serializable