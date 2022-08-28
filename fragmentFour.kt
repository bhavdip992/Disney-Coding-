package com.bloodfinder.bottom

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.disneycoding.DBhelper.DatabaseHandler
import com.example.disneycoding.DBhelper.UserData
//import com.bloodfinder.R
import com.example.disneycoding.R
import com.example.disneycoding.util.MySharedPref
import com.retorfit.disneycoding.Login

class FragmentFour:Fragment(R.layout.fragment_four) {

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ):
    View? {
        val view:View=inflater.inflate(R.layout.fragment_four,container,false)
//        (activity as navigation?)!!.fragmentMethod()

        val db = DatabaseHandler(context)
        val txt1=view.findViewById<View>(R.id.txt1) as TextView
        val txt2=view.findViewById<View>(R.id.txt2) as TextView
        val txt3=view.findViewById<View>(R.id.txt3) as TextView
        val txt4=view.findViewById<View>(R.id.txt4) as TextView
        val txt5=view.findViewById<View>(R.id.txt5) as TextView
        val logout=view.findViewById<View>(R.id.lout) as Button


        db.readableDatabase
     val cursor=db.getuser()
        cursor!!.moveToFirst()
        txt1.text="First Name= "+(cursor.getString(0).toString())
        txt2.text="Last Name= "+(cursor.getString(1).toString())
        txt3.text="Your UserName= "+(cursor.getString(2).toString())
        txt4.text="Email= "+(cursor.getString(3).toString())
        txt5.text="Password= "+(cursor.getString(4).toString())

        Log.e("data", "onCreateView: ", )
        logout.setOnClickListener{
            val prefManager: SharedPreferences = this.requireActivity()!!.getSharedPreferences(MySharedPref.KEY_SHARED_PREE_NAME_FIRST_TIME, 0)
            val editor:SharedPreferences.Editor=prefManager.edit()
            editor.clear()
            editor.commit()
            Toast.makeText(context, "Logout", Toast.LENGTH_SHORT).show()
            val intent= Intent(context, Login::class.java)
            startActivity(intent)
        requireActivity().finish()
        }
        return view
    }

}