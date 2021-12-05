package com.example.myapplication;

public class Musician {
    private String name;
    private int imageId;
    public Musician(String name, int imageId){
        this.name = name;
        this.imageId = imageId;
    }
    public String getName(){
        return name;
    }
    public int getImageId(){
        return imageId;
    }
}
