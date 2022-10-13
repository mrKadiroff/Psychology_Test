package com.shoxrux.psychology_tests.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.shoxrux.psychology_tests.R
import com.shoxrux.psychology_tests.databinding.FragmentQuestionsBinding
import com.shoxrux.psychology_tests.databinding.FragmentTestBinding
import com.shoxrux.psychology_tests.models.Category_Names
import com.shoxrux.psychology_tests.models.Test_Values
import java.util.*
import java.util.concurrent.TimeUnit

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuestionsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuestionsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding: FragmentQuestionsBinding
    private var score = 0
    private var correct = 0
    private var wrong = 0
    private var skip = 0
    private var qIndex = 0
    private var updateQueNo = 1
    // create string for question, answer and options
    private var questions = arrayOf(
        "Q.1. If a computer has more than one processor then it is known as?",
        "Q.2. Full form of URL is?",
        "Q.3. One kilobyte (KB) is equal to")
    private var answer = arrayOf(
        "Multiprocessor",
        "Uniform Resource Locator",
        "1,024 bytes")
    private var options = arrayOf(
        "Uniprocess",
        "Multiprocessor",
        "Multithreaded",
        "Multiprogramming",
        "Uniform Resource Locator",
        "Uniform Resource Linkwrong",
        "Uniform Registered Link",
        "Unified Resource Link",
        "1,000 bits",
        "1,024 bytes",
        "1,024 megabytes",
        "1,024 gigabytes")


    private val TAG = "QuestionsFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuestionsBinding.inflate(layoutInflater,container,false)

        val value = requireArguments().get("test") as Test_Values
        val positioni = requireArguments().get("position")

        binding.title.text = value.sarlavha



            initView()



        return binding.root
    }

    private fun initView() {



        binding.apply {


            var ozgaruvchi = questions

            tvQuestion.text = questions[qIndex]
            radioButton1.text = options[0]
            radioButton2.text = options[1]
            radioButton3.text = options[2]
            radioButton4.text = options[3]
            // check options selected or not
            // if selected then selected option correct or wrong
            nextQuestionBtn.setOnClickListener {
                if (radiogrp.checkedRadioButtonId == -1) {
                    Toast.makeText(binding.root.context,
                        "Please select an options",
                        Toast.LENGTH_SHORT)
                        .show()
                } else {
                    showNextQuestion()

                }


            }
            tvNoOfQues.text = "$updateQueNo/3"
            tvQuestion.text = questions[qIndex]


        }
    }


    private fun showNextQuestion() {
        qIndex++
        binding.apply {
            if (updateQueNo < 3) {
                tvNoOfQues.text = "${updateQueNo + 1}/3"
                updateQueNo++
            }
            if (qIndex <= questions.size - 1) {
                tvQuestion.text = questions[qIndex]
                Log.d(TAG, "showNextQuestion: ${qIndex}")
                radioButton1.text = options[qIndex * 4] // 2*4=8
                radioButton2.text = options[qIndex * 4 + 1] //  2*4+1=9
                radioButton3.text = options[qIndex * 4 + 2] //  2*4+2=10
                radioButton4.text = options[qIndex * 4 + 3] //  2*4+3=11
            } else {
                score = correct
                        var bundle = Bundle()
        bundle.putInt("correct",correct)
        bundle.putInt("wrong",wrong)
        bundle.putInt("skip",skip)

        val resultFragment = ResultFragment()
        resultFragment.arguments = bundle
        parentFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.frameLayout,resultFragment).commit()
            }
            radiogrp.clearCheck()
        }


    }





    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment QuestionsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuestionsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}