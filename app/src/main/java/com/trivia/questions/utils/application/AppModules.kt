package com.trivia.questions.utils.application

import com.trivia.questions.models.source.repository.DataRepository
import com.trivia.questions.models.source.serverConnection.RetrofitClientInstance
import com.trivia.questions.viewModels.CategoriesViewModel
import com.trivia.questions.viewModels.QuestionViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModules = module {
   // ​sin
    single { RetrofitClientInstance(get()) }
    single { DataRepository(get(),get()) }
}

val viewModelModules = module {
     viewModel {
         QuestionViewModel(get())
     }
    viewModel {
        CategoriesViewModel(get())
    }
}
