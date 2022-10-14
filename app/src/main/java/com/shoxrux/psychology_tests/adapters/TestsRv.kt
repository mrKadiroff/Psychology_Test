package com.shoxrux.psychology_tests.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shoxrux.psychology_tests.databinding.CategoryLayoutBinding
import com.shoxrux.psychology_tests.databinding.TestLayoutBinding
import com.shoxrux.psychology_tests.models.Category_Names
import com.shoxrux.psychology_tests.models.Test_Values


class TestsRv(var list: ArrayList<Test_Values>, var onItemClickListener: OnItemClickListener): RecyclerView.Adapter<TestsRv.Vh>() {

    inner class Vh(var itemUserBinding: TestLayoutBinding) :
        RecyclerView.ViewHolder(itemUserBinding.root) {


        fun onBind(test: Test_Values) {
            itemUserBinding.heading.text = test.sarlavha
            itemUserBinding.daqiqa.text = test.duration
            itemUserBinding.raqami.text = test.quantityOptions
            itemUserBinding.root.setOnClickListener{
                onItemClickListener.onItemClick(test,adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(TestLayoutBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnItemClickListener{
        fun onItemClick(malumotlar: Test_Values,position: Int)
    }

}