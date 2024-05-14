package models;

public class User {
	private int id;
	private String DNI;
	private String fullName;
	private String userName;
	private String password;
	private String email;
	private int phoneNum;
	private String backAccountNum;

	public User() {
		super();
	}

	public User(int id, String DNI, String fullName, String userName, String password, String email, int phoneNum,
			String backAccountNum) {
		super();
		this.id = id;
		this.DNI = DNI;
		this.fullName = fullName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.phoneNum = phoneNum;
		this.backAccountNum = backAccountNum;
	}

	public int getID() {
		return id;
	}

	public void setID(int iD) {
		id = iD;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(int phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getBackAccountNum() {
		return backAccountNum;
	}

	public void setBackAccountNum(String backAccountNum) {
		this.backAccountNum = backAccountNum;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " -> ID=" + id + ", DNI=" + DNI + ", fullName=" + fullName + ", userName=" + userName + ", password="
				+ password + ", email=" + email + ", phoneNum=" + phoneNum + ", backAccountNum=" + backAccountNum;
	}

}
