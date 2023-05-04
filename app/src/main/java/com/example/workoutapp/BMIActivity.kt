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
    private var binding: ActivityBmiactivityBinding? = null
    private var isUSUnitsActive: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiactivityBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolBarBMI)
        binding?.etMetricUnitWeight?.hint = "WEIGHT(in pounds)"
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "Calculate BMI"
        }
        binding?.toolBarBMI?.setNavigationOnClickListener {
            onBackPressed()
        }
        binding?.btnCalculateBMI?.setOnClickListener {
            calculateBMI()
        }

        binding?.rgMetrics?.setOnCheckedChangeListener { _, checkedId ->
            onChangedRadioButton(
                checkedId
            )
        }
    }

    private fun calculateBMI(){
        if (isUSUnitsActive) {
            if (validateUSUnits()){
                val weight = binding?.etMetricUnitWeight?.text.toString().toFloat()
                val height = ("${binding?.etFeet?.text.toString()}.${binding?.etInch?.text.toString()}")
                    .toFloat() * 12
                val bmi =( weight / (height * height))*703
                displayBMIResult(bmi)
            }else{
                Toast.makeText(
                    this, "Please Enter Valid Values in Units",
                    Toast.LENGTH_SHORT
                ).show()
            }

        } else {
            if (validateMetricUnit()) {
                val weight = binding?.etMetricUnitWeight?.text.toString().toFloat()
                val height = binding?.etMetricUnitHeight?.text.toString().toFloat() / 100

                val bmi = weight / (height * height)
                displayBMIResult(bmi)

            } else {
                Toast.makeText(
                    this, "Please Enter Valid Values in Metric",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun onChangedRadioButton(checkedId: Int) {

        if (checkedId == binding?.rbMetricsUnits?.id) {
            binding?.tiMetricUnitHeight?.visibility = View.VISIBLE
            binding?.etMetricUnitHeight?.visibility = View.VISIBLE
            binding?.llUsUnitsView?.visibility = View.INVISIBLE
            binding?.etMetricUnitWeight?.hint = "WEIGHT (in KG)"
            clearTextFields()
            isUSUnitsActive = false
        } else {
            binding?.etMetricUnitWeight?.hint ="WEIGHT (in pounds)"
            binding?.tiMetricUnitHeight?.visibility = View.INVISIBLE
            binding?.etMetricUnitHeight?.visibility = View.INVISIBLE
            binding?.llUsUnitsView?.visibility = View.VISIBLE
            clearTextFields()
            isUSUnitsActive = true
        }

    }

    private fun clearTextFields(){
        binding?.etMetricUnitWeight?.text?.clear()
        binding?.etFeet?.text?.clear()
        binding?.etInch?.text?.clear()
        binding?.etMetricUnitHeight?.text?.clear()
    }

    private fun displayBMIResult(bmi: Float) {
        val bmiLabel: String
        val bmiDescription: String

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

    private fun validateUSUnits(): Boolean {
        var isValid = true
        isValid = !(binding?.etMetricUnitWeight?.text.toString().isEmpty()
                || binding?.etFeet?.text.toString().isEmpty()
                || binding?.etInch?.text.toString().isEmpty())
        return isValid
    }


    private fun validateMetricUnit(): Boolean {
        var isValid = true
        isValid = !(binding?.etMetricUnitWeight?.text.toString().isEmpty()
                || binding?.etMetricUnitHeight?.text.toString().isEmpty())

        return isValid
    }
}