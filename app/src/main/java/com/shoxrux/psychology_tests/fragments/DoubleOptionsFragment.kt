package com.shoxrux.psychology_tests.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shoxrux.psychology_tests.R
import com.shoxrux.psychology_tests.databinding.FragmentDoubleOptionsBinding
import com.shoxrux.psychology_tests.databinding.FragmentQuestionsBinding
import com.shoxrux.psychology_tests.models.Test_Values

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
        "Q.1. Eringizni hattoki qarindoshlaridan ham rash qilasizmi?",
        "Q.2. Bir kun yoringizni ko'rmasangiz sog'inib qolasizmi?",
        "Q.3. Vafodor erkak siz uchun qanday",
        "Q.2. Sobiq jazmanigizni uchratsangiz nima qilasiz?")
    private var options0 = arrayOf(
        "Uniprocess",
        "Multiprocessor",
        "Multithreaded",
        "Multiprogramming",
        "Uniform Resource Locator",
        "Uniform Resource Linkwrong",
        "Uniform Registered Link",
        "Unified Resource Link")


    private var questions1 = arrayOf(
        "Q.1. Kosemtikasiz yurolasizmi?",
        "Q.2. Pamadangiz qanday rang?",
        "Q.3. Jinsiy olatingizga kosmetika ishlatasizmi?",
        "Q.4. Seks nima?",
        "Q.4. prezervativ nima?",
        "Q.4. Jalabmisan nima?")
    private var options1 = arrayOf(
        "albatta",
        "Yo'q",
        "O'ylab ko'raman",
        "Bilmadim",
        "Ha, Chunki uzaytirmoqchiman",
        "Yo'q uzunligi yaxshi",
        "Bilmadim",
        "Sikaman",
        "Jalab bo'lma",
        "Seksning siri",
        "Om yalsh",
        "Gandon kalla")
    private val TAG = "DoubleOptionsFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDoubleOptionsBinding.inflate(layoutInflater,container,false)

        sarlavha = requireArguments().get("sarlavha").toString()

        binding.title.text = sarlavha



        initView()


        return binding.root
    }

    private fun initView() {
        binding.apply {


            when(sarlavha){
                "Rashkchimisiz" ->{
                    savollar = questions0
                    variantlar = options0
                }
                "Kosmetikasiz hayot nima?" ->{
                    savollar = questions1
                    variantlar = options1
                }
                "Jinsiy hayotga tayyormisiz?" ->{
                    savollar = questions0
                    variantlar = options0
                }
            }




            birinchiSavol.setOnClickListener {
                var text = txtPlayScore.text.toString().toInt()
                val first = text + 1
                txtPlayScore.text = first.toString()
                Log.d(TAG, "initView: ${text}")
                showNextQuestion()
            }

            ikkinchiSavol.setOnClickListener {
                var text = txtPlayScore.text.toString().toInt()
                val second = text + 2
                txtPlayScore.text = second.toString()
                Log.d(TAG, "initView: ${text}")
                showNextQuestion()
            }



            tvQuestion.text = savollar[qIndex]
            firstQuestion.text = variantlar[0]
            secondQuestion.text = variantlar[1]



            tvNoOfQues.text = "$updateQueNo/${savollar.size}"
            tvQuestion.text = savollar[qIndex]


        }
    }

    private fun showNextQuestion() {
        qIndex++
        binding.apply {
            if (updateQueNo < savollar.size) {
                tvNoOfQues.text = "${updateQueNo + 1}/${savollar.size}"
                updateQueNo++
            }
            if (qIndex <= savollar.size - 1) {
                tvQuestion.text = savollar[qIndex]
                Log.d(TAG, "showNextQuestion: ${qIndex}")
                firstQuestion.text = variantlar[qIndex * 2] // 2*4=8
                secondQuestion.text = variantlar[qIndex * 2 + 1] //  2*4+1=9


            } else {
                score = correct

                var kategoriya = requireArguments().get("katposition")
                var result = txtPlayScore.text.toString().toInt()

                var bundle = Bundle()
                bundle.putString("sarlavha",sarlavha)
                bundle.putInt("options",2)
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