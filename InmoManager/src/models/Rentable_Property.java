package models;

public class Rentable_Property extends Property {
	private int rentValue;

	public Rentable_Property() {
		super();
	}

	public Rentable_Property(int id, String address, String city, String type, int age, int rooms, int floors,
			int bathrooms, int propertySize, int terrainSize, int garageSize, boolean hasGarden, boolean hasBasement,
			boolean hasGarage, boolean hasPool, boolean hasLift, boolean hasTerrace, boolean hasAC, boolean available,
			String status, int rentValue) {
		super(id, address, city, type, age, rooms, floors, bathrooms, propertySize, terrainSize, garageSize, hasGarden,
				hasBasement, hasGarage, hasPool, hasLift, hasTerrace, hasAC, available, status);
		this.rentValue = rentValue;
	}

	public int getRentValue() {
		return rentValue;
	}

	public void setRentValue(int rentValue) {
		this.rentValue = rentValue;
	}

	@Override
	public String toString() {
		return super.toString() + ", rentValue=" + rentValue;
	}

}
