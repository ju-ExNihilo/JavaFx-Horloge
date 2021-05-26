package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {

    private double xOffset;
    private double yOffset;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("Horloge");
        primaryStage.getIcons().add(new Image(this.getClass().getResource("clock.png").toExternalForm()));
        primaryStage.setScene(scene);
        mousseEvent(scene, primaryStage);
        primaryStage.show();
    }


    private void mousseEvent(Scene scene, Stage dialogStage){
        scene.setOnMousePressed(event -> {
            if (event.isSecondaryButtonDown()) {
                ContextMenu ctxMenuIm = new ContextMenu();
                EventHandler<ActionEvent> action = changeTabPlacement();
                ImageView imageClose = new ImageView(this.getClass().getResource("close.png").toExternalForm());
                MenuItem mniClose = new MenuItem("  "+ "Close" +"\t\t", imageClose);
                mniClose.setId("Close");
                mniClose.setOnAction(action);
                ctxMenuIm.getItems().addAll(mniClose);
                ctxMenuIm.show(dialogStage, event.getScreenX(), event.getScreenY());
            }else {
                xOffset = dialogStage.getX() - event.getScreenX();
                yOffset = dialogStage.getY() - event.getScreenY();
            }

        });

        scene.setOnMouseDragged(event -> {
            dialogStage.setX(event.getScreenX() + xOffset);
            dialogStage.setY(event.getScreenY() + yOffset);

        });
    }

    private  EventHandler<ActionEvent> changeTabPlacement() {
        return event -> {
            MenuItem mItem = (MenuItem) event.getSource();
            String side = mItem.getId();

            if ("Close".equalsIgnoreCase(side)) {
                System.exit(0);
            }
        };
    }

    public static void main(String[] args) {
        launch(args);
    }
}
