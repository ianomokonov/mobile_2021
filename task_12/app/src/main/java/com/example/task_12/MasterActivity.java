package com.example.task_12;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.task_12.models.MainListItem;
import com.example.task_12.models.PriceItem;
import com.example.task_12.models.UserFormValue;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
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
        MainListItem master = Masters.masters.get(masterIndex);
        ((TextView) findViewById(R.id.masterLabel)).setText(master.Name);
        ((TextView) findViewById(R.id.masterRating)).setText(master.Date);
        ((TextView) findViewById(R.id.masterDescription)).setText(master.Description);
        TextView colorView = ((TextView) findViewById(R.id.icon));

        switch (master.Type){
            case Danger: {
                colorView.setBackgroundColor(getResources().getColor(R.color.danger));
                break;
            }
            case Warning: {
                colorView.setBackgroundColor(getResources().getColor(R.color.warning));
                break;
            }
            case Problem: {
                colorView.setBackgroundColor(getResources().getColor(R.color.problem));
                break;
            }
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
