# EV Charging Station Network

An Advanced Object Oriented Programming (25CS2205E) project proposal for a Java 17 EV charging platform. A driver can find a compatible charger, reserve a time slot, track energy during a session, and get a bill for the energy delivered and any applicable idle time.

## Presentation

[Download the Review 1 PowerPoint](docs/EV-Charging-Station-Network-Review-1.pptx?raw=true)

The 13-slide deck covers the problem, related research, proposed design, use cases, requirements, feasibility, and the end-to-end workflow. Slide 5 pairs findings from four research papers with the prototype's planned responses. Full citations are in the speaker notes.

## Run the Java starter

From the repository root with JDK 17 installed:

```sh
javac -d out src/Charger.java src/ChargingStation.java src/ChargingStationNetwork.java
java -cp out ChargingStationNetwork
```

The console demo creates a station, displays its charger states, and lists the available DC fast chargers. `ChargingStation` keeps its charger collection private and returns a read-only list. `Charger` uses its ID for equality so the station rejects duplicate IDs.

## Proposed prototype

| Scenario | Planned behavior |
| --- | --- |
| Two drivers request the same slot | A database transaction locks the charger while checking for overlaps and saving the booking. Only one request gets the slot. |
| A charger disconnects mid-session | The system records an interruption and creates one partial invoice using the last verified energy reading. |
| A site reaches its fast-charging limit | The system checks and claims a shared permit before a fast-charging session starts. A fourth session waits in the demo scenario. |
| A device or payment service retries a request | The system recognizes the repeated request and avoids duplicate invoices or charges. |

## Java design

- **Records and encapsulation** model values such as `TimeSlot` and `Energy` and enforce rules such as nondecreasing energy readings.
- **Sealed types** define the supported AC and DC fast charger types.
- **State, Strategy, Observer, and Adapter** organize the session lifecycle, tariff selection, driver alerts, and messages from the charger simulator.
- **Concurrency tests** use `ExecutorService` to send simultaneous booking or charging requests and verify that limits are enforced.

The starter runs in memory with Java 17. The proposed full prototype adds Spring Boot, PostgreSQL, Maven, JUnit, and simulated charger and payment integrations.
