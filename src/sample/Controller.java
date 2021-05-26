package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Label timeLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bindToTime();

    }

    private void bindToTime() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        actionEvent -> {
                            Calendar time1 = Calendar.getInstance();
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                            timeLabel.setText(simpleDateFormat.format(time1.getTime()));
                        }
                ),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}

