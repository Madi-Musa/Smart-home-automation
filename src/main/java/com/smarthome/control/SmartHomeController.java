package com.smarthome.control;

import com.smarthome.device.SmartDevice;
import java.util.List;

public interface SmartHomeController {

    void turnOn(String deviceId);

    void turnOff(String deviceId);

    List<SmartDevice> getDevices();
}
