package com.smarthome.patterns.control;

import com.smarthome.SmartHome;
import com.smarthome.device.SmartDevice;
import com.smarthome.events.DeviceEventPublisher;
import java.util.List;
import java.util.Objects;

public final class DefaultSmartHomeController implements SmartHomeController {
    private final SmartHome home;
    private final DeviceEventPublisher publisher;

    public DefaultSmartHomeController(SmartHome home, DeviceEventPublisher publisher) {
        this.home = Objects.requireNonNull(home, "home");
        this.publisher = Objects.requireNonNull(publisher, "publisher");
    }

    @Override
    public void turnOn(String deviceId) {
        setPower(deviceId, true);
    }

    @Override
    public void turnOff(String deviceId) {
        setPower(deviceId, false);
    }

    private void setPower(String deviceId, boolean on) {
        SmartDevice device = home.getDeviceById(Objects.requireNonNull(deviceId, "deviceId"));
        if (device.isOn() == on) {
            return;
        }
        if (on) {
            device.turnOn();
        } else {
            device.turnOff();
        }
        publisher.notifyDeviceChanged(device);
    }

    @Override
    public List<SmartDevice> getDevices() {
        return home.getDevices();
    }
}
