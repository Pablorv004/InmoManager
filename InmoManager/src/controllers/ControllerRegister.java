package controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

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

			if (validDNI && validName && validUsername && validPassword && validRePassword && validEmail && validPhone)
				return true;
			else {
				paintInvalidFields(validDNI, validName, validUsername, validPassword, validRePassword, validEmail,
						validPhone);
				return false;
			}

		} else
			return false;

	}

	private void paintInvalidFields(boolean validDNI, boolean validName, boolean validUsername, boolean validPassword,
			boolean validRePassword, boolean validEmail, boolean validPhone) {

		Map<JTextField, Boolean> fields = new HashMap<>();

		fields.put(gRegister.getFieldDNI(), validDNI);
		fields.put(gRegister.getFieldName(), validName);
		fields.put(gRegister.getFieldUsername(), validUsername);
		fields.put(gRegister.getFieldPassword(), validPassword);
		fields.put(gRegister.getFieldRepeatPass(), validRePassword);
		fields.put(gRegister.getFieldEmail(), validEmail);
		fields.put(gRegister.getFieldPhone(), validPhone);

		Iterator<JTextField> it = fields.keySet().iterator();
		while (it.hasNext()) {
			JTextField textField = it.next();
			if (!fields.get(textField))
				textField.setBorder(BorderFactory.createLineBorder(Color.RED));
			else
				textField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}
	}

	private boolean validatePhone() {
		if (!gRegister.getFieldPhone().getText().strip().matches("[0-9]+{9,}")) {
			JOptionPane.showMessageDialog(gRegister, "The phone number doesn't have a valid format", "Format error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}else
			return true;
	}

	private boolean validateEmail() {
		String email = gRegister.getFieldEmail().getText().strip();
		if (!email.matches("[a-zA-Z0-9._-\\.]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
			if (email.length() > 80) {
				JOptionPane.showMessageDialog(gRegister, "The email is too long (Max: 80 characters)", "Format error",
						JOptionPane.ERROR_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(gRegister, "The email doesn't have a valid format", "Format error",
						JOptionPane.ERROR_MESSAGE);
			}
			return false;
		} else
			return true;
	}

	private boolean validateRePassword() {
		String password = String.valueOf(gRegister.getFieldPassword().getPassword()).strip();
		String rePassword = String.valueOf(gRegister.getFieldRepeatPass().getPassword()).strip();

		if (password.equals(rePassword))
			return true;
		else {
			JOptionPane.showMessageDialog(gRegister, "The passwords doesn't match", "Passwords error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
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
			else {
				JOptionPane.showMessageDialog(gRegister,
						"The password must contain at least one number and one special characterr", "Format error",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
	}

	private boolean validateUsername() {
		if (!gRegister.getFieldUsername().getText().strip().matches("[A-Za-Z0-9]{,36}")) {
			if (gRegister.getFieldUsername().getText().strip().length() > 36) {
				JOptionPane.showMessageDialog(gRegister, "The username is too long (Max: 36 characters)",
						"Format error", JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(gRegister, "The username doesn't have a valid format", "Format error",
						JOptionPane.ERROR_MESSAGE);
			}
			return false;
		} else
			return true;
	}

	private boolean validateName() {
		if (!gRegister.getFieldName().getText().strip().replaceAll("\\s+", " ")
				.matches("[A-Za-z¡…Õ”⁄·ÈÌÛ˙‹¸—Ò]{,100}")) {
			JOptionPane.showMessageDialog(gRegister, "The name doesn't have a valid format", "Format error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else
			return true;
	}

	private boolean validateDNI() {
		if (!gRegister.getFieldDNI().getText().strip().matches("[0-9]{8}[A-Z]")) {
			JOptionPane.showMessageDialog(gRegister, "The DNI doesn't have a valid format [00000000A]");
			return false;
		} else
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
				|| email.isBlank() || phoneNumber.isBlank()) {
			JOptionPane.showMessageDialog(gRegister, "The must be no blank fields", "Format error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else
			return true;
	}

	private void register() {
		// - Implement data parsing with database functionality
		// Must be careful about data type
	}
}
