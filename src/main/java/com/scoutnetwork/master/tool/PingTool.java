package com.scoutnetwork.master.tool;

import com.scoutnetwork.master.style.ConsoleColor;

import java.util.Scanner;

/*
@author Sma1lo
*/

public class PingTool {
    public static void ping(Scanner scanner) {
        System.out.print("Enter IP address or domain name to ping: ");
        String host = scanner.nextLine();

        try {
            ProcessBuilder processBuilder;
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                processBuilder = new ProcessBuilder("cmd", "/c", "ping -n 1 -w 1000 " + host);
            } else {
                processBuilder = new ProcessBuilder("ping", "-c", "1", "-W", "1", host);
            }

            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println(ConsoleColor.GREEN + "[AVAILABLE]" + ConsoleColor.RESET + " " + host + " available");
            } else {
                System.out.println(ConsoleColor.RED + "[UNAVAILABLE]" + ConsoleColor.RESET + " " + host + " unavailable");
            }
        } catch (Exception e) {
            System.out.println(ConsoleColor.RED + "[ERROR]" + ConsoleColor.RESET + " " + e.getMessage());
        }
    }
}