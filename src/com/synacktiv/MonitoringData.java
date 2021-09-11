package com.synacktiv;

import java.io.Serializable;

public class MonitoringData implements Serializable {
    private String networkConn = "";

    private String networkConnListen = "";

    private String process = "";

    public void monitor() {
        monitorNetworkConnListen();
        monitorNetworkConn();
        monitorProcess();
    }

    private void monitorNetworkConnListen() {
        String[] cmd = { "/usr/bin/ss", "-tlp" };
        this.networkConnListen = SystemMonitor.execCmd(cmd);
    }

    private void monitorNetworkConn() {
        String[] cmd = { "/usr/bin/ss", "-tp" };
        this.networkConn = SystemMonitor.execCmd(cmd);
    }

    private void monitorProcess() {
        String[] cmd = { "/usr/bin/ps", "-aux" };
        this.process = SystemMonitor.execCmd(cmd);
    }

    public String getNetworkConn() {
        return this.networkConn;
    }

    public String getNetworkConnListen() {
        return this.networkConnListen;
    }

    public String getProcess() {
        return this.process;
    }
}
