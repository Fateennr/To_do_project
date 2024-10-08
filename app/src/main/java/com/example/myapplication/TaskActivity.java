package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TaskActivity extends AppCompatActivity {
    private TaskAdapter taskAdapter;
    private List<Task> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_selection);

        RecyclerView recyclerView = findViewById(R.id.taskselectionview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        taskList = new ArrayList<>();
        taskList.add(new Task("", 0, 0)); // Add the initial task item

        taskAdapter = new TaskAdapter(taskList);
        recyclerView.setAdapter(taskAdapter);

        ImageView plusButton = findViewById(R.id.plus);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add a new empty task item when the plus button is clicked
                taskAdapter.addTask(new Task("", 0 , 0));
            }
        });

        Button proceedButton = findViewById(R.id.StartTimerButton);
        proceedButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Iterate through all items in the RecyclerView
                for (int i = 0; i < recyclerView.getChildCount(); i++) {
                    View itemView = recyclerView.getChildAt(i);

                    EditText taskNameInput = itemView.findViewById(R.id.taskname);
                    EditText taskTimeInput = itemView.findViewById(R.id.tasktime);

                    String taskName = taskNameInput.getText().toString();
                    String taskTime = taskTimeInput.getText().toString();

                    // Update the corresponding Task object in the list
                    Task task = taskList.get(i);
                    task.setTaskName(taskName);
                    Log.d("TaskDebug", "Task " + i + ": Name = " + task.getTaskName() + ", Time = " + task.getMM()+":"+task.getSS());


                    if(taskTime == "" || taskTime == ":") //fixes needed, for input ":"
                    {
                        task.setTaskTime(0, 5);
                    }
                    else {
                        processTime(taskTime , task);
                    }
//                    task.setTaskTime(taskTime);


                }

                // Now proceed to the next activity with the updated task list
                ArrayList<String> taskNames = new ArrayList<>();
                ArrayList<Integer> taskMM = new ArrayList<>();
                ArrayList<Integer> taskSS = new ArrayList<>();

                for (Task task : taskList) {
                    taskNames.add(task.getTaskName());
                    taskMM.add(task.getMM());
                    taskSS.add(task.getSS());
                }

                Intent intent = new Intent(TaskActivity.this, TaskSummaryActivity.class);
                intent.putStringArrayListExtra("taskNames", taskNames);
                intent.putIntegerArrayListExtra("taskMM", taskMM);
                intent.putIntegerArrayListExtra("taskSS", taskSS);
                startActivity(intent);
            }

        });

    }

    // process time inputs
    public void processTime(String taskTime, Task task) {
        // Split the input time by the first occurrence of ":"

        int colonIndex = taskTime.indexOf(":");

        int minutes = 0, seconds = 0;
        String minutePart, secondPart;

        if (colonIndex == -1) {

            if(taskTime.length()<=3) // if minutepart is less than 3
            {
                minutePart = taskTime;
            }
            else // if minutepart is greater than 3 rest will be ignored
            {
                minutePart = taskTime.substring(0, 3);
            }

            // If no colon is found, treat the entire input as minutes and append ":00"
            minutes = Integer.parseInt(minutePart);
            task.setTaskTime(minutes, 0);


        }
        else if(colonIndex == 0) // if colon is in front
        {
            if(taskTime.length()<=3) // if length is :12 or less than or equal to 3
            {
                secondPart = taskTime.substring(1);

            }
            else { // if length is greater than 3

                secondPart = taskTime.substring(1, 3);
            }

            seconds = Integer.parseInt(secondPart);

            if(seconds >= 60)
            {
                seconds = seconds%60;
                minutes += 1;
            }

            task.setTaskTime(minutes, seconds);
        }
        else
        {
            // Extract the minute and second parts
            minutePart = taskTime.substring(0, colonIndex);

            if(minutePart.length() > 3)
            {
                minutePart = minutePart.substring(0,3);
            }

            secondPart = taskTime.substring(colonIndex + 1);

            // If there are multiple colons, take only the first part after the first colon
            int secondColonIndex = secondPart.indexOf(":");

            if (secondColonIndex != -1)
            {
                secondPart = secondPart.substring(0, secondColonIndex);
            }

            if(secondPart.length() > 2)
            {
                secondPart = secondPart.substring(0,2);
            }

            // Convert minute and second parts to integers
            minutes = Integer.parseInt(minutePart);
            seconds = Integer.parseInt(secondPart);

            // If seconds are between 60 and 99, mod with 60 and add 1 to minutes
            if (seconds >= 60)
            {
                seconds = seconds % 60;
                minutes += 1;
            }

            // Format the seconds to always be two digits
//            String formattedSeconds = String.format("%02d", seconds);


            // Return the processed time in "mm:ss" format
            task.setTaskTime(minutes, seconds);
        }
    }

}

