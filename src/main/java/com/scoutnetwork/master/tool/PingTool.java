package com.scoutnetwork.master.tool;

import com.scoutnetwork.master.style.ConsoleColor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.IOException;

/*
@author Sma1lo
*/

public class PingTool {
    public static void ping(Scanner scanner) {
        System.out.print("Enter IP address or domain name to ping: ");
        String host = scanner.nextLine();

        try {
            Process process = Runtime.getRuntime().exec("ping " + host);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            String s;
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

            while ((s = stdError.readLine()) != null) {
                System.err.println(s);
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println(ConsoleColor.GREEN + "[AVAILABLE] " + ConsoleColor.RESET + host + " available");
            } else {
                System.out.println(ConsoleColor.RED + "[UNAVAILABLE] " + ConsoleColor.RESET + host + " unavailable (exit code: " + exitCode + ")");
            }

        } catch (IOException | InterruptedException e) {
            System.out.println(ConsoleColor.RED + "[ERROR] " + ConsoleColor.RESET + " " + e.getMessage());
        }
    }
}