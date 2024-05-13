package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import views.GUILogin;
import views.GUIMainUser;

public class ControllerMainUser {
	private GUIMainUser mainUser;

	public ControllerMainUser(GUIMainUser mainUser) {
		this.mainUser = mainUser;
		
		mainUser.addActListeners(new ButtonListeners());
	}
	
	private class ButtonListeners implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			JButton buttonPressed = (JButton) e.getSource();
			if(buttonPressed == mainUser.getBtnReturn()) {
				int option = JOptionPane.showConfirmDialog(mainUser,
						"Are you sure that you want to logout?", "Back to Login",
						JOptionPane.INFORMATION_MESSAGE);
				if(option == JOptionPane.YES_OPTION) {
					mainUser.dispose();
					new GUILogin();
				}
			}
			else if (buttonPressed == mainUser.getBtnUser()) {
				mainUser.dispose();
				//TODO: Add connection to class GUIUserProfile.
			} else if(buttonPressed == mainUser.getBtnSell()) {
				mainUser.dispose();
				//TODO: Add connection to class GUIUserSell.
			} else if(buttonPressed == mainUser.getBtnView()) {
				mainUser.dispose();
				//TODO: Add connection to class GUIUserView.
			}
			
			
		}
		
	}
}
