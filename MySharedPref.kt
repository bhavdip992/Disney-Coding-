package com.example.disneycoding.util

import android.content.Context
import android.content.SharedPreferences

class MySharedPref(val context: Context) {

    val prefManager: SharedPreferences = context.getSharedPreferences(
        KEY_SHARED_PREE_NAME_FIRST_TIME, 0
    )
    val myEditor: SharedPreferences.Editor = prefManager.edit()

    // view pager code start
    fun setFirstTimeAppOpened(value: Boolean) {
        myEditor.putBoolean(KEY_IS_FIRST_TIME, value)
        myEditor.apply()
        myEditor.commit()

    }

    //
    fun isFirsttimeAppOpen(): Boolean {
        return prefManager.getBoolean(KEY_IS_FIRST_TIME, true)

    }


    //login code start
    val prefManager1: SharedPreferences = context.getSharedPreferences(
        KEY_SHARED_PREE_NAME_FIRST_TIME, 0
    )
    val editor: SharedPreferences.Editor = prefManager1.edit()

    fun setIsLogin(value: Boolean) {
        editor.putBoolean(KEY_IS_LOGIN, value)
        editor.apply()
        editor.commit()
    }

    fun isLogin(): Boolean {
        return prefManager1.getBoolean(KEY_IS_LOGIN, true)
    }


    //User ID
    fun setteacherlogin(value: Boolean) {
        editor.putBoolean(KEY_USER_ID, value)
        editor.apply()
        editor.commit()
    }

    fun isteacherlogin(): Boolean {
        return prefManager1.getBoolean(KEY_USER_ID, true)
    }

    //    email id
    fun setisteacher(value: Boolean) {
        editor.putBoolean(KEY_USER_ID, value)
        editor.apply()
        editor.commit()
    }

    fun isteacher(): Boolean {
        return prefManager1.getBoolean(KEY_USER_NAME, true)
    }

//    User Name

    fun setteacherisfirsttime(value: Boolean) {
        editor.putBoolean(KEY_USER_NAME, value)
        editor.apply()
        editor.commit()
    }

    fun isteacherfirsttime(): Boolean {
        return prefManager1.getBoolean(KEY_USER_NAME, true)
    }

//    User Profile

    fun setUserProfile(value: String) {
        editor.putString(KEY_USER_PROFILE, value)
        editor.apply()
        editor.commit()
    }

    fun getUserProfile(): String {
        return prefManager1.getString(KEY_USER_PROFILE, "").toString()
    }


    companion object myprefkeys {

        const val KEY_SHARED_PREE_NAME_FIRST_TIME = "MY_APP_DATA_FIRST_TIME"
        private const val KEY_IS_FIRST_TIME = "is_first_time"

        //        login for user
        private const val KEY_USER_ID = "user_id"
        private const val KEY_USER_NAME = "user_name"
        private const val KEY_USER_PROFILE = "user_profile"
        private const val KEY_IS_LOGIN = "is_login"


    }
}