package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FieldUtils {

	// Checks ID field format and if it exists in the table provided in the
	// paramether
	public static boolean validateUserID(String ID, String table, JFrame frame) {
		if (!ID.matches("\\d+")) {
			JOptionPane.showMessageDialog(frame, "The ID doesn't have a valid format (Only numbers allowed)");
			return false;
		} else if (findCoincidence(ID, "id", table)) {
			JOptionPane.showMessageDialog(frame, "There's already an user with that ID", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	// Checks DNI field format and checks if it already exists in the database
	public static boolean validateDNI(String DNI, String table, JFrame frame) {
		if (!DNI.matches("[0-9]{8}[A-Z]")) {
			JOptionPane.showMessageDialog(frame, "The DNI doesn't have a valid format [00000000A]");
			return false;
		} else if (findCoincidenceOnUsers(DNI, "dni")) {
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
	public static boolean validateUsername(String username, String table, JFrame frame) {
		if (!username.matches("[A-Za-z0-9]{0,36}")) {
			JOptionPane.showMessageDialog(frame, "The username doesn't have a valid format", "Format error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (findCoincidenceOnUsers(username, "userName")) {
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
	public static boolean validateEmail(String email, JFrame frame) {
		if (!email.matches("[a-zA-Z0-9_\\-\\.]+@[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,}")) {
			if (email.length() > 80) {
				JOptionPane.showMessageDialog(frame, "The email is too long (Max: 80 characters)", "Format error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(frame, "The email doesn't have a valid format", "Format error",
						JOptionPane.ERROR_MESSAGE);
			}
			return false;
		} else if (findCoincidenceOnUsers(email, "email")) {
			JOptionPane.showMessageDialog(frame, "That email is already registered");
			return false;
		}
		return true;
	}

	// Checks phone field format and checks if it already exists in the database
	public static boolean validatePhone(String phoneNum, String table, JFrame frame) {
		if (!phoneNum.matches("([0-9]+){9,12}")) {
			JOptionPane.showMessageDialog(frame, "The phone number doesn't have a valid format", "Format error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (findCoincidenceOnUsers(Integer.parseInt(phoneNum), "phoneNum")) {
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

	// BETTER HANDLE HIRE DATE FORMAT BY GUI

//	// Checks hire date field format
//	// The paramether "hireDate" MUST be passed to this method after a .strip() and a .replaceAll("\\s+"," ")
//	public static boolean validateHireDate(String hireDate, JFrame frame) {
//		String[] dateTime = hireDate.split(" ");
//
//		if (dateTime.length != 2) {
//			JOptionPane.showMessageDialog(frame, "Invalid hire date format\nValid format: YYYY-MM-DD HH:MM:SS", "Format error", JOptionPane.ERROR_MESSAGE);
//			return false;
//		}
//
//		String[] dateArray = dateTime[0].split("-");
//		String[] timeArray = dateTime[1].split(":");
//
//		if (!checkDateArray(dateArray) || !checkTimeArray(timeArray))
//			return false;
//
//		return true;
//	}
//
//	private static boolean checkDateArray(String[] dateArray) {
//		int year = Integer.parseInt(dateArray[0]);
//		int month = Integer.parseInt(dateArray[1]);
//		int day = Integer.parseInt(dateArray[2]);
//
//		if(month == 2 || day > 29)
//			return false;
//		
//		if (year < 1900 || year > 2200 || month < 1 || month > 12 || day < 1 || day > 31)
//			return false;
//
//		return true;
//	}
//
//	private static boolean checkTimeArray(String[] timeArray) {
//		int hour = Integer.parseInt(timeArray[0]);
//		int minute = Integer.parseInt(timeArray[1]);
//		int second = Integer.parseInt(timeArray[2]);
//
//		if (hour < 0 || hour > 23 || minute < 0 || minute > 59 || second < 0 || second > 59)
//			return false;
//
//		return true;
//	}

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
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}

		return true;
	}

	// Checks salary field format
	public static boolean validateSalary(String salary, JFrame frame) {
		if (!salary.matches("\\d{1,10}(\\.\\d{2})?")) {
			JOptionPane.showMessageDialog(frame,
					"The salary doesn't have a valid format:\nMust be a number with two optionals decimals (Example: 00000.00 or 00000)",
					"Format error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	// Checks if the value exists in the respective table
	// Returns TRUE if it found at least one result or FALSE if it doesn't
	public static <T> boolean findCoincidence(T input, String field, String table) {
		boolean found = true;
		try {
			String statement = "SELECT " + field + " FROM inmomanager." + table + " WHERE " + field + " = ";

			if (input instanceof String) {
				statement += "'" + input + "'";
			} else {
				statement += input;
			}

			Connection conn = ConnectionDB.connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(statement);

			if (!rs.next()) {
				found = false;
			}

			rs.close();
			stmt.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return found;
	}

	// Checks if the value exists in the tables: Admins, Managers and Clients
	// Returns TRUE if it found at least one result or FALSE if it doesn't
	public static <T> boolean findCoincidenceOnUsers(T input, String field) {
		boolean found = true;
		try {
	    String query = 
	        "SELECT " + field + " FROM (" +
	        "    SELECT " + field + " FROM inmomanager.Managers " +
	        "    UNION " +
	        "    SELECT " + field + " FROM inmomanager.Clients " +
	        "    UNION " +
	        "    SELECT " + field + " FROM inmomanager.Administrators " +
	        ") AS AllData " +
	        "WHERE " + field + " = ";
	    
	    if (input instanceof String) {
	        query += "'" + input + "'";
	    } else if (input instanceof Integer) {
	        query += input;
	    }
	    
	    	Connection conn = ConnectionDB.connect();
	        Statement st = conn.createStatement();
	        ResultSet rs = st.executeQuery(query);
	        
	        if(!rs.next())
	        	found = false;

	        rs.close();
	        st.close();
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    }
	    return found;
	}
}
