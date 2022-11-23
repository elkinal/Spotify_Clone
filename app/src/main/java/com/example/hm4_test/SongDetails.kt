package com.example.hm4_test

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class SongDetails : AppCompatActivity() {
    lateinit var music : MediaPlayer

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

        // Code responsible for playing the music file itself
        val playButton = findViewById<ImageButton>(R.id.playButton)
        val pauseButton = findViewById<ImageButton>(R.id.pauseButton)
        val stopButton = findViewById<ImageButton>(R.id.stopButton)



        // Getting music from the internet
        music = MediaPlayer()
        music.setAudioStreamType(AudioManager.STREAM_MUSIC)

        // The seekbar representing the song progress
        val seekBar : SeekBar = findViewById(R.id.songProgress)

        // Making the progress bar update every second
        var handler = Handler(Looper.getMainLooper())
        handler.postDelayed(object : Runnable {
            override fun run() {
                try {
                    seekBar.progress = music.currentPosition
                    handler.postDelayed(this, 1000)
                } catch (e: Exception) {
                    seekBar.progress = 0
                }
            }
        }, 0)


        var loaded = false

        playButton.setOnClickListener{
            if (!loaded) {
                music.setDataSource("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3")
                music.prepare()
                loaded = true
                seekBar.max = music.duration
            }
            music.start()
        }
        pauseButton.setOnClickListener {
            Log.d("music", music.currentPosition.toString())
            Log.d("music", music.duration.toString())
            music.pause()
        }
        stopButton.setOnClickListener {
            music.pause()
            music.seekTo(0)
            // stop, reset, release, =null
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // Allows the user to change the seekbar value dynamically
                if (fromUser) {
                    Log.d("music", "stfu")
                    music.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })




        // TODO: User should be redirected back to the Main Activity
    }
}