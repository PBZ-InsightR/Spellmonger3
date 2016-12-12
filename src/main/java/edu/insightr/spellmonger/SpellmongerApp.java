package edu.insightr.spellmonger;


import org.apache.log4j.Logger;

import java.util.*;

public class SpellmongerApp {
    private static final Logger logger = Logger.getLogger(SpellmongerApp.class);
    private ArrayList<Player> playerList = new ArrayList<>(2);

    public SpellmongerApp(String player1, String player2) {
        playerList.add(new Player(player1));
        playerList.add(new Player(player2));
    }

    public Player nextPLayer(Player p) {
        if (playerList.indexOf(p) == 1) return playerList.get(0);
        else return playerList.get(1);
    }

    public boolean playCard(Player currentPlayer, Player opponent, int playerChoice) {

        Card currentCard = currentPlayer.getHand().get(playerChoice);
        if (currentPlayer.canPlayCard(currentCard)) {
            currentPlayer.decreaseEnergy(currentCard);
            if (currentCard instanceof Ritual) {
                ((Ritual) currentCard).attackRitual(currentPlayer, opponent);
                currentPlayer.getHand().remove(currentCard);
                currentPlayer.getDiscards().add(currentCard);
            } else if (currentCard instanceof Creature) {
                currentPlayer.getPlayerCreature().add((Creature) currentCard);
                currentPlayer.getHand().remove(currentCard);
                currentPlayer.sortCreatures();
            } else if (currentCard instanceof Enchantment) {
                currentPlayer.getHand().remove(currentCard);
                currentPlayer.getDiscards().add(currentCard);
                currentPlayer.setVaultOverclockingOnOff(true);
            }
            return true;
        } else {
            return false;
        }
    }

    public void playCardIA_LV1(Player currentPlayer, Player opponentPlayer) {
        int index = 0;
        while (currentPlayer.canPlay()) {
            while (index < currentPlayer.getHand().size() && !playCard(currentPlayer, opponentPlayer, index)) {
                index++;
            }
        }
    } // level 1

    public void playCardIA_LV2(Player currentPlayer, Player opponentPlayer) {
        while (currentPlayer.canPlay()) {
            int choix = -1;
            int diff = currentPlayer.getLifePoint() - opponentPlayer.getLifePoint();
            ArrayList<Card> hand = currentPlayer.getHand();
            for (int i = 0; i < hand.size(); i++) {
                Player current = currentPlayer.clone();
                Player opponent = opponentPlayer.clone();
                if (playCard(current, opponent, i)) {
                    System.out.println(i);
                    current.attackCreatures(opponent);
                    opponent.attackCreatures(current);
                    if ((current.getLifePoint() - opponent.getLifePoint()) >= diff) {
                        choix = i;
                        diff = (current.getLifePoint() - opponent.getLifePoint());
                    }
                }
            }
            if (choix == -1) {
                HashMap<Integer, Integer> energies = new HashMap<>();
                for (int i = 0; i < hand.size(); i++) {
                    Player current = currentPlayer.clone();
                    Player opponent = opponentPlayer.clone();
                    if (playCard(current, opponent, i)) {
                        System.out.println(i);
                        current.attackCreatures(opponent);
                        opponent.attackCreatures(current);
                        energies.put(i, current.getEnergy()-opponent.getEnergy());
                    }
                }
                int maxValueInMap = (Collections.max(energies.values()));
                for (Map.Entry<Integer, Integer> entry : energies.entrySet()) {
                    if (entry.getValue() == maxValueInMap) {
                        choix = entry.getKey();
                    }
                }
            }
            playCard(currentPlayer, opponentPlayer, choix);
        }
    } // level 2

    public Player getPlayer(int i) {
        return playerList.get(i);
    }

    @Override
    public String toString() {
        return playerList.get(0).toString() + " VS " + playerList.get(1).toString();
    }
}
