package com.example.joblistingapplication.database;import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Bookmark {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title, location, salary, phone, description;

    public Bookmark(String title, String location, String salary, String phone, String description) {
        this.title = title;
        this.location = location;
        this.salary = salary;
        this.phone = phone;
        this.description = description;
    }
}
