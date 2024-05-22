package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import views.GUILogin;
import views.GUIMainManager;
import views.GUIManageManagers;
import views.GUIManageProperties;
// Class to handle actions in the main manager GUI
public class ControllerMainManager {
	GUIMainManager gManager;

	public ControllerMainManager(GUIMainManager gManager) {
		this.gManager = gManager;

		gManager.addActListeners(new ActListener());
	}

	private class ActListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();

			if (btn == gManager.getBtnReturn()) {
				int option = JOptionPane.showConfirmDialog(gManager,
						"Are you sure that you want to return to login window?", "Confirmation message",
						JOptionPane.INFORMATION_MESSAGE);
				if (option == JOptionPane.YES_OPTION) {
					gManager.dispose();
					new GUILogin();
				}
			} else if (btn == gManager.getBtnManageManagers()) {
				gManager.dispose();
				new GUIManageManagers(gManager.getgLogin());
			} else if (btn == gManager.getBtnManageProperties()) {
				gManager.dispose();
				new GUIManageProperties(gManager.getgLogin());
			}
		}

	}
}
