package com.smarthome;

import com.smarthome.builder.SmartHomeBuilder;
import com.smarthome.device.SmartDevice;
import com.smarthome.factory.DeviceFactory;
import com.smarthome.factory.SmartLightFactory;
import com.smarthome.factory.ThermostatFactory;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== Smart Home Automation System ===");

        DeviceFactory lightFactory = new SmartLightFactory();
        DeviceFactory thermostatFactory = new ThermostatFactory();

        SmartDevice livingRoomLight = lightFactory.createDevice(
                "L01", "Living Room Light"
        );

        SmartDevice bedroomLight = lightFactory.createDevice(
                "L02", "Bedroom Light"
        );

        SmartDevice thermostat = thermostatFactory.createDevice(
                "T01", "Kitchen Thermostat"
        );

        SmartHome home = new SmartHomeBuilder()
                .withName("My Smart Home")
                .addDevice(livingRoomLight)
                .addDevice(bedroomLight)
                .addDevice(thermostat)
                .build();

        System.out.println("\nHome: " + home.getName());
        System.out.println("Total devices: " + home.getDevices().size());

        System.out.println("\n=== Device List ===");

        for (SmartDevice device : home.getDevices()) {
            System.out.println(
                    device.getId() + " - " + device.getName()
            );
        }

        System.out.println("\n=== Device Control ===");

        home.getDeviceById("L01").turnOn();
        home.getDeviceById("T01").turnOn();

        System.out.println(
                "Living Room Light ON: "
                        + home.getDeviceById("L01").isOn()
        );

        System.out.println(
                "Kitchen Thermostat ON: "
                        + home.getDeviceById("T01").isOn()
        );

        home.getDeviceById("L01").turnOff();
    }
}
