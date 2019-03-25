package app.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Game {
    private StringProperty title;
    private IntegerProperty nbSell;
    private IntegerProperty nbPlayers;

    public Game(String title, int nbSell, int nbPlayers) {
        this.title = new SimpleStringProperty(title);
        this.nbSell = new SimpleIntegerProperty(nbSell);
        this.nbPlayers = new SimpleIntegerProperty(nbPlayers);
    }

    public int getNbPlayers() {
        return nbPlayers.get();
    }

    public int getNbSell() {
        return nbSell.get();
    }

    public String getTitle() {
        return title.get();
    }
}
