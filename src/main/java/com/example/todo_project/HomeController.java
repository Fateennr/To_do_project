package com.example.todo_project;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

import java.time.LocalTime;

public class HomeController {

    @FXML
    private Polygon ClockDial;

    @FXML
    private Label DayTime;

    @FXML
    private Label Productive_Time;

    @FXML
    private void initialize() {
        updateClock();
        startClock();
    }

    private void updateClock() {
        LocalTime now = LocalTime.now();
        int hours = now.getHour();
        int minutes = now.getMinute();
        int seconds = now.getSecond();

        // Update time display
        DayTime.setText("It's " + hours%12 + ":" + minutes + (hours > 12 ? " PM!" : " AM!"));

        // Calculate angle for the hour hand
        double hourAngle = (hours % 12) * 30 + (minutes / 60.0) * 30; // 30 degrees per hour
        double minuteAngle = minutes * 6; // 6 degrees per minute

        // Rotate the clock dial
        ClockDial.setRotate(hourAngle);
        Productive_Time.setText("Let's have a Productive" + (hours < 12 ? " Morning!" : (hours < 18 ? " Afternoon!" : " Evening!")));

        // Optionally, update minute and second hands here
    }

    private void startClock() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateClock()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}

