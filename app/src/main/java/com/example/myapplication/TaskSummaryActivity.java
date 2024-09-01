package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TaskSummaryActivity extends AppCompatActivity {

    List<Task> tasks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_summary_view);

        Intent intent = getIntent();
        ArrayList<String> taskNames = intent.getStringArrayListExtra("taskNames");
        ArrayList<Integer> taskMM = intent.getIntegerArrayListExtra("taskMM");
        ArrayList<Integer> taskSS = intent.getIntegerArrayListExtra("taskSS");


        // Assuming you have a RecyclerView or other view in task_summary_view.xml to display the tasks
        RecyclerView summaryRecyclerView = findViewById(R.id.tasksummaryview);
        summaryRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        for (int i = 0; i < Objects.requireNonNull(taskNames).size() ; i++) {
            assert taskMM != null;
            assert taskSS != null;
            tasks.add(new Task(taskNames.get(i), taskMM.get(i), taskSS.get(i)));

            if(i != taskNames.size()-1) {
                tasks.add(new Task("Break", processBreakTime(taskMM.get(i)), 0));
            }
        }

        TaskSummaryAdapter summaryAdapter = new TaskSummaryAdapter(tasks);
        summaryRecyclerView.setAdapter(summaryAdapter);

        Button proceedButton = findViewById(R.id.StartTimerButton);
        proceedButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TaskSummaryActivity.this, TimerActivity.class);
                intent.putParcelableArrayListExtra("tasks", new ArrayList<>(tasks));
                startActivity(intent);
            }

        });

    }

    public int processBreakTime(int MM)
    {
        if(MM > 15 && MM <= 25)
        {
            return 5;
        }
        else if(MM > 25)
        {
            int breaktime = ( MM / 25 ) * 5;

            if(breaktime > 40) { breaktime = 40 ; }

            return breaktime;
        }
        else
        {
            return 2;
        }

    }
}

