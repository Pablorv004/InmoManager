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
			String region, String backAccountNum, LocalDateTime creationTime) {
		super(iD, dNI, fullName, userName, password, email, phoneNum, backAccountNum);
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

}
