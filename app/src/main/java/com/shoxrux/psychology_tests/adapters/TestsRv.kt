package com.shoxrux.psychology_tests.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.shoxrux.psychology_tests.R
import com.shoxrux.psychology_tests.databinding.CategoryLayoutBinding
import com.shoxrux.psychology_tests.databinding.TestLayoutBinding
import com.shoxrux.psychology_tests.models.Category_Names
import com.shoxrux.psychology_tests.models.Test_Values


class TestsRv(var list: ArrayList<Test_Values>, var onItemClickListener: OnItemClickListener): RecyclerView.Adapter<TestsRv.Vh>() {

    inner class Vh(var itemUserBinding: TestLayoutBinding) :
        RecyclerView.ViewHolder(itemUserBinding.root) {


        fun onBind(test: Test_Values) {
            itemUserBinding.heading.text = test.sarlavha


            if (test.quantityOptions<=10){
                itemUserBinding.minutes.text = "1 daqiqa"
            } else if (test.quantityOptions>10 && test.quantityOptions<=20){
                itemUserBinding.minutes.text = "2 daqiqa"
            }else if (test.quantityOptions >20 && test.quantityOptions<=30){
                itemUserBinding.minutes.text = "3 daqiqa"
            }else if (test.quantityOptions>30 && test.quantityOptions<=40){
                itemUserBinding.minutes.text = "4 daqiqa"
            }





            if (adapterPosition == 0){
                itemUserBinding.karta.setBackgroundResource(R.drawable.test1)
                itemUserBinding.karta.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                    setMargins(27,0,29,0)

                }


            }else if (adapterPosition == list.lastIndex){
                itemUserBinding.karta.setBackgroundResource(R.drawable.test3)
            }else{
                itemUserBinding.karta.setBackgroundResource(R.drawable.test2)
            }


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