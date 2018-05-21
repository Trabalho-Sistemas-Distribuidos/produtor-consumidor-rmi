package util;


import java.util.ArrayList;

import util.Constantes;

import java.lang.IllegalStateException;


public class Buffer {
	static private ArrayList<String> buffer;
	
	public static synchronized int put(String str){
		if(buffer.size() == Constantes.BUFFER_SIZE){
			return 1; //error
		}
		buffer.add(str);
		return 0; //   10/10
	}
	
	public static synchronized String get() throws IllegalStateException {
		if(buffer.size()==0){
			throw new IllegalStateException();
		}
		String aux = buffer.get(0);
		buffer.remove(0);
		return aux;
	}
}
