/*
 * Author:              fromCharCode - David Schneider
 * Contribution:        none
 * Date of creation:    01.06.2019
 * Deadline:            -
 * Information:         Abstract scene extending pane. Will be used in PaneFactory
 *
 * Notes:               todo: clean up: create just one main menu scene, use this, delete output
 *
 */

package main.scenes;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public abstract class Scene extends Pane {

    // those will be bound to window size later
    double width = 1700;
    double height = 800;

    String title = "Draw your Freak Show";
    Stage stage;

    Scene(Stage stage) {
        this.stage = stage;
    }

    Stage getStage() {
        return stage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
