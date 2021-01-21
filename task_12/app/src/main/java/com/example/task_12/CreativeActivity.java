package com.example.task_12;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.task_12.models.MainListItem;
import com.example.task_12.models.NotificationType;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class CreativeActivity extends AppCompatActivity {
        private Calendar dateAndTime=Calendar.getInstance();
        private EditText countryEdit;
        private EditText cityEdit;
        private EditText startEdit;
        private Spinner typeEdit;
        private EditText currentDateEdit;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_creative);

                typeEdit = (Spinner) findViewById(R.id.types);
                // Create an ArrayAdapter using the string array and a default spinner layout
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                        R.array.types_array, android.R.layout.simple_spinner_item);
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // Apply the adapter to the spinner
                typeEdit.setAdapter(adapter);

                countryEdit = findViewById(R.id.country);
                cityEdit = findViewById(R.id.city);
                startEdit = findViewById(R.id.startDate);
                typeEdit = findViewById(R.id.types);
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
        };
        public void onSaveClick(View view) {
                MainListItem master = new MainListItem(UUID.randomUUID().toString(), countryEdit.getText().toString(), cityEdit.getText().toString(), getType(typeEdit.getSelectedItem().toString()),
                        startEdit.getText().toString()
                        );
                Masters.masters.add(master);

                try {
                        FileOutputStream f = openFileOutput(Masters.FILENAME, MODE_PRIVATE);
                        ObjectOutputStream o = new ObjectOutputStream(f);

                        //Write objects to file
                        o.writeObject(Masters.masters);

                        o.close();
                        f.close();
                        Toast.makeText(this, "Уведомление сохранено", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CreativeActivity.this, MainActivity.class);
                        startActivity(intent);

                } catch (FileNotFoundException e) {
                        System.out.println("File not found");
                } catch (IOException e) {
                        System.out.println(e);
                }
        }

        private NotificationType getType(String value){
                switch (value){
                        case "Высокий приоритет": {
                                return NotificationType.Danger;
                        }
                        case "Средний приоритет": {
                                return NotificationType.Warning;
                        }
                        case "Низкий приоритет": {
                                return NotificationType.Problem;
                        }
                }

                return NotificationType.Problem;
        }
}
