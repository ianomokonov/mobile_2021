package com.example.task_10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.task_10.models.MainListItem;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // находим список
        ListView mainList = findViewById(R.id.mainList);

        // присваиваем адаптер списку
        mainList.setAdapter(new MainListAdapter(this, Masters.masters));
        mainList.setOnItemClickListener((AdapterView<?> parent, View view,
                                          int position, long id) -> {
            Intent intent = new Intent(MainActivity.this, MasterActivity.class);
            intent.putExtra("masterIndex", position);
            startActivity(intent);
        });
    }
}