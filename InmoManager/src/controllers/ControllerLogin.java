package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;

import util.ConnectionDB;
import views.GUILogin;
import views.GUIMainAdmin;
import views.GUIMainManager;
import views.GUIMainUser;
import views.GUIRegister;

/**
 * This class is responsible for handling the login functionality of the
 * application.
 * It contains methods to check if a user exists and if the provided password
 * matches the stored one.
 */
public class ControllerLogin {
	private GUILogin login;

	public ControllerLogin(GUILogin login) {
		this.login = login;

		login.addActListener(new ActListener());
	}

	public boolean userFound(String username) {
		System.out.println("Connecting...");
		boolean userFound = false;
		try {
			Connection conn = ConnectionDB.connect();
			System.out.println("Connected!");
			// Checking clients...
			PreparedStatement statementConnection = conn
					.prepareStatement("SELECT userName FROM inmomanager.Clients WHERE userName = ?");
			statementConnection.setString(1, username);
			if (statementConnection.executeQuery().next()) {
				userFound = true;
				ConnectionDB.setUserAccessLvl("Clients");
				ConnectionDB.setLoginUsername(username);
			} else {
				// Checking managers...
				statementConnection = conn
						.prepareStatement("SELECT userName FROM inmomanager.Managers WHERE userName = ?");
				statementConnection.setString(1, username);
				if (statementConnection.executeQuery().next()) {
					userFound = true;
					ConnectionDB.setUserAccessLvl("Managers");
					ConnectionDB.setLoginUsername(username);
				} else {
					// Checking administrators...
					statementConnection = conn
							.prepareStatement("SELECT userName FROM inmomanager.Administrators WHERE userName = ?");
					statementConnection.setString(1, username);
					if (statementConnection.executeQuery().next()) {
						userFound = true;
						ConnectionDB.setUserAccessLvl("Administrators");
						ConnectionDB.setLoginUsername(username);
					}
				}
			}
		} catch (ClassNotFoundException e1) {
			login.getLblError().setText("Contact an administrator for help. (Code: ClassNotFoundException, 1)");
			e1.printStackTrace();
		} catch (SQLException e1) {
			login.getLblError().setText("Contact an administrator for help. (Code: SQLException, 1)");
			e1.printStackTrace();
		}
		return userFound;
	}

	/**
	 * This method checks if the provided password matches the stored password for
	 * the given username.
	 * It determines which table to check based on the user access level set in the
	 * class.
	 * 
	 * @param username The username to check the password for.
	 * @param password The password to check against the stored password.
	 * @return true if the passwords match, false otherwise.
	 */
	public boolean passwordMatches(String username, String password) {
		try {
			// Let's determine which table to check:
			Connection conn = ConnectionDB.connect();
			if (ConnectionDB.getUserAccessLvl() != null) {
				String query = "SELECT userName FROM inmomanager." + ConnectionDB.getUserAccessLvl()
						+ " WHERE userName = ? AND password = ?";
				try (PreparedStatement statementConnection = conn.prepareStatement(query)) {
					statementConnection.setString(1, username);
					statementConnection.setString(2, password);
					return statementConnection.executeQuery().next();
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			login.getLblError()
					.setText("Contact an administrator for help. (Code: " + e.getClass().getSimpleName() + ", 1)");
		}
		return false;
	}

	private class ActListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton buttonPressed = (JButton) e.getSource();

			if (buttonPressed == login.getBtnExit())
				System.exit(0);
			else if (buttonPressed == login.getBtnRegister()) {
				new GUIRegister(login);
				login.dispose();
			} else if (buttonPressed == login.getBtnLogin()) {
				String username = login.getTxtUsername().getText();
				String password = String.valueOf(login.getPwPassword().getPassword());
				if (!userFound(username))
					login.getLblError().setText("Username not found.");
				else if (!passwordMatches(username, password))
					login.getLblError().setText("Incorrect password.");
				else {
					switch (ConnectionDB.getUserAccessLvl()) {
						case "Clients" -> new GUIMainUser(login);
						case "Managers" -> new GUIMainManager(login);
						case "Administrators" -> new GUIMainAdmin(login);
					}
					login.dispose();
					ConnectionDB.getCurrentUser();
				}
			}
		}

	}

}
