package com.shoxrux.psychology_tests.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.shoxrux.psychology_tests.R
import com.shoxrux.psychology_tests.databinding.FragmentResultBinding
import com.shoxrux.psychology_tests.databinding.FragmentTestBinding
import com.shoxrux.psychology_tests.interfaces.IOnBackPressed
import com.shoxrux.psychology_tests.models.Category_Names
import com.shoxrux.psychology_tests.models.Results
import com.shoxrux.psychology_tests.models.Scores
import com.shoxrux.psychology_tests.models.Test_Values
import com.shoxrux.psychology_tests.room.AppDatabase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultFragment : Fragment() {
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

    lateinit var binding: FragmentResultBinding
    private val TAG = "ResultFragment"
    lateinit var sortedlist:ArrayList<Results>
    lateinit var appDatabase: AppDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentResultBinding.inflate(layoutInflater,container,false)
        appDatabase = AppDatabase.getInstance(binding.root.context)
        val options = requireArguments().get("options")
        val sarlavha = requireArguments().get("sarlavha")
        val kategoriya = requireArguments().get("katposition")
        val result = requireArguments().get("result")
        val savolSoni = requireArguments().get("savollarSoni")
        sortedlist = ArrayList()




        checkResult()
        setNavigation()




        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                var bundle = Bundle()
                bundle.putString("test",sarlavha.toString())
                bundle.putInt("position",options.toString().toInt())
                bundle.putInt("katposition",kategoriya.toString().toInt())


                val introductionFragment = IntroductionFragment()
                introductionFragment.arguments = bundle
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout,introductionFragment).commit()
            }
        })




        binding.orqaga.setOnClickListener{
            var bundle = Bundle()
            bundle.putString("test",sarlavha.toString())
            bundle.putInt("position",options.toString().toInt())
            bundle.putInt("katposition",kategoriya.toString().toInt())


            val introductionFragment = IntroductionFragment()
            introductionFragment.arguments = bundle
            parentFragmentManager.beginTransaction()
                .replace(R.id.frameLayout,introductionFragment).commit()
        }
        
        return binding.root
    }

    private fun setNavigation() {

    }

    private fun checkResult() {
        val sarlavha = requireArguments().get("sarlavha")
        val result = requireArguments().get("result").toString().toInt()
        val savolSoni = requireArguments().get("savollarSoni").toString().toInt()
        val options = requireArguments().get("options").toString().toInt()
        val customObjects = getCustomObjects()
        val scoresValue = getScores()

        val scoresByTitle = appDatabase.scoresDao().getScoresByTitle(sarlavha.toString())




        customObjects.forEach {
          if (it.title == sarlavha){
              sortedlist.add(it)

          }
        }

        var maxBall = savolSoni * options
        val middle = maxBall * 0.65


        if (result >= scoresByTitle.minimum!!&&result< scoresByTitle.premiddle!!){
            binding.javobii.text = "Javobi: juda kam"
        }else if (result >= scoresByTitle.premiddle!! && result< scoresByTitle.middle!!){
            binding.javobii.text = "Javobi: o'rtachadan sal kamro"
        }else if (result >= scoresByTitle.middle!! && result< scoresByTitle.high!!){
            binding.javobii.text = "Javobi: o'rtacha"
        }else{
            binding.javobii.text = "Javobi: Yuqori"
        }

//        if (result>=middle){
//            binding.javobii.text = "Javobi: ${sortedlist[0].high}"
//        }else{
//            binding.javobii.text = "Javobi: ${sortedlist[0].middle}"
//        }

//        binding.natija.text = "Maksimal to'plangan ball: ${maxBall}"
//        binding.javobi.text = "Testda ishlangan natija: ${result}"
//        binding.variantlarSoni.text = "Variantlar soni: ${options}"
//
//        binding.ortakoeffitsiyenti.text = "O'rta koeffitsiyenti: ${middle}"
    }


    private fun getCustomObjects():ArrayList<Results> {
        val customObjects = ArrayList<Results>()

        customObjects.apply {
            add(Results("Sevgida omadlimisiz?","Sizni sevgida omadli deb bo'lmaydi, haqiqiy loosersiz va ozgina dalbayoblik jihatlaringiz ham yo'q emas","Siz krasavchiksiz va hamma qizlar sizga kampot"))
            add(Results("Yigitlar nega sizga qaramaydi??","Yigitlar sizga qaramasligining sababi siz o'zingizga bo'lgan ishonchingiz past","Sizga hamma yigitlar qaraydi va siz juda ham chiroyli qizsiz"))
            add(Results("Tabiiy go'zallikka nima yetsin","Siz o'zingizga tabiiy ingredientlarni ishlatasiz","Sizda notabiiy bo'lgan pamadalrni ko'p ishlatasiz"))
            add(Results("Rashkchimisiz","Siz o'zingizga tabiiy ingredientlarni ishlatasiz","Sizda notabiiy bo'lgan pamadalrni ko'p ishlatasiz"))
            add(Results("Jinsiy hayotga tayyormisiz?","Siz o'zingizga tabiiy ingredientlarni ishlatasiz","Sizda notabiiy bo'lgan pamadalrni ko'p ishlatasiz"))
            add(Results("Kosmetikasiz hayot nima?","Siz o'zingizga tabiiy ingredientlarni ishlatasiz","Sizda notabiiy bo'lgan pamadalrni ko'p ishlatasiz"))


            return customObjects
        }
    }


    private fun getScores():ArrayList<Scores> {
        val customObjects = ArrayList<Scores>()

        customObjects.apply {
            add(Scores("Rashkchimisiz",3,5,7,9))



            return customObjects
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ResultFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ResultFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

   
}