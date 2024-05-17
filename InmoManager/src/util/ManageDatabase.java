package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

import models.Property;
import models.Purchasable_Property;
import models.Rentable_Property;

public class ManageDatabase {

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

    public static boolean propertyExists(boolean searchRentable, boolean searchPurchasable, Property property){
		try{
			Connection conn = ConnectionDB.connect();
            String query;
            Statement statement = conn.createStatement();
            if (searchRentable){
                query = "SELECT id FROM inmomanager.Rentable_Properties WHERE address = '" + property.getAddress() + "'";
                return statement.executeQuery(query).next();
            }
            if (searchPurchasable){
                query = "SELECT id FROM inmomanager.Purchasable_Properties WHERE address = '" + property.getAddress() + "'";
              return statement.executeQuery(query).next();
            }
		} catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return false;
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
            }
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
            return new Rentable_Property(resultSet.getInt("id"),
                    resultSet.getString("address"), resultSet.getString("city"),
                    resultSet.getString("type"), resultSet.getInt("age"),
                    resultSet.getInt("rooms"), resultSet.getInt("floors"), resultSet.getInt("bathrooms"),
                    resultSet.getInt("propertySize"), terrainSize,
                    garageSize, hasGarden, hasBasement, hasGarage, hasPool,
                    hasLift, hasTerrace, hasAC, available, resultSet.getString("status"),
                    resultSet.getInt("rentValue"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
            return new Purchasable_Property(resultSet.getInt("id"),
                    resultSet.getString("address"), resultSet.getString("city"),
                    resultSet.getString("type"), resultSet.getInt("age"),
                    resultSet.getInt("rooms"), resultSet.getInt("floors"), resultSet.getInt("bathrooms"),
                    resultSet.getInt("propertySize"), terrainSize,
                    garageSize, hasGarden, hasBasement, hasGarage, hasPool,
                    hasLift, hasTerrace, hasAC, available, resultSet.getString("status"),
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
