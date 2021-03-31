package com.example.universitybazaarsystem;

public class Clubs {
    private String student_ID, c_name, c_desc;
    private byte[] club_img;

    public Clubs(String student_ID, String c_name, String c_desc,  byte[] club_img) {
        this.student_ID = student_ID;
        this.c_name = c_name;
        this.c_desc = c_desc;
        this.club_img = club_img;

    }

    public String getStudent_ID(){
        return student_ID;
    }
    public void setStudent_ID(String student_ID){
        this.student_ID = student_ID;
    }

    public String getC_name(){
        return c_name;
    }
    public void setC_name(String c_name){
        this.c_name = c_name;
    }

    public String getC_desc(){
        return  c_desc;
    }

    public void setC_desc(String c_desc) {
        this.c_desc = c_desc;
    }

    public byte[] getClub_img() {
        return club_img;
    }

    public void setClub_img(byte[] club_img) {
        this.club_img = club_img;
    }
}
