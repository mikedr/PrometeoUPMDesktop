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

import javax.comm.CommPortIdentifier;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;

public class PortTester implements Runnable, SerialPortEventListener{
	private InputStream is = null;
	private PrintStream os = null;
	private SerialPort port;
	private CommPortIdentifier portId;
	ManagerSerialComm managerSerialComm;
	private static final String PACKET_ARE = "ARE";
	private static final String PACKET_IAM = "IAM";
	private static final String PORT = "puerto";
	private static final String MESSAGE_BUSY_PORT = "Se encuentra ocupado el puerto ";
	private static final String MESSAGE_WAITING_ANSWER = ". Esperando respuesta. Intento: ";
	private static final int BAUD_RATE = 115200;
	
	public PortTester(CommPortIdentifier portId, ManagerSerialComm managerSerialComm) {
		this.portId = portId;
		this.managerSerialComm = managerSerialComm;
	}

	@Override
	public void run() {
		String portName = portId.getName();
		try {
			port = (SerialPort) portId.open(PORT, 10000);
			port.setSerialPortParams(BAUD_RATE,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
			is = port.getInputStream();
			os = new PrintStream(port.getOutputStream(), true);
			port.addEventListener(this);
			port.notifyOnDataAvailable(true);
			port.notifyOnOutputEmpty(true);
			os.print(PACKET_ARE+"\n");
		} catch (PortInUseException e) {
			System.out.println(MESSAGE_BUSY_PORT+portName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	@Override
	public void serialEvent(SerialPortEvent event) {
        switch(event.getEventType()) {
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
            break;
            case SerialPortEvent.DATA_AVAILABLE:
        	    byte[] readBuffer = new byte[50];
        	    int numBytes;
        	    InputStream inputStream;
        	    StringBuilder textBuilder;
        	    String recibido;
        	    try {
	        		while (is.available() > 0) {
	        		    numBytes = is.read(readBuffer);
	        		}
	        		inputStream = new ByteArrayInputStream(readBuffer);
	        	    textBuilder = new StringBuilder();
	        	    try (Reader reader = new BufferedReader(new InputStreamReader
	        	      (inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
	        	        int c = 0;
	        	        while ((c = reader.read()) != -1) {
	        	            textBuilder.append((char) c);
	        	        }
	        	    }
	        	    recibido = textBuilder.substring(0,3);
	        	    if(PACKET_IAM.equals(recibido)) {
	        	    	managerSerialComm.setDeviceConnected(portId);
	        	    }
        	    } catch (IOException e) {
        	    	
        	    }
            break;
            case SerialPortEvent.BI:
            	int stop;
            	stop=0;
                break;
            case SerialPortEvent.CD:
            	int stop3;
            	stop=0;
                break;
            case SerialPortEvent.CTS:
            	int stop4;
            	stop=0;
                break;
            case SerialPortEvent.DSR:
            	int stop5;
            	stop=0;
                break;
            case SerialPortEvent.FE:
            	int stop6;
            	stop=0;
                break;
            case SerialPortEvent.OE:
            	int stop7;
            	stop=0;
                break;
            case SerialPortEvent.PE:
            	int stop8;
            	stop=0;
                break;
            case SerialPortEvent.RI:
            	int stop9;
            	stop=0;
                break;
        }
	}
}