package com.example.jcj.learningskid;

import java.io.Serializable;

public class Dictionary implements Serializable {

    int id;
    String vietNam;
    String english;
    String example;

    public Dictionary(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVietNam() {
        return vietNam;
    }

    public void setVietNam(String vietNam) {
        this.vietNam = vietNam;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public Dictionary(int id, String vietNam, String english, String example) {
        this.id = id;
        this.vietNam = vietNam;
        this.english = english;
        this.example = example;
    }
}
