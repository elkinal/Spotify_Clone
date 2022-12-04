package com.example.hm4_test


import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage


class SongAdapter (
    val songList : List<Song>,
    val context : Context
) : RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    class SongViewHolder(view : View) : ViewHolder(view) {
        val songName : TextView = view.findViewById(R.id.songName)
        val artistName : TextView = view.findViewById(R.id.artistName)
        val albumName : TextView = view.findViewById(R.id.albumName)
        val albumCover : ImageView = view.findViewById(R.id.albumCover)

        val cardView : CardView = view.findViewById(R.id.cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.song_cell, parent, false)
        return SongViewHolder(view)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song : Song = songList[position]

        holder.songName.text = song.name
        holder.artistName.text = song.artist
        holder.albumName.text = song.album

        // TODO: load background using Glide
        // Downloading the image from the FireStore Database

        // Reference to an image file in Cloud Storage
        val storageRef = Firebase.storage.reference
        val pathReference = storageRef.child("sample.jpg")

        val imageref = Firebase.storage.reference.child("sample.jpg")
        imageref.downloadUrl.addOnSuccessListener {Uri->
            val imageURL = Uri.toString()
            Glide.with(context)
                .load(imageURL)
                .into(holder.albumCover)
        }

/*        Glide.with(this.context)
//            .load("http://via.placeholder.com/300.png")
            .load("https://www.alexeyelkin.com/sample.jpg")
            .centerCrop()
            .into(holder.albumCover)*/

        holder.cardView.setOnClickListener {
//            Log.d("onclicks", "position: " + position)
            var intent = Intent(context, SongDetails::class.java)
            intent.putExtra("position", position)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return songList.size
    }


}