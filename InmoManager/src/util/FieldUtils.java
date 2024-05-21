package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FieldUtils {

	// Checks ID field format and if it exists in the table provided in the
	// paramether
	public static boolean validateUserID(String ID, String table, JFrame frame, String DNI) {
		if (!ID.matches("\\d+")) {
			JOptionPane.showMessageDialog(frame, "The ID doesn't have a valid format (Only numbers allowed)");
			return false;
		} else if (findUserCoincidence(ID, "id", table, DNI)) {
			JOptionPane.showMessageDialog(frame, "There's already an user with that ID", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	// Checks DNI field format and checks if it already exists in the database
	public static boolean validateDNI(String DNI, JFrame frame) {
		if (!DNI.matches("[0-9]{8}[A-Z]")) {
			JOptionPane.showMessageDialog(frame, "The DNI doesn't have a valid format [00000000A]");
			return false;
		} else if (findCoincidenceOnUsers(DNI, "dni", DNI)) {
			JOptionPane.showMessageDialog(frame, "That DNI is already registered");
			return false;
		}

		return true;
	}

	// Checks name field format
	public static boolean validateName(String name, JFrame frame) {
		if (!name.matches("[A-Za-z ]{0,100}")) {
			JOptionPane.showMessageDialog(frame, "The name doesn't have a valid format", "Format error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	// Checks username field format and checks if it already exists in the database
	public static boolean validateUsername(String username, JFrame frame, String DNI) {
		if (!username.matches("[A-Za-z0-9]{0,36}")) {
			JOptionPane.showMessageDialog(frame, "The username doesn't have a valid format", "Format error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (findCoincidenceOnUsers(username, "userName", DNI)) {
			JOptionPane.showMessageDialog(frame, "That Username is already registered");
			return false;
		}
		return true;
	}

	// Checks if the password contains at least one special character and one number
	public static boolean validatePassword(char[] password, JFrame frame) {
		Set<Character> specialCharacters = new HashSet<>(Arrays.asList('!', '#', '$', '%', '&', '*', '+', '-', '.', ':',
				';', '<', '=', '>', '?', '@', '^', '_', '~'));

		boolean hasSpecial = false;
		boolean hasNumber = false;

		// First we check if the password has 32 or less characters
		if (password.length > 32) {
			JOptionPane.showMessageDialog(frame, "The password is too long (Max: 32 characters)", "Format error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else {

			// Check if the password has any special characters, numbers or blank spaces in
			// it
			for (char c : password) {
				if (specialCharacters.contains(c)) {
					hasSpecial = true;
				} else if (Character.isDigit(c)) {
					hasNumber = true;
				} else if (c == ' ') {
					JOptionPane.showMessageDialog(frame, "The password must not contain blank spaces", "Format error",
							JOptionPane.ERROR_MESSAGE);
					return false;
				}

				if (hasSpecial && hasNumber)
					return true;
			}

			if (hasSpecial && hasNumber)
				return true;
			JOptionPane.showMessageDialog(frame,
					"The password must contain at least one number and one special character", "Format error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	// Checks if the repeat password field content is the same as the password field
	// content
	public static boolean validateRePassword(char[] password, char[] rePassword, JFrame frame) {
		String pass = String.valueOf(password).strip();
		String rePass = String.valueOf(rePassword).strip();

		if (pass.equals(rePass))
			return true;
		else
			JOptionPane.showMessageDialog(frame, "The passwords doesn't match", "Passwords error",
					JOptionPane.ERROR_MESSAGE);

		return false;
	}

	// Checks email field format and checks if it already exists in the database
	public static boolean validateEmail(String email, JFrame frame, String DNI) {
		if (!email.matches("[a-zA-Z0-9_\\-\\.]+@[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,}")) {
			if (email.length() > 80) {
				JOptionPane.showMessageDialog(frame, "The email is too long (Max: 80 characters)", "Format error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(frame, "The email doesn't have a valid format", "Format error",
						JOptionPane.ERROR_MESSAGE);
			}
			return false;
		} else if (findCoincidenceOnUsers(email, "email", DNI)) {
			JOptionPane.showMessageDialog(frame, "That email is already registered");
			return false;
		}
		return true;
	}

	// Checks phone field format and checks if it already exists in the database
	public static boolean validatePhone(String phoneNum, JFrame frame, String DNI) {
		if (!phoneNum.matches("([0-9]+){9,12}")) {
			JOptionPane.showMessageDialog(frame, "The phone number doesn't have a valid format", "Format error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (findCoincidenceOnUsers(Integer.parseInt(phoneNum), "phoneNum", DNI)) {
			JOptionPane.showMessageDialog(frame, "That phone number is already registered");
			return false;
		}
		return true;
	}

	// Checks region field format
	public static boolean validateRegion(String region, JFrame frame) {
		if (!region.matches("[A-Za-z ]+")) {
			if (region.length() > 45) {
				JOptionPane.showMessageDialog(frame, "The region name is too long (Max: 45 characters)", "Format error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(frame, "The region doesn't have a valid format", "Format error",
						JOptionPane.ERROR_MESSAGE);
			}
			return false;
		}
		return true;
	}
	
	// Checks commission field format
	public static boolean validateComission(String commission, JFrame frame) {
		if (!commission.matches("\\d{1,2}\\.\\d{1,2}")) {
			JOptionPane.showMessageDialog(frame, "The commission doesn't have a valid format\nFormat: 0.00 or 00.00", "Format error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	// Checks bank account field format
	public static boolean validateBankAccount(String bankAccount, JFrame frame) {
		if (bankAccount.isEmpty()) {
			int option = JOptionPane.showConfirmDialog(frame, "WARNING:\nThere's no bank account number", "Warning",
					JOptionPane.INFORMATION_MESSAGE);
			if (option == JOptionPane.OK_OPTION)
				return true;
			else
				return false;
		} else if (!bankAccount.matches("[A-Z]{2}\\d{22}")) {
			JOptionPane.showMessageDialog(frame, "The bank account doesn't have a valid format", "Format error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	// Checks manager ID field format and if it exists
	public static boolean validateManagerID(String managerID, JFrame frame) {
		if (managerID.isEmpty()) {
			int option = JOptionPane.showConfirmDialog(frame,
					"WARNING: There's no manager ID value\nThis means that this manager will be a super manager, are you sure?",
					"Warning", JOptionPane.INFORMATION_MESSAGE);
			if (option == JOptionPane.OK_OPTION)
				return true;
			else
				return false;
		} else if (!managerID.matches("\\d+")) {
			JOptionPane.showMessageDialog(frame, "The manager ID doesn't have a valid format (Only numbers allowed)",
					"Format error", JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			// ManagerID 0 is allowed because of super managers
			if(managerID.equals("0"))
				return true;
			
			// The new manager ID must be a existent one
			try {
				Connection conn = ConnectionDB.connect();
				PreparedStatement pst = conn.prepareStatement("SELECT id FROM inmomanager.Managers WHERE id = ?");
				pst.setInt(1, Integer.parseInt(managerID));
				ResultSet rs = pst.executeQuery();
				if (!rs.next()) {
					JOptionPane.showMessageDialog(frame, "There's no manager with this ID number", "Error",
							JOptionPane.ERROR_MESSAGE);
					return false;
				} else if (rs.getInt(1) == 0)
					return true;
					
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}

		return true;
	}

	// Checks salary field format
	public static boolean validateSalary(String salary, JFrame frame) {
		if (!salary.matches("\\d{1,4}(\\.\\d{2})?")) {
			JOptionPane.showMessageDialog(frame,
					"The salary doesn't have a valid format:\nMust be a number with two optionals decimals (Example: 00000.00 or 00000)",
					"Format error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	// Checks if the value exists in the respective table
	// Returns TRUE if it found at least one result or FALSE if it doesn't
	public static <T> boolean findUserCoincidence(T input, String field, String table, String DNI) {
		boolean found = true;
		try {
			String statement = "SELECT " + field + " FROM inmomanager." + table + " WHERE " + field + " = ? AND DNI <> ?";

			Connection conn = ConnectionDB.connect();
			PreparedStatement pst = conn.prepareStatement(statement);
			
			pst.setObject(1, input);
			pst.setObject(2, DNI);
			
			ResultSet rs = pst.executeQuery();

			if (!rs.next()) {
				found = false;
			}

			rs.close();
			pst.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return found;
	}

	// Checks if the value exists in the tables: Admins, Managers and Clients
	// Returns TRUE if it found at least one result or FALSE if it doesn't
	public static <T> boolean findCoincidenceOnUsers(T input, String field, String DNI) {
		boolean found = true;
		try {
			String query = "SELECT " + field + ", 'Managers' AS source FROM inmomanager.Managers WHERE " + field + " = ? AND DNI <> ? " +
                    		"UNION " +
                    		"SELECT " + field + ", 'Clients' AS source FROM inmomanager.Clients WHERE " + field + " = ? AND DNI <> ? " +
                    		"UNION " +
                    		"SELECT " + field + ", 'Admins' AS source FROM inmomanager.Administrators WHERE " + field + " = ? AND DNI <> ?";
	    
	    	Connection conn = ConnectionDB.connect();
	        PreparedStatement pst = conn.prepareStatement(query);
	        
	        pst.setObject(1, input);
            pst.setObject(2, DNI);
            pst.setObject(3, input);
            pst.setObject(4, DNI);
            pst.setObject(5, input);
            pst.setObject(6, DNI);
            
	        ResultSet rs = pst.executeQuery();
	        
	        if(!rs.next())
	        	found = false;

	        rs.close();
	        pst.close();
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    }
	    return found;
	}


	////// PROPERTIES VALIDATORS ///////

	public static boolean validatePropertyID(String ID, JFrame frame, String address) {
		if (!ID.matches("\\d+")) {
			JOptionPane.showMessageDialog(frame, "The ID doesn't have a valid format (Only numbers allowed)");
			return false;
		} else if (findPropertyCoincidence(ID, "id", ID)) {
			JOptionPane.showMessageDialog(frame, "There's already a property with that ID", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	public static boolean validateAddress(String address, JFrame frame, String ID) {
		if (address.length() > 50) {
			JOptionPane.showMessageDialog(frame, "The address doesn't have a valid format", "Format error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (findPropertyCoincidence(address,"address",ID)){
			JOptionPane.showMessageDialog(frame, "There's already a property with that address", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public static boolean validateCity(String city, JFrame frame) {
		if (!city.matches("[A-Za-z]+( [\\-,]? [A-Za-z]+)?")) {
			JOptionPane.showMessageDialog(frame, "Age doesn't have a valid format", "Format error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public static boolean validateRooms(String rooms, JFrame frame) {
		if (!rooms.matches("\\d+")) {
			JOptionPane.showMessageDialog(frame, "Rooms doesn't have a valid format", "Format error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public static boolean validateFloors(String floors, JFrame frame) {
		if (!floors.matches("\\d+")) {
			JOptionPane.showMessageDialog(frame, "Floors doesn't have a valid format", "Format error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public static boolean validateBathrooms(String bathrooms, JFrame frame) {
		if (!bathrooms.matches("\\d+")) {
			JOptionPane.showMessageDialog(frame, "Bathrooms doesn't have a valid format", "Format error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public static boolean validatePropertySize(String size, JFrame frame) {
		if (!size.matches("\\d+")) {
			JOptionPane.showMessageDialog(frame, "Property size doesn't have a valid format", "Format error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public static boolean validateTerrainSize(String size, JFrame frame, String type) {
		if (!size.matches("\\d+")) {
			JOptionPane.showMessageDialog(frame, "Terrain size doesn't have a valid format", "Format error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (!type.equalsIgnoreCase("Detached House") && Integer.parseInt(size) > 0){
			JOptionPane.showMessageDialog(frame, "This property is not a detached house, so it can not\nhave a terrain size value", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public static boolean validateGarageSize(String size, JFrame frame, int hasGarage) {
		if (!size.matches("\\d+")) {
			JOptionPane.showMessageDialog(frame, "Garage size doesn't have a valid format", "Format error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (hasGarage == 0 && Integer.parseInt(size) > 0){
			JOptionPane.showMessageDialog(frame, "This house does not have a garage, please add it or\nset garage size value to 0", "Format error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public static boolean validatePrice(String value, JFrame frame) {
		if (!value.matches("\\d+")) {
			JOptionPane.showMessageDialog(frame, "Price doesn't have a valid format", "Format error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public static boolean validateAge(String age, JFrame frame) {
		if (!age.matches("\\d+")) {
			JOptionPane.showMessageDialog(frame, "The age doesn't have a valid format", "Format error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public static <T> boolean findPropertyCoincidence(T input, String field, String ID) {
		boolean found = true;
		try {
			String statement = "SELECT " + field + " FROM inmomanager.Purchasable_Properties WHERE " + field + " = ? AND id <> ? " +
								"UNION " +
								"SELECT " + field + " FROM inmomanager.Rentable_Properties WHERE " + field + " = ? AND id <> ?";

			Connection conn = ConnectionDB.connect();
			PreparedStatement pst = conn.prepareStatement(statement);

			pst.setObject(1, input);
			pst.setObject(2, ID);
			pst.setObject(3, input);
			pst.setObject(4, ID);

			ResultSet rs = pst.executeQuery();

			if (!rs.next()) {
				found = false;
			}

			rs.close();
			pst.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return found;
	}
}
