package com.smarthome.strategy;

import com.smarthome.control.SmartHomeController;

/** Away mode disables all registered devices to save energy. */
public final class AwayModeStrategy implements AutomationStrategy {
    @Override
    public void apply(SmartHomeController controller) {
        controller.getDevices().forEach(device -> controller.turnOff(device.getId()));
    }
}
