package controllers;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
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
import util.FieldUtils;
import util.ManageDatabase;
import views.GUIAddProperty;
import views.GUIMainAdmin;
import views.GUIMainManager;
import views.GUIManageProperties;
import views.GUIPropertyFilter;

public class ControllerManageProperties {
	private GUIManageProperties gProperties;
	private User currentUser;
	private List<Property> propertyList;
	private String ID;
	private String address;
	private String city;
	private String type;
	private String rooms;
	private String floors;
	private String bathrooms;
	private String propertySize;
	private String terrainSize;
	private String garageSize;
	private String age;
	private String price;
	private String status;

	public ControllerManageProperties(GUIManageProperties gProperties) {
		this.gProperties = gProperties;
		this.currentUser = ConnectionDB.getCurrentUser();
		this.propertyList = ManageDatabase.getProperties(true, true, new String[] {});
		updateTable();
		fillComboType();
		
		if(currentUser instanceof Admin)
			gProperties.getBtnEdit().setEnabled(true);
		

		gProperties.addActListener(new ActListener());
	}

	private class ActListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();

			if (obj == gProperties.getBtnReturn()) {
				handleBtnReturn();
			} else if (obj == gProperties.getBtnEdit()) {
				handleEditBtn();
			} else if (obj == gProperties.getBtnApply()) {
				handleApplyBtn();
			} else if (obj == gProperties.getBtnFilter()) {
				gProperties.setEnabled(false);
				setEnabledAll(gProperties.getPanelForm(),false);
				gProperties.getBtnDelete().setEnabled(false);
				new GUIPropertyFilter(gProperties);
			} else if (obj == gProperties.getBtnReset()) {
				setEnabledAll(gProperties.getPanelForm(),false);
				propertyList = ManageDatabase.getProperties(true, true, new String[] {});
				updateTable();
			} else if (obj == gProperties.getBtnAdd()) {
				setEnabledAll(gProperties.getPanelForm(),false);
				new GUIAddProperty(gProperties);
			} else if (obj == gProperties.getBtnDelete()) {
				setEnabledAll(gProperties.getPanelForm(),false);
				handleDeleteBtn();
			}
		}

		private void handleApplyBtn() {
			if(checkFields())
				applyChanges();
		}

		private void handleEditBtn() {
			if(gProperties.getTable().getSelectedRow() != -1) {
				setEnabledAll(gProperties.getPanelForm(), true);
				gProperties.getBtnApply().setEnabled(true);
			}else
				JOptionPane.showMessageDialog(gProperties, "There's no property selected","Error",JOptionPane.ERROR_MESSAGE);
				
			gProperties.getBtnDelete().setEnabled(false);
		}

		private void handleDeleteBtn() {
			int selectedRow = gProperties.getTable().getSelectedRow();
			if (selectedRow != -1) {
				int election = JOptionPane.showConfirmDialog(gProperties,
						"Are you sure you want to delete this property? (Address: "
								+ gProperties.getTable().getValueAt(selectedRow, 0) + ")",
						"Confirmation", JOptionPane.INFORMATION_MESSAGE);
				if (election == JOptionPane.OK_OPTION) {
					deleteProperty();
					gProperties.getBtnDelete().setEnabled(false);
				}
			} else
				JOptionPane.showMessageDialog(gProperties, "There's no property selected", "Error",
						JOptionPane.ERROR_MESSAGE);
		}
		
		private void handleBtnReturn() {
			gProperties.dispose();
			if (currentUser instanceof Manager)
				new GUIMainManager(gProperties.getgLogin());
			else if (currentUser instanceof Admin)
				new GUIMainAdmin(gProperties.getgLogin());
		}
	}

	private class TableListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			// Disables editable components each time a row is clicked
			setEnabledAll(gProperties.getPanelForm(), false);
			
			gProperties.getBtnDelete().setEnabled(true);
			
			gProperties.getBtnEdit().setEnabled(true);
			int selectedRow = gProperties.getTable().getSelectedRow();
			Property property = findProperty(String.valueOf(gProperties.getTable().getValueAt(selectedRow, 0)));
			if (property != null)
				fillFields(property);
		}

	}

	// METHODS

	private Property findProperty(String address) {
		for (Property p : propertyList)
			if (p.getAddress().equalsIgnoreCase(address))
				return p;

		return null;
	}
	private void applyChanges() {
		boolean hasGarden = gProperties.getCbxGarden().isSelected();
		boolean hasBasement = gProperties.getCbxBasement().isSelected();
		boolean hasGarage = gProperties.getCbxGarage().isSelected();
		boolean hasPool = gProperties.getCbxPool().isSelected();
		boolean hasLift = gProperties.getCbxLift().isSelected();
		boolean hasTerrace = gProperties.getCbxTerrace().isSelected();
		boolean hasAC = gProperties.getCbxAC().isSelected();
		boolean isAvailable = gProperties.getCbxAvailable().isSelected();
		boolean isPurchasable = gProperties.getCbxPurchasable().isSelected();
		boolean isRentable = gProperties.getCbxRentable().isSelected();
		int selectedRow = gProperties.getTable().getSelectedRow();

		// Old property obtained from the list to know whìch type of property class it is
		Property oldProperty = findProperty(String.valueOf(gProperties.getTable().getValueAt(selectedRow,0)));

		if(isPurchasable && oldProperty instanceof Purchasable_Property){
			Property newPurchasableProperty = new Purchasable_Property(Integer.parseInt(ID), address, city, type, Integer.parseInt(age), Integer.parseInt(rooms), Integer.parseInt(floors), Integer.parseInt(bathrooms), Integer.parseInt(propertySize), Integer.parseInt(terrainSize), Integer.parseInt(garageSize), hasGarden, hasBasement, hasGarage, hasPool, hasLift, hasTerrace, hasAC, isAvailable, status, Integer.parseInt(price));
			ManageDatabase.updateProperty(oldProperty, newPurchasableProperty, gProperties);
		} else if(isPurchasable && oldProperty instanceof Rentable_Property) {
			Property newPurchasableProperty = new Purchasable_Property(Integer.parseInt(ID), address, city, type, Integer.parseInt(age), Integer.parseInt(rooms), Integer.parseInt(floors), Integer.parseInt(bathrooms), Integer.parseInt(propertySize), Integer.parseInt(terrainSize), Integer.parseInt(garageSize), hasGarden, hasBasement, hasGarage, hasPool, hasLift, hasTerrace, hasAC, isAvailable, status, Integer.parseInt(price));
			deleteProperty();
			ManageDatabase.addPropertyToDatabase(newPurchasableProperty);
		}else if(isRentable && oldProperty instanceof Purchasable_Property) {
			Property newRentableProperty = new Rentable_Property(Integer.parseInt(ID), address, city, type, Integer.parseInt(age), Integer.parseInt(rooms), Integer.parseInt(floors), Integer.parseInt(bathrooms), Integer.parseInt(propertySize), Integer.parseInt(terrainSize), Integer.parseInt(garageSize), hasGarden, hasBasement, hasGarage, hasPool, hasLift, hasTerrace, hasAC, isAvailable, status, Integer.parseInt(price));
			deleteProperty();
			ManageDatabase.addPropertyToDatabase(newRentableProperty);
		} else if(isRentable && oldProperty instanceof Rentable_Property) {
			Property newRentableProperty = new Rentable_Property(Integer.parseInt(ID), address, city, type, Integer.parseInt(age), Integer.parseInt(rooms), Integer.parseInt(floors), Integer.parseInt(bathrooms), Integer.parseInt(propertySize), Integer.parseInt(terrainSize), Integer.parseInt(garageSize), hasGarden, hasBasement, hasGarage, hasPool, hasLift, hasTerrace, hasAC, isAvailable, status, Integer.parseInt(price));
			ManageDatabase.updateProperty(oldProperty, newRentableProperty, gProperties);
		}

		propertyList = ManageDatabase.getProperties(true,true,new String[]{});
		updateTable();
		setEnabledAll(gProperties.getPanelForm(), false);
	}

	private boolean checkFields() {
		// Property values
		ID = gProperties.getFieldID().getText().strip();
		address = gProperties.getFieldAddress().getText().strip().replaceAll("\\s+"," ");
		city = gProperties.getFieldCity().getText().strip().replaceAll("\\s+"," ");
		type = gProperties.getComboType().getSelectedItem().toString();
		rooms = gProperties.getFieldRooms().getText().strip();
		floors = gProperties.getFieldFloors().getText().strip();
		bathrooms = gProperties.getFieldBathrooms().getText().strip();
		propertySize = gProperties.getFieldPropertySize().getText().strip();
		terrainSize = gProperties.getFieldTerrainSize().getText().strip();
		garageSize = gProperties.getFieldGarageSize().getText().strip();
		age = gProperties.getFieldAge().getText().strip();
		price = gProperties.getFieldPrice().getText().strip();
		status = gProperties.getFieldStatus().getText().strip().replaceAll("\\s+"," ");
		int hasGarage = gProperties.getCbxGarage().isSelected()? 1 : 0 ;

		// Validations
		boolean validID = FieldUtils.validatePropertyID(ID,gProperties,address);
		boolean validAddress = FieldUtils.validateAddress(address,gProperties,ID);
		boolean validCity = FieldUtils.validateCity(city,gProperties);
		boolean validAge = FieldUtils.validateAge(age,gProperties);
		boolean validRooms = FieldUtils.validateRooms(rooms,gProperties);
		boolean validFloors = FieldUtils.validateFloors(floors,gProperties);
		boolean validBathrooms = FieldUtils.validateBathrooms(bathrooms,gProperties);
		boolean validPSize = FieldUtils.validatePropertySize(propertySize,gProperties);
		boolean validTSize = FieldUtils.validateTerrainSize(terrainSize,gProperties,type);
		boolean validGSize = FieldUtils.validateGarageSize(garageSize,gProperties,hasGarage);
		boolean validPrice = FieldUtils.validatePrice(price,gProperties);

		if(validID && validAddress && validCity && validAge && validRooms && validFloors && validBathrooms && validPSize && validTSize && validGSize && validPrice)
			return true;

		return false;
	}
	private void fillComboType() {
		gProperties.getComboType().addItem("Flat");
		gProperties.getComboType().addItem("Apartment");
		gProperties.getComboType().addItem("Detached House");
	}

	private void deleteProperty() {
		Property property = propertyList.get(gProperties.getTable().getSelectedRow());
		
		try {
			String statement = "DELETE FROM ";
			if(property instanceof Purchasable_Property)
				statement += "inmomanager.Purchasable_Properties WHERE id = ? AND address = ?";
			else if (property instanceof Rentable_Property)
				statement += "inmomanager.Rentable_Properties WHERE id = ? AND address = ?";
			
			Connection conn = ConnectionDB.connect();
			PreparedStatement pst = conn.prepareStatement(statement);
			pst.setInt(1, property.getId());
			pst.setString(2, property.getAddress());
			int modified = pst.executeUpdate();
			
			if(modified == 0)
				JOptionPane.showMessageDialog(gProperties, "There were no properties found","Delete failure",JOptionPane.ERROR_MESSAGE);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		propertyList = ManageDatabase.getProperties(true, true, new String[] {});
		updateTable();
	}

	public void applyFilters(boolean checkRentable, boolean checkPurchasable, String... filters) {
		propertyList = ManageDatabase.getProperties(checkRentable,
				checkPurchasable, filters);
		gProperties.setEnabled(true);
		updateTable();
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
	private void setEnabledAll(Container container, boolean onOff) {
		if (currentUser instanceof Admin) {
			Component[] components = container.getComponents();
	        for (Component component : components) {
	            component.setEnabled(onOff);
	            if(component instanceof JTextField) {
					if(component != gProperties.getFieldID())
						((JTextField) component).setEditable(onOff);
				}if (component instanceof Container)
	            	setEnabledAll((Container) component, onOff);
	        }
		}
	}


	// Fills JTextFields with the property information
	private void fillFields(Property property) {
		gProperties.getFieldID().setText(String.valueOf(property.getId()));
		gProperties.getFieldAddress().setText(property.getAddress());
		gProperties.getFieldCity().setText(property.getCity());
		gProperties.getComboType().setSelectedItem(property.getType());
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
