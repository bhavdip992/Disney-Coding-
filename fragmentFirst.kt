package com.example.kotlindemo.bottom

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.resources.Compatibility.Api21Impl.inflate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.disneycoding.Courselist_activity


//import com.bloodfinder.MainActivity
//import com.bloodfinder.MyApplication
//import com.bloodfinder.R
//import com.bloodfinder.adapters.UserListAdapter
//import com.bloodfinder.db.DatabaseHandler
//import com.bloodfinder.models.UserData
import com.example.disneycoding.R
import com.example.disneycoding.Viewpager.pageAdapter
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
//import android.R




//import com.example.kotlindemo.RecycleView.CustomAdapter
//import com.example.kotlindemo.RecycleView.ItemViewModel


class FirstFragment : Fragment(R.layout.fragment_first) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View=inflater.inflate(R.layout.fragment_first,container,false)

        val mViewPager = view.findViewById<View>(R.id.View_Pager) as ViewPager
        val free = view.findViewById<View>(R.id.Free) as Button
        free.setOnClickListener {
            activity?.let{
                val intent = Intent (it, Courselist_activity::class.java)
                it.startActivity(intent)
            }
        }
        val mdoot = view.findViewById<View>(R.id.worm_dots_indicator) as WormDotsIndicator
        mViewPager.adapter = pageAdapter(childFragmentManager)
        mdoot.attachTo(mViewPager)
//        (activity as navigation?)!!.fragmentMethod()
        return view



    }

}


