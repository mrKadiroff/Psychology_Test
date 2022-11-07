package com.shoxrux.psychology_tests.bottom_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatDelegate
import com.shoxrux.psychology_tests.MainActivity
import com.shoxrux.psychology_tests.R
import com.shoxrux.psychology_tests.databinding.FragmentInfoBinding
import com.shoxrux.psychology_tests.databinding.FragmentSettingsBinding
import com.shoxrux.psychology_tests.fragments.TestFragment
import com.shoxrux.psychology_tests.room.AppDatabase
import com.shoxrux.psychology_tests.room.themes.ThemesEntity
import com.shoxrux.psychology_tests.sharedpreferences.SaveData

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SettingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingsFragment : Fragment() {
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

    lateinit var binding: FragmentSettingsBinding
    lateinit var appDatabase: AppDatabase
    var ison = false
    private lateinit var saveData: SaveData
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(layoutInflater,container,false)
        appDatabase = AppDatabase.getInstance(binding.root.context)
        saveData = SaveData(binding.root.context)
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                requireActivity().finish()
                Toast.makeText(binding.root.context, "Fucked", Toast.LENGTH_SHORT).show()


            }
        })



        if (saveData.loadDarkModeState() == true){
            binding.btn.setChecked(true);
        }








        binding.btn?.setOnCheckedChangeListener({ _ , isChecked ->
            if (isChecked){
                Toast.makeText(binding.root.context, "nightmode", Toast.LENGTH_SHORT).show()
                saveData.setDarkModeState(true)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                setFragments()
            }else{
                Toast.makeText(binding.root.context, "lightmode", Toast.LENGTH_SHORT).show()
                saveData.setDarkModeState(false)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                setFragments()
            }
        })



        return binding.root
    }

    private fun setFragments() {
        (activity as MainActivity).binding.menuBottom.setItemSelected(R.id.home)

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SettingsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}