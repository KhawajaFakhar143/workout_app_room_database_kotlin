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
        binding?.flProgressBar?.visibility = View.VISIBLE
        binding?.flExerciseProgressBar?.visibility = View.INVISIBLE
        binding?.tvTitle?.visibility = View.VISIBLE
        binding?.tvExerciseName?.visibility = View.INVISIBLE
        binding?.tvExerciseImage?.visibility = View.INVISIBLE
        binding?.tvUpComingExerciseNameText?.visibility = View.VISIBLE
        binding?.tvUpComingExercise?.visibility = View.VISIBLE
        binding?.tvUpComingExercise?.text = exerciseList!![currentExercisePosition+1].getName()
        if (restProgress != null) {
            restProgress?.cancel()
            restTimer = 0
        }
        setRestProgress()
    }

    private fun setRestProgress() {
        // binding?.tvRestProgress?.progress = restTimer

        restProgress = object : CountDownTimer(1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                restTimer++
                binding?.tvRestProgress?.progress = 10 - restTimer
                binding?.tvTimer?.text = (10 - restTimer).toString()
            }

            override fun onFinish() {
                currentExercisePosition++
                if(currentExercisePosition+1 <= exerciseList!!.size){
                    setupExerciseProgress()
                }

            }

        }.start()
    }

    private fun setupExerciseProgress() {
        binding?.flProgressBar?.visibility = View.INVISIBLE
        binding?.flExerciseProgressBar?.visibility = View.VISIBLE
        binding?.tvTitle?.visibility = View.INVISIBLE
        binding?.tvExerciseName?.visibility = View.VISIBLE
        binding?.tvExerciseName?.text = exerciseList!![currentExercisePosition].getName()
        binding?.tvExerciseImage?.visibility = View.VISIBLE
        binding?.tvUpComingExerciseNameText?.visibility = View.INVISIBLE
        binding?.tvUpComingExercise?.visibility = View.INVISIBLE
        binding?.tvExerciseImage?.setImageResource(exerciseList!![currentExercisePosition].getImage())
        if (exerciseProgress != null) {
            exerciseProgress?.cancel()
            exerciseTimer = 0
        }
        setExerciseProgress()
    }

    private fun setExerciseProgress() {
        exerciseProgress = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                exerciseTimer++
                binding?.tvExerciseRestProgress?.progress = 30 - exerciseTimer
                binding?.tvExerciseTimer?.text = (30 - exerciseTimer).toString()
            }

            override fun onFinish() {
                if(currentExercisePosition+1 < exerciseList!!.size){
                    setupRestProgress()
                }
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