import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientThreadQ1 extends Thread {
	private ClientControllerQ1 cont;
	private String ip;
	private DataQ1 data;
	private Socket s;
	private ClientMonitor m;

	
	public ClientThreadQ1(ClientControllerQ1 cont, String ip, DataQ1 d, ClientMonitor m) {
		this.cont = cont;
		this.data = d;
		this.ip = ip;
		this.m = m;
	}

	public void run() {
		super.run();
		try {
			handleReadAndWrite();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	public void handleReadAndWrite() throws Exception {
		s = new Socket(ip, 7777);
		OutputStream outputStream = s.getOutputStream();
		ObjectOutputStream objOutputStream = new ObjectOutputStream(outputStream);
		InputStream inputStream = s.getInputStream();
		ObjectInputStream objInputStream = new ObjectInputStream(inputStream);

		// Write the Data to server
		objOutputStream.writeObject(data);

		data = (DataQ1) objInputStream.readObject();
		
		if (m.connected()) {
			cont.setMessages(data.getMessages());
			cont.setUserList(data.getUserList());
		}
		

		// close all connections
		outputStream.close();
		objOutputStream.close();
		inputStream.close();
		objInputStream.close();
		s.close();
		

	}

}
