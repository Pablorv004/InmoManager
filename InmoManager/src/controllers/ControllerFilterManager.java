package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;

import models.Manager;
import util.ConnectionDB;
import views.GUIFilterManager;

public class ControllerFilterManager {
	GUIFilterManager gFilter;

	public ControllerFilterManager(GUIFilterManager gFilter) {
		this.gFilter = gFilter;

		distinguishUser();
		
		gFilter.addActListener(new ActListener());
		gFilter.addItmListener(new ItmListener());
	}

	private class ActListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();

			if (btn == gFilter.getBtnCancel()) {
				gFilter.dispose();
				gFilter.getgManage().setEnabled(true);
			} else if (btn == gFilter.getBtnApply()) {
				
			}
		}
	}
	
	private class ItmListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			JCheckBox cbx = (JCheckBox) e.getSource();
			
			if(cbx == gFilter.getCbxDNI()) {
				
			} else if (cbx == gFilter.getCbxManagerID()) {
				
			}else if (cbx == gFilter.getCbxSalary()) {
				
			}else if (cbx == gFilter.getCbxCommission()) {
				
			}
		}
	}
	
	private void distinguishUser() {
		if(ConnectionDB.getCurrentUser() instanceof Manager) {
			gFilter.getCbxManagerID().setEnabled(false);
			gFilter.getFieldManagerID().setEditable(false);
		}
	}
}
