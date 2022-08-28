package com.example.disneycoding.Teacher

import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.disneycoding.R
//import com.example.disneycoding.databinding.ActivityTeacherregBinding
import com.example.disneycoding.util.MySharedPref
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.retorfit.disneycoding.Login
import java.lang.StringBuilder
import android.webkit.MimeTypeMap

import android.content.ContentResolver
import android.content.pm.PackageManager
import android.provider.MediaStore
import android.text.TextUtils
import android.widget.*
import com.google.firebase.firestore.OnProgressListener

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.tasks.OnFailureListener

import com.google.firebase.database.DatabaseReference

import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


class MainActivity : AppCompatActivity() {
    lateinit var upload: Button
    lateinit var profile: Button
    lateinit var upl_type: FloatingActionButton
    lateinit var getdata: TextView
    lateinit var titleEt: TextView
    lateinit var prog: ProgressBar
    lateinit var videoview: VideoView


    //    for request camera and gallary
    private val VIDEO_PICK_GALLARY_CODE = 100
    private val VIDEO_PICK_CAMERA_CODE = 101
    private val CAMERA_REQUEST_CODE = 102
    private lateinit var cameraPermission: Array<String>
    private var title: String = "";
    private var videoUri: Uri? = null

    //    progeressbar
    private lateinit var progressDialog: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val mySharedPref = MySharedPref(applicationContext)
        upload = findViewById(R.id.Upl)
        profile = findViewById(R.id.Profile)
        titleEt = findViewById(R.id.settitle)
        upl_type = findViewById(R.id.upl_type)
        videoview = findViewById(R.id.videoview)
        val tea_logout = findViewById<Button>(R.id.tea_logout)
        getdata = findViewById(R.id.prl)
        prog = findViewById<ProgressBar>(R.id.pr_main)
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Uploading Video...")
        progressDialog.setCanceledOnTouchOutside(false)


//get camera permision
        cameraPermission = arrayOf(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        val data = FirebaseDatabase.getInstance().reference

        val email = mySharedPref.getUserProfile()

        val value = email

        Log.e("email", "${value} ")
        getdata.setText("hii")
        profile.setOnClickListener {
            prog.setVisibility(View.VISIBLE)

            val getdb = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val sb = StringBuilder()
                    for (i in snapshot.children) {
                        val db = i.child(email)
                        val tea_fname = db.child("first_Name").getValue()
                        val tea_lname = db.child("last_Name").getValue()
                        val tea_pass = db.child("teacher_password").getValue()
                        val tea_name = db.child("email_Id").getValue()
                        sb.append("${i.key},\n" + "First Name=" + "$tea_fname,\n" + "Last Name=" + "$tea_lname,\n" + "Password Name=" + "$tea_pass,\n" + "User Email ID=" + "$tea_name ")
//                        getdata.text = "$tea_fname"
//                        getdata.text = "$tea_fname"
//                        getdata.text = "$tea_fname"
//                        getdata.text = "$tea_fname"
//                        getdata.text = "$tea_fname"

                    }
//                    sb.append("hiii")
                    getdata.text = sb
                    prog.setVisibility(View.INVISIBLE)


                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@MainActivity, "Error $error", Toast.LENGTH_SHORT).show()

                }

            }
            Toast.makeText(this@MainActivity, "Profile", Toast.LENGTH_LONG).show()
            data.addValueEventListener(getdb)
            data.addListenerForSingleValueEvent(getdb)

        }


        tea_logout.setOnClickListener {
            val prefManager: SharedPreferences =
                getSharedPreferences(MySharedPref.KEY_SHARED_PREE_NAME_FIRST_TIME, 0)
            val editor: SharedPreferences.Editor = prefManager.edit()

            val mySharedPref = MySharedPref(applicationContext)

            mySharedPref.setteacherisfirsttime(false)
            editor.clear()
            editor.apply()

            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()
            intent = Intent(this@MainActivity, Login::class.java)
            startActivity(intent)
            finish()
        }

        upl_type.setOnClickListener {
            videoPickDialog()
        }

        upload.setOnClickListener {
            title = titleEt.text.toString().trim()
            if (TextUtils.isEmpty(title)) {
//                no title entered
                Toast.makeText(this@MainActivity, "title is Rerquired", Toast.LENGTH_SHORT).show()
            } else if (videoUri == null) {
//                video is not picked
                Toast.makeText(this@MainActivity, "pick the video", Toast.LENGTH_SHORT).show()

            } else {
//                title entered and video is picked
//                uploadvideo()
                upoadVideoFirebase()
            }


        }


    }

    private fun upoadVideoFirebase() {
        progressDialog.show()
//        timestamp
        val timestamp = "" + System.currentTimeMillis()
//        file path
        val filePathAndName = "Videos/video_$timestamp"
//            storage reference
        val storageReference = FirebaseStorage.getInstance().getReference(filePathAndName)

//        upload video
        if (videoUri != null) {
            storageReference.putFile(videoUri!!)
                .addOnSuccessListener { taskSnapshot ->
//                uploaded
                    val uriTask = taskSnapshot.storage.downloadUrl
                    while (!uriTask.isSuccessful);
                    val downloadUri = uriTask.result
                    if (uriTask.isSuccessful) {
//                        video view receved successful
                        val hashMap = HashMap<String, Any>()
                        hashMap["id"] = "$timestamp"
                        hashMap["title"] = "$title"
                        hashMap["timestamp"] = "$timestamp"
                        hashMap["videoUri"] = "$downloadUri"

//                        put the above info to db
                        val dbreference = FirebaseDatabase.getInstance().getReference("Videos")
                        dbreference.child(timestamp).setValue(hashMap)
                            .addOnSuccessListener { taskSnapshot ->
                                progressDialog.dismiss()

                                Log.e("work", "uploaded: ")
                                Toast.makeText(this, "Video Uploaded", Toast.LENGTH_SHORT).show()
//                                video info added Successfully
                                titleEt.setText("")

                            }

                            .addOnFailureListener { e ->
//                                failed to add video
                                progressDialog.dismiss()
                                Toast.makeText(this, "${e.message}", Toast.LENGTH_SHORT).show()
                                Log.e("failure", "not upload:${e.message} ")
                            }

                    }
                }



                .addOnFailureListener { e ->
//                    Failed to upload
                    progressDialog.dismiss()
                    Toast.makeText(this@MainActivity, "${e.message}", Toast.LENGTH_SHORT).show()

                }
                .addOnProgressListener {

                    // show the progress bar
                    val progres = (100 * it.bytesTransferred / it.totalByteCount)
                    progressDialog.setMessage("Uploaded " + progres + "%");

                }
        } else {

            progressDialog.dismiss()
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
        }

    }

    private fun setVideoToVideoView() {
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoview)

