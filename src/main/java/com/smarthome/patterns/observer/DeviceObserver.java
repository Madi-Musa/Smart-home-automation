package com.smarthome.patterns.observer;

import com.smarthome.device.SmartDevice;

@FunctionalInterface
public interface DeviceObserver {
    void onDeviceChanged(SmartDevice device);
}
