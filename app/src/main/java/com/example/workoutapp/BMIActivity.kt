package com.example.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.workoutapp.databinding.ActivityBmiactivityBinding
import com.example.workoutapp.databinding.ActivityExerceBinding
import com.example.workoutapp.databinding.ActivityMainBinding
import java.math.BigDecimal
import java.math.RoundingMode

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
        binding?.btnCalculateBMI?.setOnClickListener {
            if(validateMetricUnit()){
            val weight = binding?.etMetricUnitWeight?.text.toString().toFloat()
                val height = binding?.etMetricUnitHeight?.text.toString().toFloat()/100

                val bmi = weight/(height*height)
                displayBMIResult(bmi)

            }else{
                Toast.makeText(
                    this,"Please Enter Valid Values",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun displayBMIResult(bmi: Float){
        val bmiLabel : String
        val bmiDescription : String

        if (bmi.compareTo(15f) <= 0) {
            bmiLabel = "Very severely underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0
        ) {
            bmiLabel = "Severely underweight"
            bmiDescription = "Oops!You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0
        ) {
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0
        ) {
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape!"
        } else if (bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0
        ) {
            bmiLabel = "Overweight"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0
        ) {
            bmiLabel = "Obese Class | (Moderately obese)"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0
        ) {
            bmiLabel = "Obese Class || (Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        } else {
            bmiLabel = "Obese Class ||| (Very Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        }

        val bmiValue = BigDecimal(bmi.toDouble())
            .setScale(2, RoundingMode.HALF_EVEN).toString()
        binding?.llTextView?.visibility = View.VISIBLE
        binding?.tvCalculatedBMI?.text = bmiValue
        binding?.tvBMIResultText?.text = bmiLabel
        binding?.tvWorkoutMotivateText?.text = bmiDescription
    }


    private fun validateMetricUnit() : Boolean{
        var isValid = false
        if(binding?.etMetricUnitWeight?.text.toString().isEmpty()){
            isValid = false
        }else if(binding?.etMetricUnitHeight?.text.toString().isEmpty()){
            isValid = false
        }else{
            isValid = true
        }
        return isValid
    }
}