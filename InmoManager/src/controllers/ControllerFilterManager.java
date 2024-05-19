package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import models.Admin;
import models.Manager;
import util.ConnectionDB;
import util.FieldUtils;
import views.GUIFilterManager;

public class ControllerFilterManager {
	GUIFilterManager gFilter;

	public ControllerFilterManager(GUIFilterManager gFilter) {
		this.gFilter = gFilter;

		distinguishUser();

		// Enable gManage when "X" window button is clicked
		gFilter.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				gFilter.getgManageController().getgManage().setEnabled(true);
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
				gFilter.getgManageController().getgManage().setEnabled(true);
				gFilter.dispose();
			} else if (btn == gFilter.getBtnApply()) {
				checkSelected();
			} else if (btn == gFilter.getBtnReset()) {
				int option = JOptionPane.showConfirmDialog(gFilter, "Are you sure you want to reset all?", "Warning",
						JOptionPane.INFORMATION_MESSAGE);
				if (option == JOptionPane.OK_OPTION)
					resetAll();
			}
		}

		private void checkSelected() {
			if (gFilter.getCbxDNI().isSelected()) {
				filterByDNI();
			} else {
				boolean managerIDSelected = gFilter.getCbxManagerID().isSelected();
				boolean salarySelected = gFilter.getCbxSalary().isSelected();
				boolean commissionSelected = gFilter.getCbxCommission().isSelected();
			}
		}

		private void filterByDNI() {
			String DNI = gFilter.getFieldDNI().getText().strip();
			if (FieldUtils.validateDNI(DNI, gFilter)) {
				List<Manager> filteredList = new ArrayList<>();
				for (Manager m : gFilter.getgManageController().getManagerList()) {
					if (m.getDNI().equalsIgnoreCase(DNI))
						filteredList.add(m);
				}
				gFilter.getgManageController().setManagerList(filteredList);
				gFilter.getgManageController().updateTable();
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
					if (ConnectionDB.getCurrentUser() instanceof Admin) {
						gFilter.getCbxManagerID().setSelected(false);
					}
					gFilter.getCbxSalary().setSelected(false);
					gFilter.getCbxCommission().setSelected(false);
				} else {
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
			if (ConnectionDB.getCurrentUser() instanceof Admin)
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

	private void resetAll() {
		gFilter.getCbxDNI().setSelected(false);
		gFilter.getFieldDNI().setText("");
		gFilter.getCbxManagerID().setSelected(false);
		gFilter.getFieldManagerID().setText("");
		gFilter.getCbxSalary().setSelected(false);
		gFilter.getFieldMinSalary().setText("");
		gFilter.getFieldMaxSalary().setText("");
		gFilter.getCbxCommission().setSelected(false);
		gFilter.getFieldMinCom().setText("");
		gFilter.getFieldMaxCom().setText("");
	}
}
