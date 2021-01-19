package com.example.task_13.models;

import java.io.Serializable;

public class HistoryItem implements Serializable {
    public String value;
    public String date;

    @Override
    public String toString() {
        return "value:" + value + "\ndate: " + date;
    }
}
