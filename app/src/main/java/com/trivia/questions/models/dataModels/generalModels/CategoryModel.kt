package com.trivia.questions.models.dataModels.generalModels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "category_model")
class CategoryModel(
    @PrimaryKey
    @ColumnInfo(name = "category_id") var category_id: Int,
    @ColumnInfo(name = "category_name") var category_name: String,
    @ColumnInfo(name = "category_image") var category_image: Int,
    @ColumnInfo(name = "earned_points") var earned_points: Int
) : Serializable