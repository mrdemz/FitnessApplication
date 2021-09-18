package com.example.fitnessapplication;

public class Exercise {
    private String exerciseName;
    private String date;
    private int exerciseId;


    public Exercise() {
        exerciseId = -1;
    }

    public int getProfileId() {

        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getExerciseName() {

        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {

        this.exerciseName = exerciseName;
    }
    public void setDate(String date) {

        this.date = date;
    }
    public String getDate() {

        return date;
    }



}
