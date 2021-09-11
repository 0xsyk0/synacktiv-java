package com.synacktiv;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SystemMonitor {
    public static String execCmd(String[] commands) {
        Runtime rt = Runtime.getRuntime();
        String s = null;
        String output = "";
        String error = "";
        try {
            Process proc = rt.exec(commands);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
            while ((s = stdInput.readLine()) != null)
                output = String.valueOf(output) + s + "\n";
            while ((s = stdError.readLine()) != null)
                error = String.valueOf(error) + s + "\n";
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return output;
    }
}
