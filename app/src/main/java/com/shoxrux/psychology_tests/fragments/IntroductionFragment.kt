package com.shoxrux.psychology_tests.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.shoxrux.psychology_tests.R
import com.shoxrux.psychology_tests.databinding.FragmentIntroductionBinding
import com.shoxrux.psychology_tests.databinding.FragmentTestBinding
import com.shoxrux.psychology_tests.models.Test_Values

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [IntroductionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class IntroductionFragment : Fragment() {
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

    lateinit var binding: FragmentIntroductionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentIntroductionBinding.inflate(layoutInflater,container,false)


        navigate()



        onbackPressed()

        return binding.root
    }

    private fun onbackPressed() {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                val sarlavha = requireArguments().get("test")
                val sarlavha2 = requireArguments().get("test2")
                val options = requireArguments().get("position")
                val kategoriya = requireArguments().get("katposition")
                var bundle = Bundle()
                bundle.putString("kategoriya",sarlavha2.toString())
                bundle.putInt("position",kategoriya.toString().toInt())


                val testFragment = TestFragment()
                testFragment.arguments = bundle
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout,testFragment).commit()
            }
        })
    }

    private fun navigate() {






        val sarlavha = requireArguments().get("test")
        val sarlavha2 = requireArguments().get("test2")
        val options = requireArguments().get("position")
        val varyant = requireArguments().get("varyant")
        val kategoriya = requireArguments().get("katposition")

        binding.heading.text = "${sarlavha}"
        binding.savollar.text = "${varyant}"


        binding.orqaga.setOnClickListener{

            var bundle = Bundle()
            bundle.putString("kategoriya",sarlavha2.toString())
            bundle.putInt("position",kategoriya.toString().toInt())


            val testFragment = TestFragment()
            testFragment.arguments = bundle
            parentFragmentManager.beginTransaction()
                .replace(R.id.frameLayout,testFragment).commit()
        }


        binding.start.setOnClickListener {
            var bundle =Bundle()
            bundle.putString("sarlavha",sarlavha.toString())
            bundle.putInt("katposition",kategoriya.toString().toInt())
            bundle.putInt("varyant",varyant.toString().toInt())

            when(options){
                2->{
                    val doubleOptionsFragment = DoubleOptionsFragment()
                    doubleOptionsFragment.arguments = bundle
                    parentFragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frameLayout,doubleOptionsFragment).commit()
                }
                3->{
                    val questionsFragment = QuestionsFragment()
                    questionsFragment.arguments = bundle
                    parentFragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frameLayout,questionsFragment).commit()
                }

                4->{
                    val fourOptionsFragment = FourOptionsFragment()
                    fourOptionsFragment.arguments = bundle
                    parentFragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frameLayout,fourOptionsFragment).commit()
                }
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
         * @return A new instance of fragment IntroductionFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            IntroductionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}