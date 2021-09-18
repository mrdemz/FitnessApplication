package com.example.fitnessapplication;

public class Profile {
    private String name;
    private int profileId;
    private int gender;
    private int age;
    private int activity;
    private double weight;
    private int fitness_goal;
    private double height;
    private int stepsGoal;




    public Profile() {
        profileId = -1;
    }

    public int getProfileId() {

        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {

        return age;
    }

    public void setAge(int age) {

        this.age = age;
    }


    public int getGender() {

        return gender;
    }

    public void setGender(int gender) {

        this.gender = gender;
    }



    public double getWeight() {

        return weight;
    }

    public void setWeight(double weight) {

        this.weight = weight;
    }
    public double getGoalWeight() {

        return fitness_goal;
    }

    public void setGoalWeight(int fitness_goal) {

        this.fitness_goal = fitness_goal;
    }

    public double getHeight() {

        return height;
    }

    public void setHeight(double height) {

        this.height = height;
    }

    public int getActivity() {

        return activity;
    }

    public void setActivity(int activity) {

        this.activity = activity;
    }

    public int getStepsGoal() {

        return stepsGoal;
    }

    public void setStepsGoal(int stepsGoal) {

        this.stepsGoal = stepsGoal;
    }



}
