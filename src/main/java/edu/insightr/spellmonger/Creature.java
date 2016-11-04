package edu.insightr.spellmonger;

public abstract class Creature extends Card implements Comparable<Creature> {
    protected String capacity;
    protected int lifePoints;

    public Creature() {
        capacity = "";
        lifePoints = 0;
        energyCost = 0;
    }
    public int getEnergyCost(){return energyCost;}

    public String getCapacity() {
        return capacity;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public void attackCreature(Creature opponentCreature, Player currentPlayer, Player opponent){
        if(this instanceof Eagle && opponentCreature instanceof Eagle==false){
            opponent.setLifePoint(opponent.getLifePoint()-this.getEffect());
        }
        else if(this instanceof Eagle==false && opponentCreature instanceof Eagle==false){
            int damage = this.getEffect()-opponentCreature.getEffect();
            if(damage>0){
                opponent.getDiscards().add(opponentCreature);
                opponent.getPlayerCreature().remove(opponentCreature);
            }
            else if(damage==0){
                currentPlayer.getDiscards().add(this);
                opponent.getDiscards().add(opponentCreature);
                currentPlayer.getPlayerCreature().remove(this);
                opponent.getPlayerCreature().remove(opponentCreature);
            }
            else{
                currentPlayer.getDiscards().add(this);
                currentPlayer.getPlayerCreature().remove(this);
            }
        }
        else {
            currentPlayer.getDiscards().add(this);
            opponent.getDiscards().add(opponentCreature);
            currentPlayer.getPlayerCreature().remove(this);
            opponent.getPlayerCreature().remove(opponentCreature);
        }
    }

    public void attackPlayer(Player opponent){
        int damage = this.getEffect();
        opponent.setLifePoint(opponent.getLifePoint()-damage);
    }

    public void playCreature(Player current){
        current.getPlayerCreature().add(this);
        current.sortCreatures();
        current.setStackEnergy(current.getStackEnergy()-this.getEnergyCost());
    }

    @Override
    public int compareTo(Creature other) {
        Integer obj1 = this.effect;
        Integer obj2 = new Integer(other.effect);
        return obj2.compareTo(obj1); // pour que ce soit décroissant
    }// compareTo pour ordonner les creatures, et créér le systeme d'attaque entre creatures
}