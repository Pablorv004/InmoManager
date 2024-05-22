package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.Admin;
import models.Client;
import models.Manager;
import models.User;

public class ConnectionDB {

    private static Connection conn = null;
    private static String currentUsername;
    private static String userAccessLvl;
    /**
     * Connects to the database.
     * @return the current connection.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static Connection connect() throws SQLException, ClassNotFoundException {
        if (conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(
                        "jdbc:mysql://158.179.219.75:3306/inmomanager?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                        "root", "Pest-Illo1");
                new GlobalResources();
            } catch (SQLException ex) {
                throw new SQLException(ex);
            } catch (ClassNotFoundException ex) {
                throw new ClassCastException(ex.getMessage());
            }
        }
        return conn;
    }

    public static String getUserAccessLvl() {
        return userAccessLvl;
    }

    public static void setUserAccessLvl(String userAccessLvl) {
        ConnectionDB.userAccessLvl = userAccessLvl;
    }

    public static String getLoginUsername() {
        return currentUsername;
    }

    public static void setLoginUsername(String loginUsername) {
        ConnectionDB.currentUsername = loginUsername;
    }
    /**
     * Gets the user connected to the system.
     * @return the User that's connected. Null if the user's not connected to the system yet.
     */
    public static User getCurrentUser() {
		User user = null;
		try {
			Connection conn = ConnectionDB.connect();
			if (ConnectionDB.getUserAccessLvl() != null) {
				String query = "SELECT * FROM inmomanager." + ConnectionDB.getUserAccessLvl() + " WHERE userName = '"
						+ ConnectionDB.getLoginUsername() + "'";
				try (Statement statementConnection = conn.createStatement()) {
					ResultSet result = statementConnection.executeQuery(query);
					result.next();
					if (ConnectionDB.getUserAccessLvl().equals("Administrators"))
						user = new Admin(result.getInt("id"), result.getString("DNI"), result.getString("fullName"),
								result.getString("userName"), result.getString("password"), result.getString("email"),
								result.getInt("phoneNum"), result.getDouble("salary"),
								result.getString("bankAccountNum"));
					else if (ConnectionDB.getUserAccessLvl().equals("Clients"))
						user = new Client(result.getInt("id"), result.getString("DNI"), result.getString("fullName"),
								result.getString("userName"), result.getString("password"), result.getString("email"),
								result.getInt("phoneNum"), result.getString("region"),
								result.getString("bankAccountNum"),
								result.getTimestamp("creationTime").toLocalDateTime());
					else
						user = new Manager(result.getInt("id"), result.getString("DNI"), result.getString("fullName"),
								result.getString("userName"), result.getString("password"), result.getString("email"),
								result.getInt("phoneNum"), result.getDouble("commission"),
								result.getString("bankAccountNum"), result.getTimestamp("hireDate").toLocalDateTime(),
								result.getInt("managerId"),
								result.getDouble("salary"));

				}
			}
		} catch (SQLException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		return user;
	}

    public static void close() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
}