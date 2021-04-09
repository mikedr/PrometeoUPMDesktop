package controller.serialConnection;

import java.util.Enumeration;

import javax.comm.CommPortIdentifier;

public class ManagerSerialComm {
	
	private CommPortIdentifier portId;
	private Enumeration ports;
	private static final String MESSAGE_START_TEST_PORT = "Abrimos hilo para testear puerto ";
	
	public void setConection() {
	    ports  = CommPortIdentifier.getPortIdentifiers();
        while (ports.hasMoreElements()) {
            portId = (CommPortIdentifier) ports.nextElement();
            System.out.println(MESSAGE_START_TEST_PORT+portId.getName());
            Thread newThread = new Thread(new PortTester(portId));
            newThread.start();
        }
	}
}
