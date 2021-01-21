package com.banzaraktsaeva.exam1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class City extends AppCompatActivity {

    TextView todayView;
    TextView header;
    TextView tempView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        todayView = (TextView) findViewById(R.id.todayView);
        String textDate = "Сегодня " + MainActivity.today;
        todayView.setText(textDate);

        header = (TextView) findViewById(R.id.header);
        String textHeader = "Погода в городе " + MainActivity.city;
        header.setText(textHeader);

        tempView = (TextView) findViewById(R.id.tempView);
        Random rnd = new Random();
        int temp = rnd.nextInt(20)-10;
        String stringTemp = "";
        stringTemp = stringTemp.valueOf(temp)+ "\u00B0C";
        tempView.setText(stringTemp);

    }

    public void goBack(View view){
        Intent intentB = new Intent(this, MainActivity.class);
        startActivity(intentB);
    }
}
