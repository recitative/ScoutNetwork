package com.scoutnetwork.master.tool;

import com.scoutnetwork.master.style.ConsoleColor;

import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
@author Sma1lo
*/

public class NetworkScanner {
    public static void scanNetwork(Scanner scanner) {
        System.out.print("Enter the subnet (192.168.1.): ");
        String subnet = scanner.nextLine();
        List<Thread> threads = new ArrayList<>();

        for (int i = 1; i < 255; i++) {
            String host = subnet + i;
            Thread thread = new Thread(() -> checkHost(host));
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println(ConsoleColor.RED + "[ERROR]" + ConsoleColor.RESET + " Thread interrupted: " + e.getMessage());
            }
        }
    }

    private static void checkHost(String host) {
        try (Socket socket = new Socket(host, 80)) {
            System.out.println(ConsoleColor.GREEN + "[INFO] " + ConsoleColor.RESET + host + " available");
        } catch (Exception e) {
        }
    }
}