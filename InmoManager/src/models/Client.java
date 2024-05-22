package models;

import java.time.LocalDateTime;

public class Client extends User {
	private String region;
	private LocalDateTime creationTime;

	public Client() {
		super();
		this.creationTime = LocalDateTime.now();
	}

	public Client(int iD, String dNI, String fullName, String userName, String password, String email, int phoneNum,
			String region, String bankAccountNum, LocalDateTime creationTime) {
		super(iD, dNI, fullName, userName, password, email, phoneNum, bankAccountNum);
		this.region = region;
		this.creationTime = creationTime;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public LocalDateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}

	@Override
	public String toString() {
		return super.toString() + ", Region=" + region + ", Creation date=" + creationTime;
	}
	
	public Object[] toArray() {
		Object [] array = {this.getID(),this.getDNI(),this.getFullName(),this.getEmail(),this.getPhoneNum(),this.region};
		return array;
	}

}
