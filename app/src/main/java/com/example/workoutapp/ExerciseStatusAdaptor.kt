package com.example.workoutapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.inflate
import com.example.workoutapp.databinding.RvListItemBinding

class ExerciseStatusAdaptor(val exerciseList: ArrayList<ExerciseModel>) :
    Adapter<ExerciseStatusAdaptor.ViewHolder>() {

    inner class ViewHolder(var binding: RvListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun itemBinding(exercise: ExerciseModel) {
          binding.tvExerciseNumber.text = exercise.getId().toString()
        }
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

        holder.itemBinding(exercise)

    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }
}