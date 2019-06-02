package main;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.scenes.PaneFactory;
import main.scenes.PaneType;

import static main.BrushType.*;

public class Main extends Application {

    static BrushType currentType = OVAL;
    PaneFactory paneFactory;

    @Override
    public void start(Stage primaryStage) throws Exception{

        paneFactory = new PaneFactory();


        Pane mainMenu = paneFactory.createPane(PaneType.MAINMENU, primaryStage);
        Pane playerMenu = paneFactory.createPane(PaneType.PLAYERMENU, primaryStage);

        //Scene scene = new Scene(root, width, height);
        Pane root = paneFactory.createPane(PaneType.GAMESCENE, primaryStage);

        root = playerMenu;
        System.out.println(root);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Draw your Freak Show");  // replace with root.getTitle()
        primaryStage.setResizable(true);
        primaryStage.setFullScreen(false);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public PaneFactory getPaneFactory() {
        return paneFactory;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
