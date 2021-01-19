package com.example.task_15;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

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
            if(Masters.masters[position].Image != null){
                Intent intent = new Intent(MainActivity.this, MasterActivity.class);
                intent.putExtra("masterIndex", position);
                startActivity(intent);
            }

        });
    }
}