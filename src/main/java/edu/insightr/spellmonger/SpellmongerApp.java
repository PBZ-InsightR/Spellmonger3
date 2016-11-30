package edu.insightr.spellmonger;


import org.apache.log4j.Logger;

import java.util.ArrayList;

public class SpellmongerApp {
    private static final Logger logger = Logger.getLogger(SpellmongerApp.class);
    private ArrayList<Player> playerList = new ArrayList<>(2);

    public SpellmongerApp(Player player1, Player player2) {
        playerList.add(player1);
        playerList.add(player2);
    }

    public Player nextPLayer(Player p) {
        if (playerList.indexOf(p) == 1) return playerList.get(0);
        else return playerList.get(1);
    }

    public boolean playCard(Player currentPlayer, Player opponent, int playerChoice) {

        Card currentCard = currentPlayer.getHand().get(playerChoice);

        if(currentCard.playCard(currentPlayer)){
            if(currentCard instanceof Ritual){
                ((Ritual) currentCard).setEffect(currentPlayer,opponent);
                currentPlayer.getHand().remove(currentCard);
                currentPlayer.getDiscards().add(currentCard);
            }else if(currentCard instanceof Creature){
                currentPlayer.getPlayerCreature().add((Creature)currentCard);
                currentPlayer.getHand().remove(currentCard);
                currentPlayer.sortCreatures();
            }else if(currentCard instanceof Enchantment){
                currentPlayer.getHand().remove(currentCard);
                currentPlayer.getDiscards().add(currentCard);
                currentPlayer.setVaultOverclockingOnOff(true);
            }
            return true;
        }else{
            return false;
        }




      /*  ArrayList<Card> hand=currentPlayer.getHand();
        Deck discard=currentPlayer.getDiscards();
        Card currentCard = hand.get(playerChoice);

        logger.info(currentPlayer.toString() + " draw a " + currentCard.toString());

        if (currentCard.getEnergyCost() <= currentPlayer.getEnergy()) {
            if (currentCard instanceof Creature) {
                currentPlayer.addPlayerCreature(currentCard);
                currentPlayer.sortCreatures();
                currentPlayer.setEnergyPoint(currentPlayer.getEnergy() - currentCard.getEnergyCost());
                logger.info(currentPlayer.getName() + "'s play a " + currentCard.toString());
            } else if (currentCard instanceof Ritual) {
                if (currentCard instanceof Blessing) {
                    currentPlayer.setLifePoint(currentPlayer.getLifePoint() + currentCard.getEffect());
                    currentPlayer.setEnergyPoint(currentPlayer.getEnergy() - currentCard.getEnergyCost());
                    logger.info("The Blessing Ritual add 3 Life Point to " + currentPlayer.toString());
                } else if (currentCard instanceof Curse) {
                    opponent.setLifePoint(opponent.getLifePoint() - currentCard.getEffect());
                    currentPlayer.setEnergyPoint(currentPlayer.getEnergy() - currentCard.getEnergyCost());
                    logger.info(currentPlayer.toString() + " cast a ritual that deals 3 damages to " + opponent.toString());
                } else {
                    if (opponent.getEnergy() >= 2) {
                        currentPlayer.setEnergyPoint(currentPlayer.getEnergy() + currentCard.getEffect());
                        opponent.setEnergyPoint(opponent.getEnergy() - currentCard.getEffect());
                        logger.info(currentPlayer.toString() + " cast a drain energy ritual that takes 2 energies from " + opponent.toString());
                    } else if (opponent.getEnergy() == 1) {
                        currentPlayer.setEnergyPoint(currentPlayer.getEnergy() + 1);
                        opponent.setEnergyPoint(opponent.getEnergy() - 1);
                        logger.info(currentPlayer.toString() + " cast a drain energy ritual that takes 1 energie from " + opponent.toString());
                    } else {
                        logger.info(currentPlayer.toString() + " cast a drain energy ritual but " + opponent.toString() + " have no energy to steal");
                    }
                    currentPlayer.setEnergyPoint(currentPlayer.getEnergy() - currentCard.getEnergyCost());
                }
            } else if (currentCard instanceof Enchantment) {
                if (currentCard instanceof VaultOverclocking) {
                    if (!currentPlayer.getVaultOverclockingOnOff()) {
                        currentPlayer.setVaultOverclockingOnOff(true);
                        currentPlayer.setEnergyPoint(currentPlayer.getEnergy() - currentCard.getEnergyCost());
                        logger.info(currentPlayer.getName() + "'s plays an Vault overclocking Enchantment and now has 65% of chance to gain 1 more Energy per turn and 35% of chance to burn all his Energy stack");
                    }
                }
            }

            hand.remove(playerChoice);
            if (currentCard instanceof Ritual) {
                discard.add(currentCard);
            }
        } else {
            return false;
        }
        return true;*/
    }

    public String playCardIA(Player currentPlayer, Player opponent) {

        String result;
        int choix = -1;
        int diff = currentPlayer.getLifePoint() - opponent.getLifePoint();
        int energy = 0;
        ArrayList<Card> hand=currentPlayer.getHand();
        logger.info("Life Points");
        for (int i = 0; i < hand.size(); i++) {
            Player c = currentPlayer.clone();
            Player o = opponent.clone();
            if (playCard(c, o, i)) {
                c.attack(o);
                if ((c.getLifePoint() - o.getLifePoint()) > diff || o.getPlayerCreature().size()<opponent.getPlayerCreature().size() ) {
                    choix = i;
                    diff = (c.getLifePoint() - o.getLifePoint());
                }
            }
        }

        if (choix == -1) {
            logger.info("Life Points failed, on va regarder l'energy");
            for (int i = 0; i < hand.size(); i++) {
                Player c = currentPlayer.clone();
                Player o = opponent.clone();
                if (playCard(c, o,i)) {
                    if ((c.getEnergy()) > energy) {
                        choix = i;
                        energy = c.getEnergy();
                    }
                }
            }
        }
        logger.info("on a tous fini");
        if(choix!=-1)
        {
            result="Card " + choix + " c'est un:" + currentPlayer.getHand().get(choix);
            playCard(currentPlayer, opponent, choix);
        }
        else
        {
            result= "Il a preferé ne pas joué ce round!";
        }
        return result;
    }

    public Player getPlayer(int i) {
        return playerList.get(i);
    }

    @Override
    public String toString() {
        return playerList.get(0).toString() + " VS " + playerList.get(1).toString();
    }
}
