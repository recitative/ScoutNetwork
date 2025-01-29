package com.scoutnetwork.master.tool;

import com.scoutnetwork.master.style.ConsoleColor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
@author Sma1lo
*/

public class NetworkMonitor {
    public static void monitorConnections() {
        try {
            System.out.println(ConsoleColor.GREEN + "[INFO]" + ConsoleColor.RESET + " Active network connections:");
            List<String> processOutput = executeCommand("ss", "-tun");

            if (processOutput.isEmpty()) {
                System.out.println(ConsoleColor.YELLOW + "[INFO]" + ConsoleColor.RESET + " No active connections found.");
            } else {
                for (String line : processOutput) {
                    System.out.println(line);
                }
            }
        } catch (Exception e) {
            System.out.println(ConsoleColor.RED + "[ERROR]" + ConsoleColor.RESET + " Network Monitoring: " + e.getMessage());
        }
    }

    private static List<String> executeCommand(String... command) {
        List<String> output = new ArrayList<>();
        try {
            ProcessBuilder builder = new ProcessBuilder(command);
            Process process = builder.start();
            
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.add(line);
                }
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.out.println(ConsoleColor.RED + "[ERROR]" + ConsoleColor.RESET + " Command exited with code: " + exitCode);
            }
        } catch (Exception e) {
            System.out.println(ConsoleColor.RED + "[ERROR]" + ConsoleColor.RESET + " Executing command: " + e.getMessage());
        }
        return output;
    }
}