package com.scoutnetwork.master.tool;

import com.scoutnetwork.master.style.ConsoleColor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
@author Sma1lo
*/

public class WiFiScanner {
    public static void scanWiFiNetworks() {
        try {
            System.out.println(ConsoleColor.GREEN + "[INFO]" + ConsoleColor.RESET +"Scanning for nearby Wi-Fi networks:");
            List<String> processOutput = executeCommand("nmcli", "dev", "wifi");

            for (String line : processOutput) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println(ConsoleColor.RED + "[ERROR]" + ConsoleColor.RESET + " WiFi Scan: " + e.getMessage());
        }
    }

    private static List<String> executeCommand(String... command) {
        List<String> output = new ArrayList<>();
        try {
            Process process = new ProcessBuilder(command).start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.add(line);
                }
            }
            process.waitFor();
        } catch (Exception e) {
            System.out.println(ConsoleColor.RED + "[ERROR]" + ConsoleColor.RESET + " Error executing command: " + e.getMessage());
        }
        return output;
    }
}