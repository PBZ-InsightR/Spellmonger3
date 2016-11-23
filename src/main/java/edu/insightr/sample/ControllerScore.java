package edu.insightr.sample;

import edu.insightr.spellmonger.Outils;
import edu.insightr.spellmonger.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class ControllerScore implements Initializable,ControlledScreen{
    private ScreensController myController;

    @FXML
    public TableView table;
    public TableColumn LoginColumn,NbPlayColumn,ScoreColumn;
    private final ObservableList<User> data = FXCollections.observableArrayList(); // liste de User charger du fichier Json pour les afficher dans TableColumn

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Map<String, User> map =  Outils.readFileToMap(System.getProperty("user.dir")+"/src/main/resources/scores.json");
        for(String s: map.keySet()) {
            data.add(map.get(s));
        }
        LoginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        NbPlayColumn.setCellValueFactory(new PropertyValueFactory<>("nbPlay"));
        ScoreColumn.setCellValueFactory(new PropertyValueFactory<>("scorePercent"));
        table.setItems(data);
    }

    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    //passer a la vue de Play
    public void goToPlay(){
        myController.loadScreen(Main.Play_ID,Main.Play_FILE); // charger le fichier sample.fxml
        myController.setScreen(Main.Play_ID); // activer la vue Play
    }
    public void goToMenu(){
        myController.setScreen(Main.Menu_ID);// activer la vue Menu sans load vu qu'il es déja dans la Map screens
    }
}