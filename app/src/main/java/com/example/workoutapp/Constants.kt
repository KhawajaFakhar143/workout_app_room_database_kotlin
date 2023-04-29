package com.example.workoutapp

object Constants {

    fun getExerciseList(): ArrayList<ExerciseModel> {
        var exerciseList: ArrayList<ExerciseModel> = ArrayList()

        val abdominalCrunch = ExerciseModel(1, "Abdominal Crunch", R.drawable.ic_abdominal_crunch)
        exerciseList.add(abdominalCrunch)
        val highKnees = ExerciseModel(2, "High Knees", R.drawable.ic_high_knees_running_in_place)
        exerciseList.add(highKnees)
        val jumpingJacks = ExerciseModel(3, "Jumping Jacks", R.drawable.ic_jumping_jacks)
        exerciseList.add(jumpingJacks)
        val lunge = ExerciseModel(4, "Lunge", R.drawable.ic_lunge)
        exerciseList.add(lunge)
        val plank = ExerciseModel(5, "Plank", R.drawable.ic_plank)
        exerciseList.add(plank)
        val pushUps = ExerciseModel(6, "Push Ups", R.drawable.ic_push_up)
        exerciseList.add(pushUps)
        val pushUpsAndRotation =
            ExerciseModel(7, "Push Ups and rotations", R.drawable.ic_push_up_and_rotation)
        exerciseList.add(pushUpsAndRotation)
        val sidePlank = ExerciseModel(8, "Side Plank", R.drawable.ic_side_plank)
        exerciseList.add(sidePlank)
        val squat = ExerciseModel(9, "Squats", R.drawable.ic_squat)
        exerciseList.add(squat)
        val stepUpOntoChair =
            ExerciseModel(10, "Step up onto Chair", R.drawable.ic_step_up_onto_chair)
         exerciseList.add(stepUpOntoChair)

        return exerciseList
    }
}