package com.example.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.example.workoutapp.databinding.ActivityExerceBinding

class ExerceActivity : AppCompatActivity() {
    private var binding: ActivityExerceBinding? = null
    private var restProgress: CountDownTimer? = null
    private var restTimer: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerceBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolBarExercise)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolBarExercise?.setNavigationOnClickListener {
            onBackPressed()
        }
        setRestProgress()
    }

    private fun setRestProgress() {
       // binding?.tvRestProgress?.progress = restTimer

        restProgress = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                restTimer++
                binding?.tvRestProgress?.progress = 10 - restTimer
                binding?.tvTimer?.text = (10 - restTimer).toString()
            }

            override fun onFinish() {
                Toast.makeText(
                    this@ExerceActivity,
                    "Now we will start the exercise",
                    Toast.LENGTH_LONG
                ).show()
            }

        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding =null
        if(restProgress != null){
            restProgress?.cancel()
        }
    }
}