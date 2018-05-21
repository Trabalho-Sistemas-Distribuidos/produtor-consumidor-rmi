package client;

import java.lang.IllegalStateException;

import util.Buffer;

pulic class Consumer{
  public int get() throws IllegalStateException{
    return Buffer.get();
  }
}
