package com.example.task3.dto;

import java.math.BigDecimal;

public class ActivityData {

    /**
     * Тип активности
     */
    private TrxType trxType;

    /**
     * Единицы измерения
     */
    private String units;

    /**
     * Коэффициенты каллорий
     */
    private BigDecimal coef;

    public ActivityData(TrxType trxType, String units, BigDecimal coef) {
        this.trxType = trxType;
        this.units = units;
        this.coef = coef;
    }

    public TrxType getTrxType() {
        return trxType;
    }

    public void setTrxType(TrxType trxType) {
        this.trxType = trxType;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public BigDecimal getCoef() {
        return coef;
    }

    public void setCoef(BigDecimal coef) {
        this.coef = coef;
    }

    @Override
    public String toString() {
        return "ActivityData{" +
                "activityType=" + trxType +
                ", units='" + units + '\'' +
                ", coef=" + coef +
                '}';
    }
}
