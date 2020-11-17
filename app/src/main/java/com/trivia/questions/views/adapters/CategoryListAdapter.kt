package com.trivia.questions.views.adapters

import android.view.View
import com.trivia.questions.R
import com.trivia.questions.models.dataModels.generalModels.CategoryModel
import com.trivia.questions.utils.application.loadImage
import kotlinx.android.synthetic.main.rv_category_list.view.*

class CategoryListAdapter(var items:ArrayList<CategoryModel>,var onClicker: BaseAdapter.OnItemClicker) :
    BaseAdapter(items,onClicker, R.layout.rv_category_list) {

    override fun View.bind(item: Any, position: Int) {
        val model = item as CategoryModel
        this.tvCategoryName.text = model.category_name
        this.ivCategoryImage.loadImage(model.category_image)
        this.tvPoints.text= "Points : ${model.earned_points}"

    }

}