package com.smarthome.builder;

import com.smarthome.SmartHome;
import com.smarthome.device.SmartDevice;
import com.smarthome.device.SmartLight;
import com.smarthome.device.Thermostat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmartHomeBuilderTest {

    @Test
    void shouldBuildHomeWithDevices() {
        SmartDevice light = new SmartLight("L01", "Living Room Light");
        SmartDevice thermostat = new Thermostat("T01", "Thermostat");

        SmartHome home = new SmartHomeBuilder()
                .withName("My Smart Home")
                .addDevice(light)
                .addDevice(thermostat)
                .build();

        assertEquals("My Smart Home", home.getName());
        assertEquals(2, home.getDevices().size());
        assertSame(light, home.getDeviceById("L01"));
    }

    @Test
    void shouldRejectDuplicateDeviceIds() {
        SmartHomeBuilder builder = new SmartHomeBuilder()
                .withName("My Smart Home")
                .addDevice(new SmartLight("L01", "First Light"));

        assertThrows(IllegalArgumentException.class, () ->
                builder.addDevice(new SmartLight("L01", "Second Light"))
        );
    }

    @Test
    void shouldRequireHomeName() {
        SmartHomeBuilder builder = new SmartHomeBuilder()
                .addDevice(new SmartLight("L01", "Living Room Light"));

        assertThrows(IllegalStateException.class, builder::build);
    }

    @Test
    void shouldRejectBlankHomeName() {
        assertThrows(IllegalArgumentException.class, () ->
                new SmartHomeBuilder().withName("   ")
        );
    }
}
