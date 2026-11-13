package com.smarthome.strategy;

import com.smarthome.control.SmartHomeController;

@FunctionalInterface
public interface AutomationStrategy {
    void apply(SmartHomeController controller);
}
