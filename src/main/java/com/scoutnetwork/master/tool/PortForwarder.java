package com.scoutnetwork.master.tool;

import com.scoutnetwork.master.style.ConsoleColor;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/*
@author Sma1lo
*/

public class PortForwarder {
    public static void startForwarding(Scanner scanner) throws IOException {
        System.out.print("Enter local port: ");
        int localPort = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter remote host: ");
        String remoteHost = scanner.nextLine();

        System.out.print("Enter the remote port: ");
        int remotePort = scanner.nextInt();

        ServerSocket serverSocket = new ServerSocket(localPort);
        System.out.println(ConsoleColor.GREEN + "[INFO]" + ConsoleColor.RESET + "Listen on the port " + localPort);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            new Thread(() -> handleClient(clientSocket, remoteHost, remotePort)).start();
        }
    }

    private static void handleClient(Socket clientSocket, String remoteHost, int remotePort) {
        try (Socket remoteSocket = new Socket(remoteHost, remotePort);
             InputStream clientInput = clientSocket.getInputStream();
             OutputStream clientOutput = clientSocket.getOutputStream();
             InputStream remoteInput = remoteSocket.getInputStream();
             OutputStream remoteOutput = remoteSocket.getOutputStream()) {

            Thread clientToRemote = new Thread(() -> forwardData(clientInput, remoteOutput));
            Thread remoteToClient = new Thread(() -> forwardData(remoteInput, clientOutput));

            clientToRemote.start();
            remoteToClient.start();

            clientToRemote.join();
            remoteToClient.join();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void forwardData(InputStream input, OutputStream output) {
        byte[] buffer = new byte[4096];
        int bytesRead;
        try {
            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
                output.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}