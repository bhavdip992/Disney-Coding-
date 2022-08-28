package com.example.disneycoding.Navigation

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bloodfinder.bottom.FragmentFour
import com.example.disneycoding.Courselist_activity
import com.example.disneycoding.R
import com.example.disneycoding.Viewpager.home_Activity
import com.example.disneycoding.util.MySharedPref
import com.example.kotlindemo.bottom.SecondFragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import com.example.kotlindemo.bottom.FirstFragment
import com.example.kotlindemo.bottom.ThirdFragment
import com.retorfit.disneycoding.Login


class navigation : AppCompatActivity() {
    //
    private lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarToggle: ActionBarDrawerToggle
    private lateinit var navView: NavigationView
    lateinit var appbar: MaterialToolbar



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        // Call findViewById on the DrawerLayout
        drawerLayout = findViewById(R.id.my_drawer_layout)
        appbar = findViewById(R.id.appbar)
        navView = findViewById(R.id.navview)
        title="Kotlin App"
        val fragmentManager =supportFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fra1,FirstFragment()).commit()



        setupView()

        navView.setNavigationItemSelectedListener { menuItem ->


            when (menuItem.itemId) {
                R.id.nav_home -> {

                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()

                    val fragmentTransaction=fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.fra1,FirstFragment()).commit()
//                    intent= Intent(this@navigation, home_Activity::class.java)
//                    startActivity(intent)
                    true

                }
                R.id.nav_courses -> {
//                    val fragmentTransaction=fragmentManager.beginTransaction()
//                    fragmentTransaction.replace(R.id.fra1,SecondFragment()).commit()
                    intent= Intent(this@navigation, Courselist_activity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "Courses", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_aboutus -> {
                    val fragmentTransaction=fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.fra1,ThirdFragment()).commit()
                    Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_profile -> {
                    val fragmentTransaction=fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.fra1,FragmentFour()).commit()
                    Toast.makeText(this, "profile", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.logout -> {

                    val prefManager: SharedPreferences = getSharedPreferences(MySharedPref.KEY_SHARED_PREE_NAME_FIRST_TIME, 0)
                    val editor:SharedPreferences.Editor=prefManager.edit()

                    editor.clear()
                    editor.apply()
//                    val fragmentTransaction=fragmentManager.beginTransaction()
//                    fragmentTransaction.replace(R.id.fra1,FragmentFour()).commit()

                    Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()
                    intent=Intent(this@navigation,Login::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                else -> {
                    false
                }
            }}}


    fun setupView() {
        setUpDrawerLayout()

    }

    fun setUpDrawerLayout() {
//        val appbar=findViewById(R.id.appbar) as AppBarLayout
        setSupportActionBar(appbar)
        actionBarToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name)
        actionBarToggle.syncState()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

//    fun fragmentMethod()
//    {
//        Toast.makeText(this, "Method called From Fragment", Toast.LENGTH_LONG).show()
//
//
//    }

}
