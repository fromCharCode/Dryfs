package main.scenes;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MenuMainScene extends Scene {

    Label playLabel;
    Label optLabel;
    Label exitLabel;

    HBox root;
    VBox menu;

    public MenuMainScene(Stage stage) {
        super(stage);
        init();
        setUpPane();
        getChildren().add(root);
    }

    private void init(){
        initMenu();
        initCanvas();
    }

    private void initMenu(){
        // use fonts here

        //Text playText = new Text("Play").setFont(...);

        playLabel = new Label("Play");
        optLabel = new Label("Optionen");
        exitLabel = new Label("Exit");

        // add handlers to this labels


    }

    private void setUpPane(){
        root = new HBox();

        menu = new VBox();
        menu.getChildren().addAll(playLabel, optLabel, exitLabel);
        menu.setSpacing(1080/3f);

        root.getChildren().add(menu);

    }

    private void initCanvas(){

    }
}
