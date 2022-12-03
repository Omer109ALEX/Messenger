import java.util.ArrayList;

public class ServerMonitor {

	private String messages, userList;
	private ArrayList<String> users;

	

	public ServerMonitor() {
		this.userList = "";
		this.messages = "";
		this.users = new ArrayList<String>();
	}
	public synchronized String arrayToString() {
		String ans = new String();
		for (int i=0; i<this.users.size(); i++) {
			ans += "\n" + this.users.get(i);
		}
		return ans;
	}
	public synchronized ArrayList<String> getUsers() {
		return users;
	}
	
	public synchronized void addUser(String s) {
		this.users.add(s);
	}
	
	public synchronized void removeUser(String s) {
			this.users.remove(s);
	}

	public synchronized String getMessages() {
		return messages;
	}

	public synchronized void setMessages(String messages) {
		this.messages = messages;
		notifyAll();

	}

	public synchronized String getUserList() {
		return userList;
	}

	public synchronized void setUserList(String userList) {
		this.userList = userList;
		notifyAll();

	}

	public synchronized void update(DataQ1 d, String dataUserList, String dataMessages) {
		while ((dataUserList.equals(userList)) && (dataMessages.equals(messages))) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		d.setMessages(messages);
		d.setUserList(userList);

	}

	

}
