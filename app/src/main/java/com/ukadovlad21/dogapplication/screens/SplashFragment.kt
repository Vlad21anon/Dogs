package com.ukadovlad21.dogapplication.screens

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ukadovlad21.dogapplication.R
import com.ukadovlad21.dogapplication.activity.MainActivity

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Handler().postDelayed({ //Имитация SplashScreen
            (activity as MainActivity).navController.navigate(R.id.action_splashFragment_to_mainFragment)
        },2500)
    }


}

