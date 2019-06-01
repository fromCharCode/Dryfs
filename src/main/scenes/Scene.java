package main.scenes;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public abstract class Scene extends Pane {

    double width = 1920;
    double height = 1080;

    String title = "Draw your Freak Show";
    public Stage stage;

    public Scene(Stage stage) {
        this.stage = stage;
    }

    public Pane getPane() {
        return this;
    }

    public Stage getStage() {
        return stage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
