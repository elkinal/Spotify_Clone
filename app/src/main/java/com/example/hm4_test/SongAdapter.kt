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
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

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
        holder.albumCover.setBackgroundResource(song.cover)

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