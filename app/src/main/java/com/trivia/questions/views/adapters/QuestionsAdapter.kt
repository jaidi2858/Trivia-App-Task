package com.trivia.questions.views.adapters

import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import com.trivia.questions.R
import com.trivia.questions.models.dataModels.responseModels.questionsListResponseModel.Result
import kotlinx.android.synthetic.main.rv_questions.view.*

class QuestionsAdapter(
    var items: ArrayList<Result>,
    var onClicker: OnItemClicker
) :
    BaseAdapter(items, onClicker, R.layout.rv_questions) {

    override fun View.bind(item: Any, position: Int) {
        var data = item as Result
        this.tvNumber.text = "" + (position + 1) + ")"
        this.tvQuestions.text = data.question
        for (i in 0 until data.getAllQuestions().size) {
            val button = RadioButton(context)
            button.id = View.generateViewId()
            button.text = data.getAllQuestions()[i]
            button.isChecked = data.isChecked(i)
            this.rgQuestionAnswerList.addView(button)
        }
        this.rgQuestionAnswerList.setOnCheckedChangeListener { radioGroup, i ->
            val checkedButton = findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
            items[position].selectedAnswer = checkedButton.text.toString()
        }
    }
}