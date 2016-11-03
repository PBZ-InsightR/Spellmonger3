package edu.insightr.sample;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by hope on 02/11/2016.
 */
public class Personne implements Comparable{

    private final SimpleStringProperty Login;
    private final SimpleStringProperty NbPlay;
    private final SimpleStringProperty PourcentageScore;

    public Personne(String login, double nbPlay, double pourcentageScore) {
        this.Login = new SimpleStringProperty(login);
        this.NbPlay = new SimpleStringProperty((int)nbPlay+"");
        PourcentageScore = new SimpleStringProperty(pourcentageScore+"");
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Personne){
            Personne oo = (Personne) o;
            if(Double.parseDouble(this.getNbPlay()) > Double.parseDouble(oo.getNbPlay())) return 1;
            else if(Double.parseDouble(this.getNbPlay()) < Double.parseDouble(oo.getNbPlay())) return -1;
            else return 0;
        }
        return -2;
    }

    public String getLogin() {
        return Login.get();
    }

    public SimpleStringProperty loginProperty() {
        return Login;
    }

    public void setLogin(String login) {
        this.Login.set(login);
    }

    public String getNbPlay() {
        return NbPlay.get();
    }

    public SimpleStringProperty nbPlayProperty() {
        return NbPlay;
    }

    public void setNbPlay(String nbPlay) {
        this.NbPlay.set(nbPlay);
    }

    public String getPourcentageScore() {
        return PourcentageScore.get();
    }

    public SimpleStringProperty pourcentageScoreProperty() {
        return PourcentageScore;
    }

    public void setPourcentageScore(String pourcentageScore) {
        this.PourcentageScore.set(pourcentageScore);
    }
}
