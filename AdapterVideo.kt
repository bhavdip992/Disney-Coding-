package com.example.disneycoding

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class AdapterVideo(private var context: Context, private var videoArray: ArrayList<ModelVideo>?) :
    RecyclerView.Adapter<AdapterVideo.HolderVideo>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderVideo {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_rec, parent, false)
        return HolderVideo(view)
    }

    override fun onBindViewHolder(holder: HolderVideo, position: Int) {
        val modelVideo = videoArray!![position]
        val id: String? = modelVideo.id
        val title: String? = modelVideo.title
        val timestamp: String? = modelVideo.timestamp
        val videoUri: String? = modelVideo.videoUri

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp!!.toLong()
        val formatteDateTime =
            android.text.format.DateFormat.format("dd/MM/yyyy K:mm a", calendar).toString()

        holder.titleTv.text = title
        setVideoUrl(modelVideo, holder)

    }

    private fun setVideoUrl(modelVideo: ModelVideo, holder: HolderVideo) {
        holder.progressBar.visibility = View.VISIBLE

        val videoUrl: String? = modelVideo.videoUri

        val mediaController=MediaController(context)
        mediaController.setAnchorView(holder.videoView)
        val videoUri= Uri.parse(videoUrl)
        holder.videoView.setMediaController(mediaController)
        holder.videoView.setVideoURI(videoUri)
        holder.videoView.requestFocus()
        holder.videoView.setOnPreparedListener{mediaPlayer ->
            mediaPlayer.start()


        }
        holder.videoView.setOnInfoListener(MediaPlayer.OnInfoListener{mp,what,extra->
        when(what){
            MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START->{
                holder.progressBar.visibility=View.VISIBLE
                return@OnInfoListener  true

            }
            MediaPlayer.MEDIA_INFO_BUFFERING_START->{
                holder.progressBar.visibility=View.VISIBLE
                return@OnInfoListener true
            }
            MediaPlayer.MEDIA_INFO_BUFFERING_END->
            {
                holder.progressBar.visibility=View.GONE
                return@OnInfoListener true

            }
        }
        false
    })
        holder.videoView.setOnCompletionListener { mediaPlayer->
            mediaPlayer.start()
        }
    }

    override fun getItemCount(): Int {
        return videoArray!!.size
    }

    class HolderVideo(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var videoView: VideoView = itemView.findViewById(R.id.vi_video)
        var titleTv: TextView = itemView.findViewById(R.id.title_txt)
        var progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)
    }
}