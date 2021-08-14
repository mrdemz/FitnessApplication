package com.example.fitnessapplication;

public class ExerciseModel {

    private int id;
    private String calorieCount;
    private String exerciseName;

    public ExerciseModel(int id, String calorieCount, String exerciseName) {
        this.id = id;
        this.calorieCount = calorieCount;
        this.exerciseName = exerciseName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCalorieCount() {
        return calorieCount;
    }

    public void setCalorieCount(String calorieCount) {
        this.calorieCount = calorieCount;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    @Override
    public String toString() {
        return "ExerciseModel{" +
                "id=" + id +
                ", calorieCount=" + calorieCount +
                ", exerciseName='" + exerciseName + '\'' +
                '}';
    }
}
