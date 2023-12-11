package com.example.doourbest.Model;

public class team {
    String teamname;
    String subname;
    String pw;


    public team(String teamname, String subname, String pw) {
        this.teamname = teamname;
        this.subname = subname;
        this.pw = pw;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
}
