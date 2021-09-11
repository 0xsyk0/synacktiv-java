package com.synacktiv;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IMonitoringService extends Remote {
    String login(String paramString1, String paramString2) throws RemoteException;

    String sendData(String paramString, Object paramObject) throws RemoteException;

    String getStatus() throws RemoteException;
}

