package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class HomeController extends AppCompatActivity {

    Button GetStartedButton;
    TextView DayTime, Productive_Time;
    ImageView minuteDial, hourDial;
    private Handler handler;
    private Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        DayTime = findViewById(R.id.DayTime);
        Productive_Time = findViewById(R.id.taskname);
        minuteDial = findViewById(R.id.minuteDial);
        hourDial = findViewById(R.id.hourDial);

        // Set the pivot points to the center of the dials
        minuteDial.post(() -> {
            minuteDial.setPivotX(minuteDial.getWidth() / 2);
//            minuteDial.setPivotX(100);

            minuteDial.setPivotY(minuteDial.getHeight());
        });

        hourDial.post(() -> {
            hourDial.setPivotX(hourDial.getWidth()/2);
            hourDial.setPivotY(hourDial.getHeight());
//            hourDial.setPivotY(hourDial.getHeight() - (hourDial.getHeight() / 2));
        });

//        // Start the clock
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
////                Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();

//                System.out.println("hello");
                updateClock();
                handler.postDelayed(this, 1000); // Repeat every second
            }
        };

        handler.post(runnable);

        GetStartedButton = findViewById(R.id.GetStartedButton);
//        GetStartedButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Define an intent to open the SettingsActivity
//                Intent intent = new Intent(HomeController.this, TimerController.class);
//
//                // Start the SettingsActivity
//                startActivity(intent);
//            }
//        });
    }

    private void updateClock() {
        Calendar now = Calendar.getInstance();
        int hours = now.get(Calendar.HOUR);
        int minutes = now.get(Calendar.MINUTE);
        int amPm = now.get(Calendar.AM_PM);

        // Update time display
        String timeSuffix = (amPm == Calendar.AM) ? " AM!" : " PM!";
        DayTime.setText("It's " + (hours == 0 ? 12 : hours) + ":" + String.format("%02d", minutes) + timeSuffix);
//        Toast.makeText(this, "hello" + hours, Toast.LENGTH_SHORT).show();

        // Calculate angles for the hour and minute hands
        double hourAngle = (hours % 12) * 30 + (minutes / 60.0) * 30; // 30 degrees per hour
        double minuteAngle = minutes * 6; // 6 degrees per minute

        // Rotate the dials
        hourDial.setRotation((float) hourAngle);
        minuteDial.setRotation((float) minuteAngle);

//        hourDial.setRotation(90);
//        minuteDial.setRotation(360);

        // Update Productive_Time text
        if (amPm == Calendar.AM) {
            Productive_Time.setText("Let's Have a Productive Morning!");

        } else {
            if (hours < 6 || (hours == 12 && minutes == 0)) {
                Productive_Time.setText("Let's Have a Productive Afternoon!");
            } else {
                Productive_Time.setText("Let's Have a Productive Evening!");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable); // Stop the clock updates when the activity is destroyed
    }


}