//        set video
        videoview.setMediaController(mediaController)
        videoview.setVideoURI(videoUri)
        videoview.requestFocus()
        videoview.setOnPreparedListener {
//            when video is ready,  by default not play
//            videoview.pause()
            videoview.display


        }

    }


    //upl_type function
    private fun videoPickDialog() {
        val options = arrayOf("Camera", "Gallery")

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Pick video From").setItems(options) { dialogInterface, i ->

            if (i == 0) {
//Camera
                if (!checkCameraPermission()) {
                    requestCameraPermission()

                } else {
                    videoPickCamera()
                }
            } else {
//                Gallery
                videoPickGallary()
            }
        }.show()

    }

    //    request camera permission
    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(this, cameraPermission, CAMERA_REQUEST_CODE)
    }


    private fun checkCameraPermission(): Boolean {
        val result1 = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
        val result2 = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
        return result1 && result2
    }


    private fun videoPickGallary() {
        val intent = Intent()
        intent.type = ("video/*")
        intent.action = Intent.ACTION_PICK
        intent.action = (Intent.ACTION_GET_CONTENT)
        startActivityForResult(
            Intent.createChooser(intent, "Choose Video"),
            VIDEO_PICK_GALLARY_CODE
        )
    }

    private fun videoPickCamera() {
        val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        startActivityForResult(intent, CAMERA_REQUEST_CODE)
    }

    //handle permission result
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            CAMERA_REQUEST_CODE ->
                if (grantResults.size > 0) {
                    val cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED
                    val storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED
                    if (cameraAccepted && storageAccepted) {
//                    both permission allowed
                        videoPickCamera()

                    } else {
//both or one denied
                        Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                    }
                }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

//    handle video pick result


    override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null) {
//       video is picked from camera or gallery
            if (requestCode == VIDEO_PICK_CAMERA_CODE) {
//                video Picked From Camera
                videoUri = data.data
                setVideoToVideoView()
            } else if (requestCode == VIDEO_PICK_GALLARY_CODE) {
                videoUri = data.data
                setVideoToVideoView()
            }

        } else {
            Toast.makeText(this, " Canclled", Toast.LENGTH_SHORT).show()
        }

    }
}


//*****************
//    private fun uploadvideo() {
//        if (videoUri != null) {
//            // save the selected video in Firebase storage
//           val reference = FirebaseStorage.getInstance().getReference("Files/" + System.currentTimeMillis() + "." + getfiletype(videoUri));
//            reference.putFile(videoUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
//                    while (!uriTask.isSuccessful()) ;
//                    // get the link of video
//                    String downloadUri = uriTask.getResult().toString();
//                    DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Video");
//                    HashMap<String, String> map = new HashMap<>();
//                    map.put("videolink", downloadUri);
//                    reference1.child("" + System.currentTimeMillis()).setValue(map);
//                    // Video uploaded successfully
//                    // Dismiss dialog
//                    progressDialog.dismiss();
//                    Toast.makeText(MainActivity.this, "Video Uploaded!!", Toast.LENGTH_SHORT).show();
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    // Error, Image not uploaded
//                    progressDialog.dismiss();
//                    Toast.makeText(MainActivity.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                // Progress Listener for loading
//                // percentage on the dialog box
//                @Override
//                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//                    // show the progress bar
//                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
//                    progressDialog.setMessage("Uploaded " + (int) progress + "%");
//                }
//            });
//        }

