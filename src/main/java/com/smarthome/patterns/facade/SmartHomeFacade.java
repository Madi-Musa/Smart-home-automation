package com.smarthome.patterns.facade;

import com.smarthome.SmartHome;
import com.smarthome.patterns.control.SmartHomeController;
import com.smarthome.device.SmartDevice;
import com.smarthome.device.Thermostat;
import com.smarthome.patterns.control.DefaultSmartHomeController;

import java.util.List;
import java.util.Objects;

public class SmartHomeFacade implements SmartHomeController {

    private static final double NIGHT_TEMPERATURE = 19.0;

    private final SmartHome home;
    private final SmartHomeController controller;

    public SmartHomeFacade(SmartHome home) {
        this(home, new DefaultSmartHomeController(
                home, device -> {}
        ));
    }

    public SmartHomeFacade(
            SmartHome home,
            SmartHomeController controller
    ) {
        this.home = Objects.requireNonNull(home);
        this.controller = Objects.requireNonNull(controller);
    }

    @Override
    public void turnOn(String deviceId) {
        controller.turnOn(deviceId);
    }

    @Override
    public void turnOff(String deviceId) {
        controller.turnOff(deviceId);
    }

    @Override
    public List<SmartDevice> getDevices() {
        return controller.getDevices();
    }

    public void activateNightMode(
            String deviceToTurnOffId,
            String thermostatId
    ) {
        SmartDevice deviceToTurnOff =
                home.getDeviceById(deviceToTurnOffId);

        SmartDevice climateDevice =
                home.getDeviceById(thermostatId);

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

        controller.turnOff(deviceToTurnOffId);
        controller.turnOn(thermostatId);
        thermostat.setTemperature(NIGHT_TEMPERATURE);
    }
}
