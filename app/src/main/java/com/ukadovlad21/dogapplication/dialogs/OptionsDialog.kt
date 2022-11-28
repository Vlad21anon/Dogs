package com.ukadovlad21.dogapplication.dialogs

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.DialogFragment
import com.ukadovlad21.dogapplication.ApplicationData
import com.ukadovlad21.dogapplication.R
import com.ukadovlad21.dogapplication.activity.MainActivity.Companion.ABOUT_APP_URI
import com.ukadovlad21.dogapplication.databinding.DialogOptionsBinding
import com.ukadovlad21.dogapplication.volume
import kotlinx.android.synthetic.main.preview_toolbar.view.*

class OptionsDialog(private val toolbar: View): DialogFragment() {
    private lateinit var binding: DialogOptionsBinding

    @SuppressLint("UseGetLayoutInflater")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogOptionsBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(binding.root)


        //region switchInternet
        binding.switchInternetSave.isChecked =
            ApplicationData.internetEconomy //установить текущее состояние
        binding.switchInternetSave.setOnCheckedChangeListener { _, isChecked ->
            binding.switchInternetSave.volume()
            ApplicationData.internetEconomy = isChecked
        }
        //endregion

        //region switchVolume
        //Установить текущее состояние у volume switch
        binding.switchVolume.isChecked = ApplicationData.isVolumeOn
        //Реакция на нажатие
        binding.switchVolume.setOnCheckedChangeListener { _, isChecked ->
            binding.switchVolume.volume()
            if (isChecked) {
                ApplicationData.isVolumeOn = true
                if (tag == "main"){
                    toolbar.toolbar_volume_image.setImageResource(R.drawable.ic_baseline_volume_up_36)
                }
            }else {
                ApplicationData.isVolumeOn = false
                if (tag == "main") {
                    toolbar.toolbar_volume_image.setImageResource(R.drawable.ic_baseline_volume_off_36)
                }
            }
        }
        //endregion

        binding.tvAboutApp.setOnClickListener {
            binding.tvAboutApp.volume()
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(ABOUT_APP_URI))
            startActivity(intent)
        }
        binding.tvBuyMoreHint.setOnClickListener {
            binding.tvBuyMoreHint.volume()
            dismiss()
            buyDialog(tag.toString())
        }

        binding.ibCloseDialogOptions.setOnClickListener {
            binding.ibCloseDialogOptions.volume()
            dismiss()
        }


        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    private fun buyDialog(tag:String) {
        val dialogBuy = BuyDialog(toolbar)
        dialogBuy.show(parentFragmentManager, tag)
    }

}
