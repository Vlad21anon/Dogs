package com.ukadovlad21.dogapplication.activity

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ukadovlad21.dogapplication.ApplicationData
import com.ukadovlad21.dogapplication.R
import com.ukadovlad21.dogapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    var pref: SharedPreferences?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pref = getSharedPreferences("TABLE", Context.MODE_PRIVATE)
        setupApplicationData()

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
    }

    private fun setupApplicationData() {
        ApplicationData.isVolumeOn = pref?.getBoolean(TAG_VOLUME, ApplicationData.isVolumeOn)!!
        ApplicationData.themeMode = pref?.getString(TAG_theme, ApplicationData.themeMode)!!
        ApplicationData.internetEconomy = pref?.getBoolean(
            TAG_INTERNET,
            ApplicationData.internetEconomy
        )!!
        ApplicationData.recordLVL = pref?.getInt(TAG_RECORD_LVL, ApplicationData.recordLVL)!!
        ApplicationData.hintCount = pref?.getInt(TAG_HINT_COUNT, ApplicationData.hintCount)!!

    }

    private fun saveData(){
        val editor = pref?.edit()
        editor?.putBoolean(TAG_VOLUME, ApplicationData.isVolumeOn)
        editor?.putString(TAG_theme, ApplicationData.themeMode)
        editor?.putInt(TAG_HINT_COUNT, ApplicationData.hintCount)
        editor?.putInt(TAG_RECORD_LVL, ApplicationData.recordLVL)
        editor?.putBoolean(TAG_INTERNET, ApplicationData.internetEconomy)
        editor?.apply()
    }


    override fun onPause() {
        super.onPause()
        saveData()

    }

    companion object {
        const val TAG_VOLUME = "volume"
        const val TAG_theme = "theme"
        const val TAG_INTERNET = "internet"
        const val TAG_HINT_COUNT = "hint_count"
        const val TAG_RECORD_LVL = "record_lvl"

        const val ABOUT_APP_URI = "https://github.com/Vlad21anon"
    }

}

