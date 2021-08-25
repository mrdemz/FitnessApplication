package com.example.fitnessapplication;

public class Pedometer {
    private int pedId;
    private int stepCount;
    private String date;
    private String answer;



    public Pedometer() {
        pedId = -1;
    }

    public int getPedId() {

        return pedId;
    }

    public void setPedId(int pedId) {
        this.pedId = pedId;
    }


    public void setStepCount(int stepCount) {

        this.stepCount = stepCount;
    }
    public int getstepCount() {

        return stepCount;
    }

    public void setDate(String date) {

        this.date = date;
    }
    public String getDate() {

        return date;
    }
    public void setAnswer(String answer) {

        this.answer = answer;
    }
    public String getAnswer() {

        return answer;
    }
}
