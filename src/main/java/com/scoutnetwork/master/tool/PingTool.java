package com.scoutnetwork.master.tool;

import com.scoutnetwork.master.style.ConsoleColor;

import java.net.InetAddress;
import java.util.Scanner;

/*
@author Sma1lo
*/

public class PingTool {
    public static void ping(Scanner scanner) {
        System.out.print("Enter IP address or domain name to ping: ");
        String host = scanner.nextLine();

        try {
            InetAddress address = InetAddress.getByName(host);
            if (address.isReachable(1000)) {
                System.out.println(ConsoleColor.GREEN + "[AVAILABLE]" + ConsoleColor.RESET + host + " available");
            } else {
                System.out.println(ConsoleColor.RED + "[UNAVAILABLE]" + ConsoleColor.RESET + host + " unavailable");
            }
        } catch (Exception e) {
            System.out.println(ConsoleColor.RED + "[ERROR]" + ConsoleColor.RESET + " " + e.getMessage());
        }
    }
}