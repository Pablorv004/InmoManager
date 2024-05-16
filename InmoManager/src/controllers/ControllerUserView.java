package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import models.Property;
import models.Purchasable_Property;
import models.Rentable_Property;
import util.ManageDatabase;
import views.GUIMainUser;
import views.GUIPropertyFilter;
import views.GUIUserView;

public class ControllerUserView {
	private GUIUserView userView;
	private List<Property> properties;

	private int currentIdx = 0;
	private Property currProperty;

	public ControllerUserView(GUIUserView userView) {
		this.userView = userView;
		new ArrayList<>();
		this.userView.addActListeners(new ButtonListeners());
		loadFirstProperties();
		loadPropertyOnScreen();
	}

	public void setInterested() {
		// interestedProperties.add(currentProperty);
		userView.getLblInterestedCount()
				.setText(String.valueOf(1 + Integer.parseInt(userView.getLblInterestedCount().getText())));
	}

	private class ButtonListeners implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton buttonPressed = (JButton) e.getSource();
			if (buttonPressed == userView.getBtnReturn()) {
				userView.dispose();
				new GUIMainUser(null);
			} else if (buttonPressed == userView.getBtnFilter()) {
				new GUIPropertyFilter(userView);
			} else if (buttonPressed == userView.getBtnInterested()) {
				setInterested();
			} else if (buttonPressed == userView.getBtnNext()) {
				currentIdx++;
				loadPropertyOnScreen();
			} else if (buttonPressed == userView.getBtnPrevious()) {
				currentIdx--;
				loadPropertyOnScreen();
			} else if (buttonPressed == userView.getBtnInterested()) {
			}
		}
	}

	public void refreshIdx() {
		userView.getLblResults().setText(properties.size() + " result(s) found.");
		if (currentIdx == 0)
			userView.getBtnPrevious().setEnabled(false);
		else
			userView.getBtnPrevious().setEnabled(true);
		if (currentIdx == properties.size() - 1)
			userView.getBtnNext().setEnabled(false);
		else
			userView.getBtnNext().setEnabled(true);
		userView.getLblIndex().setText((currentIdx + 1) + "/" + properties.size());
	}

	public void loadFirstProperties() {
		String[] baseFilters = { "available = 1" };
		properties = ManageDatabase.getProperties(true, true, baseFilters);
	}

	public void loadOptionalProperties() {
		currProperty = properties.get(currentIdx);
		// These might not show...
		if (currProperty.isHasGarden() && userView.getLblGarden().getParent() != userView.getPanelFeatures())
			userView.getPanelFeatures().add(userView.getLblGarden());
		else if (!currProperty.isHasGarden() && userView.getLblGarden().getParent() == userView.getPanelFeatures())
			userView.getPanelFeatures().remove(userView.getLblGarden());

		if (currProperty.isHasBasement() && userView.getLblBasement().getParent() != userView.getPanelFeatures())
			userView.getPanelFeatures().add(userView.getLblBasement());
		else if (!currProperty.isHasBasement() && userView.getLblBasement().getParent() == userView.getPanelFeatures())
			userView.getPanelFeatures().remove(userView.getLblBasement());

		if (currProperty.isHasGarage() && userView.getLblGarage().getParent() != userView.getPanelFeatures())
			userView.getPanelFeatures().add(userView.getLblGarage());
		else if (!currProperty.isHasGarage() && userView.getLblGarage().getParent() == userView.getPanelFeatures())
			userView.getPanelFeatures().remove(userView.getLblGarage());

		if (currProperty.isHasPool() && userView.getLblPool().getParent() != userView.getPanelFeatures())
			userView.getPanelFeatures().add(userView.getLblPool());
		else if (!currProperty.isHasPool() && userView.getLblPool().getParent() == userView.getPanelFeatures())
			userView.getPanelFeatures().remove(userView.getLblPool());

		if (currProperty.isHasAC() && userView.getLblAC().getParent() != userView.getPanelFeatures())
			userView.getPanelFeatures().add(userView.getLblAC());
		else if (!currProperty.isHasAC() && userView.getLblAC().getParent() == userView.getPanelFeatures())
			userView.getPanelFeatures().remove(userView.getLblAC());

		if (currProperty.isHasLift() && userView.getLblLift().getParent() != userView.getPanelFeatures())
			userView.getPanelFeatures().add(userView.getLblLift());
		else if (!currProperty.isHasLift() && userView.getLblLift().getParent() == userView.getPanelFeatures())
			userView.getPanelFeatures().remove(userView.getLblLift());

		if (currProperty.isHasTerrace() && userView.getLblTerrace().getParent() != userView.getPanelFeatures())
			userView.getPanelFeatures().add(userView.getLblTerrace());
		else if (!currProperty.isHasTerrace() && userView.getLblTerrace().getParent() == userView.getPanelFeatures())
			userView.getPanelFeatures().remove(userView.getLblTerrace());

		userView.getPanelFeatures().revalidate();
		userView.getPanelFeatures().repaint();

		if (currProperty.getTerrainSize() != 0 && userView.getLblTerrainSize().getParent() != userView.getPanelSizes())
			userView.getPanelSizes().add(userView.getLblTerrainSize());
		else if (currProperty.getTerrainSize() == 0
				&& userView.getLblTerrainSize().getParent() == userView.getPanelSizes())
			userView.getPanelSizes().remove(userView.getLblTerrainSize());

		if (currProperty.isHasGarage() && userView.getLblGarageSize().getParent() != userView.getPanelSizes())
			userView.getPanelSizes().add(userView.getLblGarageSize());
		else if (!currProperty.isHasGarage() && userView.getLblGarageSize().getParent() == userView.getPanelSizes())
			userView.getPanelSizes().remove(userView.getLblGarageSize());

		userView.getPanelSizes().revalidate();
		userView.getPanelSizes().repaint();

	}

	public void loadPropertyOnScreen() {
		currProperty = properties.get(currentIdx);
		String sellOrRent = currProperty instanceof Rentable_Property ? " To Rent" : " For Purchase";
		String priceType = currProperty instanceof Rentable_Property ? "Monthly Rent" : "Price";
		int priceValue = currProperty instanceof Rentable_Property ? ((Rentable_Property) currProperty).getRentValue()
				: ((Purchasable_Property) currProperty).getTotalValue();
		// These'll always show!
		userView.getLblType().setText(currProperty.getType() + sellOrRent);
		userView.getLblCity().setText(currProperty.getCity());
		userView.getTxtStatus().setText(currProperty.getStatus());
		userView.getTxtAddress().setText(currProperty.getAddress());
		userView.getTxtAge().setText(String.valueOf(currProperty.getAge() + " years old"));
		userView.getLblPropertySize().setText(String.valueOf(currProperty.getPropertySize()));
		userView.getLblRooms().setText(String.valueOf(currProperty.getRooms()));
		userView.getLblBathrooms().setText(String.valueOf(currProperty.getBathrooms()));
		userView.getLblFloors().setText(String.valueOf(currProperty.getFloors()));
		userView.getLblPrice().setText(priceType);
		userView.getLblPriceTag().setText(String.valueOf(priceValue));
		userView.getLblPropertyPhoto().setIcon(new ImageIcon("files/images/" + currProperty.getId() + ".png"));
		userView.getLblPropertyMap().setIcon(new ImageIcon("files/images/" + currProperty.getId() + "Map.png"));

		// Let's set the tooltiptexts:
		userView.getLblRooms().setToolTipText("Has " + userView.getLblRooms().getText() + " rooms.");
		userView.getLblBathrooms().setToolTipText("Has " + userView.getLblBathrooms().getText() + " bathrooms.");
		userView.getLblFloors().setToolTipText("Has " + userView.getLblFloors().getText() + " floors.");
		loadOptionalProperties();
		refreshIdx();
	}

	public void resetIdx(){
		currentIdx = 0;
	}

	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

}
