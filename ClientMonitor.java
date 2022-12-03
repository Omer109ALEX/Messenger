
public class ClientMonitor {

	private boolean connected;

	public ClientMonitor() {
		this.connected = false;
	}

	public synchronized boolean connected() {
		return this.connected;

	}

	public synchronized void connect() {
		this.connected = true;
	}

	public synchronized void disconnect() {
		this.connected = false;

	}

}
