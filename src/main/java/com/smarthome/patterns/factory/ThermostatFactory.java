package com.smarthome.patterns.factory;

import com.smarthome.patterns.factory.device.SmartDevice;
import com.smarthome.patterns.factory.device.Thermostat;

public class ThermostatFactory extends DeviceFactory {

    @Override
    public SmartDevice createDevice(String id, String name) {
        return new Thermostat(id, name);
    }
}
