package com.trivia.questions.models.source.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.trivia.questions.models.dataModels.generalModels.CategoryModel
import com.trivia.questions.models.dataModels.generalModels.SolvedQuestion

import com.trivia.questions.models.dataModels.responseModels.questionsListResponseModel.GetAllQuestionsListResponse
import com.trivia.questions.models.source.room.CategoriesDao
import com.trivia.questions.models.source.room.QuestionAnswersDao
import com.trivia.questions.models.source.room.QuizRoomDatabase
import com.trivia.questions.models.source.serverConnection.RetrofitClientInstance
import com.trivia.questions.utils.networkUtils.ResultWrapper
import com.trivia.questions.utils.networkUtils.safeApiCall


class DataRepository(ctx: Context, retroInstance: RetrofitClientInstance) :
    BaseRepository(ctx, retroInstance) {


    private val categoriesDao: CategoriesDao = QuizRoomDatabase.getDatabase(ctx).categoriesDao()
    private val questionAnswersDao: QuestionAnswersDao = QuizRoomDatabase.getDatabase(ctx).questionAnswersDao()

     suspend fun getAllQuestions(

          amount: Int,
          category: String?,
          difficulty: String?,
          type: String?
      ): ResultWrapper<GetAllQuestionsListResponse> {
          return safeApiCall(dispatcher) {
              getApiService().getAllQuestions(amount,category,difficulty,type)
          }
      }




    // ============================ ROOM DATABASE IMPLEMENTATION ====================


    val allCategories: LiveData<List<CategoryModel>> = categoriesDao.getAllCategories()


    suspend fun insertCategories(categories: List<CategoryModel>) {
        categoriesDao.insert(categories)
    }

    suspend fun updateCategory(category: CategoryModel) {
        categoriesDao.update(category)
    }

    suspend fun insertAllSolvedQuestion(solvedQuestions: List<SolvedQuestion>) {
        questionAnswersDao.insert(solvedQuestions)
    }





}