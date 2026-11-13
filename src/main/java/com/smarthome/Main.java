
package com.smarthome;

import com.smarthome.adapter.LampAdapter;
import com.smarthome.adapter.LegacyLamp;
import com.smarthome.device.SmartLight;
import com.smarthome.device.Thermostat;
import com.smarthome.facade.SmartHomeFacade;
import com.smarthome.patterns.control.DefaultSmartHomeController;
import com.smarthome.patterns.observer.ConsoleDeviceObserver;
import com.smarthome.patterns.observer.DeviceEventManager;
import com.smarthome.patterns.strategy.AutomationContext;
import com.smarthome.patterns.strategy.AwayModeStrategy;
import com.smarthome.patterns.strategy.HomeModeStrategy;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== Smart Home Automation System ===");

        String lampId = "lamp-1";
        String lightId = "light-1";
        String thermostatId = "thermostat-1";

        LampAdapter lamp = new LampAdapter(
                lampId, "Bedroom Lamp", new LegacyLamp()
        );

        SmartLight light = new SmartLight(
                lightId, "Living Room Light"
        );

        Thermostat thermostat = new Thermostat(
                thermostatId, "Bedroom Thermostat"
        );

        SmartHome home = new SmartHome(
                "My Smart Home",
                List.of(lamp, light, thermostat)
        );

        System.out.println("\n=== Adapter and Facade ===");

        SmartHomeFacade facade = new SmartHomeFacade(home);

        facade.turnOn(lampId);

        System.out.println(
                "Before night mode: lamp on = " + lamp.isOn()
        );

        facade.activateNightMode(lampId, thermostatId);

        System.out.println(
                "After night mode: lamp on = " + lamp.isOn()
        );

        System.out.println(
                "Thermostat on = " + thermostat.isOn()
        );

        System.out.println(
                "Temperature = " + thermostat.getTemperature() + "°C"
        );

        System.out.println("\n=== Observer and Strategy ===");

        DeviceEventManager events = new DeviceEventManager();
        ConsoleDeviceObserver dashboard = new ConsoleDeviceObserver();

        events.subscribe(dashboard);

        DefaultSmartHomeController controller =
                new DefaultSmartHomeController(home, events);

        AutomationContext automation = new AutomationContext(
                controller, new HomeModeStrategy()
        );

        System.out.println("\n=== Home Mode ===");
        automation.execute();

        System.out.println("\n=== Away Mode ===");
        automation.setStrategy(new AwayModeStrategy());
        automation.execute();

        events.unsubscribe(dashboard);

        System.out.println("\n=== Observer Unsubscribed ===");
        controller.turnOn(lightId);

        System.out.println("\n=== Demo Completed ===");
    }
}
