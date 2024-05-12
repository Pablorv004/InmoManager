package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;

import views.GUILogin;
import views.GUIMainAdmin;
import views.GUIMainManager;
import views.GUIMainUser;
import views.GUIRegister;

public class ControllerLogin {
	private GUILogin login;
	private int userAccessLvl;

	public ControllerLogin(GUILogin login) {
		this.login = login;

		login.addActListener(new ActListener());
	}

	public boolean userFound(String username) {
		boolean userFound = false;
		try (Connection conn = ConnectionDB.connect()) {
			username = login.getTxtUsername().getText();
			PreparedStatement statementConnection = conn
					.prepareStatement("SELECT userName FROM inmomanager.? WHERE userName = ?");
			statementConnection.setString(2, username);
			// Checking clients...
			statementConnection.setString(1, "Clients");
			if (statementConnection.executeQuery().next()) {
				userFound = true;
				userAccessLvl = 0;
			} else {
				// Checking managers...
				statementConnection.setString(1, "Managers");
				if (statementConnection.executeQuery().next()) {
					userFound = true;
					userAccessLvl = 1;
				} else {
					// Checking administrators...
					statementConnection.setString(1, "Administrators");
					if (statementConnection.executeQuery().next()) {
						userFound = true;
						userAccessLvl = 2;
					}
				}
			}

		} catch (ClassNotFoundException e1) {
			login.getLblError().setText("Contact an administrator for help. (Code: ClassNotFoundException, 1)");
		} catch (SQLException e1) {
			login.getLblError().setText("Contact an administrator for help. (Code: SQLException, 1)");
		}
		return userFound;
	}

	public boolean passwordMatches(String username, String password) {
		try (Connection conn = ConnectionDB.connect()) {
			username = login.getTxtUsername().getText();
			PreparedStatement statementConnection = conn
					.prepareStatement("SELECT userName FROM inmomanager.? WHERE userName = ? AND password = ?");
			statementConnection.setString(2, username);
			statementConnection.setString(3, password);
			// Let's determine which table to check:
			switch (userAccessLvl) {
			case 0 -> statementConnection.setString(1, "Clients");
			case 1 -> statementConnection.setString(1, "Managers");
			case 2 -> statementConnection.setString(1, "Administrators");
			default -> System.out.println("This shouldn't happen. What user access level is this?");
			}
			// Now let's see if the password matches:
			return (statementConnection.executeQuery().next());
		} catch (ClassNotFoundException e1) {
			login.getLblError().setText("Contact an administrator for help. (Code: ClassNotFoundException, 1)");
		} catch (SQLException e1) {
			login.getLblError().setText("Contact an administrator for help. (Code: SQLException, 1)");
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
				new GUIRegister();
				login.dispose();
			} else if (buttonPressed == login.getBtnLogin()) {
				String username = login.getTxtUsername().getText();
				String password = String.valueOf(login.getPwPassword().getPassword());
				if (!userFound(username))
					login.getLblError().setText("Username not found.");
				else if (!passwordMatches(username, password))
					login.getLblError().setText("Incorrect password.");
				else {
					switch (userAccessLvl) {
					case 0 -> new GUIMainUser();
					case 1 -> new GUIMainManager();
					case 2 -> new GUIMainAdmin();
					default -> System.out.println("What access level is this?");
					}
					login.dispose();
				}
			}
		}

	}

}
