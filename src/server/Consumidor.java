package client;

import java.lang.IllegalStateException;

import util.Buffer;

pulic class Produtor{
  public int get(String str) throws IllegalStateException{
    return Buffer.get(str);
  }
}
