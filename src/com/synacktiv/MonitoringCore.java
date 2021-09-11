package com.synacktiv;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.rmi.RemoteException;
import java.util.Scanner;

import net.sf.json.JSONObject;

public class MonitoringCore {
    private String token;

    private String login;

    private String password;

    private IMonitoringService monitoringService;

    public MonitoringCore(IMonitoringService monitoringservice) {
        this.token = "";
        this.login = "";
        this.password = "";
        this.monitoringService = monitoringservice;
    }

    public MonitoringCore(IMonitoringService monitoringservice, String login, String passwd) {
        this.token = "";
        this.login = login;
        this.password = passwd;
        this.monitoringService = monitoringservice;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private void doLogin() throws RemoteException {
        String res = this.monitoringService.login(this.login, this.password);
        JSONObject jsonObject = JSONObject.fromObject(res);
        if (jsonObject.get("status").equals("OK")) {
            System.out.println("[+] Valid credentials, storing the token.");
            this.token = (String)jsonObject.get("token");
        } else {
            System.out.println("[+] Invalid credentials.");
        }
    }

    private void sendData() throws IOException {
        if (this.token != "") {
            System.out.println("[+] Monitoring data.");
            MonitoringData monitoringData = new MonitoringData();
            monitoringData.monitor();
            System.out.println("[+] sending collected data.");
            String actual = Files.readString(Path.of("./payload.txt"));
            String res = this.monitoringService.sendData(this.token, actual);
            System.out.println(monitoringData);
            JSONObject jsonObject = JSONObject.fromObject(res);
            if (jsonObject.get("status").equals("OK")) {
                System.out.println("[+] Sucess : " + (String)jsonObject.get("message"));
            } else {
                System.out.println("[+] Failure : " + (String)jsonObject.get("reason"));
            }
//            Scanner scanner = new Scanner(System.in);
//            while (true) {
//                System.out.println("Please input a line");
//                String line = scanner.nextLine();
//                String resa = this.monitoringService.sendData(this.token, line);
//                JSONObject jsonObjecta = JSONObject.fromObject(resa);
//                System.out.println(resa);
//                if (jsonObjecta.get("status").equals("OK")) {
//                    System.out.println("[+] Sucess : " + (String)jsonObjecta.get("message"));
//                } else {
//                    System.out.println("[+] Failure : " + (String)jsonObjecta.get("reason"));
//                }
//            }
        }
    }

    public void run() throws RemoteException {
        doLogin();
        try {
            sendData();
        } catch (IOException e){
            System.out.println(e);
        }
    }
}
