package com.example.disneycoding.Viewpager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.disneycoding.R
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator

class home_Activity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarToggle: ActionBarDrawerToggle
    private lateinit var navView: NavigationView
    lateinit var appbar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Call findViewById on the DrawerLayout
        drawerLayout = findViewById(R.id.my_drawer_layout1)
        appbar = findViewById(R.id.appbar1)
        navView = findViewById(R.id.navview1)

//       **************** view pager****************
        val dotsIndicator = findViewById<WormDotsIndicator>(R.id.worm_dots_indicator)
        val viewPager = findViewById<ViewPager>(R.id.View_Pager)

        var viewAdapter: pageAdapter
        viewPager.adapter = pageAdapter(supportFragmentManager)
        dotsIndicator.attachTo(viewPager)

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })
//       **************** view pager end****************


        setupView()
//        recc()
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {

                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                    intent = Intent(this@home_Activity, home_Activity::class.java)
                    startActivity(intent)
                    true

                }
                R.id.nav_courses -> {
                    Toast.makeText(this, "Courses", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_aboutus -> {
                    Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_profile -> {
                    Toast.makeText(this, "profile", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }


    fun setupView() {
        setUpDrawerLayout()

    }

    fun setUpDrawerLayout() {
//        val appbar=findViewById(R.id.appbar) as AppBarLayout
        setSupportActionBar(appbar)
        actionBarToggle = ActionBarDrawerToggle(
            this, drawerLayout,
            R.string.app_name,
            R.string.app_name
        )
        actionBarToggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}
