package com.smarthome.strategy;

import com.smarthome.control.SmartHomeController;

/** Home mode enables all registered devices. */
public final class HomeModeStrategy implements AutomationStrategy {
    @Override
    public void apply(SmartHomeController controller) {
        controller.getDevices().forEach(device -> controller.turnOn(device.getId()));
    }
}
