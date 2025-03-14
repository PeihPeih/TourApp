package com.example.tour.model;

public class Tour {
    private int img;
    private String name, duration;

    public Tour() {

    }

    public Tour(int img, String duration, String name) {
        this.img = img;
        this.duration = duration;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getDuration(){
        return this.duration;
    }

    public void setDuration(String duration){
        this.duration = duration;
    }
}
