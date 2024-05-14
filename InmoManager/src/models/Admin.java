package models;

public class Admin extends User {
	private double salary;

	public Admin() {
		super();
	}

	public Admin(int iD, String dNI, String fullName, String userName, String password, String email, int phoneNum,
			double salary, String backAccountNum) {
		super(iD, dNI, fullName, userName, password, email, phoneNum, backAccountNum);
		this.salary = salary;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return super.toString() + ", salary=" + salary;
	}

}
