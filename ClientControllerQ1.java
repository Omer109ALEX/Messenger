
import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ClientControllerQ1 {

	private ClientMonitor monitor;

	@FXML
	private TextArea messages;

	@FXML
	private TextArea toSend;

	@FXML
	private TextArea userList;

	@FXML
	private Label myName;

	private String name;
	private Connection c;

	public void initialize() {
		this.monitor = new ClientMonitor();
		String firstName = JOptionPane.showInputDialog(null, "Enter your user name");
		myName.setText(firstName);
		name = firstName;

	}

	@FXML
	void disconnectPressed(ActionEvent event) {
		if (monitor.connected()) {
			monitor.disconnect();
			DataQ1 d = makeData("disconnect");
			new ClientThreadQ1(this, "127.0.0.1", d, monitor).run();
			this.messages.clear();
			this.userList.clear();
		} else {
			this.setMessages("you are already disconnected");
			this.userList.clear();

		}

	}

	@FXML
	void joinPressed(ActionEvent event) {
		if (!(monitor.connected())) {
			monitor.connect();
			DataQ1 d = makeData("join");
			new ClientThreadQ1(this, "127.0.0.1", d, monitor).run();
			c = new Connection(this, monitor);
			c.start();
		}
	}

	@FXML
	void sendPressed(ActionEvent event) {
		if (monitor.connected()) {
			DataQ1 d = makeData("send");
			new ClientThreadQ1(this, "127.0.0.1", d, monitor).start();
		} else {
			this.setMessages("you are not connected");
			this.userList.clear();
		}

	}

	public DataQ1 makeData(String action) {
		DataQ1 d = new DataQ1(name, action);
		d.setToSend(getToSend());
		d.setMessages(getMessages());
		d.setUserList(getUserList());
		return d;
	}

	public String getUserList() {
		return userList.getText();
	}

	public String getMessages() {
		return messages.getText();
	}

	public String getName() {
		return this.name;
	}

	public void setMessages(String newM) {
		messages.setText(newM);

	}

	public void setUserList(String newL) {
		userList.setText(newL);

	}

	public String getToSend() {
		return toSend.getText();
	}

}
