package edu.insightr.spellmonger;

import org.apache.log4j.Logger;
import javafx.application.Application;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main{
    public static void main(String[] args) {


 /* SpellmongerApp app = new SpellmongerApp();
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

*/


        Player Player1 = new Player("Valentin");
        Player Player2 = new Player("Natacha");
        SpellmongerApp game = new SpellmongerApp(Player1,Player2);
        game.CreateCardPool();
        while(Player1.isDead() || Player2.isDead())
        {
            Player1.DrawCard(Player1,Player2,game.getCardPool(),game.getDiscardPool());
            Player2.DrawCard(Player2,Player1,game.getCardPool(),game.getDiscardPool());
        }
    }


}
