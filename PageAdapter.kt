package com.example.disneycoding.Viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class pageAdapter(fm:FragmentManager):FragmentPagerAdapter(fm) {

    override fun getCount():Int {
       return 3


    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return fragment_pg_one()
            }
            1 -> {
                return fragment_pg_two()
            }
            2 -> {
                return fragment_pg_three()
            }
            else -> {
                return fragment_pg_one()
            }
        }

    }
    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Tab 1"
            }
            1 -> {
                return "Tab 2"
            }
            2 -> {
                return "Tab 3"
            }
        }
        return super.getPageTitle(position)
    }

}