package com.example.task2.dto;

import java.time.LocalDate;

public class PersonActivityDto {

    private LocalDate date;
    private Integer count;
    private ActivityData activityData;


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public ActivityData getActivityData() {
        return activityData;
    }

    public void setActivityData(ActivityData activityData) {
        this.activityData = activityData;
    }

    @Override
    public String toString() {
        return "PersonActivityDto{" +
                "date=" + date +
                ", count=" + count +
                ", activityData=" + activityData +
                '}';
    }
}
