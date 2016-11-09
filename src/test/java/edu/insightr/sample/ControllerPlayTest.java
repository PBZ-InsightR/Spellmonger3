package edu.insightr.sample;

import cucumber.api.junit.Cucumber;
import javafx.scene.layout.Pane;
import org.junit.Test;
import javafx.application.Application;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class ControllerPlayTest {
    //Natacha zone test avec cucumber


    //
    @Test
    public void draw1() throws Exception {
        Thread t = new Thread("JavaFX Init Thread") {
            public void run() {
                Application.launch(Main.class, new String[0]);
                ControllerPlay c = new ControllerPlay();
                c.draw_player_1();
            }
        };
        t.setDaemon(true);
        t.start();
    }

    @Test
    public void draw2() throws Exception {
        Thread t = new Thread("JavaFX Init Thread") {
            public void run() {
                Application.launch(Main.class, new String[0]);
                ControllerPlay c = new ControllerPlay();
                c.draw_player_2();
            }
        };
        t.setDaemon(true);
        t.start();
    }

    @Test
    public void infoCard() throws Exception {
        Thread t = new Thread("JavaFX Init Thread") {
            public void run() {
                Application.launch(Main.class, new String[0]);
                ControllerPlay c = new ControllerPlay();
                c.InfoCard();
            }
        };
        t.setDaemon(true);
        t.start();
    }

    @Test
    public void attack() throws Exception {
        Thread t = new Thread("JavaFX Init Thread") {
            public void run() {
                Application.launch(Main.class, new String[0]);
                ControllerPlay c = new ControllerPlay();
               // c.play(1,c.game.getPlayer(0),c.game.getPlayer(1));

            }
        };
        t.setDaemon(true);
        t.start();
    }

    @Test
    public void update() throws Exception {
        Thread t = new Thread("JavaFX Init Thread") {
            public void run() {
                Application.launch(Main.class, new String[0]);
                ControllerPlay c = new ControllerPlay();
                c.update();
            }
        };
        t.setDaemon(true);
        t.start();
    }

    @Test
    public void listCreatureContents() throws Exception {
        Thread t = new Thread("JavaFX Init Thread") {
            public void run() {
                Application.launch(Main.class, new String[0]);
                ControllerPlay c = new ControllerPlay();
               // c.listCreatureContents(c.game.getPlayer(0),new ScrollPane());
            }
        };
        t.setDaemon(true);
        t.start();
    }

    @Test
    public void hands() throws Exception {
        Thread t = new Thread("JavaFX Init Thread") {
            public void run() {
                Application.launch(Main.class, new String[0]);
                ControllerPlay c = new ControllerPlay();
             //   c.hands(c.game.getPlayer(0),c.game.getPlayer(1),new ScrollPane());

            }
        };
        t.setDaemon(true);
        t.start();
    }

    @Test
    public void discards() throws Exception {
        Thread t = new Thread("JavaFX Init Thread") {
            public void run() {
                Application.launch(Main.class, new String[0]);
                ControllerPlay c = new ControllerPlay();
                c.discards(c.game.getPlayer(0),new Pane());
            }
        };
        t.setDaemon(true);
        t.start();
    }

    @Test
    public void initialize() throws Exception {
        Thread t = new Thread("JavaFX Init Thread") {
            public void run() {
                Application.launch(Main.class, new String[0]);
                ControllerPlay c = new ControllerPlay();
                //c.initialize();
            }
        };
        t.setDaemon(true);
        t.start();
    }



}