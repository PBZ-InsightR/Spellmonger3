package edu.insightr.spellmonger;


public class Dragoon extends Creature{

    Dragoon(){
        effect = 4;
        lifePoints = effect;
        energyCost = 4;
        capacity="Flying";
    }

    @Override
    public String toString() {
        return "Dragoon : life Point :" + lifePoints + " strength : " + effect;
    }

    @Override
    public String getName() {
        return "Dragoon";
    }

    /*@Override
    public void creatureAttack(Player current, Player opponent) {
        Creature defCreature = null;

        if(!opponent.getPlayerCreature().isEmpty()){
            for(int i = 0; i < opponent.getPlayerCreature().size(); i++){
                if(opponent.getPlayerCreature().get(i).getCapacity()=="Flying" || opponent.getPlayerCreature().get(i).getCapacity()=="Catch"){
                    defCreature = opponent.getPlayerCreature().get(i);
                    break;
                }
            }
            if(defCreature!=null) {
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
                }
            }else{
                this.attackPlayer(opponent);
            }

        }else{
            this.attackPlayer(opponent);
        }
    }*/


}
