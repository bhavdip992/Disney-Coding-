package com.example.disneycoding.Teacher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.disneycoding.R
import com.example.disneycoding.util.MySharedPref
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class teacherreg_Activity : AppCompatActivity() {

    lateinit var Firstname: EditText
    lateinit var Secondname: EditText
    lateinit var username: EditText
    lateinit var teaEmail: EditText
    private lateinit var teaPass: EditText
    private lateinit var Teabtn_reg: Button
    lateinit var progressBar: ProgressBar


    // create Firebase authentication object
    private lateinit var auth: FirebaseAuth
    private lateinit var analytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacherreg)

        Firstname = findViewById(R.id.Ftea_name)
        Secondname = findViewById(R.id.Ltea_name)
        username = findViewById(R.id.Usertea_Name)
        teaEmail = findViewById(R.id.Utea_email)
        teaPass = findViewById(R.id.rtea_pass)
        Teabtn_reg = findViewById(R.id.tea_Register)
        val tea_login = findViewById(R.id.sign_reg) as TextView
        progressBar = findViewById<ProgressBar>(R.id.prbar)

        auth = Firebase.auth
        Teabtn_reg.setOnClickListener {
            singUpUser()
        }
        tea_login.setOnClickListener {
            intent= Intent(this@teacherreg_Activity,teacherlogin_Activity::class.java)
            startActivity(intent)
            finish()
        }



    }

    private fun singUpUser() {


        if (Firstname.text.isEmpty()) {

            Firstname.setError("Enter Name")
        }
        if (Secondname.text.isEmpty()) {
            Secondname.setError("Enter Last Name")
        }
        if (username.text.isEmpty()) {
            username.setError("Enter Number")
        }
        if (teaEmail.text.isEmpty()) {
            teaEmail.setError("Enter Email")

        }
        if (teaPass.text.isEmpty()) {
            teaPass.setError("Enter Password")
        }
        if (teaPass.text.length < 6) {
            teaPass.setError("Enter valid Password")
        } else {
            progressBar.setVisibility(View.VISIBLE)
            val firsttea = Firstname.text.toString()
            val secondtea = Secondname.text.toString()
            val Uteaname = username.text.toString()
            val emailtea = teaEmail.text.toString()
            val passtea = teaPass.text.toString()

            auth.createUserWithEmailAndPassword(emailtea, passtea).addOnCompleteListener(this)
            {


                if (it.isSuccessful) {
                    val database = Firebase.database
                    val myRef = database.getReference("Teacher")
                    val user = User(firsttea, secondtea, emailtea, passtea)
                    myRef.child(Uteaname).setValue(user)

                    Toast.makeText(
                        this@teacherreg_Activity,
                        "Register Sucessfull",
                        Toast.LENGTH_LONG
                    ).show()
                    val mySharedPref = MySharedPref(applicationContext)

//                    mySharedPref.setteacherisfirsttime(true)
                    mySharedPref.setisteacher(true)
                    mySharedPref.setUserProfile("$Uteaname")

                    val intent = Intent(this@teacherreg_Activity, MainActivity::class.java)

                    startActivity(intent)
                    finish()


                    progressBar.setVisibility(View.INVISIBLE)

                }
                if (it.isCanceled) {
                    Toast.makeText(
                        this@teacherreg_Activity,
                        "Please Check Your Internet Connection= ${it.exception}",
                        Toast.LENGTH_LONG
                    ).show()

                }
            }
        }


    }
}