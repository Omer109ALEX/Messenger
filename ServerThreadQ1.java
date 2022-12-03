import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerThreadQ1 extends Thread {
	private Socket s = null;
	private ServerMonitor monitor;
	private String action, name, messages, toSend, dataUserList, dataMessages;

	public ServerThreadQ1(Socket socket, ServerMonitor sm) {
		this.s = socket;
		this.monitor = sm;
	}

	@Override
	public void run() {
		super.run();
		try {
			handleReadAndWrite();
		} catch (Exception e) {
		}
	}

	public void handleReadAndWrite() throws Exception {
		OutputStream outputStream = s.getOutputStream();
		ObjectOutputStream objOutputStream = new ObjectOutputStream(outputStream);
		InputStream inputStream = s.getInputStream();
		ObjectInputStream objInputStream = new ObjectInputStream(inputStream);

		// get Data from client
		DataQ1 d;
		d = (DataQ1) objInputStream.readObject();

		// my:
		action = d.getAction();
		name = d.getName();
		dataMessages = d.getMessages();
		dataUserList = d.getUserList();
				
		messages = monitor.getMessages();
		toSend = d.getToSend();

		if (action.equals("join")) {
			if (!(monitor.getUsers().contains(name))) {
				monitor.addUser(name);
				monitor.setMessages(messages + "\n" + name + " have joined");
				monitor.setUserList(monitor.arrayToString());		
			}
		}
		if (action.equals("disconnect")) {
			if (monitor.getUsers().contains(name)) {
				monitor.removeUser(name);
				monitor.setMessages(messages + "\n" + name + " have disconnected");
				monitor.setUserList(monitor.arrayToString());		
			}
			
		}

		if (action.equals("send")) {
			monitor.setMessages(messages + "\n" + name + " : " + toSend);

		}

		monitor.update(d, dataUserList, dataMessages);

		objOutputStream.writeObject(d);

		// close all connections
		objInputStream.close();
		inputStream.close();
		objOutputStream.close();
		outputStream.close();
		s.close();
	}

}
