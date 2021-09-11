package com.synacktiv;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {

    public static void main(String[] args) {
        try {
            String serverIP = "172.22.1.250";
            int serverPort = 1099;
            Registry registry = LocateRegistry.getRegistry(serverIP, serverPort);
            IMonitoringService monitoringservice = (IMonitoringService)registry.lookup("monitoring");
            MonitoringCore monitoringCore = new MonitoringCore(monitoringservice, "admin", "admin");
            monitoringCore.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
