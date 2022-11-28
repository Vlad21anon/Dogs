package com.ukadovlad21.dogapplication.toolbar

import android.view.View
import androidx.fragment.app.FragmentManager
import com.ukadovlad21.dogapplication.*
import com.ukadovlad21.dogapplication.dialogs.OptionsDialog
import kotlinx.android.synthetic.main.preview_toolbar.view.toolbar_options_image
import kotlinx.android.synthetic.main.round_toolbar.view.*

class QuizToolbar(private val toolbar: View):Toolbar {

    override fun updateAppData() {
        if(ApplicationData.recordLVL< toolbar.tv_round_number.text.toString().toInt()){
            ApplicationData.recordLVL = toolbar.tv_round_number.text.toString().toInt()
        }
    }

    override fun setupClickListeners(parentFragmentManager: FragmentManager) {
        toolbar.toolbar_btn_add_hint.setOnClickListener {
            //todo Добавить суда рекламу
            ApplicationData.hintCount+=1
            toolbar.toolbar_tv_hint_count.setCorrectText()

        }
        toolbar.toolbar_options_image.setOnClickListener {
            val dialog = OptionsDialog(toolbar)
            dialog.show(parentFragmentManager, "quiz")
        }
    }




    override fun setupVolume() {
        toolbar.toolbar_btn_back.volume()
        toolbar.toolbar_btn_add_hint.volume()
        toolbar.toolbar_options_image.volume()
        toolbar.toolbar_ll_hint.volume()

    }
}