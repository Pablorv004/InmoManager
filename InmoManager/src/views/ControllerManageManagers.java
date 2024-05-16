package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerManageManagers {
	GUIManageManagers gManageManagers;
	public ControllerManageManagers(GUIManageManagers gManageManagers) {
		this.gManageManagers = gManageManagers;
		
		gManageManagers.addActListener(new ActListener());
	}
	
	private class ActListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			
			if(obj == gManageManagers.getBtnReturn()) {
				gManageManagers.dispose();
				new GUIMainManager(gManageManagers.getgManager().getgLogin());
			}
		}
		
	}
}
