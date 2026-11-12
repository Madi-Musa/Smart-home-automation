package com.smarthome.events;

import com.smarthome.device.SmartDevice;

public interface DeviceEventPublisher {

    void notifyDeviceChanged(SmartDevice device);
}
