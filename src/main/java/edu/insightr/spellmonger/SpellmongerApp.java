package edu.insightr.spellmonger;


import org.apache.log4j.Logger;

import java.util.ArrayList;

public class SpellmongerApp {
    private static final Logger logger = Logger.getLogger(SpellmongerApp.class);
    private ArrayList<Player> playerList = new ArrayList<>(2);
    private int counter;

    public SpellmongerApp(Player player1, Player player2) {
        playerList.add(player1);
        playerList.add(player2);
        counter = 0;
    }

    public Player nextPLayer() {
        if (counter == playerList.size() - 1) {
            counter = 0;
        } else {
            counter++;
        }
        return playerList.get(counter);
    }

    public void playCard(Player currentPlayer, Player opponent, ArrayList<Card> hand, int playerChoice,Deck discard) {
        currentPlayer.increaseEnergy();
        Card currentCard = hand.get(playerChoice);

        logger.info(currentPlayer.toString() + " draw a " + currentCard.toString());

        if(currentCard.getEnergyCost()< currentPlayer.getEnergy()){
            if(currentCard instanceof Creature && currentCard.getEnergyCost()<currentPlayer.getEnergy()){
                currentPlayer.addPlayerCreature (currentCard);
                currentPlayer.sortCreatures ();
                currentPlayer.setEnergyPoint (currentPlayer.getEnergy()-currentCard.getEnergyCost());
                logger.info (currentPlayer.getName ()+"'s play a "+currentCard.toString ());
            }
            else if(currentCard instanceof Ritual && currentCard.getEnergyCost()<currentPlayer.getEnergy()){
                if(currentCard instanceof Blessing){
                    currentPlayer.setLifePoint(currentPlayer.getLifePoint()+currentCard.getEffect());
                    currentPlayer.setEnergyPoint (currentPlayer.getEnergy()-currentCard.getEnergyCost());
                    logger.info("The Blessing Ritual add 3 Life Point to "+currentPlayer.toString());
                }
                else if(currentCard instanceof Curse){
                    opponent.setLifePoint(opponent.getLifePoint()-currentCard.getEffect());
                    currentPlayer.setEnergyPoint (currentPlayer.getEnergy()-currentCard.getEnergyCost());
                    logger.info(currentPlayer.toString()+" cast a ritual that deals 3 damages to "+opponent.toString());
                }
                else{
                    if(opponent.getEnergy()>=2) {
                        currentPlayer.setEnergyPoint(currentPlayer.getEnergy() + currentCard.getEffect());
                        opponent.setEnergyPoint(opponent.getEnergy() - currentCard.getEffect());
                        logger.info(currentPlayer.toString() + " cast a drain energy ritual that takes 2 energies from " + opponent.toString());
                    }
                    else if(opponent.getEnergy() == 1) {
                        currentPlayer.setEnergyPoint(currentPlayer.getEnergy() + 1);
                        opponent.setEnergyPoint(opponent.getEnergy() -1);
                        logger.info(currentPlayer.toString() + " cast a drain energy ritual that takes 1 energie from " + opponent.toString());
                    }
                    else{
                        logger.info(currentPlayer.toString() + " cast a drain energy ritual but " + opponent.toString() + " have no energy to steal");
                    }
                    currentPlayer.setEnergyPoint (currentPlayer.getEnergy()- currentCard.getEnergyCost());
                }
            }
            else if(currentCard instanceof Enchantment && currentCard.getEnergyCost()<currentPlayer.getEnergy()) {
                if(currentCard instanceof VaultOverclocking){
                    if(!currentPlayer.getVaultOverclockingOnOff()){
                        currentPlayer.setVaultOverclockingOnOff (true);
                        currentPlayer.setEnergyPoint (currentPlayer.getEnergy()-currentCard.getEnergyCost());
                        logger.info (currentPlayer.getName ()+"'s plays an Vault overclocking Enchantment and now has 65% of chance to gain 1 more Energy per turn and 35% of chance to burn all his Energy stack");
                    }
                }
            }

            hand.remove(playerChoice);
            if(currentCard instanceof  Ritual) {
                discard.add(currentCard);
            }
        }
        else{
            logger.info (currentPlayer.getName ()+"hasn't enough energy in his stack");
        }







       /* if (currentCard instanceof Creature) {
            currentPlayer.addPlayerCreature(currentCard);
            currentPlayer.sortCreatures();
            currentPlayer.setEnergyPoint (currentPlayer.getEnergy ()-((Creature) currentCard).getEnergyCost ());
        }
        else if(currentCard instanceof Ritual)
        {
            if(currentCard instanceof Blessing)
            {
                currentPlayer.setLifePoint(currentPlayer.getLifePoint()+currentCard.getEffect());
                logger.info("The Blessing Ritual add 3 Life Point to "+currentPlayer.toString());
            }
            else if(currentCard instanceof Curse)
            {
                opponent.setLifePoint(opponent.getLifePoint()-currentCard.getEffect());
                logger.info(currentPlayer.toString()+" cast a ritual that deals 3 damages to "+opponent.toString());
            }
            else if(currentCard instanceof EnergyDrain)
            {
                if(opponent.getEnergy()>2)
                {
                    currentPlayer.setEnergyPoint(currentPlayer.getEnergy() + currentCard.getEffect());
                    opponent.setEnergyPoint(opponent.getEnergy() - currentCard.getEffect());
                    logger.info(currentPlayer.toString() + " cast a drain energy ritual that takes 2 energies from " + opponent.toString());
                }
                else if(opponent.getEnergy() == 1)
                {
                    currentPlayer.setEnergyPoint(currentPlayer.getEnergy() + 1);
                    opponent.setEnergyPoint(opponent.getEnergy() -1);
                    logger.info(currentPlayer.toString() + " cast a drain energy ritual that takes 1 energie from " + opponent.toString());
                }
                else if(opponent.getEnergy() == 0)
                {
                    logger.info(currentPlayer.toString() + " cast a drain energy ritual but " + opponent.toString() + " have no energy to steal");
                }
            }
        }
        else if(currentCard instanceof Enchantment) {
                if(currentCard instanceof VaultOverclocking){
                    if(currentPlayer.getVaultOverclockingOnOff ()==false){
                        currentPlayer.setVaultOverclockingOnOff (true);
                    }
                }
        }*/

    }

    public Player getPlayer(int i){
        return playerList.get(i);
    }

    @Override
    public String toString() {
        return playerList.get(0).toString() + " VS " + playerList.get(1).toString();
    }
}
