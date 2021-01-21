package com.example.task_14.models;

import java.io.Serializable;

public class MainListItem implements Serializable {
    public String Id;
    public String Name;
    public String DateStart;
    public String DateEnd;
    public Double Sum;
    public String City;

    public MainListItem(String Id, String Name, String DateStart, String DateEnd, String City, Double Sum) {
        this.Id = Id;
        this.Name = Name;
        this.DateStart = DateStart;
        this.DateEnd = DateEnd;
        this.City = City;
        this.Sum = Sum;
    }

    @Override
    public String toString() {
        return "Id:" + Id + "\nName:" + Name + "\nDateStart: " + DateStart + "\nDateEnd: " + DateEnd + "\nSum: " + Sum + "\nCity: " + City;
    }
}
