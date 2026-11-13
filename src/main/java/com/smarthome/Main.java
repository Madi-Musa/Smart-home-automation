package com.smarthome;

import com.smarthome.patterns.control.DefaultSmartHomeController;
import com.smarthome.device.SmartLight;
import com.smarthome.device.Thermostat;
import com.smarthome.patterns.observer.ConsoleDeviceObserver;
import com.smarthome.patterns.observer.DeviceEventManager;
import com.smarthome.patterns.strategy.AutomationContext;
import com.smarthome.patterns.strategy.AwayModeStrategy;
import com.smarthome.patterns.strategy.HomeModeStrategy;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SmartHome home = new SmartHome("Demo home", List.of(
                new SmartLight("light-1", "Living room light"),
                new Thermostat("thermostat-1", "Hall thermostat")));
        DeviceEventManager events = new DeviceEventManager();
        ConsoleDeviceObserver dashboard = new ConsoleDeviceObserver();
        events.subscribe(dashboard);
        var controller = new DefaultSmartHomeController(home, events);
        var automation = new AutomationContext(controller, new HomeModeStrategy());

        System.out.println("=== Home mode ===");
        automation.execute();
        System.out.println("=== Away mode: runtime strategy switch ===");
        automation.setStrategy(new AwayModeStrategy());
        automation.execute();
        events.unsubscribe(dashboard);
        System.out.println("=== Observer unsubscribed ===");
        controller.turnOn("light-1");
    }
}
