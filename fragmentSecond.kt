package com.example.kotlindemo.bottom

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment

import com.example.disneycoding.Teacher.MainActivity
//import com.bloodfinder.R
import com.example.disneycoding.R

class SecondFragment:Fragment(R.layout.fragment_second) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View=inflater.inflate(R.layout.fragment_second,container,false)
        var coursearray=view.resources.getStringArray(R.array.Courses)
        val list=view.findViewById(R.id.course_list) as ListView
        val arrayAdapter=activity?.let {
            ArrayAdapter<String>(it, android.R.layout.simple_list_item_1, coursearray)
        }
        list.adapter=arrayAdapter

//        on item click listener
      list.onItemClickListener= AdapterView.OnItemClickListener{
          parent, view, position, id ->
          val selectedItemText=parent.getItemAtPosition(position)
          Toast.makeText(context, "$selectedItemText", Toast.LENGTH_SHORT).show()
        val intent=Intent(context, MainActivity::class.java)
          startActivity(intent)

      }





        return view
    }
}
