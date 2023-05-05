package com.example.workoutapp

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.workoutapp.databinding.ActivityFinishBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class FinishActivity : AppCompatActivity() {
    private var binding: ActivityFinishBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        binding?.tbFinishActivity?.setNavigationOnClickListener {
            onBackPressed()
        }
        binding?.bFinishButton?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val historyDao = (application as WorkOutApp).db.getHistoryDao()
        addDateToDatabase(historyDao)

    }

    private fun addDateToDatabase(historyDao: HistoryDao) {
        val calendar = Calendar.getInstance()
        val time = calendar.time

        var sdf = SimpleDateFormat("EEE MMM d hh:mm:ss a", Locale.getDefault())
        val date = sdf.format(time)
        lifecycleScope.launch(Dispatchers.IO) {
            historyDao.insert(HistoryEntity(date))
            withContext(Dispatchers.Main) {
                Toast.makeText(this@FinishActivity, "History Saved locally", Toast.LENGTH_SHORT)
                    .show()
            }

        }

    }
}