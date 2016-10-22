package edu.insightr.sample;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;
import javafx.application.Application;


/**
 * Created by hope on 21/10/2016.
 */
public class ControllerTest  {


    @Test
    public void draw1() throws Exception {
        Thread t = new Thread("JavaFX Init Thread") {
            public void run() {
                Application.launch(Main.class, new String[0]);
                Controller c = new Controller();
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
                Controller c = new Controller();
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
                Controller c = new Controller();
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
                Controller c = new Controller();
                c.attack(1,c.game.getPlayer(0),c.game.getPlayer(1));

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
                Controller c = new Controller();
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
                Controller c = new Controller();
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
                Controller c = new Controller();
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
                Controller c = new Controller();
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
                Controller c = new Controller();
                c.initialize();
            }
        };
        t.setDaemon(true);
        t.start();
    }



}