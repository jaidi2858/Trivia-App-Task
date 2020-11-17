package com.trivia.questions.viewModels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.trivia.questions.models.dataModels.generalModels.CategoryModel

import com.trivia.questions.models.dataModels.responseModels.questionsListResponseModel.GetAllQuestionsListResponse
import com.trivia.questions.models.dataModels.responseModels.questionsListResponseModel.Result
import com.trivia.questions.utils.generalUtils.OneShotEvent
import com.trivia.questions.models.source.repository.DataRepository
import com.trivia.questions.utils.networkUtils.ResultWrapper
import com.trivia.questions.views.adapters.BaseAdapter
import kotlinx.coroutines.launch

class CategoriesViewModel(var dataRepository: DataRepository) : BaseAndroidViewModel() {

    var allCategories: LiveData<ArrayList<CategoryModel>>


    init {
        allCategories = dataRepository.allCategories as LiveData<ArrayList<CategoryModel>>
    }


    fun insertAllCategories(categories: ArrayList<CategoryModel>) =
        viewModelScope.launch { dataRepository.insertCategories(categories) }


}