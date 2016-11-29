package edu.insightr.spellmonger;

public class Lizard extends Creature{

    Lizard(){
        effect = 3;
        lifePoints = 2;
        energyCost = 3;
        capacity="";
    }

    @Override
    public String toString() {
        return "Lizard : life Point :" + lifePoints + " strength : " + effect;
    }

    @Override
    public String getName() {
        return "Lizard";
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
