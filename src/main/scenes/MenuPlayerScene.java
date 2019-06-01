package main.scenes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuPlayerScene extends Scene {

    // == attributes ==
    // root
    HBox root;

    // menu
    VBox menu;

    Label section;
    Button decSec;
    int secAmount = 3; // just for default
    Label secAmountText;
    Button incSec;

    Button colorModePrev;
    Label colorMode;
    Button colorModeNext;

    // x-scale
    Label xScale;
    Button decXScale;
    Label xScaleValue;
    int xs = 1920;
    Button incXScale;
    // optional: button for infinite x-scale

    // y-scale
    Label yScale;
    Button decYScale;
    Label yScaleValue;
    int ys = 1080;
    Button incYScale;
    // optional: button for infinite x-scale

    Label background;
    ColorPicker cp;

    Button playButton;

    // == constructor ==
    public MenuPlayerScene(Stage stage){
        super(stage);
        init();
        setUpPane();
        getChildren().add(root);
    }

    private void init(){
        initMenu();
    }

    private void initMenu(){

        section = new Label("Anzahl Sektionen: ");
        decSec = new Button("<");
        secAmountText = new Label(Integer.toString(secAmount));
        incSec = new Button(">");

        colorModePrev = new Button("<");
        colorMode = new Label(".Schwarz-Weiss.");
        colorModeNext = new Button(">");

        xScale = new Label("X-Scale: ");
        decXScale = new Button("<");
        xScaleValue = new Label(Integer.toString(xs)); // parse to int
        incXScale = new Button(">");

        yScale = new Label("Y-Scale: ");
        decYScale = new Button("<");
        yScaleValue = new Label(Integer.toString(ys)); // parse to int
        incYScale = new Button(">");

        background = new Label("Hintergrund: ");
        cp = new ColorPicker();

        playButton = new Button("GO!");

        // ============================================================

        decSec.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                secAmount--;
                secAmountText.setText(Integer.toString(secAmount));
            }
        });

        incSec.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                secAmount++;
                secAmountText.setText(Integer.toString(secAmount));
            }
        });

        // =======
        // we need color mode here <<<<<
        // =======

        decXScale.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                xs--;
                xScaleValue.setText(Integer.toString(xs));
            }
        });

        incXScale.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                xs++;
                xScaleValue.setText(Integer.toString(xs));
            }
        });

        decYScale.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ys--;
                yScaleValue.setText(Integer.toString(ys));
            }
        });

        incYScale.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ys++;
                yScaleValue.setText(Integer.toString(ys));
            }
        });

        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PaneFactory factory = new PaneFactory();
                Pane root = factory.createPane(PaneType.GAMESCENE, getStage());
                javafx.scene.Scene scene = new javafx.scene.Scene(root, width, height);
                stage.setScene(scene);
            }
        });
    }

    private void setUpPane(){
        root = new HBox();

        menu = new VBox();

        HBox[] rows = new HBox[5];

        for (int i = 0; i < rows.length; i++){
           rows[i] = new HBox();

           switch (i){
               case 0:
                   rows[0].getChildren().addAll(section, decSec, secAmountText, incSec);
                   break;
               case 1:
                   rows[1].getChildren().addAll(colorModePrev, colorMode, colorModeNext);
                   break;
               case 2:
                   rows[2].getChildren().addAll(xScale, decXScale, xScaleValue, incXScale);
                   break;
               case 3:
                   rows[3].getChildren().addAll(yScale, decYScale, yScaleValue, incYScale);
                   break;
               case 4:
                   rows[4].getChildren().addAll(background, cp);
                   break;

                   default: break;
           }
        }

        for (int i = 0; i < rows.length; i++){
            menu.getChildren().add(rows[i]);
        }

        // 1080 must be replaced with height
        menu.setSpacing(1080/menu.getChildren().size());

        root.getChildren().add(menu);

        root.getChildren().add(playButton);

    }

}
