package com.smarthome.patterns.facade.control;

import com.smarthome.patterns.factory.device.SmartDevice;
import java.util.List;

public interface SmartHomeController {

    void turnOn(String deviceId);

    void turnOff(String deviceId);

    List<SmartDevice> getDevices();
}
