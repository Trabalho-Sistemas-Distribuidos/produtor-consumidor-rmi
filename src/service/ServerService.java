package service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.*;

/**
 * Implementa os métodos de controle do Servidor
 * 
 */
public class ServerService implements IService{
    private Buffer buffer = new Buffer();
    private ArrayList<IListener> clients = new ArrayList<>();
    private PrinterManager printerOut = new PrinterManager();
    
    @Override
    public void addClient(IListener cliente) throws RemoteException {
        if(clients.size() < 5){
            clients.add(cliente);
            System.out.println("Cliente #" + clients.size() + " conectado");
        }else{
            throw new RemoteException("Falha de conexão: servidor está cheio");
        }
    }

    @Override
    public void printRequest(String print) throws RemoteException {
        buffer.put(print);
        printerOut.print(print);
        String str = buffer.get();
        System.out.println("String: " + str + " foi consumida.");
    }
    
}
