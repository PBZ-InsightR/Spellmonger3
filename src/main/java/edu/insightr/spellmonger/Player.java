package edu.insightr.spellmonger;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;

public class Player {
    private static final Logger logger = Logger.getLogger(SpellmongerApp.class);
    private String name;
    private int lifePoint;
    private int energy;
    private ArrayList<Creature> playerCreature;
    private Deck cardPool;
    private Deck discardPool;

    public Player(String name) {
        this.playerCreature = new ArrayList<>();
        this.cardPool = new Deck(name); // créer une cardPool avec des valeurs (voir constructeur de la classe Deck)
        this.discardPool = new Deck(); // créer une cardPoll vide (voir constructeur de la classe Deck)
        this.name = name;
        this.lifePoint = 20;
        this.energy = 0;
    }

    public Deck getCards() {
        return cardPool;
    }

    public Deck getDiscards() {
        return discardPool;
    }

    public void reCreateCardPool() // quand la cardPool d'un joueur est finie, on la renouvelle
    {
        cardPool = new Deck(discardPool.getCardPool());
        discardPool.clearCards();
    }

    public int size() {
        return cardPool.size();
    }

    public void addPlayerCreature(Card creature) {
        playerCreature.add((Creature) creature);
    }

    public void removePlayerCreature(Card creature) {
        if (playerCreature.contains(creature)) {
            playerCreature.remove(creature);
        }
    }

    public void sortCreatures() {
        Collections.sort(playerCreature);
    } //utilisé dans le systeme d'attaque

    public ArrayList<Creature> getPlayerCreature() {
        return playerCreature;
    }

    public void setPlayerCreature(ArrayList<Creature> newOne) {
        playerCreature = newOne;
    }

    public boolean isDead() {
        return lifePoint <= 0;
    }

    public int getLifePoint() {
        return lifePoint;
    }

    public void setLifePoint(int life) {
        lifePoint = life;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergyPoint(int ene) {
        energy = ene;
    }

    public void increaseEnergy() {
        energy++;
    }

    public void winner() {
        logger.info(this.toString() + " is the winner!!!\n");
    }


    public void attack(Player opponent) {
        ArrayList<Creature> myPlayerCreature = this.playerCreature;
        ArrayList<Creature> playerCreatureOpponent = opponent.getPlayerCreature();

        //for (int i = 0; i < this.getPlayerCreature().size(); i++) {
        for (int i = 0; i < myPlayerCreature.size(); i++) {

            if (!myPlayerCreature.isEmpty() && !playerCreatureOpponent.isEmpty())// si il y'a une creature des deux coté du board
            {
                int degat = myPlayerCreature.get(i).getEffect() - playerCreatureOpponent.get(i).getEffect(); // recup des dégats la diff entre la force des deux creatures

                if (degat == 0) // si les deux créature ont la même force les deux meurt
                {
                    logger.info(this.toString() + " " + myPlayerCreature.get(i).toString() + " and " + opponent.toString() + " " +
                           playerCreatureOpponent.get(i).toString() + " have the same strength and die both ");
                    myPlayerCreature.remove(i);
                    playerCreatureOpponent.remove(i);
                } else if (degat > 0)// si la creature du joueur courant est plus forte elle tue celle de l'adversaire
                {
                    logger.info(this.toString() + " " + myPlayerCreature.get(i).toString() + " still alive and " + opponent.toString() + " " + playerCreatureOpponent.get(i).toString() + "die");
                    playerCreatureOpponent.remove(i);
                } else // si la creature de l'opposant est plus forte elle tue celle du joueur courant
                {
                    logger.info(opponent.toString() + " " + playerCreatureOpponent.get(i).toString() + " still alive and " + this.toString() + " " + myPlayerCreature.get(i).toString() + "die");
                    myPlayerCreature.remove(i);
                }
            } else if (!myPlayerCreature.isEmpty() && playerCreatureOpponent.isEmpty())// si le board de l'opposant ne contient plus de creature les creatures du joueur courant attaque l'opposant
            {
                opponent.setLifePoint(opponent.getLifePoint() - myPlayerCreature.get(i).getEffect());
            } else// si le joueur courant n'a plus de creature et qu'il en reste à l'opposant l'attaque du joueur s'arrête
            {
                break;
            }
        }

        this.setPlayerCreature(myPlayerCreature);
        opponent.setPlayerCreature(playerCreatureOpponent);
    }


    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return " " + name + "(" + getLifePoint() + "pv|" + getEnergy() + "energy)";
    }
}
