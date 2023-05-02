package com.example.workoutapp

class ExerciseModel(
    private var id: Int,
    private var name: String,
    private var image: Int,
    private var isSelected: Boolean = false,
    private var isFinished: Boolean = false
) {
    fun getId() : Int{
        return  this.id
    }

    fun setInt(id : Int) {
        this.id = id
    }

    fun getName() : String{
        return this.name
    }
    fun setName(name :String) {
        this.name = name
    }

    fun getImage(): Int {
        return this.image
    }

    fun setImage(image : Int) {
        this.image =image
    }

    fun getIsSelected() : Boolean{
        return  this.isSelected
    }

    fun setIsSelected(isSelected: Boolean){
        this.isSelected = isSelected
    }

    fun getIsFinished() : Boolean{
        return this.isFinished
    }

    fun setIsFinished(isFinished: Boolean){
        this.isFinished = isFinished
    }
}