package edu.insightr.spellmonger;


import org.apache.log4j.Logger;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;

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
        if(canPlayCard(currentCard,currentPlayer)){
            if(currentCard instanceof Ritual){
                ((Ritual) currentCard).attackRitual(currentPlayer,opponent);
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
    }

    private boolean canPlayCard(Card card,Player player) {
        return card.canPlayCard(player);
    }

    public String playCardIA_LV2(Player currentPlayer, Player opponentPlayer) {

        String result;
        int choix = -1;
        int diff = currentPlayer.getLifePoint() - opponentPlayer.getLifePoint();
        ArrayList<Card> hand=currentPlayer.getHand();
        for (int i = 0; i < hand.size(); i++) {
            Player current = currentPlayer.clone();
            Player opponent = opponentPlayer.clone();
            if (playCard(current, opponent, i)) {
                System.out.println(i);
                current.attackCreatures(opponent);
                if ((current.getLifePoint() - opponent.getLifePoint()) > diff || opponent.getPlayerCreature().size()<opponentPlayer.getPlayerCreature().size() ) {
                    choix = i;
                    diff = (current.getLifePoint() - opponent.getLifePoint());
                }
            }
        }
        if(choix!=-1)
        {
            result="Card " + choix + "\n c'est un:" + currentPlayer.getHand().get(choix);
            playCard(currentPlayer, opponentPlayer, choix);
        }
        else
        {
            result= "Il a preferé ne pas joué ce round!";
        }
        return result;
    } // level 2

    public String playCardIA_LV3(Player currentPlayer, Player opponentPlayer) {

        String result;
        int choix = -1;
        int diff = currentPlayer.getLifePoint() - opponentPlayer.getLifePoint();
        ArrayList<Card> hand=currentPlayer.getHand();
        ArrayList<Integer> difference=new ArrayList<>();
        ArrayList<Integer> differenceTemp=new ArrayList<>();
        HashMap<Integer,ArrayList<Integer>> eachCard=new HashMap<>();
        for (int i = 0; i < hand.size(); i++) {
            Player current = currentPlayer.clone();
            Player opponent = opponentPlayer.clone();
            if (playCard(current, opponent, i)) {
                System.out.println(i);
                current.attackCreatures(opponent);
                differenceTemp.add(current.getLifePoint() - opponent.getLifePoint());
                opponent.attackCreatures(current);
                difference.add(current.getLifePoint() - opponent.getLifePoint());
            }
        }

        // introduire l'energie aussi










        if(choix!=-1)
        {
            result="Card " + choix + "\n c'est un:" + currentPlayer.getHand().get(choix);
            playCard(currentPlayer, opponentPlayer, choix);
        }
        else
        {
            result= "Il a preferé ne pas joué ce round!";
        }
        return result;
    } // level 3

    public String playCardIA_LV1(Player currentPlayer, Player opponentPlayer) {
        String result;
        int choix = -1;
        while (!playCard(currentPlayer, opponentPlayer, choix+1) && choix+1<currentPlayer.getHand().size()) {
            choix++;
        }

        if(choix!=-1)
        {
            result="Card " + choix + "\n c'est un:" + currentPlayer.getHand().get(choix);
            playCard(currentPlayer, opponentPlayer, choix);
        }
        else
        {
            result= "Il a preferé ne pas joué ce round!";
        }
        return result;
    } // level 1

    public Player getPlayer(int i) {
        return playerList.get(i);
    }

    @Override
    public String toString() {
        return playerList.get(0).toString() + " VS " + playerList.get(1).toString();
    }
}
