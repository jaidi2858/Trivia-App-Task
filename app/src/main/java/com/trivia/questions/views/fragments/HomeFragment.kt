package com.trivia.questions.views.fragments

import androidx.lifecycle.Observer
import com.trivia.questions.R
import com.trivia.questions.models.dataModels.generalModels.CategoryModel
import com.trivia.questions.utils.application.setAnyAdapter
import com.trivia.questions.utils.application.visible
import com.trivia.questions.utils.generalUtils.AppInstance
import com.trivia.questions.viewModels.CategoriesViewModel
import com.trivia.questions.viewModels.QuestionViewModel
import com.trivia.questions.views.activities.MainActivity
import com.trivia.questions.views.adapters.BaseAdapter
import com.trivia.questions.views.adapters.CategoryListAdapter
import com.trivia.questions.views.dialog.ConfirmationDialog
import com.trivia.questions.views.dialog.SelectQuestionTypeDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_question_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(R.layout.fragment_home), BaseAdapter.OnItemClicker {


    val categoryList: ArrayList<CategoryModel> = ArrayList()
    var selectedCategory: CategoryModel? = null
    val viewModel: CategoriesViewModel by viewModel()


    override fun attachViewModel() {

        with(viewModel)
        {
            setupGeneralViewModel(this)
            allCategories.observe(viewLifecycleOwner, Observer{
                if (it.isNullOrEmpty()) {
                    insertAllCategories(AppInstance.getCategoryList())
                } else {
                    categoryList.clear()
                    categoryList.addAll(it)
                    rvCategory.setAnyAdapter(
                        CategoryListAdapter(categoryList, this@HomeFragment),
                        true,
                        2
                    )
                }
            })

        }

    }

    override fun initViews() {
        setToolbarTitle(getString(R.string.category_list), false, true)
        (activity as  MainActivity).ivExit.setOnClickListener {
            confirmToExit()
        }
    }

    override fun onItemClick(position: Int, data: Any) {
        selectedCategory = categoryList[position]
        SelectQuestionTypeDialog.newInstance().show(childFragmentManager, "")
    }


    fun openQuestionWithSelections(difficultyLevel: String, optionType: String) {
        selectedCategory?.let {
            navigateToFragment(
                HomeFragmentDirections.actionHomeFragmentToQuestionListFragment(
                    optionType,
                    difficultyLevel,
                    it
                )
            )
        }

    }

    fun confirmToExit() {
        ConfirmationDialog(false,getString(R.string.sure_to_exit_application),getString(R.string.alert),
            object : ConfirmationDialog.ConfirmationListener {
                override fun isConfirmed(isConfirmed: Boolean) {
                    if (isConfirmed) {
                        activity?.finish()
                    }
                }
            }).show(childFragmentManager, "")
    }
}