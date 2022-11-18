package com.shoxrux.psychology_tests.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.shoxrux.psychology_tests.MainActivity
import com.shoxrux.psychology_tests.R
import com.shoxrux.psychology_tests.databinding.FragmentDoubleOptionsBinding
import com.shoxrux.psychology_tests.databinding.FragmentQuestionsBinding
import com.shoxrux.psychology_tests.models.Scores
import com.shoxrux.psychology_tests.models.Test_Values
import com.shoxrux.psychology_tests.ombor.setData
import com.shoxrux.psychology_tests.ombor.setScores
import com.shoxrux.psychology_tests.room.AppDatabase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DoubleOptionsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DoubleOptionsFragment : Fragment() {
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

    lateinit var binding: FragmentDoubleOptionsBinding
    lateinit var appDatabase: AppDatabase
    lateinit var savollar:Array<String>
    lateinit var variantlar:Array<String>
    private var score = 0
    private var correct = 0
    private var wrong = 0
    private var skip = 0
    private var qIndex = 0
    private var updateQueNo = 1
    private var sarlavha = ""
    lateinit var sortedlist2:ArrayList<Scores>
    private val TAG = "DoubleOptionsFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDoubleOptionsBinding.inflate(layoutInflater,container,false)
        appDatabase = AppDatabase.getInstance(binding.root.context)
        sortedlist2 = ArrayList()



        sarlavha = requireArguments().get("sarlavha").toString()




        Log.d(TAG, "onCreateView: ${sarlavha}")

        initView()



        return binding.root
    }

    private fun initView() {


        binding.apply {




            when(sarlavha){
                "Qaynonangizni yashi koring?" ->{
                    savollar = setData.TestiIkki()
                    variantlar = setData.JavobIkki()
                }

            }



            binding.apply {

                backgrounddd.setOnClickListener {
                    selectedOptionStyle(backgrounddd,1)

                }
                backgrounddd2.setOnClickListener {


                    selectedOptionStyle(backgrounddd2,2)

                }


                tvQuestion.text = savollar[qIndex]
                titlee.text = variantlar[0]
                titlee2.text = variantlar[1]

                // check options selected or not
                // if selected then selected option correct or wrong
                start.setOnClickListener {

                    if (txtPlayScore2.text == "0"){
                        Toast.makeText(binding.root.context, "Iltimos tanlang", Toast.LENGTH_SHORT).show()
                    }else{

                        backgrounddd.setBackgroundResource(R.drawable.turkum1)
                        backgrounddd2.setBackgroundResource(R.drawable.turkum3)

                        val increment = txtPlayScore2.text.toString().toDouble()
                        var text = txtPlayScore.text.toString().toDouble()
                        val first = text + increment
                        txtPlayScore.text = first.toString()
                        showNextQuestion()
                        txtPlayScore2.text = "0"
                    }




                }
                tvQuestion.text = savollar[qIndex]

            }


        }
    }





    private fun selectedOptionStyle(view: ConstraintLayout, opt:Int) {
        val customObjects2 = setScores.getScores()
        customObjects2.forEach {
            if (it.category_name == sarlavha){
                sortedlist2.add(it)

            }
        }

        setOptionStyle()
        when(opt){
            1-> {
                view.setBackgroundResource(R.drawable.selectedfirst)
                binding.txtPlayScore2.text = sortedlist2[0].firstButton.toString()
            }
            2->{
                view.setBackgroundResource(R.drawable.selectedmiddle)
                binding.txtPlayScore2.text = sortedlist2[0].secondButton.toString()
            }
            3->{
                view.setBackgroundResource(R.drawable.selectredlast)
                binding.txtPlayScore2.text = sortedlist2[0].thirdButton.toString()
            }
        }

    }

    private fun setOptionStyle() {
        binding.backgrounddd.setBackgroundResource(R.drawable.turkum1)
        binding.backgrounddd2.setBackgroundResource(R.drawable.turkum3)

    }


    private fun showNextQuestion() {





        qIndex++

        binding.progressBar.progress = qIndex
        binding.progressBar.max = savollar.size

        binding.apply {
            if (updateQueNo < savollar.size) {
                updateQueNo++
            }
            if (qIndex <= savollar.size - 1) {
                tvQuestion.text = savollar[qIndex]
                titlee.text = variantlar[qIndex * 2] // 2*4=8
                titlee2.text = variantlar[qIndex * 2 + 1] //  2*4+1=9

            } else {
                score = correct

                var kategoriya = requireArguments().get("katposition")
                var result = txtPlayScore.text.toString().toDouble()
                val varyant = requireArguments().get("varyant")


                var bundle = Bundle()
                bundle.putString("sarlavha",sarlavha)
                bundle.putDouble("result",result)



                val resultFragment = ResultFragment()
                resultFragment.arguments = bundle
                parentFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.frameLayout,resultFragment).commit()
            }

        }
























    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).hideBottomNavigation()
    }

    override fun onDetach() {
        (activity as MainActivity).showBottomNavigation()
        super.onDetach()

    }







    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DoubleOptionsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DoubleOptionsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}