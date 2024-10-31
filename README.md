<div align="center">
    <img src=".github/translation_library.png" alt="i18n Library">
</div>

# i18n

This is a small internationalization (i18n) library tailored for Minecraft plugins, particularly those built on the [Paper](https://papermc.io/) platform.
It simplifies translation management, allowing you to easily support multiple languages for better player experience.

> [!IMPORTANT]
> This library is designed to work with a Paper server and is **intended for plugin developers only**!
> It is **not meant to be used directly by server owners or players**.

## Purpose

The goal of this project is to provide an easy-to-use API that helps Minecraft plugin developers add localization support to their plugins.

## Project Structure

There are two main components in this project:

- **`api`**: Contains the API classes for managing translations and interfacing with localization files.
- **`example`**: An example implementation demonstrating how to integrate and use the i18n library in a plugin.

## Features

- Simple API for loading and retrieving translations
- Customizable language files, supporting different locales
- Optimized for Minecraft plugins with a focus on ease of use

## Setup

To include this library in your own plugin project, you can pull it from the Reposilite repository. Below are instructions for different build tools:

### 1. Gradle (Kotlin DSL)

1. **Add the Reposilite Repository**:

    ```kotlin
    repositories {
        maven {
            url = uri("https://repo.ole101.de/releases")
        }
    }
    ```

2. **Add the Dependency**:

    ```kotlin
    dependencies {
        implementation("de.ole101:i18n:VERSION")
    }
    ```

3. **Optional: Relocate with Shadow Plugin**:

    ```kotlin
    plugins {
        id("com.github.johnrengelman.shadow") version "8.1.1"
    }

    tasks.named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
        relocate("de.ole101.i18n", "your.plugin.package.libs.i18n")
    }
    ```

### 2. Gradle (Groovy DSL)

1. **Add the Reposilite Repository**:

    ```groovy
    repositories {
        maven {
            url "https://repo.ole101.de/releases"
        }
    }
    ```

2. **Add the Dependency**:

    ```groovy
    dependencies {
        implementation 'de.ole101:i18n:VERSION'
    }
    ```

3. **Optional: Relocate with Shadow Plugin**:

    ```groovy
    plugins {
        id 'com.github.johnrengelman.shadow' version '8.1.1'
    }

    shadowJar {
        relocate 'de.ole101.i18n', 'your.plugin.package.libs.i18n'
    }
    ```

### 3. Maven

1. **Add the Reposilite Repository**:

    ```xml
    <repositories>
        <repository>
            <id>ole101-releases</id>
            <url>https://repo.ole101.de/releases</url>
        </repository>
    </repositories>
    ```

2. **Add the Dependency**:

    ```xml
    <dependencies>
        <dependency>
            <groupId>de.ole101</groupId>
            <artifactId>i18n</artifactId>
            <version>VERSION</version>
        </dependency>
    </dependencies>
    ```

3. **Optional: Relocate with Maven Shade Plugin**:

    ```xml
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-shade-plugin</artifactId>
                <version>3.2.4</version>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals>
                            <goal>shade</goal>
                        </goals>
                        <configuration>
                            <relocations>
                                <relocation>
                                    <pattern>de.ole101.i18n</pattern>
                                    <shadedPattern>your.plugin.package.libs.i18n</shadedPattern>
                                </relocation>
                            </relocations>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
    ```

## Usage

To use this library in your Minecraft plugin:

1. **Load translations**: Create language files (e.g., `common_en.properties`, `common_de.properties`) and place them in your resources folder.

2. **Retrieve messages**: Use the provided API methods to retrieve messages in a specific language. The API can fetch translations based on a player’s locale.

3. **Example Plugin**: Refer to the `example` module for a complete implementation on how to set up i18n in a Minecraft plugin.

## Contributing

Feel free to contribute by forking this repository, creating a new branch, and submitting a pull request.
Contributions for optimizations and features are always welcome!
