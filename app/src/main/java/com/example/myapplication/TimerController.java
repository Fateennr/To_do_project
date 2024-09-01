//package com.example.todo_project;
//
//import javafx.animation.Animation;
//import javafx.animation.KeyFrame;
//import javafx.animation.Timeline;
//import javafx.application.Platform;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.Pane;
//import javafx.util.Duration;
//
//public class TimerController {
//
//    @FXML
//    private Button StartPauseButton;
//
//    @FXML
//    private Label TaskName;
//
//    @FXML
//    private Label TimeRemaining;
//
//    @FXML
//    private Label TimerOn;
//
//
////        @FXML
////    private TextField workDurationField;
////
////    @FXML
////    private TextField breakDurationField;
////
////    @FXML
////    private Label timerLabel;
////
////    @FXML
////    private Label title;
//
//    @FXML
//    private Pane Background;
//
//    private int workDuration = 5; //default
//    private int breakDuration = 5;
//    private boolean isRunning = false;
//    private boolean isWorkPeriod = true;
//
////        private int remainingSeconds = workDuration * 60;
//    private int remainingSeconds = 5;
//    private Timeline timeline;
//
//    public void startPauseTimer()
//    {
//        if (isRunning)
//        {
//            isRunning = false;
//            timeline.stop();
//            StartPauseButton.setStyle("-fx-background-color: #1ec58e;\n" +
//                    "    -fx-background-radius: 30;");
//            StartPauseButton.setText("Start");
//        }
//        else {
//            isRunning = true;
//
//            StartPauseButton.setStyle("-fx-background-color: #F74955;\n" +
//                    "    -fx-background-radius: 30;");
//            StartPauseButton.setText("Pause");
//
//            if (isWorkPeriod){
//                TimerOn.setText("Your Timer is On!");
//                Background.setStyle("-fx-background-color: #ffffff;");
//                TimerOn.setStyle("-fx-text-fill: #898080;");
//                TimeRemaining.setStyle("-fx-text-fill: #898080;");
//                TaskName.setStyle("-fx-text-fill: #898080;");
//
//            }else {
//                TimerOn.setText("Relax a bit now!");
//                Background.setStyle("-fx-background-color: #82C0F6;");
//            }
//
//            timeline = new Timeline(
//                    new KeyFrame(Duration.seconds(1), event -> {
//                        remainingSeconds--;
//                        updateTimerLabel();
//                        if (remainingSeconds == 0)
//                        {
//                            timeline.stop();
//                            TimerOn.setText("Breaktime!!");
//                            Background.setStyle("-fx-background-color: F74955;");
//                            TimerOn.setStyle("-fx-text-fill: #ffffff;");
//                            TimeRemaining.setStyle("-fx-text-fill: #ffffff;");
//                            TaskName.setStyle("-fx-text-fill: #ffffff;");
//
//                            StartPauseButton.setStyle("-fx-background-color: #1ec58e;\n" +
//                                    "    -fx-background-radius: 30;");
//                            StartPauseButton.setText("Done");
//
//                            isRunning = false;
//
////                            Platform.runLater(() -> {
//                            isWorkPeriod = !isWorkPeriod;
////                            remainingSeconds = (isWorkPeriod ? workDuration : breakDuration) *60;
//                            remainingSeconds = (isWorkPeriod ? workDuration : breakDuration);
//
//                            updateTimerLabel();
//
////                            });
//                        }
//                    })
//            );
//            timeline.setCycleCount(Animation.INDEFINITE);
//            timeline.play();
//        }
//    }
//
//    public void resetTimer() {
//        isRunning = false;
//        isWorkPeriod = true;
//        remainingSeconds = (isWorkPeriod ? workDuration : breakDuration) * 60;
//        updateTimerLabel();
//        if (timeline != null) {
//            timeline.stop();
//        }
//    }
//
//    private void updateTimerLabel() {
//        int minutes = remainingSeconds / 60;
//        int seconds = remainingSeconds % 60;
//        TimeRemaining.setText(String.format("%02d : %02d", minutes, seconds));
//    }
//
//    @FXML
//    private void initialize() {
////        workDurationField.setText(String.valueOf(workDuration));
////        breakDurationField.setText(String.valueOf(breakDuration));
//        updateTimerLabel();
//        startPauseTimer();
//
//    }
//
//}


//package org.example.pomodoro_fateen;
//
//import javafx.animation.Animation;
//import javafx.animation.KeyFrame;
//import javafx.animation.Timeline;
//import javafx.application.Platform;
//import javafx.fxml.FXML;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.util.Duration;
//
//public class PomodoroTimerController {
//
//    @FXML
//    private TextField workDurationField;
//
//    @FXML
//    private TextField breakDurationField;
//
//    @FXML
//    private Label timerLabel;
//
//    @FXML
//    private Label title;
//
//    private int workDuration = 25; //default
//    private int breakDuration = 5;
//    private boolean isRunning = false;
//    private boolean isWorkPeriod = true;
//
//    //    private int remainingSeconds = workDuration * 60;
//    private int remainingSeconds = 5;
//    private Timeline timeline;
//
//    public void startPauseTimer() {
//        if (isRunning) {
//            isRunning = false;
//            timeline.stop();
//        } else {
//            isRunning = true;
//            if(isWorkPeriod)
//                title.setText("Focus!!");
//            else
//                title.setText("Relax a bit");
//            timeline = new Timeline(
//                    new KeyFrame(Duration.seconds(1), event -> {
//                        remainingSeconds--;
//                        updateTimerLabel();
//                        if (remainingSeconds == 0)
//                        {
//                            timeline.stop();
//                            title.setText("Breaktime!!");
//
//                            isRunning = false;
//
////                            Platform.runLater(() -> {
//                            isWorkPeriod = !isWorkPeriod;
//                            remainingSeconds = (isWorkPeriod ? workDuration : breakDuration) * 60;
//                            updateTimerLabel();
////                            });
//                        }
//                    })
//            );
//            timeline.setCycleCount(Animation.INDEFINITE);
//            timeline.play();
//        }
//    }
//
//    public void resetTimer() {
//        isRunning = false;
//        isWorkPeriod = true;
//        remainingSeconds = (isWorkPeriod ? workDuration : breakDuration) * 60;
//        updateTimerLabel();
//        if (timeline != null) {
//            timeline.stop();
//        }
//    }
//
//    private void updateTimerLabel() {
//        int minutes = remainingSeconds / 60;
//        int seconds = remainingSeconds % 60;
//        timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
//    }
//
//    @FXML
//    private void initialize() {
////        workDurationField.setText(String.valueOf(workDuration));
////        breakDurationField.setText(String.valueOf(breakDuration));
//        updateTimerLabel();
//    }
//}
