package com.example.task_11.models;

public class UserFormValue {
    public String name;
    public String phone;
    public String email;
    public int masterIndex;

    public UserFormValue(String Name, String phone, int masterIndex, String email) {
        this.name = Name;
        this.phone = phone;
        this.email = email;
        this.masterIndex = masterIndex;
    }
}
