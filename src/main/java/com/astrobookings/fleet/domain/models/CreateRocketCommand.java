package com.astrobookings.fleet.domain.models;

public record CreateRocketCommand(String id, String name, int capacity, Double maxSpeed) {
}
