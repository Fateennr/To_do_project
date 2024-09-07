package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;

public class TimerActivity extends AppCompatActivity
{

    private List<Task> tasks;
    private int currentTaskIndex = 0;
    private boolean isRunning = false;
    private boolean isWorkPeriod = true;
    private int remainingSeconds;
    private CountDownTimer countDownTimer;

    private TextView timerTextView;
    private TextView taskNameTextView;
    private TextView timer_on;
    private ConstraintLayout backgroundLayout;
    private Button startPauseButton;
    private ImageView check;
    private ImageView taskIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer);


        timerTextView = findViewById(R.id.Timer);
        taskNameTextView = findViewById(R.id.taskname);
        backgroundLayout = findViewById(R.id.main);
        startPauseButton = findViewById(R.id.TimerButton);
        timer_on = findViewById(R.id.timeron);
        check = findViewById(R.id.check);
        taskIcon = findViewById(R.id.taskIcon);

        // Get the task list from the intent
        tasks = getIntent().getParcelableArrayListExtra("tasks");

//        // Set up the first task
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
        if (isRunning)
        {
            isRunning = false;
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
            startPauseButton.setBackgroundColor(getResources().getColor(R.color.green));
            startPauseButton.setText("Start Timer!");
        }
        else
        {
            isRunning = true; //if timer is running
            startPauseButton.setText("Pause");
            startPauseButton.setBackgroundColor(getResources().getColor(R.color.red));

            if (isWorkPeriod)
            {
                backgroundLayout.setBackgroundColor(getResources().getColor(android.R.color.white));

            }
            else
            {
                backgroundLayout.setBackgroundColor(getResources().getColor(R.color.blue));
            }

            countDownTimer = new CountDownTimer(remainingSeconds * 1000L, 1000)
            {
                public void onTick(long millisUntilFinished)
                {
                    remainingSeconds = (int) (millisUntilFinished / 1000);
                    updateTimerLabel();
                }

                @SuppressLint("ResourceAsColor")
                public void onFinish()
                {
                    isRunning = false;
                    startPauseButton.setBackgroundColor(R.color.green);
                    startPauseButton.setText("Start timer!");

                    isWorkPeriod = !isWorkPeriod;
                    if (isWorkPeriod)
                    {
                        backgroundLayout.setBackgroundColor(getResources().getColor(R.color.white));
                        timer_on.setTextColor(R.color.gray);
                        timerTextView.setTextColor(R.color.gray);
                        taskNameTextView.setTextColor(R.color.gray);

                    }
                    else
                    {
                        backgroundLayout.setBackgroundColor(getResources().getColor(R.color.blue));
                        timerTextView.setText("Relax a bit now!");
                        timer_on.setTextColor(R.color.white);
                        timerTextView.setTextColor(R.color.white);
                        taskNameTextView.setTextColor(R.color.white);
                        updateTimerLabel();
//                        startPauseTimer();
                    }

                    currentTaskIndex++;
                    if (currentTaskIndex >= tasks.size())
                    {
                        showCompletionMessage();
                    }
                    else
                    {
                        setTask(currentTaskIndex);
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
        timer_on.setText("You are done!");
        backgroundLayout.setBackgroundColor(getResources().getColor(R.color.white));
        startPauseButton.setVisibility(View.GONE);
        timerTextView.setVisibility(View.INVISIBLE);
        check.setVisibility(View.VISIBLE);
        taskIcon.setImageResource(R.drawable.taskcheck);
    }
}


