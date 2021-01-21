package com.example.task2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.task2.dialog.AddActivityDialog;
import com.example.task2.fragment.ActivityListFragment;
import com.example.task2.service.ActivityDataServiceStub;
import com.example.task2.service.PersonActivityServiceStub;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Optional;

public class MainActivity extends AppCompatActivity {

    private AddActivityDialog addActivityDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDataServiceStub.initialize(getResources());
        PersonActivityServiceStub.initialize();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        addActivityDialog = new AddActivityDialog(() -> {
            Optional<ActivityListFragment> activityListFragment = getActivityListFragment();
            activityListFragment.ifPresent(ActivityListFragment::updateActivities);
        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> addActivityDialog.show(getSupportFragmentManager(), "addActivityDialog"));
    }

    public void openCaloriesActivity(View view) {
        Intent intent = new Intent(this, CaloriesActivity.class);
        startActivityForResult(intent, 0);
    }

    private Optional<ActivityListFragment> getActivityListFragment() {
        return Optional.ofNullable(getSupportFragmentManager().findFragmentById(R.id.listFragment))
                .map(fragment -> (ActivityListFragment) fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}