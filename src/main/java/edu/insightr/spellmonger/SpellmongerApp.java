package edu.insightr.spellmonger;


import org.apache.log4j.Logger;

import java.util.ArrayList;

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

    public boolean canPlayCard(Card card,Player player) {
        return card.canPlayCard(player);
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
                System.out.println(i);
                c.attackCreatures(o);

                if ((c.getLifePoint() - o.getLifePoint()) > diff || o.getPlayerCreature().size()<opponent.getPlayerCreature().size() ) {
                    choix = i;
                    diff = (c.getLifePoint() - o.getLifePoint());
                }
            }
        }
        logger.info("on a tous fini");
        if(choix!=-1)
        {
            result="Card " + choix + "\n c'est un:" + currentPlayer.getHand().get(choix);
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
