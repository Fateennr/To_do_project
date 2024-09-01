package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;

public class TimerActivity extends AppCompatActivity {

    private List<Task> tasks;
    private int currentTaskIndex = 0;
    private boolean isRunning = false;
    private boolean isWorkPeriod = true;
    private int remainingSeconds;
    private CountDownTimer countDownTimer;

    private TextView timerTextView;
    private TextView taskNameTextView;
    private ConstraintLayout backgroundLayout;
    private Button startPauseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer);

        timerTextView = findViewById(R.id.Timer);
        taskNameTextView = findViewById(R.id.taskname);
        backgroundLayout = findViewById(R.id.main);
        startPauseButton = findViewById(R.id.TimerButton);

        // Get the task list from the intent
        tasks = getIntent().getParcelableArrayListExtra("tasks");

        // Set up the first task
        setTask(currentTaskIndex);

        startPauseButton.setOnClickListener(v -> startPauseTimer());
    }

    private void setTask(int taskIndex) {
        Task task = tasks.get(taskIndex);
        taskNameTextView.setText(task.getTaskName());
        remainingSeconds = task.getMM() * 60 + task.getSS();
        updateTimerLabel();
    }

    @SuppressLint("SetTextI18n")
    private void startPauseTimer() {
        if (isRunning) {
            isRunning = false;
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
            startPauseButton.setText("Start");
        } else {
            isRunning = true;
            startPauseButton.setText("Pause");

            if (isWorkPeriod) {
                backgroundLayout.setBackgroundColor(getResources().getColor(android.R.color.white));
            } else {
                backgroundLayout.setBackgroundColor(getResources().getColor(R.color.blue));
            }

            countDownTimer = new CountDownTimer(remainingSeconds * 1000L, 1000) {
                public void onTick(long millisUntilFinished) {
                    remainingSeconds = (int) (millisUntilFinished / 1000);
                    updateTimerLabel();
                }

                public void onFinish() {
                    isWorkPeriod = !isWorkPeriod;
                    if (isWorkPeriod) {
                        currentTaskIndex++;
                        if (currentTaskIndex >= tasks.size()) {
                            showCompletionMessage();
                        } else {
                            setTask(currentTaskIndex);
                        }
                    } else {
                        // Handle break time (you can add logic to set break times here)
                        backgroundLayout.setBackgroundColor(getResources().getColor(R.color.blue));
                        remainingSeconds = 300; // Example: 5-minute break
                        updateTimerLabel();
                        startPauseTimer();
                    }
                }
            };
            countDownTimer.start();
        }
    }

    @SuppressLint("DefaultLocale")
    private void updateTimerLabel() {
        int minutes = remainingSeconds / 60;
        int seconds = remainingSeconds % 60;
        timerTextView.setText(String.format("%02d:%02d", minutes, seconds));
    }

    @SuppressLint("SetTextI18n")
    private void showCompletionMessage() {
        taskNameTextView.setText("You are done!");
        backgroundLayout.setBackgroundColor(getResources().getColor(R.color.red));
        startPauseButton.setVisibility(View.GONE);
    }
}


