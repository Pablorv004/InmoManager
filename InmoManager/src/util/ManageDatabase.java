package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import models.Client;
import models.Manager;
import models.Property;
import models.Purchasable_Property;
import models.Rentable_Property;

public class ManageDatabase {
	// 600 Undocumented lines of code? Yippeeeeeeeeeeeeeeeeeeeee
	public static List<Property> getUserHomes(String DNI) {
		List<Property> properties = new ArrayList<Property>();
		try {
			Connection conn = ConnectionDB.connect();
			String queryR = "SELECT p.* FROM inmomanager.Rents r JOIN inmomanager.Rentable_Properties p ON "
					+ "r.propertyID = p.id WHERE clientID = (SELECT id FROM inmomanager.Clients WHERE DNI = '" + DNI
					+ "')";
			String queryP = "SELECT p.* FROM inmomanager.Purchases r JOIN inmomanager.Purchasable_Properties p ON "
					+ "r.propertyID = p.id WHERE clientID = (SELECT id FROM inmomanager.Clients WHERE DNI = '" + DNI
					+ "')";
			Statement statement = conn.createStatement();
			ResultSet resultR = statement.executeQuery(queryR);
			while (resultR.next()) {
				properties.add(getRentableProperty(resultR));
			}
			ResultSet resultP = statement.executeQuery(queryP);

			while (resultP.next()) {
				properties.add(getPurchasableProperty(resultP));
			}
			return properties;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<Property> getUserCurrentHomes(String DNI) {
		List<Property> properties = new ArrayList<Property>();
		try {
			Connection conn = ConnectionDB.connect();
			String queryR = "SELECT p.* FROM inmomanager.Rents r JOIN inmomanager.Rentable_Properties p ON "
					+ "r.propertyID = p.id WHERE r.ongoing = 1 AND clientID = (SELECT id FROM inmomanager.Clients WHERE DNI = '"
					+ DNI + "')";
			Statement statement = conn.createStatement();
			ResultSet resultR = statement.executeQuery(queryR);
			while (resultR.next()) {
				properties.add(getRentableProperty(resultR));
			}
			return properties;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void updateClient(Client client) {
		if (client == null) {
			System.out.println("Client is null.");
			return;
		}

		try {
			Connection conn = ConnectionDB.connect();
			String query = "UPDATE inmomanager.Clients SET fullName = ?, userName = ?, password = ?, email = ?, phoneNum = ?, region = ?, bankAccountNum = ? WHERE DNI = ?";
			try (PreparedStatement pst = conn.prepareStatement(query)) {
				pst.setString(1, client.getFullName());
				pst.setString(2, client.getUserName());
				pst.setString(3, client.getPassword());
				pst.setString(4, client.getEmail());
				pst.setInt(5, client.getPhoneNum());
				pst.setString(6, client.getRegion());
				pst.setString(7, client.getBankAccountNum());
				pst.setString(8, client.getDNI());

				int rowsUpdated = pst.executeUpdate();
				if (rowsUpdated > 0) {
					System.out.println("Client successfully updated!");
				} else {
					System.out.println("No client was updated. Check if the DNI exists.");
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error updating client");
			e.printStackTrace();
		}
	}

	public static Map<String, Integer> managerRents() {
		Map<String, Integer> rents = new HashMap<>();
		try {
			Connection conn = ConnectionDB.connect();
			String queryR = "SELECT m.fullName, COUNT(r.propertyID) FROM inmomanager.Managers m LEFT JOIN inmomanager.Rents r ON m.id = r.managerID GROUP BY m.fullName;";
			Statement statement = conn.createStatement();
			ResultSet resultR = statement.executeQuery(queryR);
			while (resultR.next()) {
				rents.put(resultR.getString("fullName"),
						rents.getOrDefault(resultR.getString("fullName"), 0) + resultR.getInt("COUNT(r.propertyID)"));
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return rents;
	}

	public static Map<String, Integer> managerSales() {
		Map<String, Integer> sales = new HashMap<String, Integer>();
		try {
			Connection conn = ConnectionDB.connect();
			Statement statement = conn.createStatement();

			String queryP = "SELECT m.fullName, COUNT(r.propertyID) FROM inmomanager.Managers m LEFT JOIN inmomanager.Purchases r ON m.id = r.managerID GROUP BY m.fullName;";
			ResultSet resultP = statement.executeQuery(queryP);
			while (resultP.next()) {
				sales.put(resultP.getString("fullName"),
						sales.getOrDefault(resultP.getString("fullName"), 0) + resultP.getInt("COUNT(r.propertyID)"));
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return sales;
	}

	public static Map<String, Integer> featureCountMap() {
		Map<String, Integer> features = new HashMap<>();
		List<Property> properties = getProperties(true, true, new String[0]);
		for (Property p : properties) {
			if (p.isHasAC())
				features.put("AC", 1 + features.getOrDefault("AC", 0));
			if (p.isHasGarden())
				features.put("Garden", 1 + features.getOrDefault("Garden", 0));
			if (p.isHasGarage())
				features.put("Garage", 1 + features.getOrDefault("Garage", 0));
			if (p.isHasPool())
				features.put("Pool", 1 + features.getOrDefault("Pool", 0));
			if (p.isHasLift())
				features.put("Lift", 1 + features.getOrDefault("Lift", 0));
			if (p.isHasTerrace())
				features.put("Terrace", 1 + features.getOrDefault("Terrace", 0));
			if (p.isHasBasement())
				features.put("Basement", 1 + features.getOrDefault("Basement", 0));
		}
		return features;
	}

	public static Map<String, Integer> propertyTypeCountMap() {
		Map<String, Integer> propertyTypes = new HashMap<>();
		List<Property> properties = getProperties(true, true, new String[0]);
		for (Property p : properties) {
			propertyTypes.put(p.getType(), 1 + propertyTypes.getOrDefault(p.getType(), 0));
		}

		return propertyTypes;
	}

	public static Map<String, Integer> salesCityCostMap() {
		Map<String, Integer> salesCityCost = new HashMap<>();
		List<Property> properties = getProperties(false, true, new String[0]);
		for (Property p : properties) {
			salesCityCost.put(p.getCity(),
					salesCityCost.getOrDefault(p.getCity(), 0) + ((Purchasable_Property) p).getTotalValue());
		}
		return salesCityCost;
	}

	public static Map<String, Integer> rentsCityCostMap() {
		Map<String, Integer> rentsCityCost = new HashMap<>();

		List<Property> properties = getProperties(true, false, new String[0]);
		for (Property p : properties) {
			rentsCityCost.put(p.getCity(),
					rentsCityCost.getOrDefault(p.getCity(), 0) + ((Rentable_Property) p).getRentValue());
		}

		return rentsCityCost;
	}

	public static int[] countRentsSales() {
		int[] result = new int[2];
		try {
			Connection conn = ConnectionDB.connect();
			String query = "SELECT COUNT(*) FROM inmomanager.Rents";
			String queryP = "SELECT COUNT(*) FROM inmomanager.Purchases";
			Statement statement = conn.createStatement();
			ResultSet rs;
			rs = statement.executeQuery(query);
			rs.next();
			result[0] = rs.getInt("COUNT(*)");
			rs = statement.executeQuery(queryP);
			rs.next();
			result[1] = rs.getInt("COUNT(*)");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static Map<String, Integer> getSalesPerMonth() {
		Map<String, Integer> salesPerMonth = new LinkedHashMap<>();
		for (Month m : Month.values())
			salesPerMonth.put(ConversionMethods.getAbreviation(m), 0);
		try {
			Connection conn = ConnectionDB.connect();
			String queryR = "SELECT startDate FROM inmomanager.Rents";
			String queryP = "SELECT purchaseDate FROM inmomanager.Purchases";
			Statement statement = conn.createStatement();
			ResultSet resultR = statement.executeQuery(queryR);
			while (resultR.next()) {
				Month month = resultR.getTimestamp("startDate").toLocalDateTime().getMonth();
				salesPerMonth.put(ConversionMethods.getAbreviation(month),
						1 + salesPerMonth.get(ConversionMethods.getAbreviation(month)));
			}
			ResultSet resultP = statement.executeQuery(queryP);

			while (resultP.next()) {
				Month month = resultP.getTimestamp("purchaseDate").toLocalDateTime().getMonth();
				salesPerMonth.put(ConversionMethods.getAbreviation(month),
						1 + salesPerMonth.get(ConversionMethods.getAbreviation(month)));
			}
			return salesPerMonth;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void stopRent(Property property) {
		try {
			Connection conn = ConnectionDB.connect();

			String updateRentsQuery = "UPDATE inmomanager.Rents SET endDate = ?, ongoing = 0 WHERE propertyId = ? AND ongoing = 1";
			PreparedStatement updateRentsStmt = conn.prepareStatement(updateRentsQuery);
			updateRentsStmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
			updateRentsStmt.setInt(2, property.getId());
			updateRentsStmt.executeUpdate();

			String updatePropertiesQuery = "UPDATE inmomanager.Rentable_Properties SET available = 1 WHERE id = ?";
			PreparedStatement updatePropertiesStmt = conn.prepareStatement(updatePropertiesQuery);
			updatePropertiesStmt.setInt(1, property.getId());
			updatePropertiesStmt.executeUpdate();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Map<Integer, Integer> getSalesPerYear() {
		Map<Integer, Integer> salesPerYear = new TreeMap<>();
		try {
			Connection conn = ConnectionDB.connect();
			String queryR = "SELECT startDate FROM inmomanager.Rents";
			String queryP = "SELECT purchaseDate FROM inmomanager.Purchases";
			Statement statement = conn.createStatement();
			ResultSet resultR = statement.executeQuery(queryR);
			while (resultR.next()) {
				int year = resultR.getTimestamp("startDate").toLocalDateTime().getYear();
				salesPerYear.put(year, 1 + salesPerYear.getOrDefault(year, 0));
			}
			ResultSet resultP = statement.executeQuery(queryP);

			while (resultP.next()) {
				int year = resultP.getTimestamp("purchaseDate").toLocalDateTime().getYear();
				salesPerYear.put(year, 1 + salesPerYear.getOrDefault(year, 0));
			}
			return salesPerYear;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void setPropertyAvailability(boolean state, Property property) {
		try {
			int availability = state ? 1 : 0;
			Connection conn = ConnectionDB.connect();
			Statement statement = conn.createStatement();
			String sql;
			if (property instanceof Rentable_Property)
				sql = "UPDATE inmomanager.Rentable_Properties SET available = " + availability + " WHERE id = "
						+ property.getId();
			else
				sql = "UPDATE inmomanager.Purchasable_Properties SET available = " + availability + " WHERE id = "
						+ property.getId();
			statement.executeUpdate(sql);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Property state changed.");
	}

	public static Manager retrieveRandomManager() {
		try {
			Connection conn = ConnectionDB.connect();
			Statement statement = conn.createStatement();
			String sql = "SELECT * FROM inmomanager.Managers ORDER BY RAND() LIMIT 1";
			ResultSet rs = statement.executeQuery(sql);
			Manager manager = new Manager();
			while (rs.next()) {
				manager.setID(rs.getInt("id"));
				manager.setFullName(rs.getString("fullName"));
				manager.setUserName(rs.getString("userName"));
				manager.setPassword(rs.getString("password"));
				manager.setEmail(rs.getString("email"));
				manager.setPhoneNum(rs.getInt("phoneNum"));
				manager.setComission(rs.getDouble("commission"));
				manager.setBankAccountNum(rs.getString("bankAccountNum"));
				manager.setHireDate(rs.getTimestamp("hireDate").toLocalDateTime());
				manager.setManagerId(rs.getInt("managerId"));
				manager.setSalary(rs.getDouble("salary"));
			}
			return manager;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void updateClientBankInfo(Client client, String newBankInfo) {
		try {
			Connection conn = ConnectionDB.connect();
			Statement statement = conn.createStatement();
			String sql = "UPDATE inmomanager.Clients SET bankAccountNum = '" + newBankInfo + "' WHERE id = "
					+ client.getID() + ";";
			statement.executeUpdate(sql);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Updated client bank information.");
	}

	public static void insertPurchase(Property property, Client client, Manager manager) {
		try {
			Connection conn = ConnectionDB.connect();
			String sql;
			if (property instanceof Rentable_Property)
				sql = "INSERT INTO inmomanager.Rents (propertyID, clientID, managerID, startDate, endDate, ongoing) VALUES (?,?,?,?,?,?);";
			else
				sql = "INSERT INTO inmomanager.Purchases (propertyID, clientID, managerID, purchaseDate) VALUES (?,?,?,?);";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, property.getId());
			preparedStatement.setInt(2, client.getID());
			preparedStatement.setInt(3, manager.getID());
			preparedStatement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
			if (property instanceof Rentable_Property) {
				preparedStatement.setNull(5, Types.TIMESTAMP);
				preparedStatement.setInt(6, 1);
			}
			preparedStatement.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Map<String, Integer> getCityCountMap(boolean searchRentable, boolean searchPurchasable) {
		Map<String, Integer> cityCountMap = new LinkedHashMap<>();
		try {
			Connection conn = ConnectionDB.connect();
			String queryRentable = "SELECT city, available FROM inmomanager.Rentable_Properties";
			String queryPurchasable = "SELECT city, available FROM inmomanager.Purchasable_Properties";
			Statement statement = conn.createStatement();
			ResultSet rs;
			if (searchRentable) {
				rs = statement.executeQuery(queryRentable);
				while (rs.next()) {
					boolean available = rs.getInt("available") == 1 ? true : false;
					if (available) {
						String city = rs.getString("city");
						cityCountMap.put(city, 1 + cityCountMap.getOrDefault(city, 0));
					}
				}
			}
			if (searchPurchasable) {
				rs = statement.executeQuery(queryPurchasable);
				while (rs.next()) {
					boolean available = rs.getInt("available") == 1 ? true : false;
					if (available) {
						String city = rs.getString("city");
						cityCountMap.put(city, 1 + cityCountMap.getOrDefault(city, 0));
					}
				}
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		// Sort and return map
		List<Map.Entry<String, Integer>> mapentrylist = new ArrayList<>(cityCountMap.entrySet());
		mapentrylist.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

		Map<String, Integer> sortedMap = new LinkedHashMap<>();
		for (Map.Entry<String, Integer> entry : mapentrylist)
			sortedMap.put(entry.getKey(), entry.getValue());
		return sortedMap;
	}

	public static boolean propertyExists(boolean searchRentable, boolean searchPurchasable, Property property) {
		try {
			Connection conn = ConnectionDB.connect();
			String query;
			Statement statement = conn.createStatement();
			if (searchRentable) {
				query = "SELECT id FROM inmomanager.Rentable_Properties WHERE address = '" + property.getAddress()
						+ "'";
				return statement.executeQuery(query).next();
			}
			if (searchPurchasable) {
				query = "SELECT id FROM inmomanager.Purchasable_Properties WHERE address = '" + property.getAddress()
						+ "'";
				return statement.executeQuery(query).next();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static Map<String, Integer> getRentableTypeAvgMap() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		try {
			Connection conn = ConnectionDB.connect();
			String query = "SELECT type, AVG(rentValue) FROM inmomanager.Rentable_Properties GROUP BY type";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				String type = rs.getString("type");
				int price = rs.getInt("AVG(rentValue)");
				map.put(type, price);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return map;
	}

	public static Map<String, Integer> getPurchasableTypeAvgMap() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		try {
			Connection conn = ConnectionDB.connect();
			String query = "SELECT type, AVG(totalValue) FROM inmomanager.Purchasable_Properties GROUP BY type";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				String type = rs.getString("type");
				int price = rs.getInt("AVG(totalValue)");
				map.put(type, price);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return map;
	}

	public static List<Property> getProperties(boolean searchRentables, boolean searchPurchasable, String... filters) {
		List<Property> properties = new ArrayList<Property>();
		try {
			Connection conn = ConnectionDB.connect();
			String queryProperties = "SELECT * FROM inmomanager.";
			String queryPropertiesP = "SELECT * FROM inmomanager.";
			if (searchRentables)
				queryProperties += "Rentable_Properties";
			if (searchPurchasable)
				queryPropertiesP += "Purchasable_Properties";
			if (filters.length > 0) {
				queryProperties += " WHERE ";
				queryPropertiesP += " WHERE ";
				for (int i = 0; i < filters.length; i++) {
					String addition = filters[i];
					if (i != filters.length - 1) {
						queryProperties += addition + " AND ";
						queryPropertiesP += addition + " AND ";
					} else {
						if (addition.matches("totalValue .*")) {
							if (searchRentables) {
								addition = "rentValue " + filters[i].substring(10);
							}
						}
						queryProperties += addition + ";";
						queryPropertiesP += filters[i] + ";";
					}
				}
			}
			Statement search = conn.createStatement();
			ResultSet rs;
			if (searchRentables) {
				rs = search.executeQuery(queryProperties);
				while (rs.next()) {
					properties.add(getRentableProperty(rs));
				}
			}
			if (searchPurchasable) {
				rs = search.executeQuery(queryPropertiesP);
				while (rs.next()) {
					properties.add(getPurchasableProperty(rs));
				}
			}
			return properties;

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return properties;
	}

	public static Property getRentableProperty(ResultSet resultSet) {
		try {
			boolean hasGarden = resultSet.getInt("hasGarden") == 1 ? true : false;
			boolean hasBasement = resultSet.getInt("hasBasement") == 1 ? true : false;
			boolean hasGarage = resultSet.getInt("hasGarage") == 1 ? true : false;
			boolean hasPool = resultSet.getInt("hasPool") == 1 ? true : false;
			boolean hasLift = resultSet.getInt("hasLift") == 1 ? true : false;
			boolean hasTerrace = resultSet.getInt("hasTerrace") == 1 ? true : false;
			boolean hasAC = resultSet.getInt("hasAC") == 1 ? true : false;
			boolean available = resultSet.getInt("available") == 1 ? true : false;
			int garageSize = hasGarage ? resultSet.getInt("garageSize") : 0; // if the property has garage, then the
																				// garage size is not null
			int terrainSize = resultSet.getInt("terrainSize");
			return new Rentable_Property(resultSet.getInt("id"), resultSet.getString("address"),
					resultSet.getString("city"), resultSet.getString("type"), resultSet.getInt("age"),
					resultSet.getInt("rooms"), resultSet.getInt("floors"), resultSet.getInt("bathrooms"),
					resultSet.getInt("propertySize"), terrainSize, garageSize, hasGarden, hasBasement, hasGarage,
					hasPool, hasLift, hasTerrace, hasAC, available, resultSet.getString("status"),
					resultSet.getInt("rentValue"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void updateProperty(Property oldProperty, Property newProperty, JFrame frame) {
		int hasBasement = newProperty.isHasBasement() ? 1 : 0;
		int hasGarage = newProperty.isHasGarage() ? 1 : 0;
		int hasPool = newProperty.isHasPool() ? 1 : 0;
		int hasAC = newProperty.isHasAC() ? 1 : 0;
		int hasTerrace = newProperty.isHasTerrace() ? 1 : 0;
		int hasGarden = newProperty.isHasGarden() ? 1 : 0;
		int hasLift = newProperty.isHasLift() ? 1 : 0;
		int isAvailable = newProperty.isAvailable() ? 1 : 0;
		String statement = "UPDATE inmomanager. ";
		try {
			if (newProperty instanceof Rentable_Property)
				statement += "Rentable_Properties SET id = ?, address = ?, city = ?, type = ?, age = ?, rooms = ?, floors = ?, bathrooms = ?, propertySize = ?, terrainSize = ?, garageSize = ?, hasGarden = ?, hasBasement = ?, hasGarage = ?, hasPool = ?, hasLift = ?, hasTerrace = ?, hasAC = ?, available = ?, status = ?, rentValue = ?  WHERE id = ? AND address = ?";
			else if (newProperty instanceof Purchasable_Property)
				statement += "Purchasable_Properties SET id = ?, address = ?, city = ?, type = ?, age = ?, rooms = ?, floors = ?, bathrooms = ?, propertySize = ?, terrainSize = ?, garageSize = ?, hasGarden = ?, hasBasement = ?, hasGarage = ?, hasPool = ?, hasLift = ?, hasTerrace = ?, hasAC = ?, available = ?, status = ?, totalValue = ?  WHERE id = ? AND address = ?";

			Connection conn = ConnectionDB.connect();
			PreparedStatement pst = conn.prepareStatement(statement);
			pst.setInt(1, newProperty.getId());
			pst.setString(2, newProperty.getAddress());
			pst.setString(3, newProperty.getCity());
			pst.setString(4, newProperty.getType());
			pst.setInt(5, newProperty.getAge());
			pst.setInt(6, newProperty.getRooms());
			pst.setInt(7, newProperty.getFloors());
			pst.setInt(8, newProperty.getBathrooms());
			pst.setInt(9, newProperty.getPropertySize());
			if (newProperty.getTerrainSize() == 0)
				pst.setNull(10, Types.INTEGER); // sets null
			else
				pst.setInt(10, newProperty.getTerrainSize());
			if (newProperty.getGarageSize() == 0)
				pst.setNull(11, Types.INTEGER); // sets null
			else
				pst.setDouble(11, newProperty.getGarageSize());
			pst.setInt(12, hasGarden);
			pst.setInt(13, hasBasement);
			pst.setInt(14, hasGarage);
			pst.setInt(15, hasPool);
			pst.setInt(16, hasLift);
			pst.setInt(17, hasTerrace);
			pst.setInt(18, hasAC);
			pst.setInt(19, isAvailable);
			pst.setString(20, newProperty.getStatus());
			if (newProperty instanceof Rentable_Property)
				pst.setInt(21, ((Rentable_Property) newProperty).getRentValue());
			else
				pst.setInt(21, ((Purchasable_Property) newProperty).getTotalValue());
			pst.setInt(22, oldProperty.getId());
			pst.setString(23, oldProperty.getAddress());
			int updated = pst.executeUpdate();

			pst.close();
			if (updated != 0)
				JOptionPane.showMessageDialog(frame, "Property updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public static Set<String> getPropertyTypes() {
		Set<String> types = new HashSet<>();
		try {
			Connection conn = ConnectionDB.connect();
			Statement st = conn.createStatement();
			ResultSet rp = st.executeQuery("SELECT DISTINCT type FROM inmomanager.Rentable_Properties");
			while (rp.next()) {
				types.add(rp.getString("type"));
			}
			ResultSet rs = st.executeQuery("SELECT DISTINCT type FROM inmomanager.Purchasable_Properties");
			while (rs.next()) {
				types.add(rs.getString("type"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return types;
	}

	public static Property getPurchasableProperty(ResultSet resultSet) {
		try {
			boolean hasGarden = resultSet.getInt("hasGarden") == 1 ? true : false;
			boolean hasBasement = resultSet.getInt("hasBasement") == 1 ? true : false;
			boolean hasGarage = resultSet.getInt("hasGarage") == 1 ? true : false;
			boolean hasPool = resultSet.getInt("hasPool") == 1 ? true : false;
			boolean hasLift = resultSet.getInt("hasLift") == 1 ? true : false;
			boolean hasTerrace = resultSet.getInt("hasTerrace") == 1 ? true : false;
			boolean hasAC = resultSet.getInt("hasAC") == 1 ? true : false;
			boolean available = resultSet.getInt("available") == 1 ? true : false;
			int garageSize = hasGarage ? resultSet.getInt("garageSize") : 0; // if the property has garage, then the
																				// garage size is not null
			int terrainSize = resultSet.getInt("terrainSize");
			if (resultSet.wasNull()) {
				terrainSize = 0;
			}
			return new Purchasable_Property(resultSet.getInt("id"), resultSet.getString("address"),
					resultSet.getString("city"), resultSet.getString("type"), resultSet.getInt("age"),
					resultSet.getInt("rooms"), resultSet.getInt("floors"), resultSet.getInt("bathrooms"),
					resultSet.getInt("propertySize"), terrainSize, garageSize, hasGarden, hasBasement, hasGarage,
					hasPool, hasLift, hasTerrace, hasAC, available, resultSet.getString("status"),
					resultSet.getInt("totalValue"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void addPropertyToDatabase(Property property) {
		if (property != null) {
			int hasBasement = property.isHasBasement() ? 1 : 0;
			int hasGarage = property.isHasGarage() ? 1 : 0;
			int hasPool = property.isHasPool() ? 1 : 0;
			int hasAC = property.isHasAC() ? 1 : 0;
			int hasTerrace = property.isHasTerrace() ? 1 : 0;
			int hasGarden = property.isHasGarden() ? 1 : 0;
			int hasLift = property.isHasLift() ? 1 : 0;
			try {
				Connection conn = ConnectionDB.connect();
				PreparedStatement pst = null;
				if (property instanceof Rentable_Property)
					pst = conn.prepareStatement(
							"INSERT INTO inmomanager.Rentable_Properties (address, city, type, age, rooms, floors, bathrooms, propertySize, terrainSize, garageSize, hasGarden, hasBasement, hasGarage, hasPool, hasLift, hasTerrace, hasAC, available, status, rentValue) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				else
					pst = conn.prepareStatement(
							"INSERT INTO inmomanager.Purchasable_Properties (address, city, type, age, rooms, floors, bathrooms, propertySize, terrainSize, garageSize, hasGarden, hasBasement, hasGarage, hasPool, hasLift, hasTerrace, hasAC, available, status, totalValue) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				pst.setString(1, property.getAddress());
				pst.setString(2, property.getCity());
				pst.setString(3, property.getType());
				pst.setInt(4, property.getAge());
				pst.setInt(5, property.getRooms());
				pst.setInt(6, property.getFloors());
				pst.setInt(7, property.getBathrooms());
				pst.setInt(8, property.getPropertySize());
				if (property.getTerrainSize() == 0)
					pst.setNull(9, Types.INTEGER); // sets null
				else
					pst.setInt(9, property.getTerrainSize());
				if (property.getGarageSize() == 0)
					pst.setNull(10, Types.INTEGER); // sets null
				else
					pst.setDouble(10, property.getGarageSize());
				pst.setInt(11, hasGarden);
				pst.setInt(12, hasBasement);
				pst.setInt(13, hasGarage);
				pst.setInt(14, hasPool);
				pst.setInt(15, hasLift);
				pst.setInt(16, hasTerrace);
				pst.setInt(17, hasAC);
				pst.setInt(18, 1);
				pst.setString(19, property.getStatus());
				if (property instanceof Rentable_Property)
					pst.setInt(20, ((Rentable_Property) property).getRentValue());
				else
					pst.setInt(20, ((Purchasable_Property) property).getTotalValue());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Property added!", "Success", JOptionPane.INFORMATION_MESSAGE);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	}
}