package com.smarthome.patterns.factory.device;

public interface SmartDevice {

    String getId();

    String getName();

    void turnOn();

    void turnOff();

    boolean isOn();
}
