package client;

import java.lang.IllegalStateException;

import util.Buffer;

pulic class Consumer{
  public int get() throws IllegalStateException{
    while(True){
      try{
        String a = Buffer.get();
        //TODO : write in a corresponding file
      }
      catch(Exception e){
        Thread.sleep(400); //wait 400 milisseconds;
        
      }
    }
    
  }
}
