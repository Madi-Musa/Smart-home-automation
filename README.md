# Smart home automation
SDP Final Project - Smart Home Automation System

## Observer and Strategy

Requires JDK 21+ and Maven 3.9+.

```sh
mvn test
mvn compile
java -cp target/classes com.smarthome.Main
```

The demo switches Home/Away strategies at runtime and publishes device power
changes to subscribed observers. See [implementation notes and UML](docs/observer-strategy.md)
for pattern roles, integration contracts, defense notes, and remaining team requirements.
