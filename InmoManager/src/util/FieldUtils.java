package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FieldUtils {

	// Checks DNI field format and checks if it already exists in the database
	public static boolean validateDNI(String DNI, String table, JFrame frame) {
		if (!DNI.matches("[0-9]{8}[A-Z]")) {
			JOptionPane.showMessageDialog(frame, "The DNI doesn't have a valid format [00000000A]");
			return false;
		} else if (checkUniques(DNI, "dni", table)) {
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
		} else if (checkUniques(username, "userName", table)) {
			JOptionPane.showMessageDialog(frame, "That Username is already registered");
			return false;
		}
		return true;
	}

	// Checks if the password contains at least one special character and one number
	public static boolean validatePassword(char[] password, JFrame frame) {
		Set<Character> specialCharacters = new HashSet<>();
		specialCharacters.add('!');
		specialCharacters.add('#');
		specialCharacters.add('$');
		specialCharacters.add('%');
		specialCharacters.add('&');
		specialCharacters.add('*');
		specialCharacters.add('+');
		specialCharacters.add('-');
		specialCharacters.add('.');
		specialCharacters.add(':');
		specialCharacters.add(';');
		specialCharacters.add('<');
		specialCharacters.add('=');
		specialCharacters.add('>');
		specialCharacters.add('?');
		specialCharacters.add('@');
		specialCharacters.add('^');
		specialCharacters.add('_');
		specialCharacters.add('~');

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
	public static boolean validateEmail(String email, String table, JFrame frame) {
		if (!email.matches("[a-zA-Z0-9_\\-\\.]+@[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,}")) {
			if (email.length() > 80) {
				JOptionPane.showMessageDialog(frame, "The email is too long (Max: 80 characters)", "Format error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(frame, "The email doesn't have a valid format", "Format error",
						JOptionPane.ERROR_MESSAGE);
			}
			return false;
		} else if (checkUniques(email, "email", table)) {
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
		} else if (checkUniques(Integer.parseInt(phoneNum), "phoneNum", table)) {
			JOptionPane.showMessageDialog(frame, "That phone number is already registered");
			return false;
		}
		return true;
	}
	
	// Checks region field format
	public static boolean validateRegion(String region, JFrame frame) {
		if (!region.matches("[A-Za-z ]+")) {
			if (region.length() > 45) {
				JOptionPane.showMessageDialog(frame, "The region name is too long (Max: 45 characters)",
						"Format error",
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
			if(option == JOptionPane.OK_OPTION)
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
	
	// Checks repeated values with the database
	public static <T> boolean checkUniques(T input, String field, String table) {
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
}
