import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ServerQ1 {
	
	private String ip;
	private ServerMonitor sm;
	
	public ServerQ1() {
		String ip = JOptionPane.showInputDialog(null, "Enter your host ip");
		this.ip = ip;
		this.sm = new ServerMonitor();
		
		ServerSocket sc = null;
		Socket s = null;
		try {
			sc = new ServerSocket(7777);
			while (true) {
				s = sc.accept();
				new ServerThreadQ1(s, sm).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				sc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		new ServerQ1();
	}
}
