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
        val varyant = requireArguments().get("varyant")
        sortedlist = ArrayList()




        checkResult()
        setNavigation()




        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                var bundle = Bundle()
                bundle.putString("test",sarlavha.toString())
                bundle.putInt("position",options.toString().toInt())
                bundle.putInt("katposition",kategoriya.toString().toInt())
                bundle.putInt("varyant",varyant.toString().toInt())

                val introductionFragment = IntroductionFragment()
                introductionFragment.arguments = bundle
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout,introductionFragment).commit()
            }
        })




        binding.nazad.setOnClickListener{
            var bundle = Bundle()
            bundle.putString("test",sarlavha.toString())
            bundle.putInt("position",options.toString().toInt())
            bundle.putInt("katposition",kategoriya.toString().toInt())
            bundle.putInt("varyant",varyant.toString().toInt())

            val introductionFragment = IntroductionFragment()
            introductionFragment.arguments = bundle
            parentFragmentManager.beginTransaction()
                .replace(R.id.frameLayout,introductionFragment).commit()
        }


        binding.start.setOnClickListener {
            var bundle = Bundle()
            bundle.putString("test",sarlavha.toString())
            bundle.putInt("position",options.toString().toInt())
            bundle.putInt("katposition",kategoriya.toString().toInt())
            bundle.putInt("varyant",varyant.toString().toInt())

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
        val result = requireArguments().get("result").toString().toDouble()
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


        if (result <= scoresByTitle.minimum!!){
            binding.javobii.text = sortedlist[0].low
        }else if (result >scoresByTitle.minimum!! && result< scoresByTitle.high!!){
            binding.javobii.text = sortedlist[0].middle
        }else if (result>= scoresByTitle.high!!){
            binding.javobii.text = sortedlist[0].high
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
            add(Results("Odamlar bilan chiqishib keta olasizmi?","Ochig'ini aytadigan bo'lsak, siz odamlar bilan uncha tez chiqishib keta olmaysiz. Siz o'zini chki dunyosida yashaydigan odamsiz. Ammo o'zingizga qo'ygan hayotiy cheklovlaringizga qaramay do'stlaringiz sizni sevishadi va hurmat qilishadi","Siz ikki tomonli fe'l-atvorga egasiz. Bir tomonlama juda uyatchang odam bo'lsangiz,ammo ba'zida istalgan davraga moslashib ketganingizni o'zingiz ham sezmay qolasiz.Har qanday davrada o'zingizni qulay his etishingiz uchun qandaydir tashqaridan turtki kerak ya'ni sizni gapiritishga majburlaydigan holat bo'lishi kerak.","Siz nafaqat o'zingizning tanishlaringiz oldida, balki istalgan davrada tezda moslashib keta olasiz. Hammani ko'nglini topib, istalgan odam bilan osonlikcha chiqishib keta olasiz. Eng qiziqarli jihati sizning harakatlaringiz juda tabiiy va chiroyli chiqadi"))
            add(Results("Qanday ayollar sizni o'ziga maftun qiladi?","Sizga haqiqiy juftihalol emas balki o'ynash(xushtor) kerak.Sizni oila qurish, g'amxo'rlik va romantika qiziqtirmaydi. Siz ayolingizni do'st-u birodarlingiz oldida ko'z-ko'z qilshni hohlaysiz. Ammo esingizdan chiqarmang. Bu go'zallikning oqibati har doim ham sizga ma'qul bo'lmasligi mumkin.","Sizga do'st emas balki sizni cho'miltirib qo'yadigan, ovqatlantiradigan va sizni himoya qiladigan ikkinchi ona kerak. Qo'shimchasiga u sizdan itoatkorlikni talab qilishi mumkin","Sizga do'st emas balki sizni cho'miltirib qo'yadigan, ovqatlantiradigan va sizni himoya qiladigan ikkinchi ona kerak. Qo'shimchasiga u sizdan itoatkorlikni talab qilishi mumkin"))


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