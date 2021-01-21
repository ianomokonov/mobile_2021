package com.example.task3.service;

import com.example.task3.dto.TransactionDto;

import java.util.ArrayList;
import java.util.List;

public class PersonTransactionServiceStub {

    private final List<TransactionDto> transactionDtoList;
    private static PersonTransactionServiceStub INSTANCE;

    public static void initialize() {
        if (INSTANCE != null) {
            return;
        }
        INSTANCE = new PersonTransactionServiceStub();
    }

    public static PersonTransactionServiceStub getInstance() {
        return INSTANCE;
    }

    public void addUserActivity(TransactionDto transactionDto) {
        transactionDtoList.add(transactionDto);
    }

    public List<TransactionDto> getPersonTransactions() {
        return transactionDtoList;
    }

    private PersonTransactionServiceStub() {
        transactionDtoList = new ArrayList<>();
    }
}
