package com.scoutnetwork.master.tool;

import com.scoutnetwork.master.style.ConsoleColor;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class PortScanner {

    public static void scanPorts(Scanner scanner) {
        System.out.print("Enter the host (IP or domain name) to scan: ");
        String host = scanner.nextLine();

        System.out.print("Enter the starting port number: ");
        int startPort = scanner.nextInt();

        System.out.print("Enter the ending port number: ");
        int endPort = scanner.nextInt();

        System.out.println(ConsoleColor.GREEN + "[INFO]" + ConsoleColor.RESET + " Scanning ports on host: " + host);
        
        for (int port = startPort; port <= endPort; port++) {
            scanPort(host, port);
        }
    }

    private static void scanPort(String host, int port) {
        try (Socket socket = new Socket()) {
            socket.connect(new java.net.InetSocketAddress(host, port), 2000);
            System.out.println(ConsoleColor.GREEN + "[OPEN]" + ConsoleColor.RESET + " Port " + port + " is open.");
        } catch (IOException e) {
            System.out.println(ConsoleColor.RED + "[CLOSED]" + ConsoleColor.RESET + " Port " + port + " is closed.");
        }
    }
}