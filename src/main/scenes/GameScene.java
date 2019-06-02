package main.scenes;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.BrushType;
import main.debug.Logger;

public class GameScene extends Scene {

    // == attributes ==
    // Color Picker
    ColorPicker cp;
    // Slider with Label
    Label brushSizeText;
    Slider slider;
    Label value;
    double brushVal = 25;
    HBox brush;
    ComboBox brushes;
    static BrushType currentType = BrushType.OVAL;
    static BorderPane root;
    HBox clear;
    Button clearCanvas;
    ColorPicker bcp;

    Canvas gameCanvas;

    double menuWidth;


    // == constructor ==
    public GameScene(Stage stage){
        super(stage);
        init();
        setUpPane();
        getChildren().add(root);
    }


    // == private methods ==
    private void init(){
        // initTopMenu here
        initMenu(); // rename to initLeftMenu()
        initCanvas();
    }

    private String roundedDoubleToString(double value){
        return String.format("%.3f", value);
    }

    private void setUpPane(){
        root = new BorderPane();

        VBox left = new VBox();
        left.setSpacing(10f);
        left.getChildren().addAll(cp, brush, brushes, clear);

        // todo: create upper menu with dropdowns
        // File | Edit | ... |Exit
        // root.setTop();

        menuWidth = left.getWidth();

        root.setLeft(left);
        root.setCenter(gameCanvas);
    }

    private void initMenu(){
        // color picker
        cp = new ColorPicker();
        // slider with label
        slider = new Slider();
        slider.setMin(1);
        slider.setMax(100);
        slider.setValue(brushVal);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(25);
        slider.setMinorTickCount(5);
        brushSizeText = new Label("Brush size: ");
        value = new Label(roundedDoubleToString(slider.getValue()));

        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                value.setText(roundedDoubleToString(slider.getValue()));
            }
        });

        brush = new HBox();
        brush.getChildren().addAll(brushSizeText, slider, value);

        brushes = new ComboBox();
        brushes.getItems().addAll(BrushType.OVAL, BrushType.RECT);
        brushes.setValue(BrushType.OVAL);
        brushes.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                currentType = (BrushType) brushes.getValue();
            }
        });

        clear = new HBox();
        bcp = new ColorPicker();
        clearCanvas = new Button("Clear Canvas");
        clearCanvas.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                drawBackground(bcp.getValue());
            }
        });
        clear.getChildren().addAll(bcp, clearCanvas);
    }

    private void initCanvas(){
        // create the canvas, get the part for drawing
        gameCanvas = new Canvas(1700, 800);
        GraphicsContext gc = gameCanvas.getGraphicsContext2D();

        // draw background
        gc.setFill(Color.rgb(200, 200, 200));
        gc.fillRect(0, 0, 1700, 800);

        gameCanvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                gc.setFill(cp.getValue());
                gc.setLineWidth(1f);


                // == logging mouse positions during drawing
                double mx = event.getSceneX();
                double my = event.getSceneY();
                String content = String.format("MouseX: %.2f", event.getSceneX());
                content += String.format("| MouseY: %.2f", event.getSceneY());
                Logger.log(this.getClass(), content);
                // =========================================

                VBox tmp = (VBox) root.getLeft(); // needed for calculation of mouseX
                draw(gc, event.getSceneX() - tmp.getWidth(), event.getSceneY(), slider.getValue());
            }
        });
    }

    public void drawBackground(Color c){
        GraphicsContext gc = gameCanvas.getGraphicsContext2D();
        gc.setFill(c);
        gc.fillRect(0, 0, width, height);
    }

    private void draw(GraphicsContext gc, double mouseX, double mouseY, double sliderValue){
        // enum for brush state
        // switch(brushType) case: ...
        switch (currentType){
            case OVAL:
                gc.fillOval(mouseX - sliderValue/2, mouseY - sliderValue/2, sliderValue, sliderValue);
                break;

            case RECT:
                gc.fillRect(mouseX - sliderValue/2, mouseY - sliderValue/2, sliderValue, sliderValue);
                break;

            default: break;
        }
    }
}