package com.shoxrux.psychology_tests.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.shoxrux.psychology_tests.MainActivity
import com.shoxrux.psychology_tests.R
import com.shoxrux.psychology_tests.bottom_fragments.HomeFragment
import com.shoxrux.psychology_tests.databinding.FragmentQuestionsBinding
import com.shoxrux.psychology_tests.databinding.FragmentTestBinding
import com.shoxrux.psychology_tests.models.Category_Names
import com.shoxrux.psychology_tests.models.Scores
import com.shoxrux.psychology_tests.models.Test_Values
import com.shoxrux.psychology_tests.ombor.setData
import com.shoxrux.psychology_tests.ombor.setScores
import com.shoxrux.psychology_tests.room.AppDatabase
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuestionsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuestionsFragment : Fragment(),View.OnClickListener {
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

    lateinit var binding: FragmentQuestionsBinding
    lateinit var sortedlist2:ArrayList<Scores>
    lateinit var appDatabase: AppDatabase
    lateinit var savollar:Array<String>
    lateinit var variantlar:Array<String>
    private var score = 0
    private var correct = 0
    private var wrong = 0
    private var skip = 0
    private var qIndex = 0
    private var updateQueNo = 1
    private var sarlavha = ""
    // create string for question, answer and options
    private var questions0 = arrayOf(
        "Do'stlaringiz sizni tug'ilgan kunlar, kechalar va boshqa bayramlarga taklif qilishadimi?",
        "Notanish odamlar davrasida tez moslashib keta olasizmi?",
        "To'satdan e'tibor markaziga tushib qoldingiz, o'zingizni qanday his etasiz?",
        "Notanish auditoriyda ilk bor nutq so'zlayapsiz, nimalarni his etasiz?",
        "Boshqalarni ko'nglini topishni yoqtirasizmi? agar shunday bo'lsa o'sha davrani markazida bo'lish sizga ma'qulmi?",
        "Notanish odamdan yo'lni ko'rsatib yuborishini so'rash siz uchun qiyinmi?",
        "Tanishlar davrasida hazil qila olasizmi?",
        "Hozirjavobmisiz?",
        "Siz biroz taniydigan yoki umuman tanimaydigan inson bilan uchrashishga ketyapsiz. Siz u insonni oldindan bilishingiz va o'rtada aloqalar bo'lsihi kerak deb ehtiyoj sezasizmi?",
        "Siz chiroyli qiz(yigit) uchratib qoldingiz ammo uni uchrashuvga taklif etolmadingiz. O'zingizni qanday his etasiz?"
        )
    private var options0 = arrayOf(
        "Sizsiz hech qanday kecha bo'lishi mumkin emas, do'stlaringiz sizni albatta taklif qilishadi",
        "Odatada chaqirishadi lekin har doim ham emas",
        "Shovqinli kechalarni yaxshi ko'rmaysiz, shuning uchun hech kim sizni taklif qilmaydi",
        "Ha, bu mening asosiy hususiyatim",
        "faqatgina kayfiyatim yaxshi bo'lsagina",
        "Yo'q, sinfdoshlaringiz yoshligingizdan sizni antisocial ekanligingiz uchun yoqtirishmaydi",
        "Menga yoqadi bu hol",
        "Siz uchun qiziqarli, lekin biroz o'zingizni noqulay his etasiz",
        "Yer yorilsayu-ichiga kirib ketsam, bu hol menga yoqmaydi",
        "Biroz hayajonlanaman",
        "Biroz o'zimni yo'qotib qo'yaman va bu qo'rquvga aylanib ketishi mumkin",
        "goh tinchlanaman goh hayajonlanaman",
        "Ha, albatta. Davrani markazida bo'lishni yoqtiraman",
        "Buning hammasi davradagi odamlar va mening ayni paytdagi kayfiyatimga bog'liq",
        "Yo'q, menga odamlarning e'tibori yoqmaydi",
        "Yuz foiz adashib qolmagunimcha hech kimdan yordam so'ramayman",
        "So'rashim mumkin, lekin keyin o'zimni g'alati his etaman",
        "Bu men uchun umuman muammo emas, istagan odamdan yo'lni ko'rsatib yuborishini iltimos qila olaman",
        "Ha bu davrani qizdirishning eng ajoyib usuli, mehmonlar o'zlarini qulay his etishadi",
        "Ha, bazan hazillashib turaman",
        "Yo'q, men faqat tinglab o'tirishni yaxshi ko'raman",
        "Ha, bu fe'l-atvorimning asosiy hususiyati",
        "Vaziyatga qarayman",
        "Yo'q, bu hususiyat menga tegishli emas",
        "Ha, qachonki do'stingiz bilan uchrashsangiz, o'zingizni qulay his etasiz.",
        "Har doim emas, lekin bazida shunday bo'lishi kerak",
        "Yo'q, faqat uchrashuv haqida o'ylayman",
        "Hayotda har narsa bo'lib turadi, keyingi safar urinib ko'raman",
        "Menga farqi yo'q",
        "Bundan keyin hech kim bilan uchrashmayman"

        )


    private var questions1 = arrayOf(
        "Ishdan charchab keldingiz va kechki ovqat tanavvul qilib, biroz dam olmoqchisiz. Ammo, ovqat tayyorlanmagan, xona betartib va ayolingiz hech narsa bo'lmagandek televizor tomosha qilyaptdi. Siz...",
        "Sizning ayolingiz to'y yubileyingizni qanday nishonlash to'g'risida fikringizni so'rayapdi",
        "Ayolingiz sizdan sayohat qilishni iltimos qilyapdi. Siz qayerda dam olishni afzal ko'rasiz",
        "Ayollardagi siz hurmat qiladigan jihatlar",
        "Bolaligingiz qay tarzda o'tgan?")

    private var options1 = arrayOf(
        "Tabassum qilasiz va sevgan yoringizni restoranda ovqatlanishga taklif qilasiz",
        "Janjal ko'tarasiz",
        "Ayolingiz bilan yuzma-yuz gaplashib olasiz va birgalikda ovqat pishirasizlar",
        "Butunlay ayolingizning zimmasiga topshirasiz, u nima desa shu bo'ladi",
        "Juda kamxarajat bo'lishini taminlaysiz va kam sonli mehmonlarni chaqirasiz",
        "Tabiat qo'ynida barbekyu va dengiz taomlari bilan nishonlaysiz. Va u yerga barcha tanishlaringizni taklif qilasiz",
        "Okean bo'yida, qimmatli lyuksa restoranda",
        "Tabiat bilan o'ralgan manzarali joyda",
        "Qishloqdagi uyda",
        "Tejamkorlik, ovqat tayyorlay bilish, bolalarga g'amxo'rlik qilish, tikish, meva-sabzavotlar yetishtirish",
        "Chiroyli tashqi-ko'rinish, yaxshi kiyinishi va odamlar bilan yaxshi chiqishib keta olishi",
        "Romantik, san'at va kulinariyaga qiziqishi va oilaviy dam olishlarni tashkillashtira olishi",
        "Otangiz sizni haqiqiy erkak qilib tarbiyalagan",
        "Onangiz sizga g'amxo'rlik qilgan",
        "Bobo-buvilaringiz qo'lida tarbiyalangansiz")






    private var questions3 = arrayOf(
        "An'anaga ko'ra erkak ayoldan yoshi kattaroq bo'lishi kerak deb hisoblanadi. Sizningcha bu to'g'rimi?",
        "Muhim qaror qabul qilishdan oldin turmush o'rtog'ingiz bilan maslahatlashasizmi?",
        "Turmush o'rtog'ingiz futbol fanati. Siz uning qiziqishlarini qo'llab quvvatlaysizmi?",
        "Agar turmush o'rtog'ingiz bilan ajrashmoqchi bo'lsangiz, buni qanday amalga oshirasiz?",
        "O'qituvchingizni sevib qolganmisiz?",
        "Tug'ilgan kuningizni qanday nishonlaysiz?",
        "Qaysi kasb egasi sizni ko'proq jalb etadi?"
    )
    private var options3 = arrayOf(
        "Yo'q",
        "Ha shunday bo'lishi kerak, lekin istisnolar ham bo'lib turadi",
        "Katta bo'lishi kerak",
        "Faqatgina bu ikkalamizgaham tegishli bo'lsagina",
        "Muhim vaziyatlarda doim uning fikrini so'raysiz",
        "O'zingiz qaror qabul qilishga o'rgangansiz",
        "Yo'q",
        "Ba'zan",
        "Ha, birga borishdan hursandsiz",
        "Albatta janjal bilan bo'ladi",
        "Tinchgina hech qanday janjalsiz ajrashaman",
        "Ko'z yoshlarimni ushlab turolmayman",
        "Ha",
        "Ha, ammo bu sevgi emas, shunchaki sodda qizning o'yu-havaslari edi",
        "Yo'q",
        "Restoranda ikki kishilik joyda o'tkazamiz",
        "Hamma do'stlarimni yig'aman",
        "Kichik oila davrasida o'tkazamiz",
        "Politsiyachi",
        "Sudya",
        "Soliq qo'mitasi raisi"

    )




    private val TAG = "QuestionsFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuestionsBinding.inflate(layoutInflater,container,false)
        appDatabase = AppDatabase.getInstance(binding.root.context)
        sortedlist2 = ArrayList()

         sarlavha = requireArguments().get("sarlavha").toString()




        Log.d(TAG, "onCreateView: ${sarlavha}")

            initView()






        return binding.root
    }

    private fun setProgressBar() {
        binding.progressBar.progress = 1
        binding.progressBar.max = savollar.size
    }

    private fun initView() {





        binding.apply {




            when(sarlavha){
                "Odamlar bilan chiqishib keta olasizmi?" ->{
                    savollar = questions0
                    variantlar = options0
                }
                "Qanday ayollar sizni o'ziga maftun qiladi?" ->{
                    savollar = questions1
                    variantlar = options1
                }
                "Haqiqiy erkakmisiz yoki yosh bola?" ->{
                    savollar =setData.ManOrBoy()
                    variantlar = setData.ManOrBoyOptions()
                }
                "Sizga qanaqa erkak to'g'ri keladi?" ->{
                    savollar = questions3
                    variantlar = options3

                }
                "Mushaklarni o'stirish?" ->{
                    savollar = questions0
                    variantlar = options0
                    makeInvisible()
                }
                "Stressga bardoshlimisiz?" ->{
                    savollar = questions0
                    variantlar = options0
                    makeInvisible()
                }
                "Sir saqlay olasizmi?" ->{
                    savollar = questions0
                    variantlar = options0
                    makeInvisible()
                }
                "Depressiyaga chidamlilik?" ->{
                    savollar = questions0
                    variantlar = options0
                    makeInvisible()
                }
            }


            val scoresByTitle = appDatabase.scoresDao().getScoresByTitle(sarlavha)
            binding.apply {

                backgrounddd.setOnClickListener {
                    selectedOptionStyle(backgrounddd,1)

                }
                backgrounddd2.setOnClickListener {


                    selectedOptionStyle(backgrounddd2,2)

                }
                backgrounddd3.setOnClickListener {

                    selectedOptionStyle(backgrounddd3,3)


                }

                tvQuestion.text = savollar[qIndex]
                titlee.text = variantlar[0]
                titlee2.text = variantlar[1]
                titlee3.text = variantlar[2]
                // check options selected or not
                // if selected then selected option correct or wrong
                start.setOnClickListener {

                    if (txtPlayScore2.text == "0"){
                        Toast.makeText(binding.root.context, "Iltimos tanlang", Toast.LENGTH_SHORT).show()
                    }else{

                        backgrounddd.setBackgroundResource(R.drawable.turkum1)
                        backgrounddd2.setBackgroundResource(R.drawable.turkum2)
                        backgrounddd3.setBackgroundResource(R.drawable.turkum3)
                        val increment = txtPlayScore2.text.toString().toDouble()
                        var text = txtPlayScore.text.toString().toDouble()
                        val first = text + increment
                        txtPlayScore.text = first.toString()
                        showNextQuestion()
                        txtPlayScore2.text = "0"
                    }




                }
                tvQuestion.text = savollar[qIndex]

            }


        }
    }

    private fun selectedOptionStyle(view:ConstraintLayout,opt:Int) {



        val customObjects2 = setScores.getScores()
        customObjects2.forEach {
            if (it.category_name == sarlavha){
                sortedlist2.add(it)

            }
        }




        setOptionStyle()
        when(opt){
            1-> {
                view.setBackgroundResource(R.drawable.selectedfirst)
                binding.txtPlayScore2.text = sortedlist2[0].firstButton.toString()
            }
            2->{
                view.setBackgroundResource(R.drawable.selectedmiddle)
                binding.txtPlayScore2.text = sortedlist2[0].secondButton.toString()
            }
            3->{
                view.setBackgroundResource(R.drawable.selectredlast)
                binding.txtPlayScore2.text = sortedlist2[0].thirdButton.toString()
            }
        }

    }

    private fun setOptionStyle() {
        binding.backgrounddd.setBackgroundResource(R.drawable.turkum1)
        binding.backgrounddd2.setBackgroundResource(R.drawable.turkum2)
        binding.backgrounddd3.setBackgroundResource(R.drawable.turkum3)
    }


    private fun showNextQuestion() {





        qIndex++

        binding.progressBar.progress = qIndex
        binding.progressBar.max = savollar.size

        binding.apply {
            if (updateQueNo < savollar.size) {
                updateQueNo++
            }
            if (qIndex <= savollar.size - 1) {
                tvQuestion.text = savollar[qIndex]
                titlee.text = variantlar[qIndex * 3] // 2*4=8
                titlee2.text = variantlar[qIndex * 3 + 1] //  2*4+1=9
                titlee3.text = variantlar[qIndex * 3 + 2] //  2*4+2=10
            } else {
                score = correct

                var result = txtPlayScore.text.toString().toDouble()



                var bundle = Bundle()
                bundle.putString("sarlavha",sarlavha)
                bundle.putDouble("result",result)


                val resultFragment = ResultFragment()
                resultFragment.arguments = bundle
                parentFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.frameLayout,resultFragment).commit()
            }

        }
























    }

    private fun makeInvisible(){
        binding.backgrounddd.visibility = View.GONE
        binding.backgrounddd2.visibility = View.GONE
        binding.backgrounddd3.visibility = View.GONE
        binding.start.visibility = View.GONE
        binding.savolBg.visibility = View.GONE
        binding.txtPlayScore.text = "Hali test qo'shilmagan iltimos orqaga qayting"
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
         * @return A new instance of fragment QuestionsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuestionsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(p0: View?) {

    }
}