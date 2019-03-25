package app.controller;

import app.model.Game;
import app.model.SparqlQuery;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TableView<Game> resultTable;
    @FXML
    private TableColumn<Game, String> titleColumn;
    @FXML
    private TableColumn<Game, Integer> nbSellColumn;
    @FXML
    private TableColumn<Game, Integer> nbPlayerColumn;

    private SparqlQuery sparqlQuery;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sparqlQuery = new SparqlQuery("C:\\Users\\matth\\IdeaProjects\\Web-Semantique-app\\src\\resources\\steam.owl");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        nbSellColumn.setCellValueFactory(new PropertyValueFactory<>("nbSell"));
        nbPlayerColumn.setCellValueFactory(new PropertyValueFactory<>("nbPlayers"));


    }

    @FXML
    void getGamesList(ActionEvent event) {
        String query ="PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                "PREFIX steam: <http://www.semanticweb.org/corentingrard/ontologies/2019/2/steam#>\n" +
                "\n" +
                "SELECT *\n" +
                "    WHERE { ?titre a steam:Jeu ;\n" +
                "        steam:nombreDeVentes ?nbVente;\n" +
                "        steam:nombreDeJoueurs ?nbJoueurs;\n" +
                "                  }";
        ArrayList<Game> gameList = sparqlQuery.makeQuery(query);
        resultTable.setItems(FXCollections.observableArrayList(gameList));
    }
}
