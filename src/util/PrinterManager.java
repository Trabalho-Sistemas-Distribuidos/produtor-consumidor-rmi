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
    private Buffer buffer;
    private TimeWatch timeWatch;
    
    /** Inicializa as impressoras e o contador de disponíveis
     * @param buffer 
     */
    public PrinterManager(Buffer buffer){
        this.printers = new Formatter[Constants.PRINTERS_SIZE];
        this.buffer = buffer;
        this.timeWatch = TimeWatch.start();
        
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
        //Começar as Threads de impressão
        PrintThread p0 = new PrintThread(printers[0]);
        PrintThread p1 = new PrintThread(printers[1]);
        p0.start();
        p1.start();
    }

    private class PrintThread extends Thread{
        private final Formatter printer;

        PrintThread(Formatter printer) {
            super();
            this.printer = printer;
        }

        @Override
        public void run() {
            while(true){
                if(buffer.getSize() > 0){
                    String s = buffer.get();
                    printer.format(s + "\r\n");
                    printer.flush();
                    long printTime = timeWatch.time();
                    System.out.println("A string: " + s + " foi impressa.");
                    System.out.println("Tempo de execução: "+printTime+"ms.");
                }
            }
        }
    }
}
