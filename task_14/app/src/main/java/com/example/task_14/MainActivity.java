package com.example.task_14;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.task_14.models.MainListItem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // находим список
        ListView mainList = findViewById(R.id.mainList);
        Masters.masters = new ArrayList<MainListItem>();
        try {

            FileInputStream fi = openFileInput(Masters.FILENAME);
            ObjectInputStream oi = new ObjectInputStream(fi);

            //Read objects
            Masters.masters = (ArrayList<MainListItem>) oi.readObject();

            oi.close();
            fi.close();


        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if(Masters.masters == null || Masters.masters.size() == 0){
            Masters.masters.add(new MainListItem(
                UUID.randomUUID().toString(),
                "Англия",
                "14.05.2021",
                "24.05.2021",
                "Лондон",
                50000.0));
            Masters.masters.add(new MainListItem(UUID.randomUUID().toString(), "Италия", "14.06.2021", "24.06.2021", "Милан", 38000.0));
            Masters.masters.add(new MainListItem(UUID.randomUUID().toString(),"Россия", "14.08.2021", "24.08.2021", "Севастополь",
                            33000.0));

            Masters.masters.add(new MainListItem(UUID.randomUUID().toString(), "Турция", "14.07.2021", "24.07.2021", "Анталия",
                            40000.0
                    ));
        }
        // присваиваем адаптер списку
        mainList.setAdapter(new MainListAdapter(this, Masters.masters));
        mainList.setOnItemClickListener((AdapterView<?> parent, View view,
                                          int position, long id) -> {
            Intent intent = new Intent(MainActivity.this, MasterActivity.class);
            intent.putExtra("masterIndex", position);
            startActivity(intent);
        });
    }

    public void onAddButtonClick(View view)
    {
        Intent intent = new Intent(MainActivity.this, MasterActivity.class);
        intent.putExtra("masterIndex", -1);
        startActivity(intent);
    }
}