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
	private String lastSentFlag;
	private static final int BAUD_RATE = 115200;
	private static final String PORT = "puerto";
	private static final String MESSAGE_BUSY_PORT = "Se encuentra ocupado el puerto ";
	private static final String FLAG_ACK = "ACK";
	private static final String FLAG_INF = "INF";
	private static final String FLAG_READ = "READ";
	
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
		lastSentFlag = packet;
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
    	    String recibido = new String(), firstReceivedPacket, secondReceivedPacket;
    	    try {
        		while (inputStream.available() > 0) {
        		    numBytes = inputStream.read(readBuffer);
        		}
        		is = new ByteArrayInputStream(readBuffer);
        	    try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader
        	      (is, Charset.forName(StandardCharsets.UTF_8.name())))) {
        	    	firstReceivedPacket = bufferedReader.readLine().toString();
        	    }
        	    recibido = firstReceivedPacket.substring(0,3);
        	    System.out.println(firstReceivedPacket);
        	    switch (recibido) {
    	    	case FLAG_INF:
    	    		System.out.println("Se recibió una trama INF");
    	    	break;
    	    	case FLAG_ACK:
    	    		if (FLAG_READ.equals(lastSentFlag)) {
//    	    			newPacket = reader.readLine().toString();
    	    			System.out.println("Se reconoció un paquete READ");    	    			
    	    		} else {
    	    			System.out.println("Se reconoció un paquete");
    	    		}
    	    	break;
    	    }

    	    } catch (IOException e) {
    	    	
    	    }
        }
	}
}
