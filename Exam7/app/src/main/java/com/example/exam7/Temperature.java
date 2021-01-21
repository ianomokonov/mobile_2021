package com.example.exam7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

public class Temperature extends AppCompatActivity {

    static boolean temp;
    RadioGroup radioTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

        radioTemp = (RadioGroup)findViewById(R.id.radioGroup_Temp);

        radioTemp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.from36to37) {
                    temp = true;
                } else {
                    temp = false;
                }
            }
        });
    }

    public void toBloodPressure (View view){
        startActivity(new Intent(this, BloodPressure.class));
    }
}
