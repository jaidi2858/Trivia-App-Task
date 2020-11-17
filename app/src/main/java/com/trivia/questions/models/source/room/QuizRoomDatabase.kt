package com.trivia.questions.models.source.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.trivia.questions.models.dataModels.generalModels.CategoryModel
import com.trivia.questions.models.dataModels.generalModels.SolvedQuestion


@Database(
    entities = arrayOf(CategoryModel::class, SolvedQuestion::class),
    version = 1,
    exportSchema = false
)
abstract class QuizRoomDatabase : RoomDatabase() {
    abstract fun categoriesDao(): CategoriesDao
    abstract fun questionAnswersDao(): QuestionAnswersDao

    companion object {
        @Volatile
        private var INSTANCE: QuizRoomDatabase? = null

        fun getDatabase(context: Context): QuizRoomDatabase {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuizRoomDatabase::class.java,
                    "quiz_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}