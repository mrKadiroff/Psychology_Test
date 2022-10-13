package com.shoxrux.psychology_tests.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shoxrux.psychology_tests.databinding.CategoryLayoutBinding
import com.shoxrux.psychology_tests.models.Category_Names


class CategoryRv(var list: ArrayList<Category_Names>, var onItemClickListener: OnItemClickListener): RecyclerView.Adapter<CategoryRv.Vh>() {

    inner class Vh(var itemUserBinding: CategoryLayoutBinding) :
        RecyclerView.ViewHolder(itemUserBinding.root) {


        fun onBind(category: Category_Names) {
            itemUserBinding.titlee.text = category.title
            itemUserBinding.rasm.setImageResource(category.image)
            itemUserBinding.root.setOnClickListener{
                onItemClickListener.onItemClick(category,adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(CategoryLayoutBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnItemClickListener{
        fun onItemClick(malumotlar: Category_Names,position: Int)
    }

}