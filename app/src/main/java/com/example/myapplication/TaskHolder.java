package com.example.myapplication;



import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

public class TaskHolder extends RecyclerView.ViewHolder {

    TextInputEditText taskName;
    TextInputEditText taskTime;

    public TaskHolder(@NonNull View itemView) {
        super(itemView);

        taskName = itemView.findViewById(R.id.taskname);
        taskTime = itemView.findViewById(R.id.tasktime);

    }
}
