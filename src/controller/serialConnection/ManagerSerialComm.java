package controller.serialConnection;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.TooManyListenersException;

import javax.comm.CommPortIdentifier;
import javax.comm.SerialPort;

public class ManagerSerialComm {
	
	private CommPortIdentifier portId;
	private Enumeration ports;
	private static final String MESSAGE_START_TEST_PORT = "Abrimos hilo para testear puerto ";
	private boolean isDeviceConnected;
	SerialPort port;
	ReaderMediciones readerMediciones;
	
	public boolean setConection() {
		isDeviceConnected = false;
	    ports  = CommPortIdentifier.getPortIdentifiers();
        while (ports.hasMoreElements()) {
            portId = (CommPortIdentifier) ports.nextElement();
            System.out.println(MESSAGE_START_TEST_PORT+portId.getName());
            Thread newThread = new Thread(new PortTester(portId, this));
            newThread.start();
        }
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        return isDeviceConnected;
	}
	
	void setDeviceConnected(SerialPort port) {
		isDeviceConnected = true;
		this.port = port;
	}

	public void readMediciones() {
		try {
			port.removeEventListener();
			this.readerMediciones = 
					new ReaderMediciones(new PrintStream(port.getOutputStream(), true), port.getInputStream());
			try {
				port.addEventListener(readerMediciones);
			} catch (TooManyListenersException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
