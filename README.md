digital-twin/
├── default.nix
├── shell.nix
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── digitaltwin/
│   │   │               ├── Main.java
│   │   │               ├── core/
│   │   │               │   ├── Machine.java
│   │   │               │   ├── Product.java
│   │   │               │   ├── ProductionLine.java
│   │   │               │   └── Simulator.java
│   │   │               ├── event/
│   │   │               │   ├── Event.java
│   │   │               │   ├── StateChangeEvent.java
│   │   │               │   └── ProductEvent.java
│   │   │               ├── plugin/
│   │   │               │   └── BottlingLine.java
│   │   │               └── io/
│   │   │                   ├── MqttPublisher.java
│   │   │                   └── PostgresWriter.java
│   └── test/
│       └── java/
│           └── com/
│               └── example/
│                   └── digitaltwin/
│                       └── SimulatorTest.java
