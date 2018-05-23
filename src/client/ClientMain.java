package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import service.*;
import util.Constants;
/**
 * Classe a ser executada para instanciar um cliente
 * 
 */
public class ClientMain implements IListener{
    public static void main(String[] args) {
        try {
             IListener client = new ClientMain();
             IListener stub = 
                     (IListener) UnicastRemoteObject.exportObject(client, 0);

             Registry registry = LocateRegistry
                     .getRegistry(Constants.SERVICE_PORT);
             IService remoteService = 
                     (IService) registry.lookup(Constants.SERVICE_NAME);
             remoteService.addClient(stub);

             String print = "Teste 5";
             remoteService.printRequest(print);
         } catch (Exception e) {
             e.printStackTrace();
         }
    }
}
