package com.ukadovlad21.dogapplication.toolbar

import android.view.View
import androidx.fragment.app.FragmentManager
import com.ukadovlad21.dogapplication.ApplicationData
import com.ukadovlad21.dogapplication.R
import com.ukadovlad21.dogapplication.dialogs.OptionsDialog
import com.ukadovlad21.dogapplication.volume
import kotlinx.android.synthetic.main.preview_toolbar.view.*

class MainToolbar(private val toolbar: View) : Toolbar {
    override fun updateAppData() {
        setupVolume()
        if (ApplicationData.themeMode == "dark"){
            toolbar.toolbar_theme_image.setImageResource(R.drawable.ic_baseline_dark_mode_36)
        }else {
            toolbar.toolbar_theme_image.setImageResource(R.drawable.ic_baseline_light_mode_36)
        }

        if (ApplicationData.isVolumeOn){
            toolbar.toolbar_volume_image.setImageResource(R.drawable.ic_baseline_volume_up_36)
        }else {
            toolbar.toolbar_volume_image.setImageResource(R.drawable.ic_baseline_volume_off_36)
        }
    }

    override fun setupClickListeners(parentFragmentManager:FragmentManager) {
        toolbar.toolbar_theme_image.setOnClickListener {
            if (ApplicationData.themeMode == "dark"){
                ApplicationData.themeMode = "white"
            }else {
                ApplicationData.themeMode = "dark"
            }
            updateAppData()
        }
        toolbar.toolbar_volume_image.setOnClickListener {
            ApplicationData.isVolumeOn = !ApplicationData.isVolumeOn
            updateAppData()

        }
        toolbar.toolbar_options_image.setOnClickListener {
            val dialog = OptionsDialog(toolbar)
            dialog.show(parentFragmentManager, "main")
        }
    }

    override fun setupVolume(){
        toolbar.toolbar_volume_image.volume()
        toolbar.toolbar_theme_image.volume()
        toolbar.toolbar_options_image.volume()
    }
}