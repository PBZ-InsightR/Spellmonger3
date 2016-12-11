package edu.insightr.spellmonger;


public enum Capacity {
    NOCAPACITY("NOCAPACITY"),
    DEATHTOUCH("DEATHTOUCH"),
    FLYING("FLYING"),
    CATCH("CATCH");

    private String capacity = "";

    Capacity(String name){
        this.capacity = name;
    }

    public String toString(){
        return capacity;
    }
}
