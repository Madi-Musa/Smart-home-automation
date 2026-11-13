package com.smarthome.patterns.factory;

import com.smarthome.device.SmartDevice;
import com.smarthome.device.SmartLight;

public class SmartLightFactory extends DeviceFactory {

    @Override
    public SmartDevice createDevice(String id, String name) {
        return new SmartLight(id, name);
    }
}
