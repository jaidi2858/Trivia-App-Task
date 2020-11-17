package com.trivia.questions.utils.generalUtils

import com.trivia.questions.R
import com.trivia.questions.models.dataModels.generalModels.CategoryModel

object AppInstance {
    fun getCategoryList():ArrayList<CategoryModel>{

        var categoryList : ArrayList<CategoryModel> =  ArrayList()
        categoryList.clear()
        categoryList.add(CategoryModel(9,"General Knowledge", R.drawable.ic_gk,0))
        categoryList.add(CategoryModel(10,"Entertainment Books", R.drawable.ic_entertainment_books,0))
        categoryList.add(CategoryModel(23,"History", R.drawable.ic_history,0))
        categoryList.add(CategoryModel(17,"Science & Nature", R.drawable.ic_sceince_nature,0))
        categoryList.add(CategoryModel(24,"Politics", R.drawable.ic_political,0))
        categoryList.add(CategoryModel(21,"Sports", R.drawable.ic_sports,0))
        categoryList.add(CategoryModel(25,"Art", R.drawable.art_icon,0))
        categoryList.add(CategoryModel(12,"Entertainment Music", R.drawable.ic_entertainment_music,0))
        categoryList.add(CategoryModel(11,"Entertainment Film", R.drawable.ic_film,0))
        categoryList.add(CategoryModel(18,"Science Computers", R.drawable.ic_computer_science,0))
        return categoryList
    }
}