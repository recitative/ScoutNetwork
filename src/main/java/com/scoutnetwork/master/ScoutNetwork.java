package com.scoutnetwork.master;

import com.scoutnetwork.master.style.ASCIIArt;
import com.scoutnetwork.master.style.ConsoleColor;
import com.scoutnetwork.master.tool.*;

import java.util.Scanner;

/*
@author Sma1lo
*/

public class ScoutNetwork {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(ASCIIArt.NETWORKSCOUT);
            System.out.println("\t\t" + ConsoleColor.BLUE + "v 1.0" + ConsoleColor.RESET + ", Author: Sma1lo");
            System.out.println("\n[" + ConsoleColor.BLUE + "1" + ConsoleColor.RESET + "] Network Scanner");
            System.out.println("[" + ConsoleColor.BLUE + "2" + ConsoleColor.RESET + "] Port Forwarder");
            System.out.println("[" + ConsoleColor.BLUE + "3" + ConsoleColor.RESET + "] Port Scanner");
            System.out.println("[" + ConsoleColor.BLUE + "4" + ConsoleColor.RESET + "] Ping");
            System.out.println("[" + ConsoleColor.BLUE + "5" + ConsoleColor.RESET + "] Whois");
            System.out.println("[" + ConsoleColor.BLUE + "6" + ConsoleColor.RESET + "] Wi-Fi Scanner");
            System.out.println("[" + ConsoleColor.BLUE + "7" + ConsoleColor.RESET + "] Speed Test");
            System.out.println("[" + ConsoleColor.BLUE + "8" + ConsoleColor.RESET + "] Network Monitoring");
            System.out.println("[" + ConsoleColor.BLUE + "0" + ConsoleColor.RESET + "] Exit");
            System.out.print("\nEnter number: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1 -> NetworkScanner.scanNetwork(scanner);
                    case 2 -> PortForwarder.startForwarding(scanner);
                    case 3 -> PortScanner.scanPorts(scanner);
                    case 4 -> PingTool.ping(scanner);
                    case 5 -> WhoisTool.whois(scanner);
                    case 6 -> WiFiScanner.scanWiFiNetworks();
                    case 7 -> System.out.println(SpeedTest.performSpeedTest());
                    case 8 -> NetworkMonitor.monitorConnections();
                    case 0 -> System.exit(0);
                    default -> System.out.println(ConsoleColor.RED + "[ERROR]" + ConsoleColor.RESET +" Incorrect selection.");
                }
            } catch (Exception e) {
                System.out.println(ConsoleColor.RED + "[ERROR]" + ConsoleColor.RESET + " An error occurred: " + e.getMessage());
            }
        }
    }
}
