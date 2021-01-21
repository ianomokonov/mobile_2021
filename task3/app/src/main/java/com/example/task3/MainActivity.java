package com.example.task3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.task3.dto.TrxType;
import com.example.task3.fragment.TotalFragment;
import com.example.task3.fragment.TransactionListFragment;
import com.example.task3.service.PersonTransactionServiceStub;

import java.util.Optional;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PersonTransactionServiceStub.initialize();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d(LOG_TAG, "onActvityResult");
        super.onActivityResult(requestCode, resultCode, data);
        updateTransactionListFragment();
        updateTotalFragment();
    }

    public void openAddProfitDialog(View view) {
        Intent intent = new Intent(this, AddTransactionActivity.class);
        intent.putExtra("trxType", TrxType.PROFIT.name());
        startActivityForResult(intent, 0);
    }

    public void openAddWasteDialog(View view) {
        Intent intent = new Intent(this, AddTransactionActivity.class);
        intent.putExtra("trxType", TrxType.WASTE.name());
        startActivityForResult(intent, 0);
    }

    private void updateTransactionListFragment() {
        Optional.ofNullable(getSupportFragmentManager().findFragmentById(R.id.listFragment))
                .map(fragment -> (TransactionListFragment) fragment)
                .ifPresent(TransactionListFragment::updateTransactions);
    }

    private void updateTotalFragment() {
        Optional.ofNullable(getSupportFragmentManager().findFragmentById(R.id.totalFragment))
                .map(fragment -> (TotalFragment) fragment)
                .ifPresent(TotalFragment::updateAmount);
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