package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class Task implements Parcelable {

    private String taskName;
    private int MM;
    private int SS;

    public Task(String taskName, int MM, int SS) {
        this.taskName = taskName;
        this.MM = MM;
        this.SS = SS;
    }

    protected Task(Parcel in) {
        taskName = in.readString();
        MM = in.readInt();
        SS = in.readInt();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(taskName);
        dest.writeInt(MM);
        dest.writeInt(SS);
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getMM() {
        return MM;
    }

    public void setMM(int MM) {
        this.MM = MM;
    }

    public int getSS() {
        return SS;
    }

    public void setSS(int SS) {
        this.SS = SS;
    }

    public void setTaskTime(int MM, int SS) {
        this.MM = MM;
        this.SS = SS;
    }
}



//package com.example.myapplication;
//
//public class Task  {
//
//    private String taskName;
//
//
////    private String taskTime;
//
//    public void setMM(int MM) {
//        this.MM = MM;
//    }
//
//    private int MM;
//
//    public void setSS(int SS) {
//        this.SS = SS;
//    }
//
//    private int SS;
//
//
//    public int getMM() {
//        return MM;
//    }
//
//    public int getSS() {
//        return SS;
//    }
//
//    public Task(String taskName, int MM, int SS) {
//        this.taskName = taskName;
////        this.taskTime = taskTime;
//        this.MM = MM;
//        this.SS = SS;
//    }
//
//
//    public String getTaskName() {
//        return taskName;
//    }
//
//    public void setTaskName(String taskName) {
//        this.taskName = taskName;
//    }
//
////    public String getTaskTime() {
////        return taskTime;
////    }
//
//    public void setTaskTime(int MM, int SS) {
//        this.MM = MM;
//        this.SS = SS;
//    }
//
//}
