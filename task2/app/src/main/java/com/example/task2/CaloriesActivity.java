package com.example.task2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.task2.service.ActivityDataServiceStub;
import com.example.task2.service.PersonActivityServiceStub;

public class CaloriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDataServiceStub.initialize(getResources());
        PersonActivityServiceStub.initialize();
        setContentView(R.layout.calories_layout);
    }

    public void openMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivityForResult(intent, 0);
    }

}
