package com.smarthome.patterns.observer.events;

import com.smarthome.patterns.factory.device.SmartDevice;

public interface DeviceEventPublisher {

    void notifyDeviceChanged(SmartDevice device);
}
