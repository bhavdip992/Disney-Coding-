package com.example.disneycoding.DBhelper

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.Telephony.Carriers.PASSWORD
import android.util.Log
import android.widget.Toast
import org.w3c.dom.Text

class DatabaseHandler(var context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "User"
        private const val TABLE_USER = "user_data"

        private const val KEY_FNAME = "Fname"
        private const val KEY_LNAME = "Lname"
        private const val KEY_USERNAME = "USERname"
        private const val KEY_EMAIL = "USER_Email"
        private const val KEY_PASSWORD = "USERpass"

    }

    override fun onCreate(db: SQLiteDatabase) {
        val queryUser =
            ("CREATE TABLE " + TABLE_USER + "(" + KEY_FNAME + " TEXT ," + KEY_LNAME + " TEXT ," + KEY_USERNAME + " TEXT ," + KEY_EMAIL + " TEXT PRIMARY KEY ," + KEY_PASSWORD + " TEXT" + ")")
        db.execSQL(queryUser)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_USER)
    }

    fun insertUser(userdata: UserData): Long {
        val values = ContentValues()
        values.put(KEY_FNAME, userdata.FNAME)
        values.put(KEY_LNAME, userdata.LNAME)
        values.put(KEY_USERNAME, userdata.USERNAME)
        values.put(KEY_EMAIL, userdata.EMAIL)
        values.put(KEY_PASSWORD, userdata.PASSWORD)

        val db = this.writableDatabase
        val l = db.insertWithOnConflict(TABLE_USER, null, values, SQLiteDatabase.CONFLICT_IGNORE)

        if (l == -1.toLong()) {
//            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Sucessfull", Toast.LENGTH_SHORT).show()

        }
        Log.e("DB", " insert val: $l")
//        db.close()
        return l
    }

    @SuppressLint("Recycle")
    fun getSingleUserbyEmail(userdata: UserData): UserData? {
        val user = UserData(EMAIL = String(), PASSWORD = String())
        val db = this.readableDatabase
        val em = userdata.EMAIL.toString()
        val ps = userdata.PASSWORD.toString()

        Log.e("queryy", "em=$em  and ps=$ps ",)
        val cursor = db.rawQuery(
            "select * from " + TABLE_USER + " WHERE $KEY_EMAIL = '$em' AND $KEY_PASSWORD='$ps' ",
            null,
            null
        )
        if (em.equals(user.EMAIL) && ps.equals(user.PASSWORD)) {

            if (cursor != null && cursor.count > 0) {

                cursor.moveToFirst()

//            var userdata= UserData()
                userdata.LNAME = cursor.getString(1).toString()
                userdata.USERNAME = cursor.getString(2).toString()
                userdata.EMAIL = cursor.getString(3).toString()
                userdata.PASSWORD = cursor.getString(4).toString()
//                Toast.makeText(context, "login $cursor", Toast.LENGTH_SHORT).show()
                cursor.close()
//                Log.e("in data", "em=$em  and ps=$ps ",)
                return userdata

            }
        } else {

            Log.e("Cursor", "Error ",)
            return null
        }



        return null
    }

    fun getuser(): Cursor? {

        val db = this.readableDatabase


        return db.rawQuery("select * from " + TABLE_USER + "", null)
    }






}