package com.smarthome.integration;

import com.smarthome.SmartHome;
import com.smarthome.adapter.LampAdapter;
import com.smarthome.adapter.LegacyLamp;
import com.smarthome.device.Thermostat;
import com.smarthome.facade.SmartHomeFacade;
import com.smarthome.patterns.control.DefaultSmartHomeController;
import com.smarthome.patterns.observer.DeviceEventManager;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FacadeObserverIntegrationTest {

    @Test
    void facadeShouldNotifyObservers() {

        LampAdapter lamp = new LampAdapter(
                "L01", "Bedroom Lamp", new LegacyLamp()
        );

        Thermostat thermostat = new Thermostat(
                "T01", "Kitchen Thermostat"
        );

        SmartHome home = new SmartHome(
                "Test Home", List.of(lamp, thermostat)
        );

        DeviceEventManager events = new DeviceEventManager();
        List<String> notifications = new ArrayList<>();

        events.subscribe(device ->
                notifications.add(
                        device.getId() + ":" + device.isOn()
                )
        );

        DefaultSmartHomeController controller =
                new DefaultSmartHomeController(home, events);

        SmartHomeFacade facade =
                new SmartHomeFacade(home, controller);

        facade.turnOn("L01");
        facade.activateNightMode("L01", "T01");

        assertFalse(lamp.isOn());
        assertTrue(thermostat.isOn());
        assertEquals(19.0, thermostat.getTemperature());

        assertEquals(
                List.of(
                        "L01:true",
                        "L01:false",
                        "T01:true"
                ),
                notifications
        );
    }

    @Test
    void facadeRejectsControllerForDifferentHome() {
        SmartHome firstHome = new SmartHome(
                "Home 1",
                List.of(new LampAdapter("L1", "Lamp 1", new LegacyLamp()))
        );

        SmartHome secondHome = new SmartHome(
                "Home 2",
                List.of(new LampAdapter("L2", "Lamp 2", new LegacyLamp()))
        );

        DefaultSmartHomeController wrongController =
                new DefaultSmartHomeController(
                        secondHome,
                        new DeviceEventManager()
                );

        assertThrows(IllegalArgumentException.class,
                () -> new SmartHomeFacade(firstHome, wrongController));
    }
}
