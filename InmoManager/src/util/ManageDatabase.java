package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.JOptionPane;

import models.Property;
import models.Purchasable_Property;
import models.Rentable_Property;

public class ManageDatabase {
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
