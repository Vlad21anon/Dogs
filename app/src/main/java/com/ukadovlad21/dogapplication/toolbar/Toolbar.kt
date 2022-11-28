package com.ukadovlad21.dogapplication.toolbar

import androidx.fragment.app.FragmentManager

interface Toolbar{
    fun updateAppData()

    fun setupClickListeners(parentFragmentManager: FragmentManager)

    fun setupVolume()
}