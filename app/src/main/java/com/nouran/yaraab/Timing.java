package com.nouran.yaraab;

public class Timing {
 private   String id; //id is only for giving each session a unique id
    private String date;
    private String time;
    private  String lecture;
    //note constructors public ;(
    //no-arg constructor-->used in reading values
    public Timing(){
    }
    //parameterized constructor--> to intialize(pass)the values


    public Timing(String id,String date, String time, String lecture) {
        this.id=id;
        this.date = date;
        this.time = time;
        this.lecture = lecture;
    }

    //Getters -->used in Reading the values


    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getLecture() {
        return lecture;
    }
}
