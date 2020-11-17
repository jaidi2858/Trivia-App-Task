package com.trivia.questions.views.fragments

import androidx.lifecycle.Observer
import com.trivia.questions.R
import com.trivia.questions.models.dataModels.generalModels.SolvedQuestion
import com.trivia.questions.models.dataModels.responseModels.questionsListResponseModel.Result
import com.trivia.questions.utils.application.setAnyAdapter
import com.trivia.questions.utils.application.showAlertDialog
import com.trivia.questions.utils.application.visible
import com.trivia.questions.utils.generalUtils.AppConstants
import com.trivia.questions.viewModels.QuestionViewModel
import com.trivia.questions.views.adapters.BaseAdapter
import com.trivia.questions.views.adapters.QuestionsAdapter
import com.trivia.questions.views.dialog.ConfirmationDialog
import kotlinx.android.synthetic.main.fragment_question_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class QuestionListFragment : BaseFragment(R.layout.fragment_question_list),
    BaseAdapter.OnItemClicker {


    val viewModel: QuestionViewModel by viewModel()
    var questionList: ArrayList<Result> = ArrayList()

    val questionAdapter: QuestionsAdapter by lazy {
        QuestionsAdapter(questionList, this)
    }

    val optionType by lazy {
        arguments?.let {
            QuestionListFragmentArgs.fromBundle(it).optionType
        }
    }

    val difficultyLevel by lazy {
        arguments?.let {
            QuestionListFragmentArgs.fromBundle(it).difficultyLevel
        }
    }

    val category by lazy {
        arguments?.let {
            QuestionListFragmentArgs.fromBundle(it).category
        }
    }

    val pointFactor by lazy {
        when (difficultyLevel) {
            AppConstants.DIFFICULTY_LEVEL_EASY -> {
                1
            }
            AppConstants.DIFFICULTY_LEVEL_MEDIUM -> {
                2
            }
            AppConstants.DIFFICULTY_LEVEL_HARD -> {
                3
            }
            else -> {
                1
            }
        }
    }


    override fun initViews() {
        setToolbarTitle(getString(R.string.question_list), true, false)
        setToolbarIconClick()
        btnEndQuiz.setOnClickListener {
            validateAndSubmitQuiz()
        }
    }

    override fun attachViewModel() {
        with(viewModel)
        {
            setupGeneralViewModel(this)
            questionsLiveData.observe(viewLifecycleOwner, Observer{
                it.getContentIfNotHandled()?.let {
                    questionList.clear()
                    questionList.addAll(it)

                    if (!questionList.isNullOrEmpty()) {
                        setAdapter()
                        btnEndQuiz.visible()
                    }
                }
            })
            viewModel.getAllQuestions(
                10,
                category?.category_id.toString(),
                difficultyLevel,
                optionType
            )

        }
    }


    private fun setAdapter() {
        rvQuestionList.setAnyAdapter(questionAdapter)
    }


    private fun validateAndSubmitQuiz() {
        val allQuestions = questionAdapter.items
        var isAllSubmitted = true
        for (i in 0 until allQuestions.size) {
            if (allQuestions[i].selectedAnswer.isNullOrEmpty()) {
                showAlertDialog("Please answer question # " + (i + 1))
                isAllSubmitted = false
                break
            }
        }
        if (isAllSubmitted) {
            showResults()
        }

    }


    private fun submitQuiz(solvedQuestion: List<SolvedQuestion>) {
        viewModel.updateCategory(category!!)
        viewModel.insertAllQuestions(solvedQuestion)
    }


    private fun showResults() {
        val allQuestions = questionAdapter.items
        val correctAnswers =
            allQuestions.filter { it.selectedAnswer.equals(it.correct_answer) }.count()
        val pointsEarned = correctAnswers.times(pointFactor)
        val report = " Total questions : " + allQuestions.size + " \n Correct answers : " + correctAnswers +
                    " \n Incorrect answers : " + (allQuestions.size - correctAnswers) + "\n\n Points Earned : " + pointsEarned.toString()

        val allSolvedQuestions = allQuestions.map { SolvedQuestion(0,category?.category_id.toString(),it.correct_answer,it.difficulty,it.question,it.type,it.selectedAnswer.toString()) }
        showResultsDialog(report, pointsEarned, allSolvedQuestions)

    }

    private fun showResultsDialog(
        report: String,
        pointsEarned: Int,
        allSolvedQuestions: List<SolvedQuestion>
    ) {
        ConfirmationDialog(true,report,getString(R.string.Result),
            object : ConfirmationDialog.ConfirmationListener {
                override fun isConfirmed(isConfirmed: Boolean) {
                    if (isConfirmed) {
                        category?.earned_points = category!!.earned_points.plus(pointsEarned)
                        submitQuiz(allSolvedQuestions)
                        onBackPress()
                    }
                }
            }).show(childFragmentManager, "")
    }

    override fun onItemClick(position: Int, data: Any) {

    }
}