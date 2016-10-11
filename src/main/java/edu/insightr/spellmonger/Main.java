package edu.insightr.spellmonger;


import org.apache.log4j.Logger;

public class Main{
    public static void main(String[] args) {
        final Logger logger = Logger.getLogger(SpellmongerApp.class);
        Player Player1 = new Player("Valentin");
        Player Player2 = new Player("Natacha");
        SpellmongerApp game = new SpellmongerApp(Player1,Player2);

        Player current=Player1;
        Player oppenent=game.nextPLayer();

        while(!oppenent.isDead())
        {
            if(current.size()==0) current.reCreateCardPool();

            logger.info("Current  player is :"+current);
            logger.info("Oppenent player is :"+oppenent);
            game.drawCard(current,oppenent,current.getCards(),current.getDiscards());
            current.attack(oppenent);
            logger.info(game.toString());

            // si après cette attaque l'adversaire meurt, on finit le jeu
            // sinon il continue et le current devient oppenent eet vice versa
            if(!oppenent.isDead())
            {
                current=oppenent;
                oppenent=game.nextPLayer();
            }
        }

        if(Player1.isDead())
        {
            Player2.winner();
        }
        else
        {
            Player1.winner();
        }
    }


}
