# Automagix Pipeline Numbers

[![Version](https://img.shields.io/badge/version-1.0.0--SNAPSHOT-blue.svg)](gradle.properties)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

An IntelliJ Platform plugin (compatible with IntelliJ IDEA, PyCharm, and other JetBrains IDEs) that displays 0-based step index numbers at the start of each pipeline step in [Automagix](https://codeberg.org/vanadinit/automagix) YAML files.

---

## Features

- **0-Based Pipeline Indexing**: Automatically displays step numbers (`0`, `1`, `2`, ...) at the beginning of each list item in your `pipeline` sequence.
- **Strict Scope**: Only targets YAML files containing a top-level `pipeline:` sequence. Other YAML files and nested keys are ignored.
- **Modern Inlay Hints**: Built on the IntelliJ Declarative Inlay Hints API. Hints render smoothly without altering the underlying file content.
- **Configurable**: Easily enable or disable hints under `Settings` &rarr; `Editor` &rarr; `Inlay Hints` &rarr; `YAML` &rarr; `Automagix Pipeline Numbers`.
- **Dynamic Plugin Support**: Can be loaded and unloaded dynamically without restarting the IDE.

---

## Example

```yaml
name: Automagix Pipeline Example
pipeline:
  0 - python: PVARS.moin = True
  1 - PVARS.moin?local: echo 'Moin'
  2 - python: PVARS.moin = False
  3 - a=local: uptime
cleanup:
  - local: echo 'Cleaning up...'
```

*(Step indices are rendered as editor inlay hints; they are not part of the file text)*

---

## Installation

### Manual Installation (From ZIP)

1. Download or build the plugin archive (`automagix-pipeline-numbers-<version>.zip`) from `build/distributions/`.
2. In your IDE, open **Settings** (`Ctrl+Alt+S` on Linux/Windows, `Cmd+,` on macOS) &rarr; **Plugins**.
3. Click the gear icon (**⚙️**) at the top and select **Install Plugin from Disk...**.
4. Select the `.zip` file and click **OK**.

---

## Settings

To toggle or customize the hints:
1. Open **Settings / Preferences** &rarr; **Editor** &rarr; **Inlay Hints** &rarr; **YAML**.
2. Locate **Automagix Pipeline Numbers**.
3. Toggle the checkbox to enable or disable hints as desired. A live preview is displayed in the settings pane.

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
