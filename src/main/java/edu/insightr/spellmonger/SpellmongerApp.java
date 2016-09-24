package edu.insightr.spellmonger;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpellmongerApp {
    private static final Logger logger = Logger.getLogger(SpellmongerApp.class);

    Map<String, Integer> playersLifePoints = new HashMap<>(2);
    Map<String, Integer> playersCreature = new HashMap<>(2);
    List<String> cardPool = new ArrayList<>(70);

    public SpellmongerApp() {
        playersLifePoints.put("Alice", 20);
        playersLifePoints.put("Bob", 20);
        playersCreature.put("Alice", 0);
        playersCreature.put("Bob", 0);
        int ritualMod = 3;

        for (int i = 0; i < 70; i++) {
            if (i % ritualMod == 0) {
                cardPool.add("Ritual");
            }
            if (i % ritualMod != 0) {
                cardPool.add("Creature");
            }

            if (ritualMod == 3) {
                ritualMod = 2;
            } else {
                ritualMod = 3;
            }

        }
    }

    public static void main(String[] args) {
        SpellmongerApp app = new SpellmongerApp();

        boolean onePlayerDead = false;
        String currentPlayer = "Alice";
        String opponent = "Bob";
        int currentCardNumber = 0;
        int roundCounter = 1;
        String winner = null;

        while (!onePlayerDead) {
            int currentLifePoints,opponentLifePoints;
            // si toutes les cartes ont déjà été piochées, on aura juste a cloner les deux liste (discardPool et cardPool)
            if(currentCardNumber>70)
            {
            }

            logger.info("\n");
            logger.info("***** ROUND " + roundCounter);

            app.drawACard(currentPlayer, opponent, currentCardNumber);
            currentLifePoints=app.playersLifePoints.get(currentPlayer);
            opponentLifePoints=app.playersLifePoints.get(opponent);

            logger.info(opponent + " has " + opponentLifePoints + " life points and " + currentPlayer + " has " + currentLifePoints + " life points ");

            if (app.playersLifePoints.get(currentPlayer) <= 0) {
                winner = opponent;
                onePlayerDead = true;
            }
            if (app.playersLifePoints.get(opponent) <= 0) {
                winner = currentPlayer;
                onePlayerDead = true;
            }

            if ("Alice".equalsIgnoreCase(currentPlayer)) {
                currentPlayer = "Bob";
                opponent = "Alice";
            } else {
                currentPlayer = "Alice";
                opponent = "Bob";
            }
            currentCardNumber++;
            roundCounter++;
        }

        logger.info("\n");
        logger.info("******************************");
        logger.info("THE WINNER IS " + winner + " !!!");
        logger.info("******************************");


    }

    public void drawACard(String currentPlayer, String opponent, int currentCardNumber) {

        int nbCurrentPlayerCreatures = playersCreature.get(currentPlayer);
        int nbLifePointsOpponent=playersLifePoints.get(opponent);

        logger.info(currentPlayer + " draw a "+cardPool.get(currentCardNumber));

        if ("Creature".equalsIgnoreCase(cardPool.get(currentCardNumber))) {
            playersCreature.put(currentPlayer, nbCurrentPlayerCreatures + 1);
            nbCurrentPlayerCreatures ++; // nbCurrentPlayerCreatures a augmenté de 1
            playersLifePoints.put(opponent, (nbLifePointsOpponent - nbCurrentPlayerCreatures));
            logger.info("The " + nbCurrentPlayerCreatures + " creatures of " + currentPlayer + " attack and deal " + nbCurrentPlayerCreatures + " damages to its opponent");
        }
        if ("Ritual".equalsIgnoreCase(cardPool.get(currentCardNumber))) {
            if (nbCurrentPlayerCreatures > 0) {
                playersLifePoints.put(opponent, (nbLifePointsOpponent - nbCurrentPlayerCreatures - 3));
                logger.info("The " + nbCurrentPlayerCreatures + " creatures of " + currentPlayer + " attack and deal " + nbCurrentPlayerCreatures + " damages to its opponent");
            }
            logger.info(currentPlayer + " cast a ritual that deals 3 damages to " + opponent);
        }
    }

}
