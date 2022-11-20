package com.example.hm4_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    companion object songs {
        val songList = mutableListOf(
            Song("Gangsta Gangsta", "Straight Outta Compton", "NWA", R.drawable.cover1),
            Song("Purple Haze", "Are you Experienced", "Jimmy Hendrix", R.drawable.cover2),
            Song("Rainbows", "In Rainbows", "Radiohead", R.drawable.cover3),
            Song("Beat It", "Thriller", "Michael Jackson", R.drawable.cover4),
            Song("Lovely Rita", "Sgt. Pepper's Lonely Hearts Club Band", "The Beatles", R.drawable.cover5),
            Song("Shine on you Crazy Diamond", "Wish you were here", "Pink Floyd", R.drawable.cover6),
            Song("God only Knows", "Pet Sounds", "The Beach Boys", R.drawable.cover7),
            Song("Eclipse", "The Dark side of the Moon", "Pink Floyd", R.drawable.cover8),
            Song("Time", "Aladdin Sane", "David Bowie", R.drawable.cover9),
            Song("Fearless", "Echoes", "Pink Floyd", R.drawable.cover10)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView : RecyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = SongAdapter(songList, this)
    }
}