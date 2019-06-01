package main.scenes;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class PaneFactory {

    Stage stage;

    public Pane createPane(PaneType type, Stage stage){
        Pane root;
        this.stage = stage;
        switch (type.getInstance()){
            case MAINMENU:
                log("Creating MainMenu..");
                root = new MenuMainScene();
                break;
            case GAMESCENE:
                log("Creating GameScene..");
                root = new GameScene();
                break;
            case PLAYERMENU:
                log("Creating PlayerMenu..");
                root = new MenuPlayerScene(stage);
                break;
            case OPTIONSMENU:
                log("Creating OptionsMenu..");
                root = new MenuOptionsScene();
                break;

                default: return null;
        }

        log("Pane root: " + root + "| elems: " + root.getChildren().size());
        ObservableList<Node> children = root.getChildren();
        for (Node n : children){
            log(n.toString());
        }
        return root;
    }

    private void log(String content){
        System.out.println(this.getClass().toString() + ": " + content);
    }
}