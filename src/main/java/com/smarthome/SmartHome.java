
package com.smarthome;

import com.smarthome.device.SmartDevice;
import java.util.List;
import java.util.Objects;

public class SmartHome {

    private final String name;
    private final List<SmartDevice> devices;

    public SmartHome(String name, List<SmartDevice> devices) {
        this.name = Objects.requireNonNull(name);
        this.devices = List.copyOf(devices);
    }

    public String getName() {
        return name;
    }

    public List<SmartDevice> getDevices() {
        return devices;
    }

    public SmartDevice getDeviceById(String id) {
        for (SmartDevice device : devices) {
            if (device.getId().equals(id)) {
                return device;
            }
        }

        throw new IllegalArgumentException(
                "Device not found: " + id
        );
    }
}
