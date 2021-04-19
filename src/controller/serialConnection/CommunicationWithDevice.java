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

import controller.Controller;

public class CommunicationWithDevice implements SerialPortEventListener{

	private SerialPort port;
	private PrintStream outputStream;
	private InputStream inputStream;
	private CommPortIdentifier portId;
	private String lastSentFlag;
	private boolean readFromDeviceStarted = false;
	private Controller controller;
	public static final int BAUD_RATE = 115200;
	public static final String PORT = "puerto";
	public static final String MESSAGE_BUSY_PORT = "Se encuentra ocupado el puerto ";
	public static final String FLAG_ACK = "ACK";
	public static final String FLAG_INF = "INF";
	public static final String FLAG_READ = "READ";
	
	public CommunicationWithDevice(CommPortIdentifier portId, SerialPort port, Controller controller) {
		this.portId = portId;
		this.port = port;
		this.controller = controller;
		try {
			this.outputStream = new PrintStream(port.getOutputStream(), true);
			this.inputStream = port.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isReadFromDeviceStarted() {
		return readFromDeviceStarted;
	}

	public void setReadFromDeviceStarted(boolean readFromDeviceStarted) {
		this.readFromDeviceStarted = readFromDeviceStarted;
	}

	public void receiveFromDevice(String packet) {
		
	}
	
	public void sendToDevice(String packet) {
		lastSentFlag = packet;
		port.notifyOnDataAvailable(true);
		port.notifyOnOutputEmpty(true);
		if(FLAG_READ.equals(packet)) {
			setReadFromDeviceStarted(true);
		}
		outputStream.print(packet+"\n");			
	}
	
	@Override
	public void serialEvent(SerialPortEvent event) {
        switch(event.getEventType()) {
	        case SerialPortEvent.DATA_AVAILABLE:
	    	    String recibido, firstReceivedPacket, secondReceivedPacket;
	    	    try {
	        	    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader
	        	      (inputStream, Charset.forName(StandardCharsets.UTF_8.name())));
	        	    	firstReceivedPacket = bufferedReader.readLine().toString();
	        	    
	        	    recibido = firstReceivedPacket.substring(0,3);
	        	    System.out.println(firstReceivedPacket);
	        	    switch (recibido) {
	    	    	case FLAG_INF:
	    	    		System.out.println("Se recibió una trama INF");
	    	    		controller.getDb().addLinesOfMeasurements(firstReceivedPacket);
	    	    	break;
	    	    	case FLAG_ACK:
	    	    		if (FLAG_READ.equals(lastSentFlag)) {
	    	    			secondReceivedPacket = bufferedReader.readLine().toString();
	    	    			System.out.println(secondReceivedPacket);    	    			
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
