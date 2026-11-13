package com.smarthome.device;
import java.util.Objects;

public class Thermostat implements SmartDevice {

    private final String id;
    private final String name;
    private boolean on;
    private double temperature;

    public Thermostat(String id, String name) {
        this.id = Objects.requireNonNull(id, "id");
        this.name = name;
        this.on = false;
        this.temperature = 22.0;
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

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        if (!Double.isFinite(temperature) || temperature < 16 || temperature > 30) {
            throw new IllegalArgumentException(
                    "Temperature must be between 16 and 30"
            );
        }

        this.temperature = temperature;
        System.out.println(
                name + " temperature set to " + temperature + "°C"
        );
    }
}
