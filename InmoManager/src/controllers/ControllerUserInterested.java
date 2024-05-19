package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import models.Property;
import util.ConversionMethods;
import views.GUIUserConfirm;
import views.GUIUserInterested;

public class ControllerUserInterested {
    private GUIUserInterested userInterested;
    private Property interestedProperty;

   

    public ControllerUserInterested(GUIUserInterested userInterested) {
        this.userInterested = userInterested;
        interestedProperty = null;
        loadInterestedProperties();
        userInterested.addActListeners(new ButtonListeners());
        userInterested.addStateListeners(new ListSelectionListener() {

            @SuppressWarnings("unchecked")
            @Override
            public void valueChanged(ListSelectionEvent e) {
                JList<Property> list = (JList<Property>)e.getSource();
                interestedProperty = list.getSelectedValue();
            }
        });
        
    }

    private class ButtonListeners implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton buttonPressed = (JButton) e.getSource();
            if (buttonPressed == userInterested.getBtnReturn())
                userInterested.dispose();
            else if (buttonPressed == userInterested.getBtnConfirm()) {
                if (interestedProperty == null)
                    JOptionPane.showMessageDialog(userInterested, "You must select a property!", "Warning", JOptionPane.WARNING_MESSAGE);
                else {
                    new GUIUserConfirm(userInterested);
                }

            } else if (buttonPressed == userInterested.getBtnDelete()) {
                deleteSelected();
            }
        }

    }

        public void deleteSelected() {
            userInterested.getUserView().getControllerUserView().getInterestedProperties()
                    .remove(userInterested.getListProperties().getSelectedValue());
            loadInterestedProperties();
        }

        public void loadInterestedProperties() {
            userInterested.getListProperties().setListData(ConversionMethods.propertyListToArray(
                    userInterested.getUserView().getControllerUserView().getInterestedProperties()));
            userInterested.getPanelData().revalidate();
            userInterested.getPanelData().repaint();
            userInterested.getUserView().getControllerUserView().refreshLblInterestedCount();
            userInterested.getUserView().getControllerUserView().checkInterested();
        }

        public Property getInterestedProperty() {
            return interestedProperty;
        }
    
        public void setInterestedProperty(Property interestedProperty) {
            this.interestedProperty = interestedProperty;
        }
}
