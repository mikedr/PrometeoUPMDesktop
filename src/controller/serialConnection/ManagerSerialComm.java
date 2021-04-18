package controller.serialConnection;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.TooManyListenersException;

import javax.comm.CommPortIdentifier;
import javax.comm.SerialPort;

import controller.Controller;

public class ManagerSerialComm {
	
	private Controller controller;
	private CommPortIdentifier portId;
	private Enumeration ports;
	private static final String MESSAGE_START_TEST_PORT = "Abrimos hilo para testear puerto ";
	private boolean isDeviceConnected;
	SerialPort port;
	CommunicationWithDevice communicator;
	private static final String PORT = "puerto";
	private static final int BAUD_RATE = 115200;
	private InputStream is;
	private PrintStream os;
	
	public ManagerSerialComm(Controller controller) {
		this.controller = controller;
	}

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
		port.removeEventListener();
		isDeviceConnected = true;
		this.port = port;
		this.communicator = new CommunicationWithDevice(portId, port);
		try {
			port.addEventListener(communicator);
		} catch (TooManyListenersException e) {
			e.printStackTrace();
		}
	}

	public void sendPacketToDevice(String packet) {
		communicator.sendToDevice(packet);
	}
	
}
