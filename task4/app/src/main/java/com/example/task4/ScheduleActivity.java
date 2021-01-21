package com.example.task4;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_activity_layout);
        String dayOfWeek = getIntent().getStringExtra("dayOfWeek");
        TextView view = findViewById(R.id.schedule_page_header);
        String template = getString(R.string.schedule_page_header);
        view.setText(String.format(template, dayOfWeek));
    }

}
