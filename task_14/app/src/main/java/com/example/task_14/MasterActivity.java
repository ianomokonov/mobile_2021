package com.example.task_14;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.task_14.models.MainListItem;
import com.example.task_14.models.PriceItem;
import com.example.task_14.models.UserFormValue;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class MasterActivity extends AppCompatActivity implements Sendable {
    private int masterIndex;
    private Calendar dateAndTime=Calendar.getInstance();
    private EditText countryEdit;
    private EditText cityEdit;
    private EditText startEdit;
    private EditText endEdit;
    private EditText sumEdit;
    private EditText currentDateEdit;
    private MainListItem master;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.acivity_master);
        masterIndex = getIntent().getExtras().getInt("masterIndex");


        countryEdit = findViewById(R.id.country);
        cityEdit = findViewById(R.id.city);
        startEdit = findViewById(R.id.startDate);
        endEdit = findViewById(R.id.endDate);
        sumEdit = findViewById(R.id.editSum);

        if(masterIndex != -1){
            master = Masters.masters.get(masterIndex);
            countryEdit.setText(master.Name);
            cityEdit.setText(master.City);
            startEdit.setText(master.DateStart);
            endEdit.setText(master.DateEnd);
            sumEdit.setText(master.Sum.toString());
        }

    }

    public void setDate(View v) {
        currentDateEdit = (EditText) v;
        String dateString = currentDateEdit.getText().toString();
        if(dateString != null && !dateString.equals("")){
            dateAndTime.set(Integer.parseInt(dateString.substring(6,10)), Integer.parseInt(dateString.substring(3,5))-1, Integer.parseInt(dateString.substring(0,2)));
        }

        new DatePickerDialog(this, onDateSetListener,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    protected void setInitialDateTime() {
        Date currentDate = new Date(dateAndTime.getTimeInMillis());
        this.setDateViewText(dateTOString(currentDate));
    }

    protected String dateTOString(Date date){
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", new Locale("ru"));
        return  dateFormat.format(date);
    }

    protected void setDateViewText(String dateString){
        currentDateEdit.setText(dateString);
    }

    DatePickerDialog.OnDateSetListener onDateSetListener =(DatePicker view, int year, int monthOfYear, int dayOfMonth) -> {
            dateAndTime.set(year, monthOfYear, dayOfMonth);
            setInitialDateTime();
//            refreshList();
        };
    public void onSaveClick(View view) {
        if(master == null){
            master = new MainListItem(UUID.randomUUID().toString(), countryEdit.getText().toString(), startEdit.getText().toString(), endEdit.getText().toString(), cityEdit.getText().toString(),
                    Double.parseDouble(sumEdit.getText().toString()));
            Masters.masters.add(master);
        } else {
            master.City = cityEdit.getText().toString();
            master.Name = countryEdit.getText().toString();
            master.DateStart = startEdit.getText().toString();
            master.DateEnd = endEdit.getText().toString();
            master.Sum = Double.parseDouble(sumEdit.getText().toString());
        }

        try {
            FileOutputStream f = openFileOutput(Masters.FILENAME, MODE_PRIVATE);
            ObjectOutputStream o = new ObjectOutputStream(f);

            //Write objects to file
            o.writeObject(Masters.masters);

            o.close();
            f.close();
            Toast.makeText(this, "Поездка сохранена", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MasterActivity.this, MainActivity.class);
            startActivity(intent);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void sendForm(UserFormValue formValue) {
        try {
            // отрываем поток для записи
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput(Masters.FILENAME, MODE_PRIVATE)));
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
