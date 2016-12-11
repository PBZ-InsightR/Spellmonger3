package edu.insightr.spellmonger;


public enum TypeOfCard {
    RITUAL("RITUAL"),
    CREATURE("CREATURE"),
    ENCHANTMENT("ENCHANTMENT");

    private String type = "";

    TypeOfCard(String name){
        this.type = name;
    }

    public String toString(){
        return type;
    }
}
