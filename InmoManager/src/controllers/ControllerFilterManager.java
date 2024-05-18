package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;

import models.Admin;
import models.Manager;
import util.ConnectionDB;
import views.GUIFilterManager;

public class ControllerFilterManager {
	GUIFilterManager gFilter;

	public ControllerFilterManager(GUIFilterManager gFilter) {
		this.gFilter = gFilter;

		distinguishUser();

		// Enable gManage when "X" window button is clicked
		gFilter.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				gFilter.getgManage().setEnabled(true);
			}
		});

		gFilter.addActListener(new ActListener());
		gFilter.addItmListener(new ItmListener());
	}

	private class ActListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();

			if (btn == gFilter.getBtnCancel()) {
				gFilter.getgManage().setEnabled(true);
				gFilter.dispose();
			} else if (btn == gFilter.getBtnApply()) {

			}
		}
	}

	private class ItmListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			JCheckBox cbx = (JCheckBox) e.getSource();

			if (cbx == gFilter.getCbxDNI()) {
				if (gFilter.getCbxDNI().isSelected()) {
					toggleComponents(cbx, false);
					gFilter.getFieldDNI().setEditable(true);
				}else {
					toggleComponents(cbx, true);
					gFilter.getFieldDNI().setEditable(false);
				}
			} else if (cbx == gFilter.getCbxManagerID()) {
				if (gFilter.getCbxManagerID().isSelected())
					toggleComponents(cbx, true);
				else
					toggleComponents(cbx, false);
			} else if (cbx == gFilter.getCbxSalary()) {
				if (gFilter.getCbxSalary().isSelected())
					toggleComponents(cbx, true);
				else
					toggleComponents(cbx, false);
			} else if (cbx == gFilter.getCbxCommission()) {
				if (gFilter.getCbxCommission().isSelected())
					toggleComponents(cbx, true);
				else
					toggleComponents(cbx, false);
			}
		}
	}

	private void distinguishUser() {
		if (ConnectionDB.getCurrentUser() instanceof Manager) {
			gFilter.getCbxManagerID().setEnabled(false);
		}
	}

	private void toggleComponents(JCheckBox cbx, boolean onOff) {
		if (cbx == gFilter.getCbxDNI()) {
			if(ConnectionDB.getCurrentUser() instanceof Admin)
				gFilter.getCbxManagerID().setEnabled(onOff);
			gFilter.getCbxSalary().setEnabled(onOff);
			gFilter.getCbxCommission().setEnabled(onOff);
		} else if (cbx == gFilter.getCbxManagerID()) {
			gFilter.getFieldManagerID().setEditable(onOff);
		} else if (cbx == gFilter.getCbxSalary()) {
			gFilter.getFieldMinSalary().setEditable(onOff);
			gFilter.getFieldMaxSalary().setEditable(onOff);
		} else if (cbx == gFilter.getCbxCommission()) {
			gFilter.getFieldMinCom().setEditable(onOff);
			gFilter.getFieldMaxCom().setEditable(onOff);
		}
	}
}
