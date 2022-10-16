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
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import com.shoxrux.psychology_tests.R
import com.shoxrux.psychology_tests.bottom_fragments.HomeFragment
import com.shoxrux.psychology_tests.databinding.FragmentQuestionsBinding
import com.shoxrux.psychology_tests.databinding.FragmentTestBinding
import com.shoxrux.psychology_tests.models.Category_Names
import com.shoxrux.psychology_tests.models.Test_Values
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuestionsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuestionsFragment : Fragment(),View.OnClickListener {
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
    lateinit var savollar:Array<String>
    lateinit var variantlar:Array<String>
    private var score = 0
    private var correct = 0
    private var wrong = 0
    private var skip = 0
    private var qIndex = 0
    private var updateQueNo = 1
    private var sarlavha = ""
    // create string for question, answer and options
    private var questions0 = arrayOf(
        "Q.1. If a computer has more than one processor then it is known as?",
        "Q.2. Full form of URL is?",
        "Q.3. One kilobyte (KB) is equal to")
    private var answer0 = arrayOf(
        "Multiprocessor",
        "Uniform Resource Locator",
        "1,024 bytes")
    private var options0 = arrayOf(
        "Uniprocess",
        "Multiprocessor",
        "Multithreaded",
        "Multiprogramming",
        "Uniform Resource Locator",
        "Uniform Resource Linkwrong",
        "Uniform Registered Link",
        "Unified Resource Link",
        "1,000 bits")


    private var questions1 = arrayOf(
        "Q.1. Sevgiga ishonasizmi??",
        "Q.2. Siz uchun sex nima?",
        "Q.3. Jinsiy tarbiya muhimmi?",
        "Q.4. Opishishning foydali tomonlari?")
    private var answer1 = arrayOf(
        "Ha",
        "Yo'q",
        "Bilmayman")
    private var options1 = arrayOf(
        "Ha",
        "Yo'q",
        "Dinnaxuy",
        "Pashol",
        "Sizni sevaman",
        "Yolg'on gapirmang",
        "Sevgi bu sarob",
        "Seksualni erkak",
        "Jalab bo'lma",
        "Seksning siri",
        "Om yalsh",
        "Gandon kalla")




    private val TAG = "QuestionsFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuestionsBinding.inflate(layoutInflater,container,false)



         sarlavha = requireArguments().get("sarlavha").toString()





            initView()






        return binding.root
    }

    private fun setProgressBar() {
        binding.progressBar.progress = 1
        binding.progressBar.max = savollar.size
    }

    private fun initView() {





        binding.apply {


            when(sarlavha){
                "Sevgida omadlimisiz?" ->{
                    savollar = questions0
                    variantlar = options0
                }
                "Yigitlar nega sizga qaramaydi??" ->{
                    savollar = questions1
                    variantlar = options1
                }
                "Tabiiy go'zallikka nima yetsin" ->{
                    savollar = questions0
                    variantlar = options0
                }
            }




            birinchiSavol.setOnClickListener {
                setProgressBar()
                var text = txtPlayScore.text.toString().toInt()
              val first = text + 1
              txtPlayScore.text = first.toString()
              Log.d(TAG, "initView: ${text}")
              showNextQuestion()
          }

            ikkinchiSavol.setOnClickListener {
                setProgressBar()
                var text = txtPlayScore.text.toString().toInt()
                val second = text + 2
                txtPlayScore.text = second.toString()
                Log.d(TAG, "initView: ${text}")
                showNextQuestion()
            }

            uchinchiSavol.setOnClickListener {
                setProgressBar()
                var text = txtPlayScore.text.toString().toInt()
                val third = text + 3
                txtPlayScore.text = third.toString()
                Log.d(TAG, "initView: ${text}")
                showNextQuestion()
            }

            tvQuestion.text = savollar[qIndex]
            firstQuestion.text = variantlar[0]
            secondQuestion.text = variantlar[1]
            thirdQuestion.text = variantlar[2]


            tvNoOfQues.text = "$updateQueNo/${savollar.size}"
            tvQuestion.text = savollar[qIndex]


        }
    }


    private fun showNextQuestion() {

        qIndex++

        binding.progressBar.progress = qIndex
        binding.progressBar.max = savollar.size
        binding.apply {
            if (updateQueNo < savollar.size) {
                tvNoOfQues.text = "${updateQueNo + 1}/${savollar.size}"
                updateQueNo++
            }
            if (qIndex <= savollar.size - 1) {
                tvQuestion.text = savollar[qIndex]
                Log.d(TAG, "showNextQuestion: ${qIndex}")
                firstQuestion.text = variantlar[qIndex * 3] // 2*4=8
                secondQuestion.text = variantlar[qIndex * 3 + 1] //  2*4+1=9
                thirdQuestion.text = variantlar[qIndex * 3 + 2] //  2*4+2=10

            } else {
                score = correct

                var kategoriya = requireArguments().get("katposition")
                var result = txtPlayScore.text.toString().toInt()


                var bundle = Bundle()
        bundle.putString("sarlavha",sarlavha)
        bundle.putInt("options",3)
                bundle.putInt("result",result)
                bundle.putInt("savollarSoni",savollar.size)
                bundle.putInt("katposition",kategoriya.toString().toInt())


        val resultFragment = ResultFragment()
        resultFragment.arguments = bundle
        parentFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.frameLayout,resultFragment).commit()
            }

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

    override fun onClick(p0: View?) {

    }
}