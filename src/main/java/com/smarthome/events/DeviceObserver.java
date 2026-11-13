package com.smarthome.events;

import com.smarthome.device.SmartDevice;

@FunctionalInterface
public interface DeviceObserver {
    void onDeviceChanged(SmartDevice device);
}
