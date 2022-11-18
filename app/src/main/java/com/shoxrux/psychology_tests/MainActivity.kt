package com.shoxrux.psychology_tests

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.shoxrux.psychology_tests.bottom_fragments.ArticleFragment
import com.shoxrux.psychology_tests.bottom_fragments.HomeFragment
import com.shoxrux.psychology_tests.bottom_fragments.InfoFragment
import com.shoxrux.psychology_tests.bottom_fragments.SettingsFragment
import com.shoxrux.psychology_tests.databinding.ActivityMainBinding
import com.shoxrux.psychology_tests.interfaces.IOnBackPressed
import com.shoxrux.psychology_tests.room.AppDatabase
import com.shoxrux.psychology_tests.room.themes.ThemesEntity
import com.shoxrux.psychology_tests.sharedpreferences.SaveData

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val fragment = HomeFragment()
    lateinit var appDatabase: AppDatabase
    private lateinit var saveData: SaveData
    override fun onCreate(savedInstanceState: Bundle?) {

        saveData = SaveData(this)




        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appDatabase = AppDatabase.getInstance(this)

        val allThemes = appDatabase.themesDao().getAllThemes()





//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        openMainFragment()



        binding.menuBottom.setItemSelected(R.id.home)
        binding.menuBottom.setOnItemSelectedListener {
            when (it) {

                R.id.home -> {
                    openMainFragment()
                    //Setup the navGraph for this activity

                }
                R.id.article -> {
                    val articleFragment = ArticleFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, articleFragment).commit()

                }

                R.id.info -> {
                    val infoFragment = InfoFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, infoFragment).commit()

                }

                R.id.settings -> {
                    val settingsFragment = SettingsFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, settingsFragment).commit()
                }
            }
        }


    }

    private fun openMainFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment)
        transaction.commit()
    }

    fun showBottomNavigation()
    {
        binding.menuBottom.visibility = View.VISIBLE




    }

    fun hideBottomNavigation()
    {
        binding.menuBottom.visibility = View.GONE

    }

    override fun onResume() {
        super.onResume()
        if (saveData.loadDarkModeState() == true){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    fun rnd_int(min: Int, max: Int): Int {
        var max = max
        max -= min
        return (Math.random() * ++max).toInt() + min
    }



}