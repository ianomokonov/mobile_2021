package com.example.exam7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Age extends AppCompatActivity {

    static String age;
    RadioGroup radioAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age);

        radioAge = (RadioGroup)findViewById(R.id.radioGroup_Age);
        radioAge.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.from18to25:
                        age = "18 - 25";
                        break;
                    case R.id.from25to40:
                        age = "25 - 40";
                        break;
                    case R.id.from40to50:
                        age = "40 - 50";
                        break;
                    case R.id.from50to70:
                        age = "50 - 70";
                        break;
                    default:
                        age = "70+";
                        break;
                }
            }
        });
    }

    public void toTemperature (View view){
        startActivity(new Intent(this, Temperature.class));
    }
}
