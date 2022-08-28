package com.example.disneycoding.Teacher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.disneycoding.DBhelper.DatabaseHandler
import com.example.disneycoding.R
import com.example.disneycoding.util.MySharedPref
import com.google.firebase.auth.FirebaseAuth

class teacherlogin_Activity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacherlogin)

        val mySharedPref = MySharedPref(applicationContext)
        mAuth = FirebaseAuth.getInstance();

        val tea_Emailadd = findViewById<EditText>(R.id.tea_u_name) as EditText
        val tea_uname = findViewById<EditText>(R.id.tea_uname) as EditText
        val tea_Pass = findViewById<EditText>(R.id.tea_password) as EditText
        val tea_Reg = findViewById<TextView>(R.id.tea_login_new_account) as TextView
        val tea_R_Pre = findViewById<Button>(R.id.tea_login_btn) as Button


        tea_R_Pre.setOnClickListener {
            if (tea_uname.text.isEmpty()) {

                tea_Emailadd.setError("Enter User Name or Email ID")
            }
            if (tea_Emailadd.text.isEmpty()) {

                tea_Emailadd.setError("Enter Email ID")
            }
            if (tea_Pass.text.isEmpty()) {
                tea_Pass.setError("Enter Password")
            }
            if (tea_Pass.text.length < 6) {
                tea_Pass.setError("Enter valid Password")
            } else {

                val db = DatabaseHandler(applicationContext)
                val email = tea_Emailadd.text.toString()
                val pass = tea_Pass.text.toString()

                mAuth!!.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this)
                {
                    if (it.isSuccessful) {
                        Toast.makeText(
                            getApplicationContext(),
                            "Login successful!!",
                            Toast.LENGTH_LONG
                        )
                            .show()
                        mySharedPref.setisteacher(true)
                        mySharedPref.setteacherlogin(true)
                        mySharedPref.setUserProfile(tea_uname.text.toString())
                        intent= Intent(this@teacherlogin_Activity,MainActivity::class.java)
                        startActivity(intent)
                        finish()

                    }
                }

            }
        }

    }


}
