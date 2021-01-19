package com.example.task_15.models;

public class MainListItem {
    public Integer Image;
    public String Name;
    public String Description;
    public String FullDescription;
    public String Date;

    public MainListItem(Integer Image, String Name, String Description, String Date, String FullDescription) {
        this.Image = Image;
        this.Name = Name;
        this.Description = Description;
        this.Date = Date;
        this.FullDescription = FullDescription;
    }
}
