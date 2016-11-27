package edu.insightr.spellmonger;

import javafx.beans.property.SimpleStringProperty;

// Class faite spécialement pour les scores.
// User est un Player
public class User implements Comparable {
    private final SimpleStringProperty login;
    private final SimpleStringProperty nbPlay;
    private final SimpleStringProperty scorePercent;

    User(String login, double nbPlay, double scorePercent) {
        this.login = new SimpleStringProperty(login);
        this.nbPlay = new SimpleStringProperty((int) nbPlay + "");
        this.scorePercent = new SimpleStringProperty(scorePercent + "");
    }

    @Override
    public int compareTo(Object other) {
        if (other instanceof User) {
            User otherParsed = (User) other;
            if (Double.parseDouble(this.getNbPlay()) > Double.parseDouble(otherParsed.getNbPlay())) return 1;
            else if (Double.parseDouble(this.getNbPlay()) < Double.parseDouble(otherParsed.getNbPlay())) return -1;
            else return 0;
        }
        return -2;
    }

    public SimpleStringProperty loginProperty() {
        return login;
    }

    String getNbPlay() {
        return nbPlay.get();
    }

    public SimpleStringProperty nbPlayProperty() {
        return nbPlay;
    }

    void setNbPlay(String nbPlay) {
        this.nbPlay.set(nbPlay);
    }

    String getScorePercent() {
        return scorePercent.get();
    }

    public SimpleStringProperty scorePercentProperty() {
        return scorePercent;
    }

    void setScorePercent(String scorePercent) {
        this.scorePercent.set(scorePercent);
    }
}
