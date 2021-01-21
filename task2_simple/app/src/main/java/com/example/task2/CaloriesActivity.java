package com.example.task2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.task2.dto.PersonActivityDto;
import com.example.task2.service.PersonActivityServiceStub;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class CaloriesActivity extends AppCompatActivity {

    private List<PersonActivityDto> personActivityData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calories_layout);
        String personActivitiesJson = getIntent().getStringExtra("data");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            personActivityData = objectMapper.readValue(personActivitiesJson, new TypeReference<List<PersonActivityDto>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        TextView totalView = findViewById(R.id.textview_second1);
        String totalMsg = String.format(getString(R.string.text_total_activity), String.valueOf(personActivityData.size()));
        totalView.setText(totalMsg);

        TextView sumView = findViewById(R.id.textview_second2);
        int sum = personActivityData.stream()
                .mapToInt(PersonActivityDto::getCount)
                .sum();
        String sumMsg = String.format(getString(R.string.text_sum_activity), String.valueOf(sum));
        sumView.setText(sumMsg);

        TextView caloriesView = findViewById(R.id.textview_second3);
        double randomCoef = new Random().nextDouble() + 1;
        double calories = randomCoef * sum;
        String caloriesMsg = String.format(getString(R.string.text_total_callories), calories);
        caloriesView.setText(caloriesMsg);
    }

    public void openMainActivity(View view) {
        finish();
    }

}
