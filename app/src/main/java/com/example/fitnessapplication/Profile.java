package com.example.fitnessapplication;

public class Profile {
 private int profileId;
 private String name;
 private int age;
 private double weight;
    private double goalWeight;
 private double height;


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

    public double getWeight() {

        return weight;
    }

    public void setWeight(double weight) {

        this.weight = weight;
    }
    public double getGoalWeight() {

        return goalWeight;
    }

    public void setGoalWeight(double goalWeight) {

        this.goalWeight = goalWeight;
    }

    public double getHeight() {

        return height;
    }

    public void setHeight(double height) {

        this.height = height;
    }

}
