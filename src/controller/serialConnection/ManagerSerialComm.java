package controller.serialConnection;

import java.util.Enumeration;

import javax.comm.CommPortIdentifier;

public class ManagerSerialComm {
	
	private CommPortIdentifier portId;
	private Enumeration ports;
	private static final String MESSAGE_START_TEST_PORT = "Abrimos hilo para testear puerto ";
	private boolean isDeviceConnected;
	
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
	
	void setDeviceConnected(CommPortIdentifier portId) {
		isDeviceConnected = true;
		this.portId = portId;
	}
}
