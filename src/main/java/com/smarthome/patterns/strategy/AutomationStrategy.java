package com.smarthome.patterns.strategy;

import com.smarthome.control.SmartHomeController;

@FunctionalInterface
public interface AutomationStrategy {
    void apply(SmartHomeController controller);
}
