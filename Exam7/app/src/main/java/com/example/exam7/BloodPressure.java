package com.example.exam7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

public class BloodPressure extends AppCompatActivity {

    static boolean pressure = false;
    RadioGroup radioPress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_pressure);
        radioPress = (RadioGroup) findViewById(R.id.radioGroup_Press);

        radioPress.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.from18to25:
                        pressure = Age.age.equalsIgnoreCase("18 - 25");
                        break;
                    case R.id.from25to40:
                        pressure = Age.age.equalsIgnoreCase("25 - 40");
                        break;
                    case R.id.from40to50:
                        pressure = Age.age.equalsIgnoreCase("40 - 50");
                        break;
                    case R.id.from50to70:
                        pressure = Age.age.equalsIgnoreCase("50 - 70");
                        break;
                    case R.id.from70:
                        pressure = Age.age.equalsIgnoreCase("70+");
                        break;
                    default: pressure = false;
                }
            }
        });
    }

    public void toTotal(View view) {
        startActivity(new Intent(this, Total.class));
    }
}
