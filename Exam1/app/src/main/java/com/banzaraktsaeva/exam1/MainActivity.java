package com.banzaraktsaeva.exam1;

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
    TextView todayView;
    static String today;
    static  String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todayView = (TextView) findViewById(R.id.todayView);
        Date date = new Date();
        today = dateToString(date);
        todayView.setText(today);
    }

    public static String dateToString(Date date){
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateString = dateFormat.format(date);
        return dateString;
    }

    public void toCity(View view){
        Intent intent = new Intent(this, City.class);
        startActivity(intent);
    }

    public void toMoscow(View view){
        city = "Москва";
        toCity(view);
    }

    public void toSochi(View view){
        city = "Сочи";
        toCity(view);
    }

    public void toArchangelsk(View view){
        city = "Архангельск";
        toCity(view);
    }
}
