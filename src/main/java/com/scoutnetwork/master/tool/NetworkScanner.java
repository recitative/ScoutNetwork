package com.scoutnetwork.master.tool;

import com.scoutnetwork.master.style.ConsoleColor;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
@author Sma1lo
*/

public class NetworkScanner {
    public static void scanNetwork(Scanner scanner) {
        System.out.print("Enter the subnet (192.168.1.): ");
        String subnet = scanner.nextLine();
        ExecutorService executor = Executors.newFixedThreadPool(100);

        for (int i = 1; i < 255; i++) {
            String host = subnet + i;
            executor.submit(() -> checkHost(host));
        }

        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
        System.out.println(ConsoleColor.GREEN + "[INFO]"+ ConsoleColor.RESET + " Network scan completed.");
    }

    private static void checkHost(String host) {
        try {
            InetAddress inet = InetAddress.getByName(host);
            if (inet.isReachable(1000)) {
                System.out.println(ConsoleColor.GREEN + "[AVAILABLE] " + ConsoleColor.RESET + host + " available");
            } else {
                System.out.println(ConsoleColor.RED + "[UNAVAILABLE] " + ConsoleColor.RESET + host + " unavailable");
            }
        } catch (IOException e) {
            System.out.println(ConsoleColor.RED + "[UNAVAILABLE] " + ConsoleColor.RESET + host + " unavailable");
        }
    }
}