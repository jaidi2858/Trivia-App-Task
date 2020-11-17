package com.trivia.questions.models.source.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.trivia.questions.models.dataModels.generalModels.CategoryModel

@Dao
interface CategoriesDao : BaseDao<CategoryModel> {
    @Query("SELECT * from category_model")
    fun getAllCategories(): LiveData<List<CategoryModel>>

}