package com.example.exam6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class Dron extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dron);
    }

    public void turnToTheLeft(View view) {
        //Дрон поворачивает налево
    }

    public void turnToTheRight(View view) {
        //Дрон поворачивает направо
    }

    public void riseHigher(View view) {
        //Дрон поднимается выше
    }

    public void goDownBelow(View view) {
        //Дрон спускается ниже
    }
}
