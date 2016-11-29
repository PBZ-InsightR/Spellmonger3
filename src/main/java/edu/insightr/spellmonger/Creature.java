package edu.insightr.spellmonger;

abstract class Creature extends Card implements Comparable<Creature> {
    String capacity;
    int lifePoints;

    public Creature() {
        capacity = "";
        lifePoints = 0;
        energyCost = 0;
    }

    public int getEnergyCost() {
        return energyCost;
    }

    public String getCapacity() {
        return capacity;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    void attackCreature(Player currentPlayer, Player opponent) {
        /*if (this instanceof Eagle && opponentCreature instanceof Eagle == false) {
            opponent.setLifePoint(opponent.getLifePoint() - this.getEffect());
        } else if (this instanceof Eagle == false && opponentCreature instanceof Eagle == false) {
            int damage = this.getEffect() - opponentCreature.getEffect();
            if (damage > 0) {
                opponent.getDiscards().add(opponentCreature);
                opponent.getPlayerCreature().remove(opponentCreature);
            } else if (damage == 0) {
                currentPlayer.getDiscards().add(this);
                opponent.getDiscards().add(opponentCreature);
                currentPlayer.getPlayerCreature().remove(this);
                opponent.getPlayerCreature().remove(opponentCreature);
            } else {
                currentPlayer.getDiscards().add(this);
                currentPlayer.getPlayerCreature().remove(this);
            }
        } else {
            currentPlayer.getDiscards().add(this);
            opponent.getDiscards().add(opponentCreature);
            currentPlayer.getPlayerCreature().remove(this);
            opponent.getPlayerCreature().remove(opponentCreature);
        }*/
        Creature defCreature = null;

        if(this.getCapacity()=="Flying"){
            for(int i = 0; i < opponent.getPlayerCreature().size(); i++){
                if(opponent.getPlayerCreature().get(i).getCapacity()=="Flying" || opponent.getPlayerCreature().get(i).getCapacity()=="Catch"){
                    defCreature = opponent.getPlayerCreature().get(i);
                    break;
                }
            }


            if(defCreature!=null){
                if(this.getEffect()>defCreature.getEffect()){
                    opponent.getDiscards().add(defCreature);
                    opponent.getPlayerCreature().remove(defCreature);
                }else if(this.getEffect()<defCreature.getEffect()){
                    currentPlayer.getDiscards().add(this);
                    currentPlayer.getPlayerCreature().remove(this);
                }else{
                    opponent.getDiscards().add(defCreature);
                    opponent.getPlayerCreature().remove(defCreature);
                    currentPlayer.getDiscards().add(this);
                    currentPlayer.getPlayerCreature().remove(this);
                }
            }else{
                this.attackPlayer(opponent);
            }

        }else if(this.getCapacity()=="Deathtouch"){

            if(opponent.getPlayerCreature().size()!=0){
                defCreature = opponent.getPlayerCreature().get(0);
                opponent.getDiscards().add(defCreature);
                opponent.getPlayerCreature().remove(defCreature);
                currentPlayer.getDiscards().add(this);
                currentPlayer.getPlayerCreature().remove(this);
            }else {
                this.attackPlayer(opponent);
            }
        }else{

            if(opponent.getPlayerCreature().size()!=0){
                for(int i = 0; i < opponent.getPlayerCreature().size(); i++){
                    if(opponent.getPlayerCreature().get(i).getEffect()<=this.getEffect()){
                        defCreature = opponent.getPlayerCreature().get(i);
                    }
                }

                if(defCreature.getCapacity()=="Deathtouch"){
                    if(this.getEffect()>defCreature.getEffect()){
                        opponent.getDiscards().add(defCreature);
                        opponent.getPlayerCreature().remove(defCreature);
                    }else if(this.getEffect()<defCreature.getEffect()){
                        currentPlayer.getDiscards().add(this);
                        currentPlayer.getPlayerCreature().remove(this);
                    }else{
                        opponent.getDiscards().add(defCreature);
                        opponent.getPlayerCreature().remove(defCreature);
                        currentPlayer.getDiscards().add(this);
                        currentPlayer.getPlayerCreature().remove(this);
                    }
                }else{
                    opponent.getDiscards().add(defCreature);
                    opponent.getPlayerCreature().remove(defCreature);
                    currentPlayer.getDiscards().add(this);
                    currentPlayer.getPlayerCreature().remove(this);
                }
            }else{
                this.attackPlayer(opponent);
            }

        }

        /*if(this.getCapacity()=="Flying"){
            if(!opponent.getPlayerCreature().isEmpty()){
                for(int i = 0; i < opponent.getPlayerCreature().size(); i++){
                    if(opponent.getPlayerCreature().get(i).getCapacity()=="Flying" || opponent.getPlayerCreature().get(i).getCapacity()=="Catch"){
                        defCreature=opponent.getPlayerCreature().get(i);
                        break;
                    }
                }

                if(defCreature!=null){
                    if(defCreature.getEffect()>this.getEffect()){
                        currentPlayer.getPlayerCreature().remove(this);
                        currentPlayer.getDiscards().add(this);
                    }else if(defCreature.getEffect()==this.getEffect()){
                        currentPlayer.getPlayerCreature().remove(this);
                        currentPlayer.getDiscards().add(this);
                        opponent.getPlayerCreature().remove(defCreature);
                        opponent.getDiscards().add(defCreature);
                    }else{
                        opponent.getPlayerCreature().remove(defCreature);
                        opponent.getDiscards().add(defCreature);
                    }
                }else{
                    this.attackPlayer(opponent);
                }
            }else{
                this.attackPlayer(opponent);
            }
        }else if(this.getCapacity()=="DeathTouch"){
            if(!opponent.getPlayerCreature().isEmpty()){
                defCreature = opponent.getPlayerCreature().get(0);
                currentPlayer.getPlayerCreature().remove(this);
                currentPlayer.getDiscards().add(this);
                opponent.getPlayerCreature().remove(defCreature);
                opponent.getDiscards().add(defCreature);
            }else{
                this.attackPlayer(opponent);
            }
        }else{
            if(!opponent.getPlayerCreature().isEmpty()){
                for(int i =0; i < opponent.getPlayerCreature().size(); i++){
                    if(opponent.getPlayerCreature().get(i).getEffect()<=this.getEffect()){
                        defCreature = opponent.getPlayerCreature().get(i);
                        break;
                    }
                }
                if(defCreature.getCapacity()=="DeathTouch"){
                    opponent.getPlayerCreature().remove(defCreature);
                    opponent.getDiscards().add(defCreature);
                    currentPlayer.getPlayerCreature().remove(this);
                    currentPlayer.getDiscards().add(this);
                }
                else{
                    if(defCreature.getEffect()<this.getEffect()){
                        opponent.getPlayerCreature().remove(defCreature);
                        opponent.getDiscards().add(defCreature);
                    }else if(defCreature.getEffect()==this.getEffect()){
                        currentPlayer.getPlayerCreature().remove(this);
                        currentPlayer.getDiscards().add(this);
                        opponent.getPlayerCreature().remove(defCreature);
                        opponent.getDiscards().add(defCreature);
                    }
                }

            }else{
                this.attackPlayer(opponent);
            }
        }*/
    }

    void attackPlayer(Player opponent) {
        int damage = this.getEffect();
        opponent.setLifePoint(opponent.getLifePoint() - damage);
    }

    public void playCreature(Player current) {
        current.getPlayerCreature().add(this);
        current.sortCreatures();
        current.setEnergyPerTurn(current.getEnergyPerTurn() - this.getEnergyCost());
    }

    @Override
    public int compareTo(Creature other) {
        Integer obj1 = this.effect;
        Integer obj2 = other.effect;
        return obj2.compareTo(obj1); // pour que ce soit décroissant
    }// compareTo pour ordonner les creatures, et créér le systeme d'attaque entre creatures
}