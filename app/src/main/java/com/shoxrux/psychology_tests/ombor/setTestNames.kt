package com.shoxrux.psychology_tests.ombor

import com.shoxrux.psychology_tests.models.Test_Values

object setTestNames {


   fun getTestsValues():ArrayList<Test_Values> {
        val customObjects = ArrayList<Test_Values>()

        customObjects.apply {
            add(Test_Values(1,"Qanday ayollar sizni o'ziga maftun qiladi?","Bu test ma'lum bir vaziyatlarni sizga namoyish qiladi va siz o'zingizga eng mos bo'lgan javobni belgilang",5,0,3))
            add(Test_Values(2,"Haqiqiy erkakmisiz yoki yosh bola?","Qanaqa erkaksiz? Mustaqil, o'ziga ishongan va o'zining oilasi va sevgan ayoli uchun mas'uliyatni bo'yniga oladigan erkakmisiz yoki burnini oqizib yurgan yosh bolamisiz?",5,0,3))
            add(Test_Values(3,"Mushaklarni o'stirish?","ha",5,0,3))
            add(Test_Values(4,"Sizga qanaqa erkak to'g'ri keladi?","Har bir ayolning orzusidagi erkagi bo'ladi.Kimdir ko'cha bezoririlaridan himoya qiladigan supermenni oz=rzu qilsa yana kimdir o'zi bilan bir-xil fikrlaydigan turmush o'rtoq bilan birga bo'lishni hoxlaydi",7,1,3))
            add(Test_Values(5,"Qaynonangizni yashi koring?","ha",6,1,2))
            add(Test_Values(6,"Hayot haqida?","12",7,1,2))


            add(Test_Values(7,"Odamlar bilan chiqishib keta olasizmi?","Odamlar qanday muloqotga kirishasiz? Har qanday sharoitda o'zingizni qulay his etasizmi? Odamlar bilan tez chiqishib keta oladigan turiga mansubmisiz? Bu testni yechish orqali yuqoridagi barcha savollarga javob olasiz",10,2,3))
            add(Test_Values(8,"Stressga bardoshlimisiz?","ha",15,2,3))
            add(Test_Values(9,"Sir saqlay olasizmi?","ha",33,2,3))
            add(Test_Values(10,"Depressiyaga chidamlilik?","ha",22,2,3))







            return customObjects
        }
    }


}