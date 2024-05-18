package controllers;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import models.Property;
import models.Purchasable_Property;
import models.Rentable_Property;
import util.ManageDatabase;
import views.GUIMainUser;
import views.GUIPropertyFilter;
import views.GUIUserInterested;
import views.GUIUserView;

public class ControllerUserView {
	private GUIUserView userView;
	private List<Property> properties, interestedProperties;

	private int currentIdx = 0;
	private Property currProperty;

	public ControllerUserView(GUIUserView userView) {
		this.userView = userView;
		interestedProperties = new ArrayList<>();
		this.userView.addActListeners(new ButtonListeners());
		loadFirstProperties();
		loadPropertyOnScreen();
	}

	public void refreshLblInterestedCount() {
		userView.getLblInterestedCount().setText(String.valueOf(interestedProperties.size()));
		if (interestedProperties.size() > 0) {
			userView.getLblInterestedCount().setForeground(Color.RED);
			userView.getLblInterestedCount().setFont(new Font("Tahoma", Font.PLAIN, 18));
		} else {
			userView.getLblInterestedCount().setForeground(Color.BLACK);
			userView.getLblInterestedCount().setFont(new Font("Tahoma", Font.PLAIN, 14));
		}

	}

	public void setInterested() {
		interestedProperties.add(currProperty);
		userView.getBtnInterested().setEnabled(false);
		userView.getBtnInterested().setText("Property added!");
		userView.getLblInterestedCount()
				.setText(String.valueOf(1 + Integer.parseInt(userView.getLblInterestedCount().getText())));
		refreshLblInterestedCount();
	}

	public void checkInterested() {
		if (interestedProperties.contains(currProperty)) {
			userView.getBtnInterested().setEnabled(false);
			userView.getBtnInterested().setText("Currently interested.");
		} else {
			userView.getBtnInterested().setEnabled(true);
			userView.getBtnInterested().setText("Interested!");
		}
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
			} else if (buttonPressed == userView.getBtnInterestedList()) {
				new GUIUserInterested(userView);
			}
		}
	}

	public void refreshIdx() {

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

	public void setResultsFound() {
		userView.getLblResults().setText(properties.size() + " result(s) found.");
		if (properties.size() == 0) {
			userView.getLblResults().setForeground(new Color(128, 0, 0));
		} else
			userView.getLblResults().setForeground(new Color(0, 128, 0));
	}

	public void loadFirstProperties() {
		String[] baseFilters = { "available = 1" };
		properties = ManageDatabase.getProperties(true, true, baseFilters);
		setResultsFound();
		refreshIdx();
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
		refreshIdx();
		currProperty = properties.get(currentIdx);
		checkInterested();
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
		userView.getLblPropertyPhoto()
				.setIcon(new ImageIcon("files/images/properties/" + currProperty.getId() + ".png"));
		userView.getLblPropertyMap()
				.setIcon(new ImageIcon("files/images/properties/" + currProperty.getId() + "Map.png"));

		// Let's set the tooltiptexts:
		userView.getLblRooms().setToolTipText("Has " + userView.getLblRooms().getText() + " rooms.");
		userView.getLblBathrooms().setToolTipText("Has " + userView.getLblBathrooms().getText() + " bathrooms.");
		userView.getLblFloors().setToolTipText("Has " + userView.getLblFloors().getText() + " floors.");
		userView.getLblPropertySize().setToolTipText("Property has " + userView.getLblPropertySize().getText() + " square meters.");
		userView.getLblPriceTag().setToolTipText("The price is " + userView.getLblPriceTag().getText() + "EUR");
		
		userView.getLblTerrainSize().setText(String.valueOf(currProperty.getTerrainSize()));
		userView.getLblTerrainSize().setToolTipText("Terrain has " + userView.getLblTerrainSize().getText() + " square meters.");
		userView.getLblGarageSize().setText(String.valueOf(currProperty.getGarageSize()));
		userView.getLblGarageSize().setToolTipText("Garage has " + userView.getLblGarageSize().getText() + " square meters.");
		loadOptionalProperties();
	}

	public void resetIdx() {
		currentIdx = 0;
	}

	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

	public void applyFilters(boolean checkRentable, boolean checkPurchasable, String... filters) {

		properties = ManageDatabase.getProperties(checkRentable,
				checkPurchasable, filters);
		setResultsFound();
		if (properties.size() == 0) {
			JOptionPane.showMessageDialog(userView,
					"Sorry, there are no results for your desired inputs. Try again or come back later.",
					"No results found", JOptionPane.INFORMATION_MESSAGE);
			loadFirstProperties();
			resetIdx();
		} else {

			setProperties(properties);
			resetIdx();
			loadPropertyOnScreen();
		}
	}

	public List<Property> getInterestedProperties() {
		return interestedProperties;
	}

	public void setInterestedProperties(List<Property> interestedProperties) {
		this.interestedProperties = interestedProperties;
	}

}
