package com.shoxrux.psychology_tests.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.marginRight
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.shoxrux.psychology_tests.R
import com.shoxrux.psychology_tests.databinding.CategoryLayoutBinding
import com.shoxrux.psychology_tests.models.Category_Names
import com.shoxrux.psychology_tests.room.CategoryEntity


class CategoryRv(var list: List<CategoryEntity>, var onItemClickListener: OnItemClickListener): RecyclerView.Adapter<CategoryRv.Vh>() {

    inner class Vh(var itemUserBinding: CategoryLayoutBinding) :
        RecyclerView.ViewHolder(itemUserBinding.root) {


        fun onBind(category: CategoryEntity) {

            if (adapterPosition == 0){
                itemUserBinding.backgrounddd.setBackgroundResource(R.drawable.turkum1)
            }else if (adapterPosition == 1){
                itemUserBinding.backgrounddd.setBackgroundResource(R.drawable.turkum2)
                var rasmer = 10.249
                itemUserBinding.backgrounddd.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                    setMargins(14,0,10,0)

                }



            }



            itemUserBinding.titlee.text = category.category_name
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
        fun onItemClick(malumotlar: CategoryEntity,position: Int)
    }

}