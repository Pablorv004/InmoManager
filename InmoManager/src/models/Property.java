package models;

public class Property {
	private String address;
	private String city;
	private String type;
	private int age;
	private int rooms;
	private int floors;
	private int bathrooms;
	private int propertySize;
	private int terrainSize;
	private int garageSize;
	private boolean hasGarden;
	private boolean hasBasement;
	private boolean hasGarage;
	private boolean hasPool;
	private boolean hasLift;
	private boolean hasTerrace;
	private boolean hasAC;
	private boolean available;
	private String status;

	public Property(){
		
	}

	public Property(String address, String city, String type, int age, int rooms, int floors, int bathrooms,
			int propertySize, int terrainSize, int garageSize, boolean hasGarden, boolean hasBasement,
			boolean hasGarage, boolean hasPool, boolean hasLift, boolean hasTerrace, boolean hasAC, boolean available,
			String status) {
		super();
		this.address = address;
		this.city = city;
		this.type = type;
		this.age = age;
		this.rooms = rooms;
		this.floors = floors;
		this.bathrooms = bathrooms;
		this.propertySize = propertySize;
		this.terrainSize = terrainSize;
		this.garageSize = garageSize;
		this.hasGarden = hasGarden;
		this.hasBasement = hasBasement;
		this.hasGarage = hasGarage;
		this.hasPool = hasPool;
		this.hasLift = hasLift;
		this.hasTerrace = hasTerrace;
		this.hasAC = hasAC;
		this.available = available;
		this.status = status;
	}
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getRooms() {
		return rooms;
	}

	public void setRooms(int rooms) {
		this.rooms = rooms;
	}

	public int getFloors() {
		return floors;
	}

	public void setFloors(int floors) {
		this.floors = floors;
	}

	public int getBathrooms() {
		return bathrooms;
	}

	public void setBathrooms(int bathrooms) {
		this.bathrooms = bathrooms;
	}

	public int getPropertySize() {
		return propertySize;
	}

	public void setPropertySize(int propertySize) {
		this.propertySize = propertySize;
	}

	public int getTerrainSize() {
		return terrainSize;
	}

	public void setTerrainSize(int terrainSize) {
		this.terrainSize = terrainSize;
	}

	public int getGarageSize() {
		return garageSize;
	}

	public void setGarageSize(int garageSize) {
		this.garageSize = garageSize;
	}

	public boolean isHasGarden() {
		return hasGarden;
	}

	public void setHasGarden(boolean hasGarden) {
		this.hasGarden = hasGarden;
	}

	public boolean isHasBasement() {
		return hasBasement;
	}

	public void setHasBasement(boolean hasBasement) {
		this.hasBasement = hasBasement;
	}

	public boolean isHasGarage() {
		return hasGarage;
	}

	public void setHasGarage(boolean hasGarage) {
		this.hasGarage = hasGarage;
	}

	public boolean isHasPool() {
		return hasPool;
	}

	public void setHasPool(boolean hasPool) {
		this.hasPool = hasPool;
	}

	public boolean isHasLift() {
		return hasLift;
	}

	public void setHasLift(boolean hasLift) {
		this.hasLift = hasLift;
	}

	public boolean isHasTerrace() {
		return hasTerrace;
	}

	public void setHasTerrace(boolean hasTerrace) {
		this.hasTerrace = hasTerrace;
	}

	public boolean isHasAC() {
		return hasAC;
	}

	public void setHasAC(boolean hasAC) {
		this.hasAC = hasAC;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " -> address=" + address + ", city=" + city + ", type=" + type + ", age=" + age
				+ ", rooms=" + rooms + ", floors=" + floors + ", bathrooms=" + bathrooms + ", propertySize="
				+ propertySize + ", terrainSize=" + terrainSize + ", garageSize=" + garageSize + ", hasGarden="
				+ hasGarden + ", hasBasement=" + hasBasement + ", hasGarage=" + hasGarage + ", hasPool=" + hasPool
				+ ", hasLift=" + hasLift + ", hasTerrace=" + hasTerrace + ", hasAC=" + hasAC + ", available="
				+ available + ", status=" + status;
	}

}
