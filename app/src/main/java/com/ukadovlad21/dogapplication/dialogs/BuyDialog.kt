package com.ukadovlad21.dogapplication.dialogs

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.DialogFragment
import com.ukadovlad21.dogapplication.ApplicationData
import com.ukadovlad21.dogapplication.databinding.DialogBuyHintBinding
import com.ukadovlad21.dogapplication.setCorrectText
import kotlinx.android.synthetic.main.round_toolbar.view.*


class BuyDialog(private val toolbar: View): DialogFragment() {
    private lateinit var binding: DialogBuyHintBinding

    @SuppressLint("UseGetLayoutInflater")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogBuyHintBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(binding.root)

        with(binding){
            buyHintCount.text = ApplicationData.hintCount.toString()
            llBuy1.setOnClickListener {
                ApplicationData.hintCount+=1000
                buyHintCount.text = ApplicationData.hintCount.toString()
                if (tag=="quiz"){ toolbar.toolbar_tv_hint_count.setCorrectText() }

            }
            llBuy2.setOnClickListener {
                ApplicationData.hintCount+=5000
                buyHintCount.text = ApplicationData.hintCount.toString()
                if (tag=="quiz"){ toolbar.toolbar_tv_hint_count.setCorrectText() }

            }
            llBuy3.setOnClickListener {
                ApplicationData.hintCount+=10000
                buyHintCount.text = ApplicationData.hintCount.toString()
                if (tag=="quiz"){ toolbar.toolbar_tv_hint_count.setCorrectText() }

            }
            tvCloseBuy.setOnClickListener {
                dismiss()
            }
        }

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }


}

