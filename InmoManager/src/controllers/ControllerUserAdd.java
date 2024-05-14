package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import models.Property;
import models.Purchasable_Property;
import models.Rentable_Property;
import util.ConnectionDB;
import views.GUIMainUser;
import views.GUIUserAdd;

public class ControllerUserAdd {
	private GUIUserAdd userAdd;

	public ControllerUserAdd(GUIUserAdd userAdd) {
		this.userAdd = userAdd;
		userAdd.addActListeners(new ButtonListeners());
		userAdd.addChangeListeners(new SliderListener());
		userAdd.addRadioButtonListeners(new RadioButtonListeners());
		userAdd.addCheckBoxListeners(new CheckBoxListeners());
	}

	public boolean isNotValid() {
		return (userAdd.getTxtAddress().getText().isBlank() || userAdd.getTxtCity().getText().isBlank()
				|| userAdd.getTxtPropertySize().getText().isBlank() || userAdd.getTxtValue().getText().isBlank()
				|| userAdd.getTxtStatus().getText().isBlank()
				|| (userAdd.getCbxGarage().isSelected() && userAdd.getTxtGarage().getText().isBlank())
				|| (!userAdd.getRdBtnRent().isSelected() && !userAdd.getRdBtnSell().isSelected()));
	}

	public Property getPropertyData() {
		int terrainsize;
		if (userAdd.getTxtTerrain().getText().isBlank())
			terrainsize = 0;
		else
			terrainsize = Integer.parseInt(userAdd.getTxtTerrain().getText());
		int garagesize;
		if (userAdd.getCbxGarage().isSelected())
			garagesize = Integer.parseInt(userAdd.getTxtGarage().getText());
		else
			garagesize = 0;
		if (userAdd.getRdBtnRent().isSelected())
			return new Rentable_Property(userAdd.getTxtAddress().getText(), userAdd.getTxtCity().getText(),
					userAdd.getcBType().getSelectedItem().toString(), Integer.parseInt(userAdd.getTxtAge().getText()),
					Integer.parseInt(userAdd.getcBRooms().getSelectedItem().toString()),
					userAdd.getSliderFloors().getValue(),
					Integer.parseInt(userAdd.getcBBathrooms().getSelectedItem().toString()),
					Integer.parseInt(userAdd.getTxtPropertySize().getText()),
					terrainsize, garagesize,
					userAdd.getCbxGarden().isSelected(), userAdd.getCbxBasement().isSelected(),
					userAdd.getCbxGarage().isSelected(),
					userAdd.getCbxPool().isSelected(), userAdd.getCbxLift().isSelected(),
					userAdd.getCbxTerrace().isSelected(),
					userAdd.getCbxAC().isSelected(), true,
					userAdd.getTxtStatus().getText(), Integer.parseInt(userAdd.getTxtValue().getText()));
		else
			return new Purchasable_Property(userAdd.getTxtAddress().getText(), userAdd.getTxtCity().getText(),
					userAdd.getcBType().getSelectedItem().toString(), Integer.parseInt(userAdd.getTxtAge().getText()),
					Integer.parseInt(userAdd.getcBRooms().getSelectedItem().toString()),
					userAdd.getSliderFloors().getValue(),
					Integer.parseInt(userAdd.getcBBathrooms().getSelectedItem().toString()),
					Integer.parseInt(userAdd.getTxtPropertySize().getText()),
					terrainsize, garagesize,
					userAdd.getCbxGarden().isSelected(), userAdd.getCbxBasement().isSelected(),
					userAdd.getCbxGarage().isSelected(),
					userAdd.getCbxPool().isSelected(), userAdd.getCbxLift().isSelected(),
					userAdd.getCbxTerrace().isSelected(),
					userAdd.getCbxAC().isSelected(), true,
					userAdd.getTxtStatus().getText(), Integer.parseInt(userAdd.getTxtValue().getText()));

	}

