package com.example.workoutapp

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {

    @Insert
     fun insert(historyEntity: HistoryEntity)

     @Query("SELECT * from history_table")
     fun getData(): Flow<List<HistoryEntity>>


}