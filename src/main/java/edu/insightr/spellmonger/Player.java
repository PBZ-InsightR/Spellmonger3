package edu.insightr.spellmonger;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;

public class Player {
    private static final Logger logger = Logger.getLogger(SpellmongerApp.class);
    private String name;
    private int lifePoint;
    private int energy;
    private Deck cardPool;
    private Deck discardPool;
    private boolean vaultOverclockingOnOff;
    private ArrayList<Creature> playerCreature;
    private ArrayList<Card> hand;
    private int stackEnergy;

    public Player(String name) {
        this.playerCreature = new ArrayList<>();
        this.cardPool = new Deck(name); // créer une cardPool avec des valeurs (voir constructeur de la classe Deck)
        this.discardPool = new Deck(); // créer une cardPoll vide (voir constructeur de la classe Deck)
        this.name = name;
        this.lifePoint = 20;
        this.energy = 0;
        this.stackEnergy = energy;
        hand = new ArrayList<>(3); // les cartes que le joueur en main
        addInitialCards();
        vaultOverclockingOnOff = false;
    }

    public int getStackEnergy(){return stackEnergy;}

    public void setStackEnergy(int stackEnergy){this.stackEnergy=stackEnergy;}

    private void addInitialCards() {
        addToHand(cardPool.get(0));
        addToHand(cardPool.get(1));
        cardPool.remove(0);
        cardPool.remove(1);
    }

    public boolean getVaultOverclockingOnOff() {
        return vaultOverclockingOnOff;
    }

    public void setVaultOverclockingOnOff() {
        vaultOverclockingOnOff = true;
    }
    public void setVaultOverclockingOnOff(boolean OnOff) {
        vaultOverclockingOnOff = OnOff;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void addToHand(Card c) {
        hand.add(c);
    }

    public String getName() {
        return name;
    }

    public Deck getCards() {
        return cardPool;
    }

    public Deck getDiscards() {
        return discardPool;
    }

    public ArrayList<Creature> getPlayerCreature() {
        return playerCreature;
    }

    public void setPlayerCreature(ArrayList<Creature> newOne) {
        playerCreature = newOne;
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

    public void reCreateCardPool() {
        cardPool = new Deck(discardPool.getCardPool());
        discardPool.clearCards();
    }

    public boolean isDead() {
        return lifePoint <= 0;
    }

    public int size() {
        return cardPool.size();
    }

    public void addPlayerCreature(Card creature) {
        playerCreature.add((Creature) creature);
    }

    public void sortCreatures() {
        Collections.sort(playerCreature);
    } //utilisé dans le systeme d'attaque

    public void increaseEnergy() {
        energy++;
    }

    public void winner() {
        logger.info(this.toString() + " is the winner!!!\n");
    }

    public boolean canPlay(){
        boolean result=false;
        for(Card c : this.getHand())
        {
            if(c.getEnergyCost()<=this.energy)
            {
                result=true;
                break;
            }
        }
        if (!result) AlertBox.displayError("Energy issue",this.getName()+",you cannot play any of your cards!");
        return result;
    }

    public void attack(Player opponent){
        ArrayList<Creature> currentBoard = this.playerCreature;
        ArrayList<Creature> opponentBoard = opponent.getPlayerCreature();
        int diffBoard = currentBoard.size()-opponentBoard.size();

        if(diffBoard>0){
            for(int i = 0; i < (currentBoard.size()-diffBoard); i++){
                currentBoard.get(i).attackCreature(opponentBoard.get(i),this,opponent);
            }

            for(int i = (currentBoard.size()-diffBoard); i < currentBoard.size(); i++){
                currentBoard.get(i).attackPlayer(opponent);
            }
        }
        else if(diffBoard==0){
            for(int i = 0; i < currentBoard.size(); i++){
                currentBoard.get(i).attackCreature(opponentBoard.get(i),this,opponent);
            }
        }
        else{
            for(int i = 0; i < currentBoard.size(); i++){
                currentBoard.get(i).attackCreature(opponentBoard.get(i),this,opponent);
            }
        }

    }

    public Player clone(){

        Player p = new Player(this.name);
        p.setLifePoint(this.getLifePoint());
        p.setEnergyPoint(this.getEnergy());
        p.setPlayerCreature((ArrayList<Creature>)playerCreature.clone());
        p.discardPool = this.discardPool.clone();
        p.cardPool = this.cardPool.clone();
        p.hand = (ArrayList<Card> ) this.hand.clone();
        p.setVaultOverclockingOnOff(this.vaultOverclockingOnOff);
        return p;
        }

    @Override
    public String toString() {
        return " " + name + "(" + getLifePoint() + "pv|" + getEnergy() + "energy)";
    }
}
