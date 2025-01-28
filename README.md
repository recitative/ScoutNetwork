# Scout Network

Scout Network is a Java program that provides a set of tools for analyzing and managing networks. It includes features such as network scanning, port forwarding, pinging, domain analysis, and much more.

## Features

The program offers the following capabilities:

1. **Network Scanner** - Scan the network to discover connected devices.
2. **Port Forwarder** - Forward ports to access devices on the local network.
3. **Port Scanner** - Scan ports on a specified IP address.
4. **Ping** - Check the availability of a device on the network.
5. **Whois** - Retrieve information about a domain.
6. **Wi-Fi Scanner** - Scan for available Wi-Fi networks.
7. **Speed Test** - Test the speed of your internet connection.
8. **Network Monitoring** - Monitor active connections.

## Installation

### Linux

```bash 
git clone https://github.com/recitative/ScoutNetwork
```

```bash
cd ScoutNetwork
```

```bash
javac -cp "build/libs/*" src/main/java/com/scoutnetwork/master/tool/*.java src/main/java/com/scoutnetwork/master/style/*.java && java -cp "build/libs/*:." com.scoutnetwork.master.tool.WhoisTool
```

```bash 
cd src/main/java
```

```bash
javac com/scoutnetwork/master/ScoutNetwork.java
```

```bash
java com.scoutnetwork.master.ScoutNetwork
```