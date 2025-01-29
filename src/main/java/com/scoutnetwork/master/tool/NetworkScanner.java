package com.scoutnetwork.master.tool;

import com.scoutnetwork.master.style.ConsoleColor;

import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
@author Sma1lo
*/

public class NetworkScanner {
    public static void scanNetwork(Scanner scanner) {
        System.out.print("Enter the subnet (192.168.1.): ");
        String subnet = scanner.nextLine();
        ExecutorService executor = Executors.newFixedThreadPool(20);

        for (int i = 1; i < 255; i++) {
            String host = subnet + i;
            executor.submit(() -> checkHost(host));
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
        }
    }

    private static void checkHost(String host) {
        try {
            Thread.sleep(100);
            try (Socket socket = new Socket(host, 80)) {
                System.out.println(ConsoleColor.GREEN + "[INFO] " + ConsoleColor.RESET + host + " available");
            }
        } catch (Exception e) {
        }
    }
}