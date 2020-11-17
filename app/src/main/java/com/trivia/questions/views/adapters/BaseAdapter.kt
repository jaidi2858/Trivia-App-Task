package com.trivia.questions.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


abstract class BaseAdapter constructor(
    protected var itemList: List<kotlin.Any>,
    var onItemClicker: OnItemClicker,
    private val layoutResId: Int)
    : RecyclerView.Adapter<BaseAdapter.Holder>() {

    override fun getItemCount() = itemList.size

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = itemList[position]
        holder.itemView.bind(item,position)
        holder.itemView.setOnClickListener {
            onItemClicker.onItemClick(position,itemList[position] )
        }
        holder.setIsRecyclable(false)
    }


    protected open fun View.bind(item: Any, position: Int) {
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface OnItemClicker{
        fun onItemClick(position:Int,data:Any)
    }
}