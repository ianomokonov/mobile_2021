package com.example.task3;

import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.task3.dto.TransactionDto;
import com.example.task3.dto.TrxType;
import com.example.task3.service.PersonTransactionServiceStub;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AddTransactionActivity extends AppCompatActivity {

    private TransactionDto transactionDto;

    private static final String LOG_TAG = "AddTransactionDialog";
    private TrxType trxType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_transaction_acitivity);
        transactionDto = new TransactionDto();
        String trxType = getIntent().getStringExtra("trxType");
        this.trxType = TrxType.valueOf(trxType);
        updateMessage();
        findViewById(R.id.btnAdd).setOnClickListener((v) -> onAddClick());
        findViewById(R.id.btnCancel).setOnClickListener((v) -> finish());
    }

    public void onAddClick() {
        Log.d(LOG_TAG, "Add button");
        DatePicker picker = findViewById(R.id.trx_date);
        LocalDate actualDate = LocalDate.of(picker.getYear(), picker.getMonth() + 1, picker.getDayOfMonth());
        transactionDto.setDate(actualDate);
        EditText editText = findViewById(R.id.amount);
        BigDecimal money = new BigDecimal(editText.getText().toString());
        if (trxType == TrxType.WASTE) {
            money = money.negate();
        }
        transactionDto.setMoney(money);
        PersonTransactionServiceStub.getInstance().addUserActivity(transactionDto);
        Log.d(LOG_TAG, "Saved: " + transactionDto);
        transactionDto = new TransactionDto();
        finish();
    }


    private void updateMessage() {
        TextView textView = findViewById(R.id.dialog_header);
        int msgId = trxType == TrxType.PROFIT ? R.string.add_profit_msg : R.string.add_waste_msg;
        textView.setText(getResources().getString(msgId));
    }


}
