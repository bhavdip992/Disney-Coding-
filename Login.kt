package com.retorfit.disneycoding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.disneycoding.DBhelper.DatabaseHandler
import com.example.disneycoding.DBhelper.UserData
import com.example.disneycoding.Navigation.navigation
//import com.bloodfinder.util.MySharedPref
import com.example.disneycoding.R
import com.example.disneycoding.Registration_Activity
import com.example.disneycoding.util.MySharedPref

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val mySharedPref = MySharedPref(applicationContext)

        val Emailadd = findViewById<EditText>(R.id.u_name) as EditText
        val Pass = findViewById<EditText>(R.id.password) as EditText
        val Re_me = findViewById<CheckBox>(R.id.remember_me) as CheckBox
        val Reg = findViewById<TextView>(R.id.login_new_account) as TextView
        val R_Pass = findViewById<Button>(R.id.login_btn) as Button

        R_Pass.setOnClickListener {
            if (Emailadd.text.isEmpty()) {

                Emailadd.setError("Enter User Name or Email ID")
            }
            if (Pass.text.isEmpty()) {
                Pass.setError("Enter Password")
            }
            if (Pass.text.length < 6) {
                Pass.setError("Enter valid Password")
            }
            else {


                val db = DatabaseHandler(applicationContext)
                val email = Emailadd.text.toString()
                val pass = Pass.text.toString()

                Log.e("msg", "email=$email and Password=$pass ")

                val user = UserData(email, pass)

                var data = db.getSingleUserbyEmail(user).toString()

                Log.e("data", "data"+data )
                Log.e("user", user.EMAIL + user.PASSWORD, )

                if(user.EMAIL.equals(email) &&user.PASSWORD.equals(pass))
                {
                    val mySharedPref = MySharedPref(applicationContext)
                    mySharedPref.setIsLogin(true)
                    intent = Intent(this@Login, navigation::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "login", Toast.LENGTH_SHORT).show()
                    finish()


                }


            }
        }
        Reg.setOnClickListener {
            mySharedPref.setIsLogin(false)
            intent = Intent(this@Login, Registration_Activity::class.java)
            startActivity(intent)
            finish()
        }
    }
}