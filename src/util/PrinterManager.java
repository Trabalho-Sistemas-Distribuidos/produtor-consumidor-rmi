package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.ServerService;

/**
 * Classe que agirá controlando a rotina de consumo
 * 
 */
public class PrinterManager {
    private Formatter[] printers;
    private int availablePrinterIndex;
    
    /** Inicializa as impressoras e o contador de disponíveis */
    public PrinterManager(){
        printers = new Formatter[Constants.PRINTERS_SIZE];
        availablePrinterIndex = Constants.PRINTERS_SIZE - 1;
        
        for (int i = 0; i < printers.length; i++) {
            String path = "printers" + File.separator + "printer" + i + ".txt";
            File printerFile = new File(path);
            try {
                printers[i] = new Formatter(printerFile);
            } catch (FileNotFoundException ex) {
                printerFile.getParentFile().mkdirs(); 
                try {
                    printerFile.createNewFile();
                } catch (IOException ex1) {
                    Logger.getLogger(ServerService.class.getName())
                        .log(Level.SEVERE, null, ex1);
                }
            }
        }
    }
    
    /** Imprime se há impressoras disponíveis
     * @param s é a string a ser imprimida
     */
    public synchronized void print(String s) {
        if(availablePrinterIndex == -1){
            try {
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(PrinterManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            int index = availablePrinterIndex--;
            printers[index].format(s + "\n");
            printers[index].flush();
            availablePrinterIndex++;
            this.notifyAll();
        }
    }
    
}
