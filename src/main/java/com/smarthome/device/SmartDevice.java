package com.smarthome.device;

public interface SmartDevice {

    String getId();

    String getName();

    void turnOn();

    void turnOff();

    boolean isOn();
}
