package controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import util.ConnectionDB;
import views.GUILogin;
import views.GUIRegister;

public class ControllerRegister {
	private GUIRegister gRegister;

	public ControllerRegister(GUIRegister gRegister) {
		this.gRegister = gRegister;

		gRegister.addActListener(new ActListener());
	}

	private class ActListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();

			if (obj == gRegister.getBtnCancel()) {
				gRegister.dispose();
				new GUILogin();
			} else if (obj == gRegister.getBtnRegister()) {
				if (validateFields())
					register();
			}
		}

	}

	private boolean validateFields() {
		if (checkBlankValues()) {
			boolean validDNI = validateDNI();
			boolean validName = validateName();
			boolean validUsername = validateUsername();
			boolean validPassword = validatePassword();
			boolean validRePassword = validateRePassword();
			boolean validEmail = validateEmail();
			boolean validPhone = validatePhone();
			boolean validRegion = validateRegion();

			if (validDNI && validName && validUsername && validPassword && validRePassword && validEmail && validPhone
					&& validRegion)
				return true;
			else {
				paintInvalidFields(validDNI, validName, validUsername, validPassword, validRePassword, validEmail,
						validPhone, validRegion);
				return false;
			}

		} else
			return false;

	}

	private void paintInvalidFields(boolean validDNI, boolean validName, boolean validUsername, boolean validPassword,
			boolean validRePassword, boolean validEmail, boolean validPhone, boolean validRegion) {

		Map<JTextField, Boolean> fields = new HashMap<>();

		fields.put(gRegister.getFieldDNI(), validDNI);
		fields.put(gRegister.getFieldName(), validName);
		fields.put(gRegister.getFieldUsername(), validUsername);
		fields.put(gRegister.getFieldPassword(), validPassword);
		fields.put(gRegister.getFieldRepeatPass(), validRePassword);
		fields.put(gRegister.getFieldEmail(), validEmail);
		fields.put(gRegister.getFieldPhone(), validPhone);
		fields.put(gRegister.getFieldRegion(), validRegion);

		Iterator<JTextField> it = fields.keySet().iterator();
		while (it.hasNext()) {
			JTextField textField = it.next();
			if (!fields.get(textField))
				textField.setBorder(BorderFactory.createLineBorder(Color.RED));
			else
				textField.setBorder(new LineBorder(new Color(100, 149, 237)));
		}
	}

	private boolean validateRegion() {
		if (!gRegister.getFieldRegion().getText().strip().matches("[A-Za-z ]+")) {
			if (gRegister.getFieldRegion().getText().strip().length() > 45) {
				JOptionPane.showMessageDialog(gRegister, "The region name is too long (Max: 45 characters)",
						"Format error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(gRegister, "The region doesn't have a valid format", "Format error",
						JOptionPane.ERROR_MESSAGE);
			}
			return false;
		}
		return true;
	}

	private boolean validatePhone() {
		if (!gRegister.getFieldPhone().getText().strip().matches("([0-9]+){9,12}")) {
			JOptionPane.showMessageDialog(gRegister, "The phone number doesn't have a valid format", "Format error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	private boolean validateEmail() {
		String email = gRegister.getFieldEmail().getText().strip();
		if (!email.matches("[a-zA-Z0-9_\\-\\.]+@[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,}")) {
			if (email.length() > 80) {
				JOptionPane.showMessageDialog(gRegister, "The email is too long (Max: 80 characters)", "Format error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(gRegister, "The email doesn't have a valid format", "Format error",
						JOptionPane.ERROR_MESSAGE);
			}
			return false;
		}
		return true;
	}

	private boolean validateRePassword() {
		String password = String.valueOf(gRegister.getFieldPassword().getPassword()).strip();
		String rePassword = String.valueOf(gRegister.getFieldRepeatPass().getPassword()).strip();

		if (password.equals(rePassword))
			return true;
		JOptionPane.showMessageDialog(gRegister, "The passwords doesn't match", "Passwords error",
				JOptionPane.ERROR_MESSAGE);
		return false;
	}

	private boolean validatePassword() {
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

		char[] password = gRegister.getFieldPassword().getPassword();
		boolean hasSpecial = false;
		boolean hasNumber = false;

		// First we check if the password has 32 or less characters
		if (password.length > 32) {
			JOptionPane.showMessageDialog(gRegister, "The password is too long (Max: 32 characters)", "Format error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else {

			// Check if the password has any special characters or numbers in it
			for (char c : password) {
				if (specialCharacters.contains(c)) {
					hasSpecial = true;
				} else if (Character.isDigit(c)) {
					hasNumber = true;
				}

				if (hasSpecial && hasNumber)
					return true;
			}

			if (hasSpecial && hasNumber)
				return true;
			JOptionPane.showMessageDialog(gRegister,
					"The password must contain at least one number and one special character", "Format error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	private boolean validateUsername() {
		if (!gRegister.getFieldUsername().getText().strip().matches("[A-Za-z0-9]{0,36}")) {
			if (gRegister.getFieldUsername().getText().strip().length() > 36) {
				JOptionPane.showMessageDialog(gRegister, "The username is too long (Max: 36 characters)",
						"Format error", JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(gRegister, "The username doesn't have a valid format", "Format error",
						JOptionPane.ERROR_MESSAGE);
			}
			return false;
		}
		return true;
	}

	private boolean validateName() {
		if (!gRegister.getFieldName().getText().strip().replaceAll("\\s+", " ").matches("[A-Za-z ]{0,100}")) {
			JOptionPane.showMessageDialog(gRegister, "The name doesn't have a valid format", "Format error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	private boolean validateDNI() {
		if (!gRegister.getFieldDNI().getText().strip().matches("[0-9]{8}[A-Z]")) {
			JOptionPane.showMessageDialog(gRegister, "The DNI doesn't have a valid format [00000000A]");
			return false;
		}
		return true;
	}

	private boolean checkBlankValues() {
		String DNI = gRegister.getFieldDNI().getText();
		String name = gRegister.getFieldName().getText();
		String username = gRegister.getFieldUsername().getText();
		String password = String.valueOf(gRegister.getFieldPassword().getPassword());
		String rePassword = String.valueOf(gRegister.getFieldRepeatPass().getPassword());
		String email = gRegister.getFieldEmail().getText();
		String phoneNumber = gRegister.getFieldPhone().getText();

		if (DNI.isBlank() || name.isBlank() || username.isBlank() || password.isBlank() || rePassword.isBlank()
				|| email.isBlank() || phoneNumber.isBlank() || phoneNumber.isBlank()) {
			JOptionPane.showMessageDialog(gRegister, "There must be no blank fields", "Format error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else
			return true;
	}

	// Inserts a new user into the database
	private void register() {

		try {
			Connection conn = ConnectionDB.connect();
			String DNI = gRegister.getFieldDNI().getText().strip();
			String fullname = gRegister.getFieldName().getText().strip().replaceAll("\\s+", " ");
			String username = gRegister.getFieldUsername().getText().strip();
			String password = String.valueOf(gRegister.getFieldPassword().getPassword()).strip();
			String email = gRegister.getFieldEmail().getText().strip();
			int phoneNumber = Integer.parseInt(gRegister.getFieldPhone().getText().strip());
			String region = gRegister.getFieldRegion().getText().strip().replaceAll("\\s+", " ");

			PreparedStatement pst = conn
					.prepareStatement(
							"INSERT INTO inmomanager.Clients (DNI,fullName,userName,password,email,phoneNum,region)"
									+ "VALUES (?,?,?,?,?,?,?)");
			pst.setString(1, DNI);
			pst.setString(2, fullname);
			pst.setString(3, username);
			pst.setString(4, password);
			pst.setString(5, email);
			pst.setInt(6, phoneNumber);
			pst.setString(7, region);

			int insertedData = pst.executeUpdate();

			if (insertedData > 0)
				JOptionPane.showMessageDialog(gRegister, "User registered succesfully! You may now log-in!");
			else
				JOptionPane.showMessageDialog(gRegister, "Something went wrong! User could not been registered!",
						"Error",
						JOptionPane.ERROR_MESSAGE);

			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}
}
