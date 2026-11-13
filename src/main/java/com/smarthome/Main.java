package com.smarthome;

import com.smarthome.device.SmartDevice;
import com.smarthome.factory.DeviceFactory;
import com.smarthome.factory.SmartLightFactory;
import com.smarthome.factory.ThermostatFactory;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== Smart Home Automation System ===");

        DeviceFactory lightFactory = new SmartLightFactory();
        DeviceFactory thermostatFactory = new ThermostatFactory();

        SmartDevice light = lightFactory.createDevice(
                "L01", "Living Room Light"
        );

        SmartDevice thermostat = thermostatFactory.createDevice(
                "T01", "Bedroom Thermostat"
        );

        light.turnOn();
        thermostat.turnOn();

        System.out.println("Light status: " + light.isOn());
        System.out.println("Thermostat status: " + thermostat.isOn());

        light.turnOff();
        thermostat.turnOff();
    }
}
