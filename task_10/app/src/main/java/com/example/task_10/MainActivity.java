package com.example.task_10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.task_10.models.MainListItem;

public class MainActivity extends AppCompatActivity {
    MainListItem[] names = {
            new MainListItem(R.drawable.first_master, "Кабаковский Сергей Владимирович", "Пунктуальный, креативный и порядочный мастер. Опыт в сфере мелкого бытового ремонта более 15 лет!", 4.5),
            new MainListItem(R.drawable.first_master, "Кабаковский Сергей Владимирович", "Пунктуальный, креативный и порядочный мастер. Опыт в сфере мелкого бытового ремонта более 15 лет!", 4.5),
            new MainListItem(R.drawable.first_master, "Кабаковский Сергей Владимирович", "Пунктуальный, креативный и порядочный мастер. Опыт в сфере мелкого бытового ремонта более 15 лет!", 4.5),
            new MainListItem(R.drawable.first_master, "Кабаковский Сергей Владимирович", "Пунктуальный, креативный и порядочный мастер. Опыт в сфере мелкого бытового ремонта более 15 лет!", 4.5)
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // находим список
        ListView mainList = findViewById(R.id.mainList);

        // присваиваем адаптер списку
        mainList.setAdapter(new MainListAdapter(this, names));
    }
}