package com.example.task3.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionDto {

    private LocalDate date;
    private BigDecimal money;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "PersonActivityDto{" +
                "date=" + date +
                ", money=" + money +
                '}';
    }
}
