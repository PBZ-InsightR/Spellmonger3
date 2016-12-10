package edu.insightr.spellmonger;

import java.util.Objects;

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

        if (Objects.equals(this.getCapacity(), "Flying")) {
            flyingAttack(currentPlayer, opponent);
        } else if (Objects.equals(this.getCapacity(), "DeathTouch")) {
            deathTouchAttack(currentPlayer, opponent);
        } else {
            othersAttack(currentPlayer, opponent);
        }
    }

    private void flyingAttack(Player currentPlayer, Player opponent) {
        Creature defCreature = null;
        for (int i = 0; i < opponent.getPlayerCreature().size(); i++) { // on cherche les flying qui sont moins fort OU les catch (fort ou pas un flying ne peut pas le prévoir)
            if ((Objects.equals(opponent.getPlayerCreature().get(i).getCapacity(), "Flying")) || Objects.equals(opponent.getPlayerCreature().get(i).getCapacity(), "Catch")) {
                defCreature = opponent.getPlayerCreature().get(i);
                break;
            }
        }

        if (defCreature != null) {
            if (this.getEffect() < defCreature.getEffect() && Objects.equals(defCreature.getCapacity(), "Catch")) {
                // System.out.println("\n\nCreature31\n\n");
                currentPlayer.getPlayerCreatureDead().add(this);
            } else if (this.getEffect() > defCreature.getEffect()) {
                // System.out.println("\n\nCreature32\n\n");
                CreatureDead(opponent, defCreature);
            } else if (this.getEffect() == defCreature.getEffect()) {
                //System.out.println("\n\nCreature3\n\n");
                CreatureDead(opponent, defCreature);
                currentPlayer.getPlayerCreatureDead().add(this);
            } else {
                //System.out.println("\n\nCreature3RIEN\n\n");
            }
        } else {
            this.damagePlayer(opponent);
            //System.out.println("\n\n4\n\n");
        }
    }

    private void deathTouchAttack(Player currentPlayer, Player opponent) {
        Creature defCreature;
        if (opponent.getPlayerCreature().size() != 0) {
            defCreature = opponent.getPlayerCreature().get(0);
            CreatureDead(opponent, defCreature);
            currentPlayer.getPlayerCreatureDead().add(this);
        } else {
            this.damagePlayer(opponent);
        }
    }

    private void othersAttack(Player currentPlayer, Player opponent) {
        Creature defCreature = null;
        if (opponent.getPlayerCreature().size() != 0) {
            boolean flyingWeaker = false;
            for (int i = 0; i < opponent.getPlayerCreature().size(); i++) {
                if (opponent.getPlayerCreature().get(i).getEffect() <= this.getEffect()) {
                    if (Objects.equals(opponent.getPlayerCreature().get(i).getCapacity(), "Flying")) {
                        if (!flyingWeaker) {
                            flyingWeaker = true;
                        }
                    } else {
                        defCreature = opponent.getPlayerCreature().get(i);
                        break;
                    }
                }
            }
            if (defCreature != null) {
                if (Objects.equals(defCreature.getCapacity(), "DeathTouch")) {
                    currentPlayer.getPlayerCreatureDead().add(this);
                    CreatureDead(opponent, defCreature);
                } else {
                    if (this.getEffect() > defCreature.getEffect()) {
                        CreatureDead(opponent, defCreature);
                    } else {
                        CreatureDead(opponent, defCreature);
                        currentPlayer.getPlayerCreatureDead().add(this);
                    }
                }
            } else if (flyingWeaker) {
                this.damagePlayer(opponent);
            }
        } else {
            this.damagePlayer(opponent);
        }
    }

    private void CreatureDead(Player player, Creature card) {
        player.getDiscards().add(card);
        player.getPlayerCreature().remove(card);
        player.getPlayerCreatureDead().add(card);
    }

    private void damagePlayer(Player opponent) {
        int damage = this.getEffect();
        opponent.setLifePoint(opponent.getLifePoint() - damage);
    }

    @Override
    public int compareTo(Creature other) {
        Integer obj1 = this.effect;
        Integer obj2 = other.effect;
        return obj2.compareTo(obj1); // pour que ce soit décroissant
    }// compareTo pour ordonner les creatures, et créér le systeme d'attaque entre creatures
}