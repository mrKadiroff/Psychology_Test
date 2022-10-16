package com.shoxrux.psychology_tests.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.shoxrux.psychology_tests.MainActivity
import com.shoxrux.psychology_tests.R
import com.shoxrux.psychology_tests.adapters.TestsRv
import com.shoxrux.psychology_tests.bottom_fragments.HomeFragment
import com.shoxrux.psychology_tests.databinding.FragmentHomeBinding
import com.shoxrux.psychology_tests.databinding.FragmentTestBinding
import com.shoxrux.psychology_tests.models.Category_Names
import com.shoxrux.psychology_tests.models.Test_Values

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TestFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TestFragment : Fragment() {
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

    lateinit var testsRv: TestsRv
    lateinit var binding: FragmentTestBinding
    lateinit var sortedlist:ArrayList<Test_Values>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTestBinding.inflate(layoutInflater,container,false)

        sortedlist = ArrayList()
        setRv()


        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {




                val homeFragment = HomeFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout,homeFragment).commit()
            }
        })


        return binding.root
    }

    private fun setRv() {

        binding.nazad.setOnClickListener {
            val homeFragment = HomeFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.frameLayout,homeFragment).commit()
        }




        val value = requireArguments().get("kategoriya")
        val positioni = requireArguments().get("position")





        val customObjects = getTestsValues()
        customObjects.forEach {
            if (it.position == positioni){
                sortedlist.add(it)
            }
        }

        binding.sarlavhasi.text = value.toString()

        testsRv = TestsRv(sortedlist,object :TestsRv.OnItemClickListener{
            override fun onItemClick(malumotlar: Test_Values, position: Int) {
                var bundle = Bundle()
                bundle.putString("test",malumotlar.sarlavha)
                bundle.putString("test2",value.toString())
                bundle.putInt("position",malumotlar.options)
                bundle.putInt("katposition",positioni.toString().toInt())

                        val introductionFragment = IntroductionFragment()
                introductionFragment.arguments = bundle
                        parentFragmentManager.beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.frameLayout,introductionFragment).commit()



//                when(malumotlar.options){
//                    2->{
//                        val doubleOptionsFragment = DoubleOptionsFragment()
//                        doubleOptionsFragment.arguments = bundle
//                        parentFragmentManager.beginTransaction()
//                            .addToBackStack(null)
//                            .replace(R.id.frameLayout,doubleOptionsFragment).commit()
//                    }
//                    3->{
//                        val questionsFragment = QuestionsFragment()
//                        questionsFragment.arguments = bundle
//                        parentFragmentManager.beginTransaction()
//                            .addToBackStack(null)
//                            .replace(R.id.frameLayout,questionsFragment).commit()
//                    }
//
//                    4->{
//                        val fourOptionsFragment = FourOptionsFragment()
//                        fourOptionsFragment.arguments = bundle
//                        parentFragmentManager.beginTransaction()
//                            .addToBackStack(null)
//                            .replace(R.id.frameLayout,fourOptionsFragment).commit()
//                    }
//                }



            }


        })

        binding.recyclerView.adapter = testsRv


    }


    private fun getTestsValues():ArrayList<Test_Values> {
        val customObjects = ArrayList<Test_Values>()

        customObjects.apply {
            add(Test_Values("Rashkchimisiz","16","2",0,2,))
            add(Test_Values("Jinsiy olatingizdan mamnunmisiz?","12","1",0,4))
            add(Test_Values("Sevgida omadlimisiz?","12","3",0,3))
            add(Test_Values("Jinsiy hayotga tayyormisiz?","22","2",0,2))

            add(Test_Values("Yigitlar nega sizga qaramaydi??","15","4",1,3))
            add(Test_Values("Kosmetikasiz hayot nima?","22","2",1,2))
            add(Test_Values("Tabiiy go'zallikka nima yetsin","9","2",1,3))
            add(Test_Values("Qaynona bilan kelishish?","22","2",1,5))


            return customObjects
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
         * @return A new instance of fragment TestFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TestFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}