package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import models.Client;
import models.Manager;
import models.Property;
import models.Purchasable_Property;
import models.Rentable_Property;
import util.ConnectionDB;
import util.ManageDatabase;
import views.GUIMainUser;
import views.GUIUserConfirm;

public class ControllerUserConfirm {
    private GUIUserConfirm userConfirm;
    private Client currUser;
    private Manager assignedManager;
    private Property interestedProperty;

    public ControllerUserConfirm(GUIUserConfirm userConfirm) {
        this.userConfirm = userConfirm;
        currUser = (Client) ConnectionDB.getCurrentUser();
        interestedProperty = userConfirm.getUserInterested().getUserInterested().getInterestedProperty();
        initializeElements();
        userConfirm.addActListeners(new ButtonListeners());
    }

    private class ButtonListeners implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton buttonPressed = (JButton) e.getSource();
            if (buttonPressed == userConfirm.getBtnCancel())
                userConfirm.dispose();
            else
                completePurchase();
        }

    }

    public void initializeElements() {
        initializeUserInfo();
        initializePropertyInfo();
        setAssignedManager();
    }

    public void initializeUserInfo() {
        userConfirm.getTxtDNI().setText(currUser.getDNI());
        userConfirm.getTxtFullName().setText(currUser.getFullName());
        userConfirm.getTxtEmail().setText(currUser.getEmail());
        userConfirm.getTxtPhoneNum().setText(String.valueOf(currUser.getPhoneNum()));
        userConfirm.getTxtRegion().setText(currUser.getRegion());
        userConfirm.getTxtBankAccountNum()
                .setText(currUser.getBankAccountNum() == null ? "" : currUser.getBankAccountNum());

    }

    public void initializePropertyInfo() {

        if (interestedProperty instanceof Rentable_Property) {
            userConfirm.getLblPropertyPhoto().setIcon(new ImageIcon("files/images/rentable_properties/"
                    + interestedProperty.getId() + ".png"));
            userConfirm.getLblTotalPrice()
                    .setText("Price per month: " + ((Rentable_Property) interestedProperty).getRentValue() + " EUR");
        } else {
            userConfirm.getLblPropertyPhoto().setIcon(new ImageIcon("files/images/purchasable_properties/"
                    + userConfirm.getUserInterested().getUserInterested().getInterestedProperty().getId() + ".png"));
            userConfirm.getLblTotalPrice()
                    .setText("Price to pay: " + ((Purchasable_Property) interestedProperty).getTotalValue() + " EUR");
        }
        userConfirm.getTxtAreaProperty().setText(interestedProperty.toString());

    }

    public void setAssignedManager() {
        assignedManager = ManageDatabase.retrieveRandomManager();
        userConfirm.getLblManager().setText("The manager you'll get assigned to is: " + assignedManager.getFullName());
    }

    public void completePurchase() {
        registerPurchase();
        JOptionPane.showMessageDialog(userConfirm, "Thank you! Your designated manager will contact you shortly.",
                "Success", JOptionPane.INFORMATION_MESSAGE);
        userConfirm.getUserInterested().getUserView().getControllerUserView().getInterestedProperties()
                .remove(interestedProperty);
        userConfirm.dispose();
        userConfirm.getUserInterested().dispose();
        userConfirm.getUserInterested().getUserView().dispose();
        new GUIMainUser(null);
    }

    public void registerPurchase() {
        ManageDatabase.setPropertyAvailability(false,
                userConfirm.getUserInterested().getUserInterested().getInterestedProperty());
        ManageDatabase.insertPurchase(interestedProperty, currUser, assignedManager);
        if (userConfirm.getCbxSaveBankInfo().isSelected())
            ManageDatabase.updateClientBankInfo(currUser, userConfirm.getTxtBankAccountNum().getText());

    }

}
