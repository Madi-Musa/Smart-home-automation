package com.smarthome.patterns.strategy;

import com.smarthome.patterns.control.SmartHomeController;

public final class AwayModeStrategy implements AutomationStrategy {
    @Override
    public void apply(SmartHomeController controller) {
        controller.getDevices().forEach(device -> controller.turnOff(device.getId()));
    }
}
