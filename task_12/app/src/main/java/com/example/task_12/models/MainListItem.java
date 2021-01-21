package com.example.task_12.models;

import java.io.Serializable;

public class MainListItem implements Serializable {
    public String Id;
    public String Name;
    public String Description;
    public NotificationType Type;
    public String Date;

    public MainListItem(String Id, String Name, String Description, NotificationType Type, String Date) {
        this.Id = Id;
        this.Name = Name;
        this.Date = Date;
        this.Description = Description;
        this.Type = Type;
    }

    @Override
    public String toString() {
        return "Id:" + Id + "\nName:" + Name + "\nDate: " + Date + "\nType: " + Type + "\nDescription: " + Description;
    }
}
