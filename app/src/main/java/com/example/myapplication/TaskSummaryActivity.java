package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TaskSummaryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_summary_view);

        Intent intent = getIntent();
        ArrayList<String> taskNames = intent.getStringArrayListExtra("taskNames");
        ArrayList<String> taskTimes = intent.getStringArrayListExtra("taskTimes");

        // Assuming you have a RecyclerView or other view in task_summary_view.xml to display the tasks
        RecyclerView summaryRecyclerView = findViewById(R.id.tasksummaryview);
        summaryRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < taskNames.size(); i++) {
            tasks.add(new Task(taskNames.get(i), taskTimes.get(i)));
        }

        TaskSummaryAdapter summaryAdapter = new TaskSummaryAdapter(tasks);
        summaryRecyclerView.setAdapter(summaryAdapter);
    }
}

