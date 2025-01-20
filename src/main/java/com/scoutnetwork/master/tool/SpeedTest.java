package com.scoutnetwork.master.tool;

import com.scoutnetwork.master.style.ConsoleColor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
@author Sma1lo
*/

public class SpeedTest {
    public static String performSpeedTest() {
        try {
            System.out.println (ConsoleColor.GREEN + "[INFO]" + ConsoleColor.RESET +" Performing Speed Test...");
            List<String> output = executeCommand("speedtest-cli", "--simple");

            if (output.isEmpty()) {
                return ConsoleColor.RED + "[ERROR]" + ConsoleColor.RESET +" Speed test failed. No output from speedtest-cli.";
            }
            return String.join("\n", output);
        } catch (Exception e) {
            return ConsoleColor.RED + "[ERROR]" + ConsoleColor.RESET +" Speed Test: " + e.getMessage();
        }
    }

    private static List<String> executeCommand(String... command) {
        List<String> output = new ArrayList<>();
        try {
            Process process = new ProcessBuilder(command).start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.add(line);
                }
            }
            process.waitFor();
        } catch (Exception e) {
            System.out.println(ConsoleColor.RED + "[ERROR]" + ConsoleColor.RESET + " Executing command: " + e.getMessage());
        }
        return output;
    }
}
