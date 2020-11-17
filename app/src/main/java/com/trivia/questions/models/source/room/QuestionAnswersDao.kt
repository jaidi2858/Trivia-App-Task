package com.trivia.questions.models.source.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.trivia.questions.models.dataModels.generalModels.CategoryModel
import com.trivia.questions.models.dataModels.generalModels.SolvedQuestion

@Dao
interface QuestionAnswersDao : BaseDao<SolvedQuestion> {
    @Query("SELECT * from solved_question")
    fun getAllSolvedQuestions(): LiveData<List<SolvedQuestion>>
}