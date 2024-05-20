package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import models.Property;
import models.Purchasable_Property;
import models.Rentable_Property;
import util.ManageDatabase;
import views.GUIMainUser;
import views.GUIManageProperties;
import views.GUIAddProperty;

public class ControllerAddProperty {
	private GUIAddProperty addProperty;

	public ControllerAddProperty(GUIAddProperty addProperty) {
		this.addProperty = addProperty;
		
		addProperty.addActListeners(new ButtonListeners());
		addProperty.addChangeListeners(new SliderListener());
		addProperty.addRadioButtonListeners(new RadioButtonListeners());
		addProperty.addCheckBoxListeners(new CheckBoxListeners());
	}

	public boolean isNotValid() {
		return (addProperty.getTxtAddress().getText().isBlank() || addProperty.getTxtCity().getText().isBlank()
				|| addProperty.getTxtPropertySize().getText().isBlank() || addProperty.getTxtValue().getText().isBlank()
				|| addProperty.getTxtStatus().getText().isBlank()
				|| (addProperty.getCbxGarage().isSelected() && addProperty.getTxtGarage().getText().isBlank())
				|| (!addProperty.getRdBtnRent().isSelected() && !addProperty.getRdBtnSell().isSelected()));
	}

	public Property getPropertyData() {
		try {
			int terrainsize;
			if (addProperty.getTxtTerrain().getText().isBlank())
				terrainsize = 0;
			else
				terrainsize = Integer.parseInt(addProperty.getTxtTerrain().getText());
			int garagesize;
			if (addProperty.getCbxGarage().isSelected())
				garagesize = Integer.parseInt(addProperty.getTxtGarage().getText());
			else
				garagesize = 0;
			if (addProperty.getRdBtnRent().isSelected())
				return new Rentable_Property(0, addProperty.getTxtAddress().getText(), addProperty.getTxtCity().getText(),
						addProperty.getcBType().getSelectedItem().toString(),
						Integer.parseInt(addProperty.getTxtAge().getText()),
						Integer.parseInt(addProperty.getcBRooms().getSelectedItem().toString()),
						addProperty.getSliderFloors().getValue(),
						Integer.parseInt(addProperty.getcBBathrooms().getSelectedItem().toString()),
						Integer.parseInt(addProperty.getTxtPropertySize().getText()),
						terrainsize, garagesize,
						addProperty.getCbxGarden().isSelected(), addProperty.getCbxBasement().isSelected(),
						addProperty.getCbxGarage().isSelected(),
						addProperty.getCbxPool().isSelected(), addProperty.getCbxLift().isSelected(),
						addProperty.getCbxTerrace().isSelected(),
						addProperty.getCbxAC().isSelected(), true,
						addProperty.getTxtStatus().getText(), Integer.parseInt(addProperty.getTxtValue().getText()));
			else
				return new Purchasable_Property(0, addProperty.getTxtAddress().getText(), addProperty.getTxtCity().getText(),
						addProperty.getcBType().getSelectedItem().toString(),
						Integer.parseInt(addProperty.getTxtAge().getText()),
						Integer.parseInt(addProperty.getcBRooms().getSelectedItem().toString()),
						addProperty.getSliderFloors().getValue(),
						Integer.parseInt(addProperty.getcBBathrooms().getSelectedItem().toString()),
						Integer.parseInt(addProperty.getTxtPropertySize().getText()),
						terrainsize, garagesize,
						addProperty.getCbxGarden().isSelected(), addProperty.getCbxBasement().isSelected(),
						addProperty.getCbxGarage().isSelected(),
						addProperty.getCbxPool().isSelected(), addProperty.getCbxLift().isSelected(),
						addProperty.getCbxTerrace().isSelected(),
						addProperty.getCbxAC().isSelected(), true,
						addProperty.getTxtStatus().getText(), Integer.parseInt(addProperty.getTxtValue().getText()));
		} catch (NumberFormatException e) {
			throw e;
		}
	}

	private class RadioButtonListeners implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			JRadioButton rdBtnPressed = (JRadioButton) e.getSource();
			if (rdBtnPressed == addProperty.getRdBtnRent() && rdBtnPressed.isSelected()) {
				addProperty.getLblValue().setText("Rent value per month:");
				addProperty.getLblType().setText("Select the type of housing you'd like to rent:");
			} else if (rdBtnPressed == addProperty.getRdBtnSell() && rdBtnPressed.isSelected()) {
				addProperty.getLblValue().setText("Total Value:");
				addProperty.getLblType().setText("Select the type of housing you'd like to sell:");
			}
		}

	}

	private class CheckBoxListeners implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			JCheckBox cbxPressed = (JCheckBox) e.getSource();
			if (!cbxPressed.isSelected())
				addProperty.getTxtGarage().setEnabled(false);
			else
				addProperty.getTxtGarage().setEnabled(true);
		}
	}

	private class SliderListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			addProperty.getLblFloors().setText("Floors: " + addProperty.getSliderFloors().getValue());
		}
	}

	private class ButtonListeners implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton buttonPressed = (JButton) e.getSource();
			if (buttonPressed == addProperty.getBtnCancel()) {
				addProperty.dispose();
				if(addProperty.getgProperties() == null)
					new GUIMainUser(null);
			} else if (buttonPressed == addProperty.getBtnAdd()) {
				if (isNotValid()) {
					JOptionPane.showMessageDialog(addProperty, "Please fill all the required fields.", "Fill",
							JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						if (!ManageDatabase.propertyExists(addProperty.getRdBtnRent().isSelected(),
								addProperty.getRdBtnSell().isSelected(), getPropertyData())) {
							ManageDatabase.addPropertyToDatabase(getPropertyData());
							
							if(addProperty.getgProperties() == null)
								new GUIMainUser(null);
							
							addProperty.dispose();
						}
						else
							JOptionPane.showMessageDialog(addProperty,
									"A property with that address already exists in our system!", "Error",
									JOptionPane.ERROR_MESSAGE);

					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "Input a valid number!", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}
		}
	}
}