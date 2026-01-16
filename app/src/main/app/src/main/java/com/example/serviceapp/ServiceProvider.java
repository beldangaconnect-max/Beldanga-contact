package com.example.serviceapp;

public class ServiceProvider {

    public String name;
    public String phone;
    public String service;
    public String area;
    public boolean available;

    public ServiceProvider() {
        // Firebase needs empty constructor
    }

    public ServiceProvider(String name, String phone, String service, String area, boolean available) {
        this.name = name;
        this.phone = phone;
        this.service = service;
        this.area = area;
        this.available = available;
    }
}
