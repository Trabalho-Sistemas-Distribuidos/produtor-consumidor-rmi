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
        availablePrinterIndex = 0;
        
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
    
    /**
     * Começa a thread de impressão
     * 
     * @param s string a ser imprimida pela thread
     */
    public void print(String s) {
        PrintThread p = new PrintThread(s);
        Thread t = new Thread(p);
        t.start();
    }
    
    /** Classe que representa uma thread de impressão */
    public class PrintThread implements Runnable{
        private String printableStr;
        
        /** 
         * Construtor da classe 
         * @param printableStr é a string a ser imprimida
         */
        public PrintThread(String printableStr){
            this.printableStr = printableStr;
        }
        
        @Override
        public void run() {
            try {
                attemptPrint(printableStr);
            } catch (InterruptedException ex) {
                Logger.getLogger(PrinterManager.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }

        /** 
         * Imprime se há impressoras disponíveis
         * @param s é a string a ser imprimida
         */
        private synchronized void attemptPrint(String s) throws InterruptedException {
            if(availablePrinterIndex < Constants.PRINTERS_SIZE){
                int index = availablePrinterIndex++;
                printers[index].format(s + "\r\n");
                printers[index].flush();
                Thread.sleep(3000); //adicionado o delay para ver o paralelismo
                availablePrinterIndex--;
                this.notifyAll();
            }else{
                this.wait();
            }
        }
    }
}
