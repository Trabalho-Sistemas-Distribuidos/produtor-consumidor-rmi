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
     * Adiciona o arquivo no buffer se não estiver cheio do produtor/consumidor,
     * caso contrário o cliente fica em espera
     * 
     * @param print é a String a ser impressa no arquivo 
     *      por uma das impressoras do servidor
     * 
     * @throws RemoteException
     */
    void printRequest(String print) throws RemoteException;
}