	public static void addPropertyToDatabase(Property property) {
		int hasBasement = property.isHasBasement() ? 1 : 0;
		int hasGarage = property.isHasGarage() ? 1 : 0;
		int hasPool = property.isHasPool() ? 1 : 0;
		int hasAC = property.isHasAC() ? 1 : 0;
		int hasTerrace = property.isHasTerrace() ? 1 : 0;
		int hasGarden = property.isHasGarden() ? 1 : 0;
		int hasLift = property.isHasLift() ? 1 : 0;
		try {
			Connection conn = ConnectionDB.connect();
			PreparedStatement pst = null;
			if (property instanceof Rentable_Property)
				pst = conn.prepareStatement(
						"INSERT INTO inmomanager.Rentable_Properties (address, city, type, age, rooms, floors, bathrooms, propertySize, terrainSize, garageSize, hasGarden, hasBasement, hasGarage, hasPool, hasLift, hasTerrace, hasAC, available, status, rentValue) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			else
				pst = conn.prepareStatement(
						"INSERT INTO inmomanager.Purchasable_Properties (address, city, type, age, rooms, floors, bathrooms, propertySize, terrainSize, garageSize, hasGarden, hasBasement, hasGarage, hasPool, hasLift, hasTerrace, hasAC, available, status, totalValue) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pst.setString(1, property.getAddress());
			pst.setString(2, property.getCity());
			pst.setString(3, property.getType());
			pst.setInt(4, property.getAge());
			pst.setInt(5, property.getRooms());
			pst.setInt(6, property.getFloors());
			pst.setInt(7, property.getBathrooms());
			pst.setInt(8, property.getPropertySize());
			if (property.getTerrainSize() == 0)
				pst.setNull(9, Types.INTEGER); //sets null
			else
				pst.setInt(9, property.getTerrainSize());
			if (property.getGarageSize() == 0)
				pst.setNull(10, Types.INTEGER); //sets null
			else
				pst.setDouble(10, property.getGarageSize());
			pst.setInt(11, hasGarden);
			pst.setInt(12, hasBasement);
			pst.setInt(13, hasGarage);
			pst.setInt(14, hasPool);
			pst.setInt(15, hasLift);
			pst.setInt(16, hasTerrace);
			pst.setInt(17, hasAC);
			pst.setInt(18, 1);
			pst.setString(19, property.getStatus());
			if(property instanceof Rentable_Property)
			pst.setInt(20, ((Rentable_Property) property).getRentValue());
			else
			pst.setInt(20, ((Purchasable_Property) property).getTotalValue());
			pst.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private class RadioButtonListeners implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			JRadioButton rdBtnPressed = (JRadioButton) e.getSource();
			if (rdBtnPressed == userAdd.getRdBtnRent() && rdBtnPressed.isSelected()) {
				userAdd.getLblValue().setText("Rent value per month:");
				userAdd.getLblType().setText("Select the type of housing you'd like to rent:");
			} else if (rdBtnPressed == userAdd.getRdBtnSell() && rdBtnPressed.isSelected()) {
				userAdd.getLblValue().setText("Total Value:");
				userAdd.getLblType().setText("Select the type of housing you'd like to sell:");
			}
		}

	}

	private class CheckBoxListeners implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			JCheckBox cbxPressed = (JCheckBox) e.getSource();
			if (!cbxPressed.isSelected())
				userAdd.getTxtGarage().setEnabled(false);
			else
				userAdd.getTxtGarage().setEnabled(true);
		}
	}

	private class SliderListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			userAdd.getLblFloors().setText("Floors: " + userAdd.getSliderFloors().getValue());
		}
	}

	private class ButtonListeners implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton buttonPressed = (JButton) e.getSource();
			if (buttonPressed == userAdd.getBtnCancel()) {
				userAdd.dispose();
				new GUIMainUser(null);
			} else if (buttonPressed == userAdd.getBtnAdd()) {
				if (isNotValid()) {
					JOptionPane.showMessageDialog(userAdd, "Please fill all the required fields.", "Fill",
							JOptionPane.WARNING_MESSAGE);
				} else {
					addPropertyToDatabase(getPropertyData());
					JOptionPane.showMessageDialog(userAdd, "User added!", "Success", JOptionPane.INFORMATION_MESSAGE);
					userAdd.dispose();
					new GUIMainUser(null);
				}
			}
		}
	}
}