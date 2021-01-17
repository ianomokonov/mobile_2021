package com.example.task_10.models;

import android.app.Activity;

import androidx.annotation.DrawableRes;

import com.example.task_10.R;

public class MainListItem {
    public Integer Image;
    public String Name;
    public String Description;
    public String FullDescription;
    public Double Rating;
    public PriceItem[] Prices;

    public MainListItem(Integer Image, String Name, String Description, Double Rating, String FullDescription, PriceItem[] Prices) {
        this.Image = Image;
        this.Name = Name;
        this.Description = Description;
        this.Rating = Rating;
        this.FullDescription = FullDescription;
        this.Prices = Prices;
    }
}
