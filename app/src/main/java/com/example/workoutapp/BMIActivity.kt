package com.example.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.workoutapp.databinding.ActivityBmiactivityBinding
import com.example.workoutapp.databinding.ActivityExerceBinding
import com.example.workoutapp.databinding.ActivityMainBinding

class BMIActivity : AppCompatActivity() {
    private var binding: ActivityBmiactivityBinding? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiactivityBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolBarBMI)
        println(supportActionBar != null)
        if(supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "Calculate BMI"
        }
        binding?.toolBarBMI?.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}