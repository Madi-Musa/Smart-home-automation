package com.smarthome.patterns.observer;

import com.smarthome.patterns.factory.device.SmartDevice;

@FunctionalInterface
public interface DeviceObserver {
    void onDeviceChanged(SmartDevice device);
}
