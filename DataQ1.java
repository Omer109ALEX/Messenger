import java.io.Serializable;

public class DataQ1 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -247036372860674032L;
	private String name, action, messages, userList, toSend;


	public DataQ1(String n, String a) {
		this.name = n;
		this.action = a;
		this.messages = "";
		this.userList = "";
		this.toSend = "";
	}
	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

	public String getUserList() {
		return userList;
	}

	public void setUserList(String userList) {
		this.userList = userList;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	public String getAction() {
		return this.action;
	}
	
	public String getName() {
		return this.name;
	}
	public String getToSend() {
		return toSend;
	}
	public void setToSend(String toSend) {
		this.toSend = toSend;
	}
	
	

	
	
}
