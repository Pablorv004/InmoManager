package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import models.Admin;
import models.Manager;
import models.Property;
import models.User;
import util.ConnectionDB;
import util.ManageDatabase;
import views.GUIMainAdmin;
import views.GUIMainManager;
import views.GUIManageProperties;

public class ControllerManageProperties {
	private GUIManageProperties gProperties;
	private User currentUser;
	private List<Property> propertyList;

	public ControllerManageProperties(GUIManageProperties gProperties) {
		this.gProperties = gProperties;
		this.currentUser = ConnectionDB.getCurrentUser();
		this.propertyList = ManageDatabase.getProperties(true, true, new String[] {});
		updateTable();

		gProperties.addActListener(new ActListener());
	}

	private class ActListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();

			if (obj == gProperties.getBtnReturn()) {
				handleBtnReturn();
			} else if (obj == gProperties.getBtnEdit()) {

			} else if (obj == gProperties.getBtnApply()) {

			} else if (obj == gProperties.getBtnFilter()) {

			} else if (obj == gProperties.getBtnReset()) {

			}
		}
	}

	private class TableListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			// Disables editable components each time a row is clicked
			enableDisableComponents(false);
			
			gProperties.getBtnEdit().setEnabled(true);
			int selectedRow = gProperties.getTable().getSelectedRow();
			Property property = findProperty(String.valueOf(gProperties.getTable().getValueAt(selectedRow, 0)));
			if (property != null)
				fillFields(property);
		}

		private Property findProperty(String address) {
			for (Property p : propertyList)
				if (p.getAddress().equalsIgnoreCase(address))
					return p;

			return null;
		}

	}

	// METHODS

	private void handleBtnReturn() {
		gProperties.dispose();
		if (currentUser instanceof Manager)
			new GUIMainManager(gProperties.getgLogin());
		else if (currentUser instanceof Admin)
			new GUIMainAdmin(gProperties.getgLogin());
	}

	// Updates table with the information about the properties
	public void updateTable() {
		String[] headers = {"Address", "City", "Type", "Age","Rooms","Floors"};
		DefaultTableModel model = new DefaultTableModel(toDataList(), headers) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JTable table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.gProperties.setTable(table);
		this.gProperties.addTableListener(new TableListener());
	}

	private Object[][] toDataList() {
		Object[][] datos = new Object[propertyList.size()][];
		for (int i = 0; i < propertyList.size(); i++) {
			datos[i] = propertyList.get(i).toArray();
		}
		return datos;
	}

	private void enableDisableComponents(boolean b) {
	}

	// Fills JTextFields with the property information
	private void fillFields(Property property) {
		gProperties.getFieldID().setText(String.valueOf(property.getId()));
	}
}
