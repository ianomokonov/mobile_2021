package com.example.task3.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.task3.R;
import com.example.task3.dto.TransactionDto;
import com.example.task3.service.PersonTransactionServiceStub;

import java.math.BigDecimal;

public class TotalFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.total_fragment_layout, container);
        return v;

//        v.findViewById(R.id.btnAdd).setOnClickListener(this);
//        v.findViewById(R.id.btnCancel).setOnClickListener((dialog) -> onCancel(getDialog()));
//        if (personActivityDto == null) {
//            personActivityDto = new PersonActivityDto();
//        }
//        prepareActivitySpinner(v);
//        return v;
    }

    public void updateAmount() {
        if (getView() == null) {
            return;
        }
        TextView amount = getView().findViewById(R.id.total_amount_trx);
        String unit = getResources().getString(R.string.unit_rubles);
        amount.setText(String.format("%s %s", getTotalAmount(), unit));
    }

    private BigDecimal getTotalAmount() {
        return PersonTransactionServiceStub.getInstance().getPersonTransactions()
                .stream()
                .map(TransactionDto::getMoney)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

}
