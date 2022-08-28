package com.example.disneycoding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast

import com.example.disneycoding.DBhelper.DatabaseHandler
import com.example.disneycoding.DBhelper.UserData
import com.example.disneycoding.util.MySharedPref
import com.retorfit.disneycoding.Login
import com.example.disneycoding.Navigation.navigation
import com.example.disneycoding.Teacher.MainActivity
import com.example.disneycoding.Teacher.teacherreg_Activity
import java.net.FileNameMap

class Splash_Screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val F_Name = "bhavdip"
        val L_name = "dodiya"
        val username = "bkd"
        val email = "bhavdeep992@gmail.com"
        val password = "bhavdip"

        val db = DatabaseHandler(applicationContext)
        val user = UserData(F_Name, L_name, username, email, password)
        db.insertUser(user)


        Handler().postDelayed({

            val mySharedPref = MySharedPref(applicationContext)
            if (mySharedPref.isFirsttimeAppOpen()) {
                mySharedPref.setFirstTimeAppOpened(false)

                Toast.makeText(applicationContext, "register redirect", Toast.LENGTH_SHORT)
                    .show()
                val intent = Intent(this@Splash_Screen, Registration_Activity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                if (mySharedPref.isLogin()) {
                    val intent = Intent(this@Splash_Screen, navigation::class.java)
                    startActivity(intent)
                    Toast.makeText(applicationContext, "navigation", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    mySharedPref.setIsLogin(false)
                    Toast.makeText(applicationContext, "login", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@Splash_Screen, Login::class.java)
                    startActivity(intent)
                    finish()
                }
            if (mySharedPref.isteacher()) {
                if (mySharedPref.isteacherfirsttime()) {
                    val intent = Intent(this@Splash_Screen, teacherreg_Activity::class.java)
                    startActivity(intent)
                    Toast.makeText(applicationContext, "teacher", Toast.LENGTH_SHORT).show()
                    finish()
                } else if (mySharedPref.isteacherlogin()) {
                    val intent = Intent(this@Splash_Screen, MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(applicationContext, "teacher", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }

        }}, 2500)


    }
}