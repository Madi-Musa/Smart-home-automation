package com.smarthome.device;
import java.util.Objects;

public class SmartLight implements SmartDevice {

    private final String id;
    private final String name;
    private boolean on;

    public SmartLight(String id, String name) {
        this.id = Objects.requireNonNull(id, "id");
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
