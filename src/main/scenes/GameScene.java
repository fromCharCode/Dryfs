package main.scenes;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import main.BrushType;

import java.text.DecimalFormat;

public class GameScene extends Pane {

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

    Canvas gameCanvas;

    double menuWidth;


    // == constructor ==
    public GameScene(){
        init();
        setUpPane();
        getChildren().add(root);
    }


    // == private methods ==
    private void init(){
        initMenu();
        initCanvas();
    }

    private String roundedDoubleToString(double value){
        return String.format("%.3f", value);
    }

    private void setUpPane(){
        root = new BorderPane();

        VBox left = new VBox();
        left.setSpacing(10f);
        left.getChildren().addAll(cp, brush, brushes);

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
    }

    private void initCanvas(){
        // create the canvas, get the part for drawing
        gameCanvas = new Canvas(1920, 1080);
        GraphicsContext gc = gameCanvas.getGraphicsContext2D();

        // draw background
        gc.setFill(Color.rgb(200, 200, 200));
        gc.fillRect(0, 0, 1920, 1080);

        gameCanvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                gc.setFill(cp.getValue());
                gc.setLineWidth(1f);


                System.out.println("Mouse: " + event.getSceneX() + "| " + event.getSceneY() + " <|> our logical x: " + (event.getSceneX() + root.getLeft().getScaleX()));
                System.out.println("Distance between: " + (event.getSceneX() - (event.getSceneX() - root.getLeft().getScaleX())));

                VBox tmp = (VBox) root.getLeft();
                draw(gc, event.getSceneX() - tmp.getWidth(), event.getSceneY(), slider.getValue());
            }
        });
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