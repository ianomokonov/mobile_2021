package com.example.exam7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Total extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total);
        TextView todayView = (TextView) findViewById(R.id.todayView);
        todayView.setText(MainActivity.dateString);

        TextView total = (TextView)findViewById(R.id.totalText);

        if((Temperature.temp)&(BloodPressure.pressure)){
            total.setText("Поздравляем!\nВы здоровы как бык!");
        } else {
            total.setText("Рекомендуем Вам\nзаписаться к врачу");
        }
    }

    public void toMain(View view) {
        //пройти тест еще раз
        startActivity(new Intent(this, MainActivity.class));
    }

    public void toDoctor(View view) {
        //запись к врачу
    }
}
