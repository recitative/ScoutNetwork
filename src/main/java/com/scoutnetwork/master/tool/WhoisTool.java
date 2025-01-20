package com.scoutnetwork.master.tool;

import java.util.Scanner;

import com.scoutnetwork.master.style.ConsoleColor;
import org.apache.commons.net.whois.WhoisClient;
import java.io.IOException;

/*
@author Sma1lo
*/

public class WhoisTool {

    public static void whois(Scanner scanner) {
        System.out.print("Enter Whois server (default: whois.iana.org): ");
        String server = scanner.nextLine();
        if (server.isEmpty()) {
            server = "whois.iana.org";
        }

        System.out.print("Enter a query (domain name, IP or command): ");
        String query = scanner.nextLine();

        WhoisClient whois = new WhoisClient();
        try {
            whois.connect(server);
            System.out.println(ConsoleColor.GREEN + "[INFO]" + ConsoleColor.RESET + "Sending a request to the server " + server + "...");
            String result = whois.query(query);
            System.out.println(ConsoleColor.GREEN + "[INFO]" + ConsoleColor.RESET + "Whois answer:");
            System.out.println(result);
            whois.disconnect();
        } catch (IOException e) {
            System.out.println(ConsoleColor.RED + "[ERROR]" + ConsoleColor.RESET + "Executing Whois query: " + e.getMessage());
        }
    }
}

