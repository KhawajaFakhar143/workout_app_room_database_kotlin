package com.example.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.workoutapp.databinding.ActivityExerceBinding

class ExerceActivity : AppCompatActivity() {
    private var binding : ActivityExerceBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerceBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolBarExercise)
        if(supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolBarExercise?.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}