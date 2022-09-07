package com.example.xmlparser;

public class Employee{
    private long id;
    private String name;
    private String phone;
    private String title;

    public Employee() {
    }

    public Employee(long id, String name, String phone, String title) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
