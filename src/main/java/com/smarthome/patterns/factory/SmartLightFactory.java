package com.smarthome.patterns.factory;

import com.smarthome.patterns.factory.device.SmartDevice;
import com.smarthome.patterns.factory.device.SmartLight;

public class SmartLightFactory extends DeviceFactory {

    @Override
    public SmartDevice createDevice(String id, String name) {
        return new SmartLight(id, name);
    }
}
