package com.example.exam5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.TypedArrayUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView list;
    //ArrayList<String> items = new ArrayList<>();
    //"Олимпийские Игры", "Чемпионат Мира по футболу", "Теннисный турнир «Уимблдон»", "Формула-1", "Финал Национальной Баскетбольной Ассоциации (NBA)"
    //String[] items = {"Олимпийские Игры", "Чемпионат Мира по футболу", "Теннисный турнир «Уимблдон»", "Формула-1", "Финал Национальной Баскетбольной Ассоциации (NBA)"};
    List items = Arrays.asList("Олимпийские Игры", "Чемпионат Мира по футболу", "Теннисный турнир «Уимблдон»", "Формула-1", "Финал Национальной Баскетбольной Ассоциации (NBA)");
    static String site = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, items);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                TextView textView = (TextView) itemClicked;
                String strText = textView.getText().toString();

                switch (items.indexOf(strText)){
                    case (0):
                        site = "https://www.olympic.org/";
                        startActivity(new Intent(MainActivity.this, WebActivity.class));
                        break;
                    case (1):
                        site = "https://www.fifa.com/";
                        startActivity(new Intent(MainActivity.this, WebActivity.class));
                        break;
                    case (2):
                        site = "https://www.wimbledon.org/";
                        startActivity(new Intent(MainActivity.this, WebActivity.class));
                        break;
                    case (3):
                        site = "https://www.formula1.com/";
                        startActivity(new Intent(MainActivity.this, WebActivity.class));
                        break;
                    case (4):
                        site = "https://www.sports.ru/nba/?gr=www";
                        startActivity(new Intent(MainActivity.this, WebActivity.class));
                        break;
                }
                //if(strText.equalsIgnoreCase(getResources().getString(R.string.name1))) {
                    // Запускаем активность, связанную с определенным именем кота

                //}
            }
        });

    }


    public void onClick(View view) {
        int activePosition = 0; // первый элемент списка
        list.performItemClick(list.getAdapter().
                getView(activePosition, null, null), activePosition, list.getAdapter().
                getItemId(activePosition));
    }
}

