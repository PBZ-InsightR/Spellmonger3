package edu.insightr.spellmonger;

import edu.insightr.sample.Personne;

import java.util.Map;


public class Main {

    public static void main(String[] args){

       // Map<String, Personne> m = Outils.readFileToMap(System.getProperty("user.dir")+"/src/main/resources/scoresb.json");
        //Outils.createFilewithMap(m,System.getProperty("user.dir")+"/src/main/resources/scores.json");
        Outils.updateJsonFile("Lounes",false);
    }

}
