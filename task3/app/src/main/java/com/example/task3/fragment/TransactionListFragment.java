package com.example.task3.fragment;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import androidx.fragment.app.ListFragment;

import com.example.task3.R;
import com.example.task3.dto.TransactionDto;
import com.example.task3.service.PersonTransactionServiceStub;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionListFragment extends ListFragment {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy");

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(prepareAdapter());
        setEmptyText(getResources().getString(R.string.empty_text));
    }

    public void updateTransactions() {
        setListAdapter(prepareAdapter());
    }

    private ListAdapter prepareAdapter() {
        return new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, preparePersonActivities());
    }

    private List<String> preparePersonActivities() {
        return PersonTransactionServiceStub.getInstance().getPersonTransactions().stream()
                .sorted((trx1, trx2) -> -trx1.getDate().compareTo(trx2.getDate()))
                .map(this::resolveText)
                .collect(Collectors.toList());
    }

    private String resolveText(TransactionDto trx) {
        int msgId = trx.getMoney().compareTo(BigDecimal.ZERO) >= 0 ?
                R.string.profit_template : R.string.waste_template;
        BigDecimal money = trx.getMoney().abs();
        String msg = getResources().getString(msgId);
        return String.format(msg, money, formatter.format(trx.getDate()));
    }

}
