package com.example.kotlindemo.bottom
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
//import com.bloodfinder.R
import com.example.disneycoding.R
import android.content.Intent
import android.net.Uri
import android.widget.Button


class ThirdFragment:Fragment(R.layout.fragment_third) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view:View=inflater.inflate(R.layout.fragment_third,container,false)
//        (activity as navigation?)!!.fragmentMethod()
        val phno = "9974215979"
        val emil="disneycodinglab@gmail.com"
        val btn=view.findViewById<View>(R.id.call) as Button
        val em=view.findViewById<View>(R.id.email) as Button

        btn.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            val temp = "tel:$phno"
            intent.data = Uri.parse(temp)
            startActivity(intent)
        }
        em.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain" // send email as plain text

            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("disneycodinglab@gmail.com"))
            intent.putExtra(Intent.EXTRA_SUBJECT, "subject")
            intent.putExtra(Intent.EXTRA_TEXT, "mail body")
            startActivity(Intent.createChooser(intent, ""))
        }



        return view
    }
}