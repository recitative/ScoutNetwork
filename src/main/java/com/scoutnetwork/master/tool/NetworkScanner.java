package com.scoutnetwork.master.tool;

import com.scoutnetwork.master.style.ConsoleColor;

import java.net.InetAddress;
import java.util.Scanner;

/*
@author Sma1lo
*/

public class NetworkScanner {
    public static void scanNetwork(Scanner scanner) {
        System.out.print("Enter the subnet (192.168.1.): ");
        String subnet = scanner.nextLine();

        for (int i = 1; i < 255; i++) {
            String host = subnet + i;
            try {
                InetAddress address = InetAddress.getByName(host);
                if (address.isReachable(1000)) {
                    System.out.println(ConsoleColor.GREEN + "[INFO] " + ConsoleColor.RESET + host + " available");
                }
            } catch (Exception e) {
                System.out.println(ConsoleColor.RED + "[ERROR]" + ConsoleColor.RESET +"While checking " + host + ": " + e.getMessage());
            }
        }
    }
}