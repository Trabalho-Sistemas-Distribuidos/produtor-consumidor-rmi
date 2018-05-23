package util;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer{
    static private ArrayList<String> buffer;
    
    /** 
     * Inicializa o buffer do produtor/consumidor com 
     * {@link #Constants.BUFFER_SIZE }
     */
    public Buffer(){
        buffer = new ArrayList<>(Constants.BUFFER_SIZE);
    }

    /**
     * Coloca string no Buffer(Produz) se o buffer não estiver cheio,
     * caso contrário fica em espera
     * 
     * @param str é a string a ser inserida
     * 
     */
    public synchronized void put(String str){
        try {
            if (buffer.size() == Constants.BUFFER_SIZE) {
                this.wait();
            }

            buffer.add(str);
            this.notifyAll();

            System.out.println("String: \"" + str + "\" produzida");
        } catch (InterruptedException ex) {
            Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Tira string do Buffer(Consome) se o buffer não estiver vazio,
     * caso contrário fica em espera
     * 
     * @return string consumida
     * 
     */
    public synchronized String get() {
        String aux = null;
        try {
            if (buffer.isEmpty()) {
                this.wait();
            }

            aux = buffer.remove(0);
            this.notifyAll();

        } catch (InterruptedException ex) {
            Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aux;
    }
}
