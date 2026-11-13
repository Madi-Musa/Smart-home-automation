package com.smarthome.patterns.observer;

import com.smarthome.device.SmartDevice;

public final class ConsoleDeviceObserver implements DeviceObserver {
    @Override
    public void onDeviceChanged(SmartDevice device) {
        System.out.printf("[Observer] %s: %s%n", device.getName(), device.isOn() ? "ON" : "OFF");
    }
}
