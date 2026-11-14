package com.smarthome.patterns.observer;

import com.smarthome.patterns.factory.device.SmartDevice;
import com.smarthome.patterns.observer.events.DeviceEventPublisher;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public final class DeviceEventManager implements DeviceEventPublisher {
    private final Set<DeviceObserver> observers = new LinkedHashSet<>();

    public void subscribe(DeviceObserver observer) {
        observers.add(Objects.requireNonNull(observer, "observer"));
    }

    public void unsubscribe(DeviceObserver observer) {
        observers.remove(Objects.requireNonNull(observer, "observer"));
    }

    @Override
    public void notifyDeviceChanged(SmartDevice device) {
        Objects.requireNonNull(device, "device");
        RuntimeException failure = null;
        for (DeviceObserver observer : List.copyOf(observers)) {
            try {
                observer.onDeviceChanged(device);
            } catch (RuntimeException exception) {
                if (failure == null) {
                    failure = new IllegalStateException("One or more device observers failed");
                }
                failure.addSuppressed(exception);
            }
        }
        if (failure != null) {
            throw failure;
        }
    }
}
