package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import util.ConversionMethods;
import util.ManageDatabase;
import views.GUIPropertyFilter;
import views.GUIUserView;

public class ControllerPropertyFilter {

    private GUIPropertyFilter propertyFilter;
    private JFrame parentFrame;

    public ControllerPropertyFilter(GUIPropertyFilter propertyFilter, JFrame parentFrame) {
        this.propertyFilter = propertyFilter;
        this.parentFrame = parentFrame;
        initializeListCities();
        initializeCBCounts();
        this.propertyFilter.addActListeners(new ButtonListeners());
        this.propertyFilter.addItemListeners(new ItemListeners());
    }

    private class ButtonListeners implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton buttonPressed = (JButton) e.getSource();
            if (buttonPressed == propertyFilter.getBtnReturn())
                propertyFilter.dispose();
            if (buttonPressed == propertyFilter.getBtnReset())
                resetFilters();
            if (buttonPressed == propertyFilter.getBtnApply()) {
                String[] filters = null;
                try {
                    filters = getFilters();
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(propertyFilter, "Insert valid numbers!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                if (filters != null) {
                    propertyFilter.dispose();
                    applyFilters(parentFrame, propertyFilter.getCbxRent().isSelected(),
                            propertyFilter.getCbxSale().isSelected(), filters);
                }

            }
        }
    }

    // Call methods for each one
    public static void applyFilters(JFrame frame, boolean checkRentable, boolean checkPurchasable,
            String... filters) {
        if (frame instanceof GUIUserView) {
            ((GUIUserView) frame).getControllerUserView().applyFilters(checkRentable, checkPurchasable, filters);
        }
    }

    private class ItemListeners implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            JCheckBox checkboxSelected = (JCheckBox) e.getSource();
            if (checkboxSelected == propertyFilter.getCbxSale() || checkboxSelected == propertyFilter.getCbxRent())
                initializeListCities();
            else if (checkboxSelected == propertyFilter.getCbxGarage()) {
                if (checkboxSelected.isSelected()) {
                    checkboxSelected.setIcon(new ImageIcon(
                            "files/images/" + checkboxSelected.getToolTipText().toLowerCase() + "40S.png"));
                    propertyFilter.getTxtGarageInit().setEnabled(true);
                    propertyFilter.getTxtGarageFinal().setEnabled(true);
                } else {
                    checkboxSelected.setIcon(new ImageIcon(
                            "files/images/" + checkboxSelected.getToolTipText().toLowerCase() + "40.png"));
                    propertyFilter.getTxtGarageInit().setEnabled(false);
                    propertyFilter.getTxtGarageFinal().setEnabled(false);
                }
            } else if (checkboxSelected.isSelected())
                checkboxSelected.setIcon(new ImageIcon(
                        "files/images/" + checkboxSelected.getToolTipText().toLowerCase() + "40S.png"));
            else
                checkboxSelected.setIcon(new ImageIcon(
                        "files/images/" + checkboxSelected.getToolTipText().toLowerCase() + "40.png"));

        }

    }

    public boolean garageInserted() {
        return (propertyFilter.getCbxGarage().isSelected() && !propertyFilter.getTxtGarageInit().getText().isBlank()
                && !propertyFilter.getTxtGarageFinal().getText().isBlank())
                || !propertyFilter.getCbxGarage().isSelected();
    }

    public String[] getFilters() throws NumberFormatException {
        List<String> filters = new ArrayList<>();
        filters.add("available = 1");
        String ageTextInit = propertyFilter.getTxtAgeInit().getText();
        String ageTextFinal = propertyFilter.getTxtAgeFinal().getText();
        if (propertyFilter.getListCities().getSelectedValuesList().size() > 0)
            filters.add(getSelectedCitiesFilter());
        if (!ageTextInit.isBlank() || !ageTextFinal.isBlank()) {
            int ageInit = Integer.parseInt(ageTextInit);
            int ageFinal = Integer.parseInt(ageTextFinal);
            String age = "age BETWEEN " + ageInit + " AND " + ageFinal;
            filters.add(age);
        }
        String propertyTextInit = propertyFilter.getTxtPropertyInit().getText();
        String propertyTextFinal = propertyFilter.getTxtPropertyFinal().getText();
        if (!propertyTextInit.isBlank() || !propertyTextFinal.isBlank()) {
            int propertyInit = Integer.parseInt(propertyTextInit);
            int propertyFinal = Integer.parseInt(propertyTextFinal);
            String property = "propertySize BETWEEN " + propertyInit + " AND " + propertyFinal;
            filters.add(property);
        }
        String terrainTextInit = propertyFilter.getTxtTerrainInit().getText();
        String terrainTextFinal = propertyFilter.getTxtTerrainFinal().getText();
        if (!terrainTextInit.isBlank() || !terrainTextFinal.isBlank()) {
            int terrainInit = Integer.parseInt(terrainTextInit);
            int terrainFinal = Integer.parseInt(terrainTextFinal);
            String terrain = "terrainSize BETWEEN " + terrainInit + " AND " + terrainFinal;
            filters.add(terrain);
        }
        String garageSizeInit = propertyFilter.getTxtGarageInit().getText();
        String garageSizeFinal = propertyFilter.getTxtGarageFinal().getText();
        if ((!garageSizeInit.isBlank() || !garageSizeFinal.isBlank()) && propertyFilter.getCbxGarage().isSelected()) {
            int garageInit = Integer.parseInt(garageSizeInit);
            int garageFinal = Integer.parseInt(garageSizeFinal);
            String garageSize = "garageSize BETWEEN " + garageInit + " AND " + garageFinal;
            filters.add(garageSize);
        }
        int numFloors = propertyFilter.getCbFloors().getSelectedIndex();
        int numRooms = propertyFilter.getCbRooms().getSelectedIndex();
        int numBathrooms = propertyFilter.getCbBathrooms().getSelectedIndex();
        if (numFloors != -1 && numFloors != 0)
            filters.add("floors = " + numFloors);
        if (numRooms != -1 && numRooms != 0)
            filters.add("rooms = " + numRooms);
        if (numBathrooms != -1 && numBathrooms != 0)
            filters.add("bathrooms = " + numBathrooms);
        if (propertyFilter.getCbxGarage().isSelected())
            filters.add("hasGarage = 1");
        if (propertyFilter.getCbxLift().isSelected())
            filters.add("hasLift = 1");
        if (propertyFilter.getCbxGarden().isSelected())
            filters.add("hasGarden = 1");
        if (propertyFilter.getCbxPool().isSelected())
            filters.add("hasPool = 1");
        if (propertyFilter.getCbxBasement().isSelected())
            filters.add("hasBasement = 1");
        if (propertyFilter.getCbxAC().isSelected())
            filters.add("hasAc = 1");
        if (propertyFilter.getCbxTerrace().isSelected())
            filters.add("hasTerrace = 1");
        String priceTextInit = propertyFilter.getTxtPriceInit().getText();
        String priceTextFinal = propertyFilter.getTxtPriceFinal().getText();
        if (!priceTextInit.isBlank() || !priceTextFinal.isBlank()) {
            int priceInit = Integer.parseInt(priceTextInit);
            int priceFinal = Integer.parseInt(priceTextFinal);
            String price = "totalValue BETWEEN " + priceInit + " AND " + priceFinal;
            filters.add(price);

        }
        System.out.println(Arrays.toString(ConversionMethods.stringListToArray(filters)));
        return ConversionMethods.stringListToArray(filters);
    }

    public String getSelectedCitiesFilter() {
        String cities = "";
        List<String> selectedCities = propertyFilter.getListCities().getSelectedValuesList();
        for (int i = 0; i < selectedCities.size(); i++) {
            if (i != selectedCities.size() - 1)
                cities += "city = '" + selectedCities.get(i).split("\\(")[0] + "' OR ";
            else
                cities += "city = '" + selectedCities.get(i).split("\\(")[0] + "'";
        }
        return cities;
    }

    public void initializeListCities() {
        Map<String, Integer> map = ManageDatabase.getCityCountMap(propertyFilter.getCbxRent().isSelected(),
                propertyFilter.getCbxSale().isSelected());
        DefaultListModel<String> dlm = new DefaultListModel<>();
        int idx = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet())
            dlm.add(idx++, entry.getKey() + "(" + entry.getValue() + ")");
        propertyFilter.getListCities().setModel(dlm);
        propertyFilter.getPanelData().revalidate();
        propertyFilter.getPanelData().repaint();
    }

    public void initializeCBCounts() {
        for (int i = 0; i < 100; i++) {
            propertyFilter.getCbFloors().addItem(i);
            propertyFilter.getCbBathrooms().addItem(i);
            propertyFilter.getCbRooms().addItem(i);
        }
        propertyFilter.getCbFloors().setSelectedIndex(-1);
        propertyFilter.getCbRooms().setSelectedIndex(-1);
        propertyFilter.getCbBathrooms().setSelectedIndex(-1);
    }

    public void resetFilters() {
        // Default filters:
        propertyFilter.getCbxRent().setSelected(true);
        propertyFilter.getCbxSale().setSelected(true);
        propertyFilter.getListCities().setSelectedIndex(-1);
        propertyFilter.getTxtAgeInit().setText("");
        propertyFilter.getTxtAgeFinal().setText("");
        propertyFilter.getTxtPropertyInit().setText("");
        propertyFilter.getTxtPropertyFinal().setText("");
        propertyFilter.getTxtTerrainInit().setText("");
        propertyFilter.getTxtTerrainFinal().setText("");
        propertyFilter.getTxtPriceInit().setText("");
        propertyFilter.getTxtPriceFinal().setText("");
        propertyFilter.getCbFloors().setSelectedIndex(-1);
        propertyFilter.getCbRooms().setSelectedIndex(-1);
        propertyFilter.getCbBathrooms().setSelectedIndex(-1);
        propertyFilter.getCbxGarden().setSelected(false);
        propertyFilter.getCbxLift().setSelected(false);
        propertyFilter.getCbxAC().setSelected(false);
        propertyFilter.getCbxBasement().setSelected(false);
        propertyFilter.getCbxTerrace().setSelected(false);
        propertyFilter.getCbxGarage().setSelected(false);
        propertyFilter.getCbxPool().setSelected(false);
    }

    public GUIPropertyFilter getPropertyFilter() {
        return propertyFilter;
    }

    public void setPropertyFilter(GUIPropertyFilter propertyFilter) {
        this.propertyFilter = propertyFilter;
    }
}
