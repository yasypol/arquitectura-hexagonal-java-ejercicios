package com.astrobookings.sales.domain.models;

public class RocketInfo {
    String id;
    int capacity;

    public RocketInfo(String id, int capacity) {
        this.id = id;
        this.capacity = capacity;
    }

    public String getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }
};
