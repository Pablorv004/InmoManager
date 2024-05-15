package models;

public class Purchasable_Property extends Property {
	private int totalValue;

	public Purchasable_Property() {
		super();
	}

	public Purchasable_Property(int id, String address, String city, String type, int age, int rooms, int floors,
			int bathrooms, int propertySize, int terrainSize, int garageSize, boolean hasGarden, boolean hasBasement,
			boolean hasGarage, boolean hasPool, boolean hasLift, boolean hasTerrace, boolean hasAC, boolean available,
			String status, int totalValue) {
		super(id, address, city, type, age, rooms, floors, bathrooms, propertySize, terrainSize, garageSize, hasGarden,
				hasBasement, hasGarage, hasPool, hasLift, hasTerrace, hasAC, available, status);
		this.totalValue = totalValue;
	}

	public int getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(int totalValue) {
		this.totalValue = totalValue;
	}

	@Override
	public String toString() {
		return super.toString() + ", totalValue=" + totalValue;
	}

}
