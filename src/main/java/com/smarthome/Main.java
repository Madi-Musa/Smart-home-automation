package com.smarthome;

import com.smarthome.adapter.LampAdapter;
import com.smarthome.adapter.LegacyLamp;
import com.smarthome.builder.SmartHomeBuilder;
import com.smarthome.device.SmartDevice;
import com.smarthome.device.Thermostat;
import com.smarthome.facade.SmartHomeFacade;
import com.smarthome.factory.DeviceFactory;
import com.smarthome.factory.SmartLightFactory;
import com.smarthome.factory.ThermostatFactory;
import com.smarthome.patterns.control.DefaultSmartHomeController;
import com.smarthome.patterns.observer.ConsoleDeviceObserver;
import com.smarthome.patterns.observer.DeviceEventManager;
import com.smarthome.patterns.strategy.AutomationContext;
import com.smarthome.patterns.strategy.AwayModeStrategy;
import com.smarthome.patterns.strategy.HomeModeStrategy;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== Smart Home Automation System ===");

        DeviceFactory lightFactory = new SmartLightFactory();
        DeviceFactory thermostatFactory = new ThermostatFactory();

        SmartDevice livingRoomLight = lightFactory.createDevice(
                "L01", "Living Room Light"
        );

        SmartDevice bedroomLight = lightFactory.createDevice(
                "L02", "Bedroom Light"
        );

        SmartDevice thermostat = thermostatFactory.createDevice(
                "T01", "Kitchen Thermostat"
        );

        LampAdapter legacyLamp = new LampAdapter(
                "LEG01", "Legacy Bedroom Lamp", new LegacyLamp()
        );

        SmartHome home = new SmartHomeBuilder()
                .withName("My Smart Home")
                .addDevice(livingRoomLight)
                .addDevice(bedroomLight)
                .addDevice(thermostat)
                .addDevice(legacyLamp)
                .build();

        System.out.println("\n=== Factory Method and Builder ===");
        System.out.println("Home: " + home.getName());
        System.out.println("Total devices: " + home.getDevices().size());

        for (SmartDevice device : home.getDevices()) {
            System.out.println(
                    device.getId() + " - " + device.getName()
            );
        }

        DeviceEventManager events = new DeviceEventManager();
        ConsoleDeviceObserver dashboard = new ConsoleDeviceObserver();

        events.subscribe(dashboard);

        DefaultSmartHomeController controller =
                new DefaultSmartHomeController(home, events);

        SmartHomeFacade facade = new SmartHomeFacade(
                home, controller
        );

        System.out.println("\n=== Adapter and Facade ===");

        facade.turnOn("LEG01");

        System.out.println(
                "Before night mode: lamp on = " + legacyLamp.isOn()
        );

        facade.activateNightMode("LEG01", "T01");

        System.out.println(
                "After night mode: lamp on = " + legacyLamp.isOn()
        );

        System.out.println(
                "Thermostat on = " + thermostat.isOn()
        );

        if (thermostat instanceof Thermostat actualThermostat) {
            System.out.println(
                    "Temperature = "
                            + actualThermostat.getTemperature() + "°C"
            );
        }

        System.out.println("\n=== Observer and Strategy ===");

        AutomationContext automation = new AutomationContext(
                facade, new HomeModeStrategy()
        );

        System.out.println("\n=== Home Mode ===");
        automation.execute();

        System.out.println("\n=== Away Mode ===");
        automation.setStrategy(new AwayModeStrategy());
        automation.execute();

        events.unsubscribe(dashboard);

        System.out.println("\n=== Observer Unsubscribed ===");
        facade.turnOn("L01");

        System.out.println("\n=== Demo Completed ===");
    }
}
