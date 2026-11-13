package com.smarthome.facade;

import com.smarthome.SmartHome;
import com.smarthome.control.SmartHomeController;
import com.smarthome.device.SmartDevice;
import com.smarthome.device.Thermostat;

import java.util.List;
import java.util.Objects;

public class SmartHomeFacade implements SmartHomeController {
    private static final double NIGHT_TEMPERATURE = 19.0;

    private final SmartHome home;

    public SmartHomeFacade(SmartHome home) {
        this.home = Objects.requireNonNull(home);
    }

    @Override
    public void turnOn(String deviceId) {
        home.getDeviceById(deviceId).turnOn();
    }

    @Override
    public void turnOff(String deviceId) {
        home.getDeviceById(deviceId).turnOff();
    }

    @Override
    public List<SmartDevice> getDevices() {
        return home.getDevices();
    }

    public void activateNightMode(String deviceToTurnOffId, String thermostatId) {
        SmartDevice deviceToTurnOff = home.getDeviceById(deviceToTurnOffId);
        SmartDevice climateDevice = home.getDeviceById(thermostatId);

        if (!(climateDevice instanceof Thermostat thermostat)) {
            throw new IllegalArgumentException(
                    "Device is not a thermostat: " + thermostatId
            );
        }
        if (deviceToTurnOff == thermostat) {
            throw new IllegalArgumentException(
                    "Select a separate device to turn off"
            );
        }

        deviceToTurnOff.turnOff();
        thermostat.turnOn();
        thermostat.setTemperature(NIGHT_TEMPERATURE);
    }
}