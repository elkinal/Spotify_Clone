package com.example.hm4_test

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
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
        val imageRef = Firebase.storage.reference.child(MainActivity.songList[position].cover)
        imageRef.downloadUrl.addOnSuccessListener { Uri->
            val imageURL = Uri.toString()
            Glide.with(this)
                .load(imageURL)
                .into(cover)
        }

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
//                    seekBar.progress = music.currentPosition
                    seekBar.progress = music.currentPosition
//                    Log.d("music", music.currentPosition.toString())
//                    Log.d("music", seekBar.progress.toString())
                    handler.postDelayed(this, 1000)
                } catch (e: Exception) {
                    seekBar.progress = 0
                }
            }
        }, 0)


        var loaded = false

        //TODO: Load the song to be played using (-> from firestore by URL)

        playButton.setOnClickListener{
            if (!loaded) {

                // Loading the mp3 file from Firestore
                val storage = FirebaseStorage.getInstance()
                storage.reference.child(MainActivity.songList[position].path
                ).downloadUrl.addOnSuccessListener {
                    music.setDataSource(it.toString())
                    music.setOnPreparedListener { player ->
                        player.start()
                        seekBar.max = player.duration
                    }
                    music.prepareAsync()
                }




//                music.setDataSource("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3")
//                music.prepare()
                loaded = true
//                seekBar.max = music.duration
//                Log.d("music", "duration: " + music.duration.toString())
            }
            music.start()
        }
        pauseButton.setOnClickListener {
            Log.d("music", music.currentPosition.toString())
            Log.d("music", music.duration.toString())
            music.pause()
        }
        stopButton.setOnClickListener {
            music.reset()
            loaded = false;
//            music.seekTo(0)
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