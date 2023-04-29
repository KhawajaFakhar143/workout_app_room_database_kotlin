package com.example.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.workoutapp.databinding.ActivityExerceBinding

class ExerceActivity : AppCompatActivity() {
    private var binding: ActivityExerceBinding? = null
    private var restProgress: CountDownTimer? = null
    private var restTimer: Int = 0
    private var exerciseProgress: CountDownTimer? = null
    private var exerciseTimer: Int = 0
    private var exerciseList : ArrayList<ExerciseModel>? = null
    private var currentExercisePosition = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerceBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolBarExercise)
        exerciseList = Constants.getExerciseList()
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolBarExercise?.setNavigationOnClickListener {
            onBackPressed()
        }
        binding?.flExerciseProgressBar?.visibility = View.GONE
        setupRestProgress()

    }

    private fun setupRestProgress() {
        if (restProgress != null) {
            restProgress?.cancel()
            restTimer = 0
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
                currentExercisePosition++
                Toast.makeText(
                    this@ExerceActivity,
                    "Start Exercise",
                    Toast.LENGTH_LONG
                ).show()
                setupExerciseProgress()
            }

        }.start()
    }

    private fun setupExerciseProgress() {
        binding?.flProgressBar?.visibility = View.INVISIBLE
        binding?.flExerciseProgressBar?.visibility = View.VISIBLE
        binding?.tvTitle?.text = "JUMPING JACK"
        if (exerciseProgress != null) {
            exerciseProgress?.cancel()
            exerciseTimer = 0
        }
        setExerciseProgress()
    }

    private fun setExerciseProgress() {
        exerciseProgress = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                exerciseTimer++
                binding?.tvExerciseRestProgress?.progress = 30 - exerciseTimer
                binding?.tvExerciseTimer?.text = (30 - exerciseTimer).toString()
            }

            override fun onFinish() {
                Toast.makeText(
                    this@ExerceActivity,
                    "Jumping Jack finished",
                    Toast.LENGTH_LONG
                ).show()
            }

        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        if (restProgress != null) {
            restProgress?.cancel()
            restTimer = 0
        }
        if (exerciseProgress != null){
            exerciseProgress?.cancel()
            exerciseTimer = 0
        }
    }
}