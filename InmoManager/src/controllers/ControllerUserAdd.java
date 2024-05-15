package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import models.Property;
import models.Purchasable_Property;
import models.Rentable_Property;
import util.ManageDatabase;
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
		try {
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
				return new Rentable_Property(0, userAdd.getTxtAddress().getText(), userAdd.getTxtCity().getText(),
						userAdd.getcBType().getSelectedItem().toString(),
						Integer.parseInt(userAdd.getTxtAge().getText()),
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
				return new Purchasable_Property(0, userAdd.getTxtAddress().getText(), userAdd.getTxtCity().getText(),
						userAdd.getcBType().getSelectedItem().toString(),
						Integer.parseInt(userAdd.getTxtAge().getText()),
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
		}catch (NumberFormatException e) {
			throw e;
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
					try{
					ManageDatabase.addPropertyToDatabase(getPropertyData());
					userAdd.dispose();
					new GUIMainUser(null);
				} catch(NumberFormatException e1){
					JOptionPane.showMessageDialog(null, "Input a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				}
			}
		}
	}
}