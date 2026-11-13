package com.smarthome.factory;

import com.smarthome.device.SmartDevice;
import com.smarthome.device.SmartLight;
import com.smarthome.device.Thermostat;
import com.smarthome.patterns.factory.DeviceFactory;
import com.smarthome.patterns.factory.SmartLightFactory;
import com.smarthome.patterns.factory.ThermostatFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FactoryMethodTest {

    @Test
    void shouldCreateSmartLight() {
        DeviceFactory factory = new SmartLightFactory();

        SmartDevice device = factory.createDevice(
                "L01", "Living Room Light"
        );

        assertInstanceOf(SmartLight.class, device);
        assertEquals("L01", device.getId());
        assertEquals("Living Room Light", device.getName());
        assertFalse(device.isOn());
    }

    @Test
    void shouldCreateThermostat() {
        DeviceFactory factory = new ThermostatFactory();

        SmartDevice device = factory.createDevice(
                "T01", "Bedroom Thermostat"
        );

        assertInstanceOf(Thermostat.class, device);
        assertEquals("T01", device.getId());
        assertEquals("Bedroom Thermostat", device.getName());
        assertFalse(device.isOn());
    }

    @Test
    void shouldCreateAndTurnOnDevice() {
        DeviceFactory factory = new SmartLightFactory();

        SmartDevice device = factory.createAndTurnOn(
                "L02", "Kitchen Light"
        );

        assertTrue(device.isOn());
    }
}
