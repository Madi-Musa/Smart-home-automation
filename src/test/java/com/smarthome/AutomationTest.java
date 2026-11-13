package com.smarthome;

import com.smarthome.patterns.control.DefaultSmartHomeController;
import com.smarthome.device.SmartDevice;
import com.smarthome.device.SmartLight;
import com.smarthome.device.Thermostat;
import com.smarthome.patterns.observer.DeviceEventManager;
import com.smarthome.patterns.observer.DeviceObserver;
import com.smarthome.patterns.strategy.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.jupiter.api.Assertions.*;

class AutomationTest {
    private final SmartLight light = new SmartLight("light", "Light");
    private final Thermostat thermostat = new Thermostat("heat", "Heating");
    private final DeviceEventManager events = new DeviceEventManager();
    private final DefaultSmartHomeController controller = new DefaultSmartHomeController(
            new SmartHome("Test", List.of(light, thermostat)), events);

    @Test
    void runtimeStrategySwitchChangesDevicesAndNotifiesObservers() {
        List<String> changes = new ArrayList<>();
        events.subscribe(device -> changes.add(device.getId() + ":" + device.isOn()));
        var context = new AutomationContext(controller, new HomeModeStrategy());
        context.execute();
        assertTrue(light.isOn());
        assertTrue(thermostat.isOn());
        context.execute();
        assertEquals(2, changes.size());
        context.setStrategy(new AwayModeStrategy());
        context.execute();
        assertFalse(light.isOn());
        assertFalse(thermostat.isOn());
        assertEquals(List.of("light:true", "heat:true", "light:false", "heat:false"), changes);
    }

    @Test
    void duplicateSubscriptionsAndUnsubscribeWork() {
        AtomicInteger calls = new AtomicInteger();
        DeviceObserver observer = device -> calls.incrementAndGet();
        events.subscribe(observer);
        events.subscribe(observer);
        controller.turnOn("light");
        assertEquals(1, calls.get());
        events.unsubscribe(observer);
        events.unsubscribe(observer);
        controller.turnOff("light");
        assertEquals(1, calls.get());
    }

    @Test
    void callbackCanUnsubscribeWithoutSkippingOtherObservers() {
        AtomicInteger calls = new AtomicInteger();
        DeviceObserver selfRemoving = new DeviceObserver() {
            public void onDeviceChanged(SmartDevice device) { events.unsubscribe(this); }
        };
        events.subscribe(selfRemoving);
        events.subscribe(device -> calls.incrementAndGet());
        controller.turnOn("light");
        controller.turnOff("light");
        assertEquals(2, calls.get());
    }

    @Test
    void failingObserverDoesNotPreventDeliveryToOthers() {
        AtomicInteger calls = new AtomicInteger();
        events.subscribe(device -> { throw new IllegalArgumentException("Failed subscriber"); });
        events.subscribe(device -> calls.incrementAndGet());
        var failure = assertThrows(IllegalStateException.class, () -> controller.turnOn("light"));
        assertEquals(1, failure.getSuppressed().length);
        assertEquals(1, calls.get());
        assertTrue(light.isOn());
    }

    @Test
    void customStrategyNeedsNoChangesToContext() {
        var context = new AutomationContext(controller, c -> c.turnOn("light"));
        context.execute();
        assertTrue(light.isOn());
        assertFalse(thermostat.isOn());
    }

    @Test
    void unknownDeviceIsRejectedWithoutNotification() {
        AtomicInteger calls = new AtomicInteger();
        events.subscribe(device -> calls.incrementAndGet());
        assertThrows(IllegalArgumentException.class, () -> controller.turnOn("missing"));
        assertEquals(0, calls.get());
    }

    @Test
    void emptyHomeIsSupported() {
        var empty = new DefaultSmartHomeController(new SmartHome("Empty", List.of()), events);
        assertDoesNotThrow(() -> new AutomationContext(empty, new HomeModeStrategy()).execute());
        assertDoesNotThrow(() -> new AutomationContext(empty, new AwayModeStrategy()).execute());
    }

    @Test
    void nullDependenciesAreRejected() {
        assertThrows(NullPointerException.class, () -> events.subscribe(null));
        assertThrows(NullPointerException.class, () -> events.notifyDeviceChanged(null));
        assertThrows(NullPointerException.class, () -> new AutomationContext(null, new HomeModeStrategy()));
        var context = new AutomationContext(controller, new HomeModeStrategy());
        assertThrows(NullPointerException.class, () -> context.setStrategy(null));
        assertThrows(NullPointerException.class, () -> new DefaultSmartHomeController(null, events));
    }

    @Test
    void thermostatRejectsNaN() {
        assertThrows(IllegalArgumentException.class,
                () -> thermostat.setTemperature(Double.NaN));
    }

    @Test
    void nullDeviceIdsAreRejected() {
        assertThrows(NullPointerException.class,
                () -> new SmartLight(null, "Light"));

        assertThrows(NullPointerException.class,
                () -> new Thermostat(null, "Heating"));
    }

    @Test
    void directSmartHomeRejectsDuplicateIds() {
        assertThrows(IllegalArgumentException.class,
                () -> new SmartHome("Test", List.of(
                        new SmartLight("L01", "First Light"),
                        new SmartLight("L01", "Second Light")
                )));
    }
}
