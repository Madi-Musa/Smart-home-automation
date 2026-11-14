# Smart Home Automation System

Software Design Patterns (SDP) — Final Team Project

## 1. Project Overview

Smart Home Automation is a Java application that demonstrates the integration of six software design patterns into a single smart home management system.

The application supports creating smart devices, configuring a smart home, controlling devices, integrating legacy equipment, receiving device state notifications, and switching between automation modes.

The project is implemented using Java 21, Maven, and JUnit 5.

## 2. Implemented Design Patterns

The project implements six design patterns from three categories.

| Category | Design Pattern | Main Classes |
|---|---|---|
| Creational | Factory Method | DeviceFactory, SmartLightFactory, ThermostatFactory |
| Creational | Builder | SmartHomeBuilder, SmartHome |
| Structural | Adapter | LampAdapter, LegacyLamp |
| Structural | Facade | SmartHomeFacade |
| Behavioral | Observer | DeviceEventManager, DeviceObserver, ConsoleDeviceObserver |
| Behavioral | Strategy | AutomationContext, AutomationStrategy, HomeModeStrategy, AwayModeStrategy |

### Factory Method

Creates different smart devices through specialized factory classes.

### Builder

Constructs a SmartHome object by configuring its name and adding devices.

### Adapter

Integrates a legacy lamp into the system through the common SmartDevice interface.

### Facade

Provides simplified access to smart home operations and implements a night mode that turns off a selected device and activates a thermostat at 19°C.

### Observer

Notifies subscribed observers when device power states change through the controller.

### Strategy

Allows the application to switch between Home Mode and Away Mode during runtime.

## 3. Pattern Integration

All six design patterns are integrated into one application.

The main execution flow is:

1. Factory Method creates smart devices.
2. Builder assembles the SmartHome.
3. Adapter integrates a legacy lamp.
4. Facade provides simplified device management.
5. Strategy executes the selected automation mode.
6. Observer notifies subscribers when device power states change.

The integration is demonstrated in `Main.java`.

## 4. Technologies

- Java 21
- Maven
- JUnit 5
- Object-Oriented Programming
- SOLID, KISS, and DRY principles
- UML class diagrams

## 5. Project Structure

The Java source is organized under exactly six main design-pattern packages: `adapter`, `builder`, `facade`, `factory`, `observer`, and `strategy`. Shared supporting classes are nested under the relevant pattern package. `Main.java` is the only class directly in `com.smarthome`.

```text
src/
├── main/
│   └── java/
│       └── com/
│           └── smarthome/
│               ├── Main.java
│               └── patterns/
│                   ├── adapter/
│                   │   ├── LampAdapter.java
│                   │   └── LegacyLamp.java
│                   ├── builder/
│                   │   ├── SmartHome.java
│                   │   └── SmartHomeBuilder.java
│                   ├── facade/
│                   │   ├── SmartHomeFacade.java
│                   │   └── control/
│                   │       ├── DefaultSmartHomeController.java
│                   │       └── SmartHomeController.java
│                   ├── factory/
│                   │   ├── DeviceFactory.java
│                   │   ├── SmartLightFactory.java
│                   │   ├── ThermostatFactory.java
│                   │   └── device/
│                   │       ├── SmartDevice.java
│                   │       ├── SmartLight.java
│                   │       └── Thermostat.java
│                   ├── observer/
│                   │   ├── ConsoleDeviceObserver.java
│                   │   ├── DeviceEventManager.java
│                   │   ├── DeviceObserver.java
│                   │   └── events/
│                   │       └── DeviceEventPublisher.java
│                   └── strategy/
│                       ├── AutomationContext.java
│                       ├── AutomationStrategy.java
│                       ├── AwayModeStrategy.java
│                       └── HomeModeStrategy.java
└── test/
    └── java/
        └── com/
            └── smarthome/
                ├── AutomationTest.java
                ├── builder/
                │   └── SmartHomeBuilderTest.java
                ├── factory/
                │   └── FactoryMethodTest.java
                └── integration/
                    ├── AdapterFacadeIntegrationTest.java
                    └── FacadeObserverIntegrationTest.java
```