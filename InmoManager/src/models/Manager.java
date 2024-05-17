package models;

import java.time.LocalDateTime;

public class Manager extends User {
	private double comission;
	private double salary;
	private LocalDateTime hireDate;
	private int managerId;

	public Manager() {
		super();
	}

	public Manager(int iD, String dNI, String fullName, String userName, String password, String email, int phoneNum,
			double comission, String bankAccountNum, LocalDateTime hireDate, int managerId, double salary) {
		super(iD, dNI, fullName, userName, password, email, phoneNum, bankAccountNum);
		this.comission = comission;
		this.salary = salary;
		this.hireDate = hireDate;
		this.managerId = managerId;
	}

	public double getComission() {
		return comission;
	}

	public void setComission(double comission) {
		this.comission = comission;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public LocalDateTime getHireDate() {
		return hireDate;
	}

	public void setHireDate(LocalDateTime hireDate) {
		this.hireDate = hireDate;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	
	public Object[] toArray() {
		Object [] array = {this.getID(),this.getDNI(),this.getFullName(),this.getEmail(),this.getPhoneNum(),this.getHireDate(),this.getManagerId(),this.getSalary()};
		return array;
	}

	@Override
	public String toString() {
		return super.toString() + ", comission=" + comission + ", salary=" + salary + ", hireDate=" + hireDate
				+ ", managerId=" + managerId;
	}

}
