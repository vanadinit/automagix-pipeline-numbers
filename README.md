# Automagix Pipeline Numbers

[![Version](https://img.shields.io/badge/version-1.0.0--SNAPSHOT-blue.svg)](gradle.properties)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

An IntelliJ Platform plugin (compatible with IntelliJ IDEA, PyCharm, and other JetBrains IDEs) that displays indecies of the main pipeline in [Automagix](https://codeberg.org/vanadinit/automagix) script files.

---

## Installation

### Manual Installation (From ZIP)

1. Build and get the plugin archive (`automagix-pipeline-numbers-<version>.zip`) from `build/distributions/`.
2. In your IDE, open **Settings** (`Ctrl+Alt+S` on Linux/Windows, `Cmd+,` on macOS) &rarr; **Plugins**.
3. Click the gear icon (**⚙️**) at the top and select **Install Plugin from Disk...**.
4. Select the `.zip` file and click **OK**.

---

## Development & Building

### Prerequisites

- JDK 21+ (managed automatically via Gradle toolchain)
- Gradle (use the included `./gradlew` wrapper)

### Common Tasks

- **Run tests**:
  ```bash
  ./gradlew test
  ```

- **Run a sandbox IDE with the plugin installed**:
  ```bash
  ./gradlew runIde
  ```

- **Build the distribution ZIP**:
  ```bash
  ./gradlew buildPlugin
  ```
  The packaged archive will be created in `build/distributions/`.

- **Verify plugin compatibility**:
  ```bash
  ./gradlew verifyPlugin
  ```

---

## License

This project is licensed under the [MIT License](LICENSE).
