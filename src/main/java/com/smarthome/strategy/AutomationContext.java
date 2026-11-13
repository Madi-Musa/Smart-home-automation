package com.smarthome.strategy;

import com.smarthome.control.SmartHomeController;
import java.util.Objects;

/** Holds a replaceable algorithm without knowing any concrete mode. */
public final class AutomationContext {
    private final SmartHomeController controller;
    private AutomationStrategy strategy;

    public AutomationContext(SmartHomeController controller, AutomationStrategy strategy) {
        this.controller = Objects.requireNonNull(controller, "controller");
        setStrategy(strategy);
    }

    public void setStrategy(AutomationStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy, "strategy");
    }

    public void execute() {
        strategy.apply(controller);
    }
}
