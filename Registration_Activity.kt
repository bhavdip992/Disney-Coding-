package com.example.disneycoding

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony.Carriers.PASSWORD
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.disneycoding.DBhelper.DatabaseHandler
import com.example.disneycoding.DBhelper.UserData
//import com.bloodfinder.util.MySharedPref
import com.example.disneycoding.util.MySharedPref
import com.retorfit.disneycoding.Login
import com.example.disneycoding.Navigation.navigation
import com.example.disneycoding.Teacher.teacherreg_Activity
import com.example.disneycoding.Viewpager.home_Activity

class Registration_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        val F_Name = findViewById<EditText>(R.id.F_name) as EditText
        val L_name = findViewById<EditText>(R.id.L_name) as EditText
        val User_name = findViewById<EditText>(R.id.User_Name) as EditText
        val User_Email = findViewById<EditText>(R.id.U_email) as EditText
        val R_Pass = findViewById<EditText>(R.id.r_pass) as EditText
        val teacher = findViewById<Button>(R.id.teacher)

        val databasehandler= DatabaseHandler

        val userData:UserData

        val sign_Reg = findViewById<TextView>(R.id.sign_reg)
        val Reg_user = findViewById<TextView>(R.id.Register)



        Reg_user.setOnClickListener {
            if (F_Name.text.isEmpty()) {

                F_Name.setError("Enter Name")
            }
            if (L_name.text.isEmpty()) {
                L_name.setError("Enter Last Name")
            }
            if (User_name.text.isEmpty()) {
                User_name.setError("Enter Number")
            }
            if (User_Email.text.isEmpty()) {
                User_Email.setError("Enter Email")

            }
            if (R_Pass.text.isEmpty()) {
                R_Pass.setError("Enter Password")
            }
            if (R_Pass.text.length < 6) {
                R_Pass.setError("Enter valid Password")
            }
            else {
//                val userData:UserData
                val db=DatabaseHandler(applicationContext)
                val Fname=F_Name.text.toString()
                val Lname=L_name.text.toString()
                val username=User_name.text.toString()
                val Email=User_Email.text.toString()
                val pass=R_Pass.text.toString()

                val user=UserData(Fname,Lname,username,Email,pass)
                db.insertUser(user)
                intent = Intent(this@Registration_Activity, navigation::class.java)
                startActivity(intent)
                finish()
                showtextscreen()



            }
            }

            sign_Reg.setOnClickListener {
                intent = Intent(this@Registration_Activity, Login::class.java)
                startActivity(intent)
                finish()
            }
        teacher.setOnClickListener {
            intent= Intent(this@Registration_Activity,teacherreg_Activity::class.java)
            startActivity(intent)
            finish()
        }

        }
    fun showtextscreen()
    {
        val mySharedPref= MySharedPref(applicationContext)
        mySharedPref.setFirstTimeAppOpened(false)
        val myIntent = Intent(this@Registration_Activity, navigation::class.java)
        startActivity(myIntent)
        finish()
    }


}

