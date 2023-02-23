[![Build Status](https://travis-ci.com/theKayani/hklib-lualibs.svg?branch=main)](https://travis-ci.com/github/theKayani/hklib-lualibs)
[![Maven Central](https://img.shields.io/maven-central/v/com.thekayani/hklib-lualibs.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.thekayani%22%20AND%20a:%22hklib-lualibs%22)
[![javadoc](https://javadoc.io/badge2/com.thekayani/hklib-lualibs/javadoc.svg)](https://javadoc.io/doc/com.thekayani/hklib-lualibs)

# hklib-lualibs
_(extension of hklib)_

This is an addition to the [hklib](https://github.com/theKayani/hklib)
library, which contains a Lua Interpreter with strong Java
integration. This library encompasses various native Java packages and
functionalities into various types of [`LuaLibrary`](https://javadoc.io/doc/com.thekayani/hklib/latest/com/hk/lua/package-summary.html) s.

##### Version: 1.0.2 / _built with_ [_hklib-1.3.1_](https://mvnrepository.com/artifact/com.thekayani/hklib/1.3.1) _and_ `java 1.8`

### Current Features
- UUID Library
- _Socket Library (WIP)_
- _Graphics Library (WIP)_
- _HTTP Library (WIP)_
- _File System Library (WIP)_
- _Javax Swing Library (WIP)_
- _Java Discord API Library (WIP)_
- _Vector, Matrix, Quaternion Library (WIP)_
- _Neural Network Library (WIP)_
- _Unit Testing Library (WIP)_

## Usage

#### Reference
You can access the Javadoc over at [https://javadoc.io/doc/com.thekayani/hklib-lualibs](https://javadoc.io/doc/com.thekayani/hklib-lualibs)

The process to use it is very simple and are similar to each other.
Simply add the library/dependency to your build file.

### Maven
Add `hklib-lualibs` dependency

    <dependency>
        <groupId>com.thekayani</groupId>
        <artifactId>hklib-lualibs</artifactId>
        <version>1.0.2</version>
    </dependency>

### Gradle
Add `hklib-lualibs` dependency

    dependencies {
	        implementation 'com.thekayani:hklib-lualibs:1.0.2'
	}

### Flat `jar` File
You have access to the `jar` file with the compiled code and sources under GitHub releases.
You can download the `jar` file [here](https://search.maven.org/artifact/com.thekayani/hklib-lualibs).
This can be used directly on the command line when executing a Java program using
the `-classpath` flag with the `java` command. Or added into your own project in various
IDEs. **NOTE: You will also have to include the flat `hklib` jar file as well, from [here](https://github.com/theKayani/hklib#flat-jar-file)**

#### Others
You can explore the other ways over at [search.maven.org](https://search.maven.org/artifact/com.thekayani/hklib-lualibs)

## Development

To develop this library further, you can clone the repo and use Maven to
import this project into the major Java IDEs such as Eclipse, Netbeans, and IntelliJ IDEA