package com.example.doourbest.Work;

public class homework {
    private int id;
    private String tName;
    private String wName;
    private String wContents;
    private String rPw;
    private String time;

    public homework(int id, String tName, String wName, String wContents, String rPw, String time) {
        this.id = id;
        this.tName = tName;
        this.wName = wName;
        this.wContents = wContents;
        this.rPw = rPw;
        this.time = time;
    }

    public homework() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String getwName() {
        return wName;
    }

    public void setwName(String wName) {
        this.wName = wName;
    }

    public String getwContents() {
        return wContents;
    }

    public void setwContents(String wContents) {
        this.wContents = wContents;
    }

    public String getrPw() {
        return rPw;
    }

    public void setrPw(String rPw) {
        this.rPw = rPw;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String toString() {
        return String.format("[%s] %s", tName, wName, wContents, rPw, time);
    }
}
