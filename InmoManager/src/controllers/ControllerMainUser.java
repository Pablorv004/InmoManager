package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import views.GUIAddProperty;
import views.GUILogin;
import views.GUIMainUser;
import views.GUIStopRent;
import views.GUIUserProfile;
import views.GUIUserView;

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
				new GUIUserProfile(mainUser);
			} else if(buttonPressed == mainUser.getBtnAdd()) {
				mainUser.dispose();
				new GUIAddProperty(mainUser);
			} else if(buttonPressed == mainUser.getBtnView()) {
				mainUser.dispose();
				new GUIUserView(mainUser);
			} else if (buttonPressed == mainUser.getBtnStopRent()) {
				mainUser.dispose();
				new GUIStopRent(mainUser);
			}
			
			
		}
		
	}
}
