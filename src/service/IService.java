package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface para implementação no servidor com os métodos que serão visíveis
 * aos clientes
 * 
 */
public interface IService extends Remote {
    /**
     * Adiciona cliente no servidor se tiver slot de usuário disponível
     * 
     * @param client representa o cliente a ser adicionado
     * 
     * @throws RemoteException caso não tenha mais slots de usuário disponíveis
     */
    void addClient(IListener client) throws RemoteException;
    /**
     * Adiciona o arquivo no buffer do cliente e servidor
     * 
     * @param print é a String a ser impressa no arquivo 
     *      printer1 ou printer2 pelo servidor
     * 
     * @throws RemoteException
     */
    void printRequest(String print) throws RemoteException;
}
