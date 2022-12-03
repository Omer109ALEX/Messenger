
public class Connection extends Thread {
	
	private ClientControllerQ1 cont;
	private ClientMonitor m;

	public Connection(ClientControllerQ1 cont, ClientMonitor m) {
		this.cont = cont;
		this.m = m;
	}

	public void run() {
		super.run();
		try {
			while (m.connected()) {
				DataQ1 d = cont.makeData("update");
				new ClientThreadQ1(cont, "127.0.0.2", d, m).run();
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
