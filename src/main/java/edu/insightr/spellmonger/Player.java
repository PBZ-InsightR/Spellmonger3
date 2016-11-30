package edu.insightr.spellmonger;

//import org.apache.log4j.Logger;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Player {
    // private static final Logger logger = Logger.getLogger(SpellmongerApp.class);
    private String name;
    private int lifePoint;
    private int energyPerTurn;
    private int energy;
    private Deck cardPool;
    private Deck discardPool;
    private boolean vaultOverclockingOnOff;
    private ArrayList<Creature> playerCreature;
    private ArrayList<Creature> playerCreatureDead;
    private ArrayList<Card> hand;

    public Player(String name) {
        this.playerCreature = new ArrayList<>();
        this.playerCreatureDead=new ArrayList<>();
        this.cardPool = new Deck(name);
        this.discardPool = new Deck();
        this.name = name;
        this.lifePoint = 20;
        this.energy = 0;
        this.energyPerTurn = 0;
        hand = new ArrayList<>(3);
        addInitialCards();
        vaultOverclockingOnOff = false;
    }



    private void addInitialCards() {
        addToHand(cardPool.get(0));
        addToHand(cardPool.get(1));
        cardPool.remove(0);
        cardPool.remove(1);
    }

    public void vaultOverclockingActiveEffect(Player player) {
        Random rand = new Random();
        int nbRand = rand.nextInt(99);
        if (player.getVaultOverclockingOnOff()==true) {
            if (nbRand > 34) {
                player.setEnergyPerTurn(1);
            } else {
                player.setEnergyPerTurn(player.getEnergy() + 1);
            }
        }
    }



    public int getEnergyPerTurn(){return this.energyPerTurn;}

    public void setEnergyPerTurn(int energyPerTurn){this.energyPerTurn = energyPerTurn;}

    public void setVaultOverclockingOnOff(boolean OnOff) {
        vaultOverclockingOnOff = OnOff;
    }

    public boolean getVaultOverclockingOnOff(){return vaultOverclockingOnOff;}

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

    public ArrayList<Creature> getPlayerCreatureDead()
    {
        return playerCreatureDead;
    }

    public void clearPlayerCreatureDead() {
        playerCreatureDead.clear();
    }
    private void setPlayerCreature(ArrayList<Creature> newOne) {
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

    void setEnergyPoint(int ene) {
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

    void addPlayerCreature(Card creature) {
        playerCreature.add((Creature) creature);
    }

    void sortCreatures() {
        Collections.sort(playerCreature);
    } //utilisé dans le systeme d'attaque

    public void increaseEnergy() {
        if(this.energy <10){
            energy++;
        }
    }

    public boolean winner(Player other) {
      return this.lifePoint> other.lifePoint;
    }

    public void drawCard(){
        if (this.size() == 0) this.reCreateCardPool();
        this.addToHand(this.getCards().get(0));
        this.getCards().remove(0);
    }

    public boolean canDraw(){
        return this.getHand().size() < 5;
    }

    public boolean canPlay() {
        boolean result = false;
        for (Card c : this.getHand()) {
            if (c.getEnergyCost() <= this.getEnergyPerTurn()) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void attack(Player opponent) {

        this.clearPlayerCreatureDead();
        opponent.clearPlayerCreatureDead();

        for(int i = 0; i < this.getPlayerCreature().size(); i++){
            this.getPlayerCreature().get(i).attackCreature(this,opponent);
        }
        /*for(Creature creature : this.getPlayerCreature()){
            creature.creatureAttack(this,opponent);
        }*/
        /*ArrayList<Creature> attackFlying = new ArrayList<>();
        ArrayList<Creature> attack = new ArrayList<>();

        ArrayList<Creature> defFlying = new ArrayList<>();
        ArrayList<Creature> def = new ArrayList<>();

        for(Creature creature : this.getPlayerCreature()){
            if(creature.getCapacity()==""){
                attack.add(creature);
            }else if(creature.getCapacity()=="Flying"){
                attackFlying.add(creature);
            }
        }

        for(Creature creature : opponent.getPlayerCreature()){
            if(creature.getCapacity()==""){
                def.add(creature);
            }else if(creature.getCapacity()=="Flying" || creature.getCapacity()=="Catch"){
                defFlying.add(creature);
            }
        }

        for(int i = 0; i < attackFlying.size(); i++){
            attackFlying.get(i).creatureAttack(defFlying.get(i),this,opponent);
        }

        for(int i = 0; i < attack.size(); i++){
            attack.get(i).creatureAttack(def.get(i),this,opponent);
        }*/

       /* for(Creature opponentCreature : opponent.getPlayerCreature()){
            for (Creature creature:this.getPlayerCreature()){
                creature.creatureAttack(opponentCreature,this,opponent);
            }
        }*/


        /*ArrayList<Creature> currentBoard = this.playerCreature;
        ArrayList<Creature> opponentBoard = opponent.getPlayerCreature();
        int diffBoard = currentBoard.size() - opponentBoard.size();

        if (diffBoard > 0) {
            for (int i = 0; i < (currentBoard.size() - diffBoard); i++) {
                currentBoard.get(i).attackCreature(opponentBoard.get(i), this, opponent);
            }

            for (int i = (currentBoard.size() - diffBoard); i < currentBoard.size(); i++) {
                currentBoard.get(i).attackPlayer(opponent);
            }
        } else if (diffBoard == 0) {
            for (int i = 0; i < currentBoard.size(); i++) {
                currentBoard.get(i).attackCreature(opponentBoard.get(i), this, opponent);
            }
        } else {
            for (int i = 0; i < currentBoard.size(); i++) {
                currentBoard.get(i).attackCreature(opponentBoard.get(i), this, opponent);
            }
        }*/

    }

    public Player clone() {

        Player p = new Player(this.name);
        p.setLifePoint(this.getLifePoint());
        p.setEnergyPoint(this.getEnergy());
        p.setPlayerCreature((ArrayList<Creature>) playerCreature.clone());
        p.discardPool = this.discardPool.clone();
        p.cardPool = this.cardPool.clone();
        p.hand = (ArrayList<Card>) this.hand.clone();
        p.setVaultOverclockingOnOff(this.vaultOverclockingOnOff);
        return p;
    }

    @Override
    public String toString() {
        return " " + name + "(" + getLifePoint() + "pv|" + getEnergy() + "energy)";
    }
}
