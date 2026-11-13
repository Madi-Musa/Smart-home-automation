package com.smarthome.patterns.adapter;
import com.smarthome.device.SmartDevice;

import java.util.Objects;

public class LampAdapter implements SmartDevice {
    private final String id;
    private final String name;
    private final LegacyLamp lamp;

    public LampAdapter(String id, String name, LegacyLamp lamp) {
        this.id = Objects.requireNonNull(id);

        if (id.isBlank()) {
            throw new IllegalArgumentException(
                    "Device ID cannot be empty"
            );
        }

        this.name = Objects.requireNonNull(name);

        if (name.isBlank()) {
            throw new IllegalArgumentException(
                    "Device name cannot be empty"
            );
        }
        this.lamp = Objects.requireNonNull(lamp);
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
        lamp.switchOn();
    }

    @Override
    public void turnOff() {
        lamp.switchOff();
    }

    @Override
    public boolean isOn() {
        return lamp.hasPower();
    }
}