package com.scoutnetwork.master.tool;

import com.scoutnetwork.master.style.ConsoleColor;

import java.net.Socket;
import java.util.Scanner;

/*
@author Sma1lo
*/

public class PortScanner {
    public static void scanPorts(Scanner scanner) {
        System.out.print("Enter host to scan: ");
        String host = scanner.nextLine();

        System.out.print("Enter the starting port: ");
        int startPort = scanner.nextInt();

        System.out.print("Enter the destination port: ");
        int endPort = scanner.nextInt();

        for (int port = startPort; port <= endPort; port++) {
            try (Socket socket = new Socket(host, port)) {
                System.out.println(ConsoleColor.GREEN + "[INFO]" + ConsoleColor.RESET + "Port " + port + " open");
            } catch (Exception e) {
            }
        }
    }
}