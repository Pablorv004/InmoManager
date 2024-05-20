package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import models.Admin;
import models.Manager;
import models.Property;
import models.Purchasable_Property;
import models.Rentable_Property;
import models.User;
import util.ConnectionDB;
import util.ManageDatabase;
import views.GUIMainAdmin;
import views.GUIMainManager;
import views.GUIManageProperties;
import views.GUIPropertyFilter;

public class ControllerManageProperties {
	private GUIManageProperties gProperties;
	private User currentUser;
	private List<Property> propertyList;
	private List<JCheckBox> extraList;

	public ControllerManageProperties(GUIManageProperties gProperties) {
		this.gProperties = gProperties;
		this.currentUser = ConnectionDB.getCurrentUser();
		this.propertyList = ManageDatabase.getProperties(true, true, new String[] {});
		this.extraList = getExtraCheckboxes();
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
				new GUIPropertyFilter(gProperties);
			} else if (obj == gProperties.getBtnReset()) {
				propertyList = ManageDatabase.getProperties(true, true, new String[] {});
				updateTable();
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
	
	public void applyFilters(boolean checkRentable, boolean checkPurchasable, String... filters) {
		propertyList = ManageDatabase.getProperties(checkRentable,
				checkPurchasable, filters);
		updateTable();
	}

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

	// Enables / Disables components depending of the current user
	private void enableDisableComponents(boolean onOff) {
		if (currentUser instanceof Admin) {
//			for (JTextField textField : gProperties.getTextFieldList()) {
//				
//			}
		}else if (currentUser instanceof Manager) {
			
		}
	}

	// Fills JTextFields with the property information
	private void fillFields(Property property) {
		
		
		gProperties.getFieldID().setText(String.valueOf(property.getId()));
		gProperties.getFieldAddress().setText(property.getAddress());
		gProperties.getFieldCity().setText(property.getCity());
		gProperties.getFieldType().setText(property.getType());
		gProperties.getFieldAge().setText(String.valueOf(property.getAge()));
		gProperties.getFieldRooms().setText(String.valueOf(property.getRooms()));
		gProperties.getFieldFloors().setText(String.valueOf(property.getFloors()));
		gProperties.getFieldBathrooms().setText(String.valueOf(property.getBathrooms()));
		gProperties.getFieldPropertySize().setText(String.valueOf(property.getPropertySize()));
		gProperties.getFieldTerrainSize().setText(String.valueOf(property.getTerrainSize()));
		gProperties.getFieldGarageSize().setText(String.valueOf(property.getGarageSize()));
		gProperties.getFieldStatus().setText(property.getStatus());
		
		setCheckboxes(property);
	}

	// Gets all the JCheckboxes from the "Extras" panel and adds it to a list, then returns it
	private List<JCheckBox> getExtraCheckboxes() {
		List<JCheckBox> cbxList = new ArrayList<>();
		cbxList.add(gProperties.getCbxAC());
		cbxList.add(gProperties.getCbxPool());
		cbxList.add(gProperties.getCbxBasement());
		cbxList.add(gProperties.getCbxLift());
		cbxList.add(gProperties.getCbxGarage());
		cbxList.add(gProperties.getCbxTerrace());
		cbxList.add(gProperties.getCbxGarden());
		
		return cbxList;
	}
	
	// Sets the selections of the "Extras" panel JCheckboxes depending of the property
	private void setCheckboxes(Property property) {
	    setCheckbox(gProperties.getCbxGarden(), property.isHasGarden());
	    setCheckbox(gProperties.getCbxPool(), property.isHasPool());
	    setCheckbox(gProperties.getCbxBasement(), property.isHasBasement());
	    setCheckbox(gProperties.getCbxLift(), property.isHasLift());
	    setCheckbox(gProperties.getCbxGarage(), property.isHasGarage());
	    setCheckbox(gProperties.getCbxTerrace(), property.isHasTerrace());
	    setCheckbox(gProperties.getCbxAC(), property.isHasAC());
	    setCheckbox(gProperties.getCbxAvailable(), property.isAvailable());

	    if (property instanceof Rentable_Property) {
	        gProperties.getCbxRentable().setSelected(true);
	        gProperties.getCbxPurchasable().setSelected(false);
	        gProperties.getFieldPrice().setText(String.valueOf(((Rentable_Property) property).getRentValue()));
	    } else if (property instanceof Purchasable_Property) {
	        gProperties.getCbxPurchasable().setSelected(true);
	        gProperties.getCbxRentable().setSelected(false);
	        gProperties.getFieldPrice().setText(String.valueOf(((Purchasable_Property) property).getTotalValue()));
	    }
	}

	private void setCheckbox(JCheckBox checkBox, boolean value) {
	    checkBox.setSelected(value);
	}
}
