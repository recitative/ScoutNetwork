package com.scoutnetwork.master.tool;

import com.scoutnetwork.master.style.ConsoleColor;

import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/*
@author Sma1lo
*/

public class PortScanner {
    private static int timeout;

    public static void scanPorts(Scanner scanner) {
        System.out.print("Enter host to scan: ");
        String host = scanner.nextLine();

        System.out.print("Enter the starting port: ");
        int startPort = scanner.nextInt();

        System.out.print("Enter the destination port: ");
        int endPort = scanner.nextInt();

        System.out.print("Enter the timeout in milliseconds: ");
        timeout = scanner.nextInt();

        System.out.print("Enter the number of threads (1-100): ");
        int threadCount = scanner.nextInt();
        if (threadCount < 1 || threadCount > 100) {
            System.out.println(ConsoleColor.RED + "[ERROR]" + ConsoleColor.RESET + "Invalid number of threads. Defaulting to 10.");
            threadCount = 10;
        }

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        for (int port = startPort; port <= endPort; port++) {
            final int currentPort = port;
            executor.submit(() -> {
                try (Socket socket = new Socket(host, currentPort)) {
                    socket.setSoTimeout(timeout);
                    System.out.println(ConsoleColor.GREEN + "[INFO]" + ConsoleColor.RESET + "Port " + currentPort + " is open");
                } catch (Exception e) {
                    System.out.println(ConsoleColor.GREEN + "[INFO]" + ConsoleColor.RESET + "Port " + currentPort + " is closed");
                }
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            
        }

        System.out.println(ConsoleColor.YELLOW + "[INFO]" + ConsoleColor.RESET + "Scanning completed.");
    }
}