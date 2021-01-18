package com.example.task_10;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.task_10.models.MainListItem;
import com.example.task_10.models.PriceItem;
import com.example.task_10.models.UserFormValue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MasterActivity extends AppCompatActivity implements Sendable {
    private int masterIndex;
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
        ((TextView) findViewById(R.id.masterRating)).setText(master.Rating.toString());
        ((TextView) findViewById(R.id.masterDescription)).setText(master.FullDescription);
        LinearLayout linearLayout = findViewById(R.id.masterPrices);
        for (PriceItem price : master.Prices) {
            TextView priceView = new TextView(getApplicationContext());
            priceView.setText(price.Name + ": " + price.Price + " " + price.Measure);
            priceView.setLayoutParams(
                    new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT)
            );
            linearLayout.addView(priceView);
        }


    }

    public void onChooseButtonClick(View view)
    {
        CustomDialogFragment dialog = new CustomDialogFragment();
        Bundle args = new Bundle();
        args.putInt("masterIndex", masterIndex);
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), "custom");
    }

    @Override
    public void sendForm(UserFormValue formValue) {
        try {
            // отрываем поток для записи
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput(FILENAME, MODE_PRIVATE)));
            // пишем данные
            bw.write(formValue.name + ", "+ formValue.email + ", "+formValue.phone);
            Toast.makeText(this, "Заявка принята, мастер свяжется с Вами в ближайшее время!", Toast.LENGTH_SHORT).show();
            // закрываем поток
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
