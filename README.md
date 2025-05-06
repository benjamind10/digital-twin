# 🏭 Digital Twin Simulator (Java + Maven)

This is a lightweight digital twin simulation of a manufacturing production line written in Java. It models products moving through machines, simulates machine states, tracks scrap, and logs production metrics.

---

## 🚀 Features

- Product flow across a virtual production line
- Machine state simulation (IDLE → RUNNING, etc.)
- Random scrap logic with scrap count tracking
- Product timestamps (entry & exit)
- End-of-run production report
- Unit tests with JUnit 5
- Nix shell support for reproducible development

---

## 🧰 Requirements

- Java 17+
- [Maven](https://maven.apache.org/)
- (Optional) [Nix](https://nixos.org/) for dev shell

---

## 🛠️ Setup & Build

```bash
# Enter Nix shell (if using NixOS)
nix-shell

# Clone this repo (if remote)
git clone <your-repo-url>
cd digital-twin

# Build the project
mvn clean package
```

---

## ▶️ Run the Simulator

```bash
# Run via Maven exec plugin
mvn exec:java
```

Or directly via Java:

```bash
java -cp target/digital-twin-1.0-SNAPSHOT.jar com.example.digitaltwin.Main
```

---

## ✅ Run Tests

```bash
mvn test
```

---

## 📦 Project Structure

```
src/
├── main/java/com/example/digitaltwin/
│   ├── core/             # Product, Machine, Simulator, Line
│   └── event/            # Events (future)
└── test/java/            # JUnit tests
```

---

## 📋 Output Sample

```
⏱️ Cycle 1
🔄 Product XYZ processed by Cutter
❌ Product ABC scrapped at Welder
📊 Produced: 1, Scrapped: 1
```

---

## 🧩 Future Enhancements

- MQTT integration (e.g. HiveMQ)
- PostgreSQL or InfluxDB data logging
- Fault and rework simulation
- GraphQL API or Ignition Perspective front-end

---

## 📄 License

MIT — use freely, modify boldly.
