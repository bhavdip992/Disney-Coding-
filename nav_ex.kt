package com.bloodfinder.bottom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
//import com.bloodfinder.R
import com.example.disneycoding.R
import com.example.kotlindemo.bottom.FirstFragment
import com.example.kotlindemo.bottom.SecondFragment
import com.example.kotlindemo.bottom.ThirdFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
//import kotlinx.android.synthetic.main.activity_nav_ex.*

class nav_ex : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_ex)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.activity_main_bottom_navigation_view)
        val navController = findNavController(R.id.activity_main_nav_host_fragment)
        bottomNavigationView.setupWithNavController(navController)

    }

}