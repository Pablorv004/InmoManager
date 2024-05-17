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
import util.FieldUtils;
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
			boolean validDNI = FieldUtils.validateDNI(gRegister.getFieldDNI().getText().strip(), gRegister);
			boolean validName = FieldUtils.validateName(gRegister.getFieldName().getText().strip().replaceAll("\\s+", " "), gRegister);
			boolean validUsername = FieldUtils.validateUsername(gRegister.getFieldUsername().getText().strip(), gRegister);
			boolean validPassword = FieldUtils.validatePassword(gRegister.getFieldPassword().getPassword(), gRegister);
			boolean validRePassword = FieldUtils.validateRePassword(gRegister.getFieldPassword().getPassword(),gRegister.getFieldRepeatPass().getPassword(), gRegister);
			boolean validEmail = FieldUtils.validateEmail(gRegister.getFieldEmail().getText().strip(), gRegister);
			boolean validPhone = FieldUtils.validatePhone(gRegister.getFieldPhone().getText().strip(), gRegister);
			boolean validRegion = FieldUtils.validateRegion(gRegister.getFieldRegion().getText().strip(), gRegister);

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

			PreparedStatement pst = conn.prepareStatement(
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
						"Error", JOptionPane.ERROR_MESSAGE);

			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}
}
