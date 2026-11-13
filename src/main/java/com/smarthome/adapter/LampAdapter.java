package com.smarthome.adapter;
import com.smarthome.device.SmartDevice;

import java.util.Objects;

public class LampAdapter implements SmartDevice {
    private final String id;
    private final String name;
    private final LegacyLamp lamp;

    public LampAdapter(String id, String name, LegacyLamp lamp) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
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