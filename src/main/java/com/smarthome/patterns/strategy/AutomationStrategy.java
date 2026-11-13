package com.smarthome.patterns.strategy;

import com.smarthome.patterns.control.SmartHomeController;

@FunctionalInterface
public interface AutomationStrategy {
    void apply(SmartHomeController controller);
}
