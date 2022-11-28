package com.ukadovlad21.dogapplication.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.drawToBitmap
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.ukadovlad21.dogapplication.*
import com.ukadovlad21.dogapplication.Share.CacheJava
import com.ukadovlad21.dogapplication.activity.MainActivity
import com.ukadovlad21.dogapplication.ViewModels.QuizViewModel
import com.ukadovlad21.dogapplication.databinding.FragmentQuizBinding
import com.ukadovlad21.dogapplication.toolbar.QuizToolbar
import kotlinx.android.synthetic.main.fragment_quiz.*
import kotlinx.android.synthetic.main.round_toolbar.*
import kotlinx.android.synthetic.main.round_toolbar.view.*

class QuizFragment : Fragment() {
    private lateinit var binding: FragmentQuizBinding
    var ivList: List<ImageView> = listOf()
    var answerName: String = ""
    private lateinit var dogChoiceShuffledList: List<String>
    private lateinit var viewModel: QuizViewModel

    var health: Int = 3
    val dogList = DogData.dogAllNamesList!!
    var viewIsClickable = 8
    val loadingPhotosDelay:Long = 200

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuizBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        toolbar_tv_hint_count.setCorrectText()
        tv_record_lvl.text = "Your record: ${ApplicationData.recordLVL}"
        setupClickListeners()
        startFirstLevel()
    }

    @SuppressLint("SetTextI18n")
    fun setupClickListeners() {
        btn_go_to_docs.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("DogName", answerName)
            (this.activity as MainActivity).navController.navigate(R.id.action_quizFragment_to_dogListPhotosFragment,bundle)
        }
        btn_repeat.setOnClickListener {
            tv_record_lvl.text = "Your record: ${ApplicationData.recordLVL}"
            health = 3
            round_toolbar.tv_round_number.text = "1"
            startNewLevel()
            ll_end_game.visibility = View.GONE
            heart1.setBackgroundResource(R.drawable.ic_baseline_favorite_36)
            heart2.setBackgroundResource(R.drawable.ic_baseline_favorite_36)
            heart3.setBackgroundResource(R.drawable.ic_baseline_favorite_36)
        }
        btn_share.setOnClickListener {
            share()
        }

        ivList = listOf(iv_dog1, iv_dog2, iv_dog3, iv_dog4, iv_dog5, iv_dog6, iv_dog7, iv_dog8, iv_dog9)
        for (i in ivList.indices) {
            ivList[i].setOnClickListener { tabReaction(ivList[i]) }
        }

        QuizToolbar(round_toolbar).setupClickListeners(parentFragmentManager)
        round_toolbar.toolbar_ll_hint.setOnClickListener {
            useHint()
        }
        round_toolbar.toolbar_btn_back.setOnClickListener {
            (this.activity as MainActivity).navController.navigate(R.id.action_quizFragment_to_mainFragment)
        }
    }
    private fun useHint() {
        if (viewIsClickable>0) {
            if (ApplicationData.hintCount >= 1) {
                val randomNum = (0..8).shuffled().last()
                if (ivList.get(randomNum).tag != answerName && ivList[randomNum].isClickable) {
                    ivList.get(randomNum).isClickable = false
                    ivList.get(randomNum).setBackgroundResource(R.drawable.dog_wrong_answer_background)
                    ApplicationData.hintCount -=1
                    round_toolbar.toolbar_tv_hint_count.setCorrectText()
                    viewIsClickable-=1
                } else {
                    useHint()
                }
            } else {
                //Звук отказа
            }
        }
    }

    private fun share() {
        val cache = CacheJava(requireContext())
        val screenshotUri = cache.saveToCacheAndGetUri(makeScreenshotBitmap())
        val photoIntent = Intent(Intent.ACTION_SEND)

        photoIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri)
        photoIntent.type = "image/*"
        photoIntent.action = Intent.ACTION_SEND
        startActivity(Intent.createChooser(photoIntent, "Winner screen"))
    }
    private fun makeScreenshotBitmap(): Bitmap {
        binding.btnRepeat.visibility = View.GONE
        binding.btnShare.visibility = View.GONE
        binding.btnGoToDocs.visibility = View.GONE
        val screenshot = binding.mainLl.drawToBitmap()
        binding.btnRepeat.visibility = View.VISIBLE
        binding.btnShare.visibility = View.VISIBLE
        binding.btnGoToDocs.visibility = View.VISIBLE
        return screenshot

    }


    private fun startFirstLevel() {
        setData()//updates dogChoiceSuffledList
        offClickListeners()
        viewModel = ViewModelProvider(this)[QuizViewModel::class.java]
        viewModel.getDogLinkListFromList(dogChoiceShuffledList)
        viewModel.listOfDogNameAndLink.observe(viewLifecycleOwner) {
            for (i in 0 until it.size){
                Glide.with(requireContext()).load(it[i].dogLink)
                    .error(R.drawable.ic_baseline_error_outline_32)
                    .into(ivList[i])
                ivList[i].tag = it[i].dogName

            }
        }
        Handler().postDelayed({
            onClickListeners()
        },loadingPhotosDelay)

    }

    private fun tabReaction(iv: ImageView) {
        viewIsClickable -=1
        if (answerName==iv.tag) {
            correctAnswer(iv)
        }else {
            wrongAnswer(iv)
        }
    }
    private fun wrongAnswer(iv:ImageView) {
        doToast("No. This is ${iv.tag}")
        iv.setBackgroundResource(R.drawable.dog_wrong_answer_background)
        iv.isClickable = false
        healthMinus()
    }
    private fun healthMinus() {
        when(health) {
            3 -> {
                health--
                heart3.setBackgroundResource(R.drawable.ic_baseline_favorite_border_36)
            }
            2 -> {
                health--
                heart2.setBackgroundResource(R.drawable.ic_baseline_favorite_border_36)
            }
            1 -> {
                health--
                offClickListeners()
                ll_end_game.visibility = View.VISIBLE
                heart1.setBackgroundResource(R.drawable.ic_baseline_favorite_border_36)

                if (ApplicationData.recordLVL < round_toolbar.tv_round_number.text.toString().toInt()){
                    ApplicationData.recordLVL = round_toolbar.tv_round_number.text.toString().toInt()
                }

                Handler().postDelayed({
                    doToast("You loose :(")
                    showCorrectAnswer()
                },1000)
            }

        }

    }
    private fun showCorrectAnswer() {
        ivList.map {
            if (it.tag==answerName) {
                it.setBackgroundResource(R.drawable.dog_correct_answer_background)
            }
        }
    }
    @SuppressLint("SetTextI18n")
    private fun correctAnswer(iv:ImageView) {
        offClickListeners()
        doToast("TRUE!!!")
        round_toolbar.tv_round_number.text = "${round_toolbar.tv_round_number.text.toString().toInt() + 1}"
        iv.setBackgroundResource(R.drawable.dog_correct_answer_background)
        Handler().postDelayed({
            startNewLevel()
        },800)

    }

    private fun startNewLevel() {
        nullingInfo()
        setData()
        viewModel.getDogLinkListFromList(dogChoiceShuffledList)
        Handler().postDelayed({
            onClickListeners()
        },loadingPhotosDelay)
        QuizToolbar(round_toolbar).updateAppData()


    }

    private fun offClickListeners() {
        ivList.map {
            it.isClickable = false
        }
        round_toolbar.toolbar_ll_hint.isClickable = false
        round_toolbar.toolbar_btn_add_hint.isClickable = false
    }
    private fun onClickListeners() {
        ivList.map {
            it.isClickable = true
        }
        round_toolbar.toolbar_ll_hint.isClickable = true
        round_toolbar.toolbar_btn_add_hint.isClickable = true
    }

    private fun nullingInfo() {
        viewIsClickable = 8
        ivList.map{
            it.setBackgroundResource(R.drawable.dog_name_background)
            it.setImageBitmap(null)
            it.tag = null
        }
    }
    @SuppressLint("SetTextI18n")
    private fun setData() {
        val answerNumber = (0..dogList.size).shuffled().last()
        var currentLevelDogList:MutableList<String> = dogList.toMutableList()
        answerName = currentLevelDogList[answerNumber]
        binding.tvFindDogName.text = "Find: $answerName"
        currentLevelDogList.removeAt(answerNumber)

        var dogChoiceList = mutableListOf(answerName)
        for (i in 0..7) {
            val num = (0 until currentLevelDogList.size).shuffled().last()
            dogChoiceList.add(currentLevelDogList[num])
        }
        dogChoiceShuffledList = dogChoiceList.shuffled()
    }


    private fun doToast(text: Any) {
        Toast.makeText(requireContext(),text.toString(),Toast.LENGTH_SHORT).show()
    }
}
