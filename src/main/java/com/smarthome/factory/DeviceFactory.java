package com.smarthome.factory;

import com.smarthome.device.SmartDevice;

public abstract class DeviceFactory {

    public abstract SmartDevice createDevice(
            String id, String name
    );

    public SmartDevice createAndTurnOn(
            String id, String name
    ) {
        SmartDevice device = createDevice(id, name);
        device.turnOn();
        return device;
    }
}
