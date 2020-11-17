package com.trivia.questions.views.dialog

import android.view.View
import com.trivia.questions.R
import com.trivia.questions.utils.application.observe
import com.trivia.questions.utils.application.showToast
import com.trivia.questions.utils.generalUtils.AppConstants
import com.trivia.questions.views.fragments.HomeFragment
import kotlinx.android.synthetic.main.fragment_question_type.view.*

class SelectQuestionTypeDialog : BaseDialog() {


    var difficultyLevel: String? = null
    var optionType: String? = null

    override fun initViews(view: View) {
        view.btnNext.setOnClickListener {
            if (optionType.isNullOrEmpty()) {
                showToast("Please select option type")
            } else if (difficultyLevel.isNullOrEmpty()) {
                showToast("Please select difficulty")
            } else {
                (parentFragment as HomeFragment).openQuestionWithSelections(
                    difficultyLevel.toString(),
                    optionType.toString()
                )
                dismiss()
            }
        }

        view.spDifficulty.observe {
            when (it) {
                0 -> {
                    difficultyLevel = null
                }

                1 -> {
                    difficultyLevel = AppConstants.DIFFICULTY_LEVEL_EASY
                }
                2 -> {
                    difficultyLevel = AppConstants.DIFFICULTY_LEVEL_MEDIUM
                }
                3 -> {
                    difficultyLevel = AppConstants.DIFFICULTY_LEVEL_HARD
                }
            }
        }

        view.spOptionType.observe {
            when (it) {
                0 -> {
                    optionType = null
                }

                1 -> {
                    optionType = AppConstants.OPTION_TYPE_MCQ
                }
                2 -> {
                    optionType = AppConstants.OPTION_TYPE_TF
                }

            }
        }


    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_question_type
    }

    companion object {
        fun newInstance(): SelectQuestionTypeDialog {
            var fragment = SelectQuestionTypeDialog()
            return fragment
        }
    }
}