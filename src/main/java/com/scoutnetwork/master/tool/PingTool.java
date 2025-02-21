package com.scoutnetwork.master.tool;

import com.scoutnetwork.master.style.ConsoleColor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/*
@author Sma1lo
*/

public class PingTool {
    public static void ping(Scanner scanner) {
        System.out.print("Enter IP address or domain name to ping: ");
        String host = scanner.nextLine();

        try {
            Process process = Runtime.getRuntime().exec("ping " + host);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                if (line.contains("Destination Host Unreachable") || line.contains("Request timed out")) {
                    System.out.println(ConsoleColor.GREEN + "[INFO] " + ConsoleColor.RESET + host + " unavailable");
                    return;
                }
                if (line.contains("ttl=")) {
                    System.out.println(ConsoleColor.GREEN + "[INFO] " + ConsoleColor.RESET + host + " available");
                    return;
                }
            }
            process.waitFor();
        } catch (Exception e) {
            System.out.println(ConsoleColor.RED + "[ERROR] " + ConsoleColor.RESET + " " + e.getMessage());
        }
    }
}