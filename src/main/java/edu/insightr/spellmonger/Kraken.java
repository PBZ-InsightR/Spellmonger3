package edu.insightr.spellmonger;


import java.util.ArrayList;
import java.util.List;

public class Kraken extends Creature{

    Kraken(){
        effect = 4;
        lifePoints = effect;
        energyCost = 4;
        capacity="Catch";
    }

    @Override
    public String toString() {
        return "Kraken : life Point :" + lifePoints + " strength : " + effect;
    }

    @Override
    public String getName() {
        return "Kraken";
    }

   /* @Override
    public void creatureAttack(Player current, Player opponent) {

        Creature defCreature = null;
        if(!opponent.getPlayerCreature().isEmpty()){
            for(int i = 0; i < opponent.getPlayerCreature().size(); i++){
                if(opponent.getPlayerCreature().get(i).getEffect()<=this.getEffect()){
                    defCreature = opponent.getPlayerCreature().get(i);
                    break;
                }
            }
            if(defCreature.getEffect()>this.getEffect()){
                current.getDiscards().add(this);
                current.getPlayerCreature().remove(this);
            }else if(defCreature.getEffect()==this.getEffect()){
                current.getDiscards().add(this);
                current.getPlayerCreature().remove(this);
                opponent.getDiscards().add(defCreature);
                opponent.getPlayerCreature().remove(this);
            }else if(defCreature.getEffect()<this.getEffect()){
                opponent.getDiscards().add(defCreature);
                opponent.getPlayerCreature().remove(this);
            }else if(defCreature.getCapacity()=="DeathTouch"){
                opponent.getDiscards().add(defCreature);
                opponent.getPlayerCreature().remove(defCreature);
                current.getDiscards().add(this);
                current.getPlayerCreature().remove(this);
            }
        }else{
            this.attackPlayer(opponent);
        }

    }*/


}
