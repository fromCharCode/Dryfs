/*
 * Author:              fromCharCode - David Schneider
 * Contribution:        none
 * Date of creation:    01.06.2019
 * Deadline:            -
 * Information:         this class is the main.
 *                      JavaFX life cycle: launch(args) -> init() -> start() -> stop()
 *
 * Notes:               todo: clean up: create just one main menu scene, use this, delete output
 *
 */

package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.debug.Logger;
import main.scenes.PaneFactory;
import main.scenes.PaneType;
import main.types.BrushType;

import static main.types.BrushType.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        PaneFactory paneFactory = new PaneFactory();

        Pane mainMenu = paneFactory.createPane(PaneType.MAINMENU, primaryStage);
        Pane playerMenu = paneFactory.createPane(PaneType.PLAYERMENU, primaryStage);

        //Scene scene = new Scene(root, width, height);
        Pane root = paneFactory.createPane(PaneType.GAMESCENE, primaryStage);

        // todo: remove after debug
        root = playerMenu;
        Logger.log(this.getClass(), root.toString());

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Draw your Freak Show");  // replace with root.getTitle()
        primaryStage.setResizable(true);
        primaryStage.setFullScreen(false);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
