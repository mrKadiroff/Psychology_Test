package com.shoxrux.psychology_tests.bottom_fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.shoxrux.psychology_tests.MainActivity
import com.shoxrux.psychology_tests.R
import com.shoxrux.psychology_tests.adapters.CategoryRv
import com.shoxrux.psychology_tests.databinding.FragmentHomeBinding
import com.shoxrux.psychology_tests.fragments.TestFragment
import com.shoxrux.psychology_tests.models.Category_Names
import com.shoxrux.psychology_tests.room.AppDatabase
import com.shoxrux.psychology_tests.room.CategoryEntity
import com.shoxrux.psychology_tests.room.scrores.ScoresEntity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
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

    lateinit var binding: FragmentHomeBinding
    lateinit var categoryRv: CategoryRv
    lateinit var appDatabase: AppDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        appDatabase = AppDatabase.getInstance(binding.root.context)

        insertToRoom()
        setView()

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                requireActivity().finish()


            }
        })


        return binding.root
    }

    private fun setView() {
        binding.progressBar.progress = 4
        binding.progressBar.max = 9
    }

    private fun insertToRoom() {
        val customObjects = getCustomObjects()

        val allCategory = appDatabase.categoryDao().getAllCategory()

        if (allCategory.isNullOrEmpty()){
            appDatabase.categoryDao().addCategory(categoryEntity = CategoryEntity("Erkaklar uchun",R.drawable.mujik,0))
            appDatabase.categoryDao().addCategory(categoryEntity = CategoryEntity("Ayollar uchun",R.drawable.women,1))
            appDatabase.categoryDao().addCategory(categoryEntity = CategoryEntity("Munosabatlar",R.drawable.munosabatlar,2))
        }

        val allScores = appDatabase.scoresDao().getAllScores()

        if (allScores.isNullOrEmpty()){
            appDatabase.scoresDao().addScores(scoresEntity = ScoresEntity("Odamlar bilan chiqishib keta olasizmi?",10,11,18,2.1,1.0,1.11))
            appDatabase.scoresDao().addScores(scoresEntity = ScoresEntity("Qanday ayollar sizni o'ziga maftun qiladi?",7,8,21,1.0,2.0,3.0))
            appDatabase.scoresDao().addScores(scoresEntity = ScoresEntity("Haqiqiy erkakmisiz yoki yosh bola?",5,9,15,1.0,2.0,3.0))
            appDatabase.scoresDao().addScores(scoresEntity = ScoresEntity("Sizga qanaqa erkak to'g'ri keladi?",7,14,15,1.0,2.0,3.0))
        }


    }

    private fun setRv() {
        val customObjects = getCustomObjects()

        val allCategory = appDatabase.categoryDao().getAllCategory()


        binding.titlee.text = allCategory[0].category_name
        binding.titlee2.text = allCategory[1].category_name
        binding.titlee3.text = allCategory[2].category_name

        binding.apply {

            backgrounddd.setOnClickListener {
                var bundle = Bundle()
                bundle.putString("kategoriya",allCategory[0].category_name)
                bundle.putInt("position",0)

                val testFragment = TestFragment()
                testFragment.arguments = bundle
                parentFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.frameLayout,testFragment).commit()
            }

            backgrounddd2.setOnClickListener {
                var bundle = Bundle()
                bundle.putString("kategoriya",allCategory[1].category_name)
                bundle.putInt("position",1)

                val testFragment = TestFragment()
                testFragment.arguments = bundle
                parentFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.frameLayout,testFragment).commit()
            }

            backgrounddd3.setOnClickListener {
                var bundle = Bundle()
                bundle.putString("kategoriya",allCategory[2].category_name)
                bundle.putInt("position",2)

                val testFragment = TestFragment()
                testFragment.arguments = bundle
                parentFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.frameLayout,testFragment).commit()
            }


        }


        categoryRv = CategoryRv(allCategory,object :CategoryRv.OnItemClickListener{
            override fun onItemClick(malumotlar: CategoryEntity, position: Int) {

                var bundle = Bundle()
                bundle.putString("kategoriya",malumotlar.category_name)
                bundle.putInt("position",position)

                val testFragment = TestFragment()
                testFragment.arguments = bundle
                parentFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.frameLayout,testFragment).commit()
            }

        })

//        binding.rv.adapter = categoryRv
    }


    private fun getCustomObjects():ArrayList<Category_Names> {
        val customObjects = ArrayList<Category_Names>()

        customObjects.apply {
            add(Category_Names("Erkaklar uchun",R.drawable.mujik))
            add(Category_Names("Ayollar uchun",R.drawable.women))
            add(Category_Names("Munosabatlar",R.drawable.munosabatlar))


            return customObjects
        }
    }

    override fun onResume() {
        super.onResume()
        setRv()
        (activity as MainActivity).showBottomNavigation()
    }


//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        (activity as MainActivity).hideBottomNavigation()
//    }
//
//    override fun onDetach() {
//        (activity as MainActivity).showBottomNavigation()
//        super.onDetach()
//
//    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}