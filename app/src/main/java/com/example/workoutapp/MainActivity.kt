package com.example.workoutapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.FrameLayout
import android.widget.Toast
import com.example.workoutapp.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private var binding: ActivityMainBinding? = null
    private var tts: TextToSpeech? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        tts = TextToSpeech(this, this)
        binding?.flStart?.setOnClickListener {
            tts?.speak("Take rest for 10 seconds", TextToSpeech.QUEUE_FLUSH, null,"" )
            val intent = Intent(this, ExerceActivity::class.java)
            startActivity(intent)
        }
        binding?.flBMIActivity?.setOnClickListener {
            val intent = Intent(this, BMIActivity::class.java)
            startActivity(intent)
        }

        binding?.flHistoryActivity?.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts?.language = Locale.ENGLISH
        } else {
            Toast.makeText(this, "Failed To initialized Text to Speech", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if(tts != null){
            tts!!.shutdown()
            tts!!.stop()
        }
    }
}