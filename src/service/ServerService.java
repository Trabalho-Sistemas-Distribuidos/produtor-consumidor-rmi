package service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementa os métodos de controle do Servidor
 * 
 */
public class ServerService implements IService{
    private ArrayList<String> buffer;
    private ArrayList<IListener> clients;
    private File[] printerFiles;
    private int bufferSize;
    
    public ServerService() {
        bufferSize = 3; // Enunciado do trabalho buffer de tamanho 3
        printerFiles = new File[2]; // Enunciado do trabalho 2 impressoras
        clients = new ArrayList<>();
        buffer = new ArrayList<>();
        for (int i = 0; i < printerFiles.length; i++) {
            String path = "printers" + File.separator + "printer" + i + ".txt";
            File f = new File(path);
        }
    }
    
    @Override
    public void addClient(IListener cliente) throws RemoteException {
        if(clients.size() < 5){
            clients.add(cliente);
        }else{
            throw new RemoteException("Servidor está cheio");
        }
    }

    @Override
    public void printRequest(String print) throws RemoteException {
        for(File printerFile : printerFiles){
            try {
                Formatter printer = new Formatter(printerFile);
            } catch (FileNotFoundException ex) {
                printerFile.getParentFile().mkdirs(); 
                try {
                    printerFile.createNewFile();
                } catch (IOException ex1) {
                    Logger.getLogger(ServerService.class.getName())
                        .log(Level.SEVERE, null, ex1);
                }
            } finally {
                // TODO escolher impressora e imprimir de acordo com
                // o problema produtor-consumidor
            }
        }
    }
    
}
