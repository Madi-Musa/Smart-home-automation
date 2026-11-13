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

```text
src/
├── main/
│   └── java/
│       └── com/
│           └── smarthome/
│               ├── adapter/
│               ├── builder/
│               ├── control/
│               ├── device/
│               ├── events/
│               ├── facade/
│               ├── factory/
│               ├── patterns/
│               │   ├── control/
│               │   ├── observer/
│               │   └── strategy/
│               ├── Main.java
│               └── SmartHome.java
└── test/
    └── java/
        └── com/
            └── smarthome/
                ├── builder/
                ├── factory/
                ├── integration/
                └── AutomationTest.java
```

## 6. How to Run

### Requirements

- JDK 21 or later
- Maven (3.9+ recommended)

### Run Tests

```sh
mvn test
```

### Compile the Project

```sh
mvn compile
```

### Run the Application

```sh
java -cp target/classes com.smarthome.Main
```

Alternatively, open the project in IntelliJ IDEA and run `Main.java`.

## 7. Application Demonstration

The console demonstration includes:

1. Smart device creation.
2. Smart home configuration.
3. Legacy lamp integration.
4. Device control through Facade.
5. Night Mode activation.
6. Observer notifications.
7. Home Mode execution.
8. Switching to Away Mode.
9. Observer unsubscription.
10. Completion of the demonstration.

## 8. Testing

JUnit 5 is used to verify the functionality of the application.

The test suite includes:

- `AutomationTest`
- `SmartHomeBuilderTest`
- `FactoryMethodTest`
- `AdapterFacadeIntegrationTest`
- `FacadeObserverIntegrationTest`

The tests cover design pattern behavior, device operations, object construction, event notifications, automation modes, and integration between components.

Run all tests using:

```sh
mvn test
```

## 9. UML Diagrams

UML diagrams are available in the `docs/uml/` directory.

- `factory-builder-uml.png`
- `adapter-facade-uml.png`
- `observer-strategy-uml.png`
- `smart-home-full-uml.png`

The full UML diagram represents the integrated architecture of all six design patterns.

Additional implementation details are available in [Observer and Strategy Documentation](docs/observer-strategy.md).

## 10. Design Principles

The application applies object-oriented design principles.

- **SRP:** Classes have separated responsibilities.
- **OCP:** New devices, observers, and strategies can be introduced through abstractions.
- **LSP:** Concrete implementations follow their interface contracts.
- **ISP:** Focused interfaces define specific responsibilities.
- **DIP:** Components depend on abstractions rather than unnecessary concrete implementations.
- **DRY:** Shared behavior is reused instead of duplicated.
- **KISS:** The architecture uses straightforward Java classes and interfaces.

## 11. Current Limitations

- The application uses a console interface.
- Smart devices are simulated rather than connected to physical hardware.
- Temperature changes do not automatically generate Observer notifications.
- Automation actions do not provide transactional rollback.

## 12. Further Improvements

Possible future improvements include:

- Integration with real IoT devices.
- Temperature and motion sensors.
- Additional automation strategies.
- A graphical user interface.
- Asynchronous event notifications.
- Expanded integration tests.

## 13. Conclusion

The Smart Home Automation project demonstrates six integrated software design patterns in a cohesive Java application.

The system combines device creation, configuration, legacy device integration, centralized management, event notifications, and interchangeable automation algorithms.