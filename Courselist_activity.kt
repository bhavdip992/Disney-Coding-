package com.example.disneycoding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

private lateinit var videoArrayList: ArrayList<ModelVideo>
private lateinit var recycler:RecyclerView

class Courselist_activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courselist)
         recycler=findViewById(R.id.recycler)
        loadVideosFromFirebase()
    }

    private fun loadVideosFromFirebase() {
        videoArrayList= ArrayList()
        val ref=FirebaseDatabase.getInstance().getReference("Videos")
        ref.addValueEventListener(object :ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                videoArrayList.clear()
                for(ds in snapshot.children){
                    val modelVideo=ds.getValue(ModelVideo::class.java)
                    videoArrayList.add(modelVideo!!)

                }
               val adapterVideo=AdapterVideo(this@Courselist_activity, videoArrayList)
                recycler.adapter=adapterVideo


            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }

}