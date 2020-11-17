package com.trivia.questions.viewModels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.trivia.questions.models.dataModels.generalModels.CategoryModel
import com.trivia.questions.models.dataModels.generalModels.SolvedQuestion

import com.trivia.questions.models.dataModels.responseModels.questionsListResponseModel.GetAllQuestionsListResponse
import com.trivia.questions.models.dataModels.responseModels.questionsListResponseModel.Result
import com.trivia.questions.utils.generalUtils.OneShotEvent
import com.trivia.questions.models.source.repository.DataRepository
import com.trivia.questions.utils.networkUtils.ResultWrapper
import com.trivia.questions.views.adapters.BaseAdapter
import kotlinx.coroutines.launch
class QuestionViewModel(var dataRepository: DataRepository) : BaseAndroidViewModel() {

    var questionsLiveData: MutableLiveData<OneShotEvent<ArrayList<Result>>> = MutableLiveData()


   fun getAllQuestions(
       amount: Int,
       category: String?,
       difficulty: String?,
       type: String?) {
        showProgressBar(true)
        viewModelScope.launch {
            dataRepository.getAllQuestions(amount,category,difficulty,type)?.let { response ->
                showProgressBar(false)
                when (response) {
                    is ResultWrapper.Success ->
                        if (isSuccess(response.value)) {
                            questionsLiveData.value = OneShotEvent(response.value.results)
                        }
                    else -> handleErrorType(response)
                }
            }
        }
    }






    fun updateCategory(category: CategoryModel) =
        viewModelScope.launch { dataRepository.updateCategory(category) }

    fun insertAllQuestions(solvedQuestion: List<SolvedQuestion>) =
        viewModelScope.launch { dataRepository.insertAllSolvedQuestion(solvedQuestion) }





}