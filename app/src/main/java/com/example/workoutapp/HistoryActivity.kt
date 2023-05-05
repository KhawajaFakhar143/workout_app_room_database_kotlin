package com.example.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workoutapp.databinding.ActivityHistoryBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryActivity : AppCompatActivity() {
    private var binding : ActivityHistoryBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.tbHistory)
        if(supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.tbHistory?.setNavigationOnClickListener {
            onBackPressed()
        }
        val dao = (application as WorkOutApp).db.getHistoryDao()
        getHistoryOfExercise(dao)

    }
    private fun getHistoryOfExercise(dao: HistoryDao){
        lifecycleScope.launch{
           dao.getData().collect{
               var historyList = ArrayList(it)
               val historyAdaptor =HistoryAdaptor(historyList)
               withContext(Dispatchers.Main){
                   binding?.rvHistory?.layoutManager = LinearLayoutManager(
                       this@HistoryActivity,LinearLayoutManager.VERTICAL,false)
                   binding?.rvHistory?.adapter = historyAdaptor
               }
           }

        }
    }
}