/*
 * Author:              fromCharCode - David Schneider
 * Contribution:        none
 * Date of creation:    01.06.2019
 * Deadline:            -
 * Information:         In this class we use the factory method design pattern to create the different layouts
 *
 * Notes:               
 *
 */

package main.scenes;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.debug.Logger;

public class PaneFactory {

    Stage stage;

    public Pane createPane(PaneType type, Stage stage){
        Pane root;
        this.stage = stage;
        Logger.log(this.getClass(), ("Creating pane object of Type: " + type.toString()));
        switch (type.getInstance()){
            case MAINMENU:
                root = new MenuMainScene(stage);
                break;
            case GAMESCENE:
                root = new GameScene(stage);
                break;
            case PLAYERMENU:
                root = new MenuPlayerScene(stage);
                break;
            case OPTIONSMENU:
                root = new MenuOptionsScene(stage);
                break;

                default: return null;
        }

        return root;
    }
}