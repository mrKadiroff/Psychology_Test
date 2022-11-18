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
import com.shoxrux.psychology_tests.bottom_fragments.HomeFragment
import com.shoxrux.psychology_tests.databinding.FragmentResultBinding
import com.shoxrux.psychology_tests.databinding.FragmentTestBinding
import com.shoxrux.psychology_tests.interfaces.IOnBackPressed
import com.shoxrux.psychology_tests.models.Category_Names
import com.shoxrux.psychology_tests.models.Results
import com.shoxrux.psychology_tests.models.Scores
import com.shoxrux.psychology_tests.models.Test_Values
import com.shoxrux.psychology_tests.ombor.setResult
import com.shoxrux.psychology_tests.ombor.setScores
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
    lateinit var sortedlist2:ArrayList<Scores>
    lateinit var appDatabase: AppDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentResultBinding.inflate(layoutInflater,container,false)
        appDatabase = AppDatabase.getInstance(binding.root.context)
        sortedlist = ArrayList()
        sortedlist2 = ArrayList()




        checkResult()
        setNavigation()




        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val homeFragment = HomeFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout,homeFragment).commit()
            }
        })




        binding.nazad.setOnClickListener{

            val homeFragment = HomeFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.frameLayout,homeFragment).commit()
        }


        binding.start.setOnClickListener {
            val homeFragment = HomeFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.frameLayout,homeFragment).commit()
        }
        
        return binding.root
    }

    private fun setNavigation() {

    }

    private fun checkResult() {
        val sarlavha = requireArguments().get("sarlavha")
        val result = requireArguments().get("result").toString().toDouble()
        val customObjects = setResult.getCustomObjects()
        val customObjects2 = setScores.getScores()

        val scoresByTitle = appDatabase.scoresDao().getScoresByTitle(sarlavha.toString())

        binding.javobii.text = sarlavha.toString()


        customObjects.forEach {
          if (it.title == sarlavha){
              sortedlist.add(it)

          }
        }




        customObjects2.forEach {
            if (it.category_name == sarlavha){
                sortedlist2.add(it)

            }
        }


        binding.cogniticvvee.text = sortedlist2[0].high.toString()




        if (result <= sortedlist2[0].minimum!!){
            binding.javobii.text = sortedlist[0].low
        }else if (result >sortedlist2[0].minimum!! && result< sortedlist2[0].high!!){
            binding.javobii.text = sortedlist[0].middle
        }else if (result>= sortedlist2[0].high!!){
            binding.javobii.text = sortedlist[0].high
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