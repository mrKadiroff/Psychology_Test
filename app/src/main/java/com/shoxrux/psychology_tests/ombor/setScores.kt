package com.shoxrux.psychology_tests.ombor

import com.shoxrux.psychology_tests.models.Results
import com.shoxrux.psychology_tests.models.Scores
import com.shoxrux.psychology_tests.room.scrores.ScoresEntity

object setScores {

    fun getScores():ArrayList<Scores> {
        val customObjects = ArrayList<Scores>()

        customObjects.apply {
            add(Scores("Odamlar bilan chiqishib keta olasizmi?",10,11,18,2.1,1.0,1.11))
            add(Scores("Qanday ayollar sizni o'ziga maftun qiladi?",7,8,21,1.0,2.0,3.0))
            add(Scores("Haqiqiy erkakmisiz yoki yosh bola?",5,9,15,1.0,2.0,3.0))
            add(Scores("Sizga qanaqa erkak to'g'ri keladi?",7,14,15,1.0,2.0,3.0))
            add(Scores("Qaynonangizni yashi koring?",3,4,6,1.0,2.0,3.0))
            return customObjects
        }
    }


}