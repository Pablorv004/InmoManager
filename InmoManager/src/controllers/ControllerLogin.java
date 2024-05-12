package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		System.out.println("Connecting...");
		boolean userFound = false;
		try (Connection conn = ConnectionDB.connect()) {
			System.out.println("Connected!");
			// Checking clients...
			PreparedStatement statementConnection = conn
					.prepareStatement("SELECT userName FROM inmomanager.Clients WHERE userName = ?");
			statementConnection.setString(1, username);
			if (statementConnection.executeQuery().next()) {
				
				userFound = true;
				userAccessLvl = 0;
			} else {
				// Checking managers...
				statementConnection = conn
						.prepareStatement("SELECT userName FROM inmomanager.Managers WHERE userName = ?");
				statementConnection.setString(1, username);
				if (statementConnection.executeQuery().next()) {
					userFound = true;
					userAccessLvl = 1;
				} else {
					// Checking administrators...
					statementConnection = conn
							.prepareStatement("SELECT userName FROM inmomanager.Administrators WHERE userName = ?");
					statementConnection.setString(1, username);
					if (statementConnection.executeQuery().next()) {
						userFound = true;
						userAccessLvl = 2;
					}
				}
			}
			System.out.println("Users access level: " + userAccessLvl);
		} catch (ClassNotFoundException e1) {
			login.getLblError().setText("Contact an administrator for help. (Code: ClassNotFoundException, 1)");
			e1.printStackTrace();
		} catch (SQLException e1) {
			login.getLblError().setText("Contact an administrator for help. (Code: SQLException, 1)");
			e1.printStackTrace();
		}
		return userFound;
	}
	
	public String getTableName() {
		switch (userAccessLvl) {
        case 0:
            return "Clients";
        case 1:
            return "Managers";
        case 2:
            return "Administrators";
        default:
            System.out.println("This shouldn't happen. What user access level is this?");
            break;
    }
		return null;
	}

	public boolean passwordMatches(String username, String password) {
	    try (Connection conn = ConnectionDB.connect()) {
	        // Let's determine which table to check:
	        String tableName = getTableName();
	        if(tableName != null) {
	            String query = "SELECT userName FROM inmomanager." + tableName + " WHERE userName = ? AND password = ?";
	            try (PreparedStatement statementConnection = conn.prepareStatement(query)) {
	                statementConnection.setString(1, username);
	                statementConnection.setString(2, password);
	                ResultSet resultSet = statementConnection.executeQuery();
	                return resultSet.next();
	            }
	        }
	    } catch (ClassNotFoundException | SQLException e) {
	        login.getLblError().setText("Contact an administrator for help. (Code: " + e.getClass().getSimpleName() + ", 1)");
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
