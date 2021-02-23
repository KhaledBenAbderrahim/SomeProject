package de.smartcrew.eatforfitserver.constants;

public enum Sex {
    M("M"), W("W"), D("D"), NOTSET("NOTSET");

    private String sID;

    private Sex(String sID) {
        this.sID = sID;
    }

    public String getSex(){
        return sID;
    }
}