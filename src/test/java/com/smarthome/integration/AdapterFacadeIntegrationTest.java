package com.smarthome.integration;

import com.smarthome.patterns.builder.SmartHome;
import com.smarthome.patterns.adapter.LampAdapter;
import com.smarthome.patterns.adapter.LegacyLamp;
import com.smarthome.patterns.factory.device.Thermostat;
import com.smarthome.patterns.facade.SmartHomeFacade;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdapterFacadeIntegrationTest {

    @Test
    void adapterShouldControlLegacyLamp() {
        LegacyLamp legacy = new LegacyLamp();
        LampAdapter lamp = new LampAdapter(
                "L01", "Bedroom Lamp", legacy
        );

        lamp.turnOn();
        assertTrue(legacy.hasPower());

        lamp.turnOff();
        assertFalse(legacy.hasPower());
    }

    @Test
    void facadeShouldControlDevices() {
        LampAdapter lamp = new LampAdapter(
                "L01", "Bedroom Lamp", new LegacyLamp()
        );

        SmartHome home = new SmartHome(
                "Test Home", List.of(lamp)
        );

        SmartHomeFacade facade = new SmartHomeFacade(home);

        facade.turnOn("L01");
        assertTrue(lamp.isOn());

        facade.turnOff("L01");
        assertFalse(lamp.isOn());
    }

    @Test
    void nightModeShouldUpdateDevices() {
        LampAdapter lamp = new LampAdapter(
                "L01", "Bedroom Lamp", new LegacyLamp()
        );

        Thermostat thermostat = new Thermostat(
                "T01", "Kitchen Thermostat"
        );

        SmartHome home = new SmartHome(
                "Test Home", List.of(lamp, thermostat)
        );

        SmartHomeFacade facade = new SmartHomeFacade(home);

        facade.turnOn("L01");
        facade.activateNightMode("L01", "T01");

        assertFalse(lamp.isOn());
        assertTrue(thermostat.isOn());
        assertEquals(19.0, thermostat.getTemperature());
    }
}
