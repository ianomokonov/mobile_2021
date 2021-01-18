package com.example.task_11;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.task_11.models.MainListItem;

public class MasterActivity extends AppCompatActivity implements Sendable {
    private int masterIndex;
    private int deviceIndex;
    final String FILENAME = "user_data";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.acivity_master);
        masterIndex = getIntent().getExtras().getInt("masterIndex");
        MainListItem master = Masters.masters[masterIndex];
        ((ImageView) findViewById(R.id.masterIcon)).setImageResource(master.Image);
        ((TextView) findViewById(R.id.masterLabel)).setText(master.Name);
        ListView deviceList = (ListView) findViewById(R.id.deviceList);

        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, master.Devices);

        // устанавливаем для списка адаптер
        deviceList.setAdapter(adapter);
        deviceList.setOnItemClickListener((AdapterView<?> parent, View view,
                                         int position, long id) -> {
            CustomDialogFragment dialog = new CustomDialogFragment();
            Bundle args = new Bundle();
            deviceIndex = position;
            args.putInt("masterIndex", masterIndex);
            args.putInt("deviceIndex", position);
            dialog.setArguments(args);
            dialog.show(getSupportFragmentManager(), "custom");
        });


    }

    @Override
    public void sendForm(boolean turnOn) {
        Toast.makeText(this, "Устройство \"" + Masters.masters[masterIndex].Devices[deviceIndex] + "\"" + (turnOn ? " включено." : " выключено."), Toast.LENGTH_SHORT).show();
    }
}
