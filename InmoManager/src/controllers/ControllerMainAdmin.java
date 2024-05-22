package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import views.GUIFreeChart;
import views.GUILogin;
import views.GUIMainAdmin;
import views.GUIManageClients;
import views.GUIManageManagers;
import views.GUIManageProperties;

// Class to handle actions in the main admin GUI
public class ControllerMainAdmin {
	GUIMainAdmin gAdmin;

	public ControllerMainAdmin(GUIMainAdmin gAdmin) {
		this.gAdmin = gAdmin;

		gAdmin.addActListeners(new ActListener());
	}

	private class ActListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();

			if (btn == gAdmin.getBtnReturn()) {
				int option = JOptionPane.showConfirmDialog(gAdmin,
						"Are you sure that you want to return to login window?", "Confirmation message",
						JOptionPane.INFORMATION_MESSAGE);
				if (option == JOptionPane.YES_OPTION) {
					gAdmin.dispose();
					new GUILogin();
				}
			} else if (btn == gAdmin.getBtnManageManagers()) {
				gAdmin.dispose();
				new GUIManageManagers(gAdmin.getgLogin());
			} else if (btn == gAdmin.getBtnManageProperties()) {
				gAdmin.dispose();
				new GUIManageProperties(gAdmin.getgLogin());
			} else if (btn == gAdmin.getBtnManageClients()) {
				gAdmin.dispose();
				new GUIManageClients(gAdmin.getgLogin());
			} else if (btn == gAdmin.getBtnStatistics()) {
				new GUIFreeChart(gAdmin);
				gAdmin.dispose();
			}
		}

	}
}
