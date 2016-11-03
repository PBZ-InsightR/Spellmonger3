package edu.insightr.spellmonger;
import org.json.JSONObject;
import org.json.JSONWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

public class User {

    private String name;
    private String mdp;
    private int nbWin;
    private int nbGame;

    public User(String name, String mdp){
        this.name=name;
        this.mdp=mdp;
        this.nbWin=0;
        this.nbGame=0;
    }

    public String getUserName(){return name;}
    public String getMdp(){return mdp;}
    public void setNbWin(int nbWin){this.nbWin=nbWin;}
    public void setNbGame(int nbGame){this.nbGame=nbGame;}

    public int winRate(){
        int winRate = (nbWin/nbGame)*100;
        return winRate;
    }

    public void saveScore(){

        try {
            File file = new File("C:\\Users\\Numa\\IdeaProjects\\Spellmonger3\\Score.json");
            file.createNewFile();
            FileWriter writer = new FileWriter(file);

            JSONObject obj = new JSONObject();
            obj.put("name",this.name);
            obj.put("mdp",this.mdp);
            obj.put("win Rate",this.winRate());

            writer.write(obj.toString());
        }
        catch (Exception e){}





    }
}
