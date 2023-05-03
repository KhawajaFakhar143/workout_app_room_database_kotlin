package com.example.workoutapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.inflate
import com.example.workoutapp.databinding.RvListItemBinding

class ExerciseStatusAdaptor(val exerciseList: ArrayList<ExerciseModel>) :
    Adapter<ExerciseStatusAdaptor.ViewHolder>() {

    inner class ViewHolder(val binding: RvListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvExerciseNumber = binding.tvExerciseNumber

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RvListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val exercise = exerciseList[position]
        holder.tvExerciseNumber.text = exercise.getId().toString()
        when {
            exercise.getIsSelected() -> {
                holder.tvExerciseNumber.background = ContextCompat.getDrawable(
                    holder.itemView.context,
                    R.drawable.rv_active_exercise_list_item_bg
                    )
                holder.tvExerciseNumber.setTextColor(Color.BLACK)
            }
            exercise.getIsFinished() -> {
                holder.tvExerciseNumber.background = ContextCompat.getDrawable(
                    holder.itemView.context,
                    R.drawable.rv_completed_exercise_list_item_bg
                )
                holder.tvExerciseNumber.setTextColor(Color.WHITE)
            }
            else -> {

            }
        }
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }
}