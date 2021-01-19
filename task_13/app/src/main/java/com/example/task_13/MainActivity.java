package com.example.task_13;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.task_13.R;
import com.example.task_13.models.HistoryItem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public static final Integer RecordAudioRequestCode = 1;
    private SpeechRecognizer speechRecognizer;
    private EditText editText;
    private ImageView micButton;
    private final String FILENAME = "history.txt";
    ArrayList<HistoryItem> items = new ArrayList<HistoryItem>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            checkPermission();
        }
        ListView deviceList = (ListView) findViewById(R.id.deviceList);

        // создаем адаптер
        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, new ArrayList<String>());

        // устанавливаем для списка адаптер
        deviceList.setAdapter(adapter);
        deviceList.setOnItemClickListener((AdapterView<?> parent, View view,
                                           int position, long id) -> {
            HistoryItem historyItem = items.get(items.size() - 1 - position);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(historyItem.value)
                    .setMessage(historyItem.date)
                    .setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Закрываем окно
                            dialog.cancel();
                        }
                    });
            builder.show();
        });
        try {

            FileInputStream fi = openFileInput(FILENAME);
            ObjectInputStream oi = new ObjectInputStream(fi);

            //Read objects
            items = (ArrayList<HistoryItem>) oi.readObject();

            oi.close();
            fi.close();
            showItems();


        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        editText = findViewById(R.id.text);
        micButton = findViewById(R.id.button);
        ImageView saveButton = findViewById(R.id.buttonSave);
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);

        final Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {
                editText.setText("");
                editText.setHint("Listening...");
            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle bundle) {
                micButton.setImageResource(R.drawable.ic_baseline_mic_off_24);
                ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                editText.setText(data.get(0));
            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });

        micButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    speechRecognizer.stopListening();
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    micButton.setImageResource(R.drawable.ic_baseline_mic_24);
                    speechRecognizer.startListening(speechRecognizerIntent);
                }
                return false;
            }
        });

        saveButton.setOnClickListener((View view) -> {
            HistoryItem newItem = new HistoryItem();
            newItem.value = editText.getText().toString();
            newItem.date = Calendar.getInstance().getTime().toString();

            items.add(newItem);
            try {
                FileOutputStream f = openFileOutput(FILENAME, MODE_PRIVATE);
                ObjectOutputStream o = new ObjectOutputStream(f);

                //Write objects to file
                o.writeObject(items);

                o.close();
                f.close();
                Toast.makeText(this, "Текст сохранен", Toast.LENGTH_SHORT).show();
                showItems();


            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            } catch (IOException e) {
                System.out.println(e);
            }
        });


    }

    private void showItems() {
        adapter.clear();
        if(items != null && items.size() > 0){
            for(int i = items.size() - 1; i> -1; i--){
                adapter.add(items.get(i).value);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        speechRecognizer.destroy();
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO},RecordAudioRequestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RecordAudioRequestCode && grantResults.length > 0 ){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show();
        }
    }
}