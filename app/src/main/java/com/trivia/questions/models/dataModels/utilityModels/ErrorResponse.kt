package com.trivia.questions.models.dataModels.utilityModels

import java.io.Serializable

class ErrorResponse(var message: String, var code: Int) : Serializable {

}