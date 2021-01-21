package com.example.exam7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    static String dateString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Date date = new Date();
        dateString = dateToString(date);
        TextView todayView = (TextView) findViewById(R.id.todayView);
        todayView.setText(dateString);
    }

    public void toAge(View view){
        Intent intent = new Intent(this, Age.class);
        startActivity(intent);
    }

    public static String dateToString(Date date){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return dateFormat.format(date);
    }
}

