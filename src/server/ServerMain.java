package server;

import java.io.FileNotFoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import service.*;
import util.Constants;

/**
 * Classe a ser executada para começar o servidor
 * 
 */
public class ServerMain {
    public static void main(String[] args) throws FileNotFoundException {
        try {
            IService service = new ServerService();
            IService stub = 
                    (IService) UnicastRemoteObject.exportObject(service, 0);

            /** 
             * Criando registro aqui para não precisar rodar o rmiregistry.
             * Em um "servidor real" recomendado dar o get no lugar do create
             * e rodar o rmiregistry separadamente na pasta dos compilados:
             * AKA "build" ou "build/classes/" ou "bin/" dependendo do ambiente.
             */
            Registry registry = 
                    LocateRegistry.createRegistry(Constants.SERVICE_PORT);
            registry.bind(Constants.SERVICE_NAME, stub);
            System.out.printf("Servico disponivel: %s%n", Constants.SERVICE_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
