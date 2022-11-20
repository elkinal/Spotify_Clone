package com.example.hm4_test

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SongDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        // The Song Details
        val position : Int = intent.getIntExtra("position", -1)

        val name : TextView = findViewById(R.id.detailedSongName)
        name.text = MainActivity.songList[position].name

        val artist : TextView = findViewById(R.id.detailedArtistName)
        artist.text = MainActivity.songList[position].artist

        val album : TextView = findViewById(R.id.detailedAlbumName)
        album.text = MainActivity.songList[position].album

        val cover : ImageView = findViewById(R.id.detailedCover)
        cover.setImageResource(MainActivity.songList[position].cover)


        // Initializing the Input Fields
        val editName : EditText = findViewById(R.id.editSongName)
        val editAlbum : EditText = findViewById(R.id.editAlbumName)
        val editArtist : EditText = findViewById(R.id.editArtistName)

        // Setting the placeholder text values for the EditTexts
        editName.setText(name.text)
        editAlbum.setText(artist.text)
        editArtist.setText(album.text)

        val saveButton : Button = findViewById(R.id.saveButton)
        saveButton.setOnClickListener {
            MainActivity.songList[position].name = editName.text.toString()
            MainActivity.songList[position].artist = editArtist.text.toString()
            MainActivity.songList[position].album = editAlbum.text.toString()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // TODO: User should be redirected back to the Main Activity
    }
}