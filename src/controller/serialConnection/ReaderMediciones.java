package controller.serialConnection;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;

public class ReaderMediciones implements SerialPortEventListener{

	PrintStream printStream;
	InputStream inputStream;
	
	public ReaderMediciones(PrintStream printStream, InputStream inputStream) {
		this.printStream = printStream;
		this.inputStream = inputStream;
	}

	@Override
	public void serialEvent(SerialPortEvent event) {
        switch(event.getEventType()) {

        case SerialPortEvent.DATA_AVAILABLE:
    	    byte[] readBuffer = new byte[50];
    	    int numBytes;
    	    InputStream is;
    	    StringBuilder textBuilder;
    	    String recibido = new String();
    	    try {
        		while (inputStream.available() > 0) {
        		    numBytes = inputStream.read(readBuffer);
        		}
        		is = new ByteArrayInputStream(readBuffer);
        	    textBuilder = new StringBuilder();
        	    try (Reader reader = new BufferedReader(new InputStreamReader
        	      (is, Charset.forName(StandardCharsets.UTF_8.name())))) {
        	        int c = 0;
        	        while ((c = reader.read()) != -1) {
        	            textBuilder.append((char) c);
        	        }
        	    }
        	    recibido = textBuilder.substring(0,3);
        	    System.out.println(textBuilder);
    	    } catch (IOException e) {
    	    	
    	    }
        }
	}
}
