package com.example.task2.dto;

import java.math.BigDecimal;

public class ActivityData {

    /**
     * Тип активности
     */
    private ActivityType activityType;

    /**
     * Единицы измерения
     */
    private String units;

    /**
     * Коэффициенты каллорий
     */
    private BigDecimal coef;

    public ActivityData(ActivityType activityType, String units, BigDecimal coef) {
        this.activityType = activityType;
        this.units = units;
        this.coef = coef;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
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
                "activityType=" + activityType +
                ", units='" + units + '\'' +
                ", coef=" + coef +
                '}';
    }
}
