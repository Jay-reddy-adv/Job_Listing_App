package com.example.joblistingapplication.models;

public class Job {
    private String title;
    private String location;
    private String salary;
    private String phone;
    private String description;

    // Getters and setters

    public String getTitle() { return title; }
    public String getLocation() { return location; }
    public String getSalary() { return salary; }
    public String getPhone() { return phone; }
    public String getDescription() { return description; }


    public Job(String title, String location, String salary, String phone, String description) {
        this.title = title;
        this.location = location;
        this.salary = salary;
        this.phone = phone;
        this.description = description;
    }

}
