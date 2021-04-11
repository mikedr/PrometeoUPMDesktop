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
import java.util.TooManyListenersException;

import javax.comm.CommPortIdentifier;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;

public class CommunicationWithDevice implements SerialPortEventListener{

	private SerialPort port;
	private PrintStream outputStream;
	private InputStream inputStream;
	private CommPortIdentifier portId;
	private static final int BAUD_RATE = 115200;
	private static final String PORT = "puerto";
	private static final String MESSAGE_BUSY_PORT = "Se encuentra ocupado el puerto ";
	
	public CommunicationWithDevice(CommPortIdentifier portId, SerialPort port) {
		this.portId = portId;
		this.port = port;
		try {
			this.outputStream = new PrintStream(port.getOutputStream(), true);
			this.inputStream = port.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void receiveFromDevice(String packet) {
		
	}
	
	public void sendToDevice(String packet) {
		port.notifyOnDataAvailable(true);
		port.notifyOnOutputEmpty(true);
		outputStream.print(packet+"\n");			
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
