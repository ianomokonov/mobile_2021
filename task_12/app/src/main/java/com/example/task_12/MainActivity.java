package com.example.task_12;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.task_12.models.MainListItem;
import com.example.task_12.models.NotificationType;

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
                            "Lorem ipsum dolor sit amet.",
                            "Lorem ipsum dolor sit amet consectetur adipisicing elit. At, quasi. Cumque maiores voluptatum enim totam temporibus blanditiis repudiandae. Dignissimos, voluptas.",
                            NotificationType.Danger,
                            "23.12.2021"));
            Masters.masters.add(new MainListItem(
                    UUID.randomUUID().toString(),
                    "Lorem ipsum dolor sit amet.",
                    "Lorem ipsum dolor sit amet consectetur adipisicing elit. At, quasi. Cumque maiores voluptatum enim totam temporibus blanditiis repudiandae. Dignissimos, voluptas.",
                    NotificationType.Warning,
                    "23.12.2021"));
            Masters.masters.add(new MainListItem(UUID.randomUUID().toString(),
                    "Lorem ipsum dolor sit amet.",
                    "Lorem ipsum dolor sit amet consectetur adipisicing elit. At, quasi. Cumque maiores voluptatum enim totam temporibus blanditiis repudiandae. Dignissimos, voluptas.",
                    NotificationType.Problem,
                    "23.12.2021"));
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
        Intent intent = new Intent(MainActivity.this, CreativeActivity.class);
        startActivity(intent);
    }
}


// = {
//         new MainListItem(
//         UUID.randomUUID().toString(),
//         "Кабаковский Сергей Владимирович",
//         "Пунктуальный, креативный и порядочный мастер. Опыт в сфере мелкого бытового ремонта более 15 лет!",
//         NotificationType.Danger,
//         "23.12.2021"),
//         new MainListItem(
//         UUID.randomUUID().toString(),
//         "Кабаковский Сергей Владимирович",
//         "Пунктуальный, креативный и порядочный мастер. Опыт в сфере мелкого бытового ремонта более 15 лет!",
//         NotificationType.Warning,
//         "23.12.2021"),
//         new MainListItem(
//         UUID.randomUUID().toString(),
//         "Кабаковский Сергей Владимирович",
//         "Пунктуальный, креативный и порядочный мастер. Опыт в сфере мелкого бытового ремонта более 15 лет!",
//         NotificationType.Problem,
//         "23.12.2021"),
//
//         };