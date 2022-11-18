package com.shoxrux.psychology_tests.workmanager

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import com.shoxrux.psychology_tests.ombor.setTestNames
import com.shoxrux.psychology_tests.room.AppDatabase
import com.shoxrux.psychology_tests.room.themes.ThemesEntity


lateinit var appDatabase: AppDatabase
class UploadWork(var context: Context, workerParameters: WorkerParameters) :
    Worker(context,workerParameters) {
    private val TAG = "UploadWork"
    override fun doWork(): Result {

        uploadImages()

        return Result.success()
    }

    private fun uploadImages() {
        appDatabase = AppDatabase.getInstance(context)
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(context, "Toast", Toast.LENGTH_SHORT).show()
        }
        Log.d(TAG, "uploadImages: Fucked")

        val size = setTestNames.getTestsValues().size
        val rndInt = rnd_int(1, size)
        val allThemes = appDatabase.themesDao().getAllThemes()


        if (!allThemes.isNullOrEmpty()){
            val themesById = appDatabase.themesDao().getThemesById(1)
            themesById[0].random = rndInt
            appDatabase.themesDao().updateTheme(themesById)
        }




        

    }

    fun rnd_int(min: Int, max: Int): Int {
        var max = max
        max -= min
        return (Math.random() * ++max).toInt() + min
    }
}