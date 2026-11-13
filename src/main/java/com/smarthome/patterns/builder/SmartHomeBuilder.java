package com.smarthome.patterns.builder;

import com.smarthome.SmartHome;
import com.smarthome.device.SmartDevice;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SmartHomeBuilder {

    private String name;
    private final List<SmartDevice> devices = new ArrayList<>();

    public SmartHomeBuilder withName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "Home name cannot be empty"
            );
        }

        this.name = name;
        return this;
    }

    public SmartHomeBuilder addDevice(SmartDevice device) {
        Objects.requireNonNull(device, "Device cannot be null");

        for (SmartDevice existing : devices) {
            if (existing.getId().equals(device.getId())) {
                throw new IllegalArgumentException(
                        "Duplicate device ID: " + device.getId()
                );
            }
        }

        devices.add(device);
        return this;
    }

    public SmartHome build() {
        if (name == null) {
            throw new IllegalStateException(
                    "Home name is required"
            );
        }

        return new SmartHome(name, devices);
    }
}
