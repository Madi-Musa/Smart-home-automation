package com.smarthome.patterns.factory.device;
import java.util.Objects;

public class SmartLight implements SmartDevice {

    private final String id;
    private final String name;
    private boolean on;

    public SmartLight(String id, String name) {
        this.id = Objects.requireNonNull(id, "id");

        if (id.isBlank()) {
            throw new IllegalArgumentException(
                    "Device ID cannot be empty"
            );
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "Device name cannot be empty"
            );
        }

        this.name = name;
        this.on = false;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void turnOn() {
        on = true;
        System.out.println(name + " turned ON");
    }

    @Override
    public void turnOff() {
        on = false;
        System.out.println(name + " turned OFF");
    }

    @Override
    public boolean isOn() {
        return on;
    }
}
