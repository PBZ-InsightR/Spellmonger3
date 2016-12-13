package edu.insightr.spellmonger;


public enum TypeOfCard {
    RITUAL("RITUAL"),
    CREATURE("CREATURE"),
    VAULTOVERCLOCKING("VAULTOVERCLOCKING");

    private String type = "";

    TypeOfCard(String name){
        this.type = name;
    }

    public String toString(){
        return type;
    }
}
