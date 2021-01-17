package com.example.task_10.models;

import android.app.Activity;

import androidx.annotation.DrawableRes;

import com.example.task_10.R;

public class MainListItem {
    public Integer Image;
    public String Name;
    public String Description;
    public Double Rating;

    public MainListItem(Integer Image, String Name, String Description, Double Rating) {
        this.Image = Image;
        this.Name = Name;
        this.Description = Description;
        this.Rating = Rating;
    }
}
