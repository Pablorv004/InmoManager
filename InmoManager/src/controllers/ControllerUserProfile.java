package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import models.Client;
import models.Property;
import util.ConnectionDB;
import util.FieldUtils;
import util.ManageDatabase;
import views.GUIMainUser;
import views.GUIUserProfile;
import views.GUIUserView;

public class ControllerUserProfile {

    private GUIUserProfile userProfile;
    private boolean editMode = false;
    private GUIUserView userView;

    public ControllerUserProfile(GUIUserProfile userProfile) {
        this.userProfile = userProfile;
        initializeElements();
        userProfile.addActListeners(new ButtonListeners());
    }

    private class ButtonListeners implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            if (source == userProfile.getBtnReturn()) {
                userProfile.dispose();
                new GUIMainUser(null);
            } else if (source == userProfile.getBtnEdit()) {
                editMode = !editMode;
                if (editMode) {
                    enableEditElements();
                } else
                    cancelEdit();
            } else if (source == userProfile.getBtnApply()) {
                if (checkAllElements() && passwordsMatch()) {
                    System.out.println("Applying changes...");
                    applyChanges();
                }
            } else if (source == userProfile.getBtnHomes()) {

                userView = new GUIUserView(userProfile);
                userView.getControllerUserView()
                        .setProperties(ManageDatabase.getUserHomes(ConnectionDB.getCurrentUser().getDNI()));
                if (userView.getControllerUserView().getProperties().size() == 0) {
                    JOptionPane.showMessageDialog(userProfile, "You don't have any inserted homes!");
                    userView.dispose();
                } else {
                    userProfile.dispose();
                    getFilteredView();
                }
            } else if (source == userProfile.getBtnRents()) {
                List<Property> properties = ManageDatabase.getUserCurrentHomes(ConnectionDB.getCurrentUser().getDNI());

                if (properties.size() == 0) {
                    JOptionPane.showMessageDialog(userView, "You don't have any rents!");
                } else {
                    userView = new GUIUserView(userProfile);
                    userView.getControllerUserView()
                            .setProperties(properties);
                    userProfile.dispose();
                    getFilteredView();
                }
            }

        }

    }

    public void getFilteredView() {
        userView.getControllerUserView().loadPropertyOnScreen();
        userView.getControllerUserView().setResultsFound();
        userView.getPanelPictureMap().remove(userView.getBtnInterested());
        userView.getPanelPictureMap().remove(userView.getBtnFilter());
        userView.getPanelInterestedCart().remove(userView.getLblInterestedCount());
        userView.getPanelInterestedCart().remove(userView.getBtnInterestedList());
        userView.getPanelPictureMap().revalidate();
        userView.getPanelPictureMap().repaint();
    }

    public boolean applyChanges() {
        try {
            Client currentUser = (Client) ConnectionDB.getCurrentUser();
            if (currentUser == null) {
                System.out.println("Current user is null.");
                return false;
            }

            if (userProfile == null || userProfile.getTxtPhone() == null || userProfile.getTxtName() == null ||
                    userProfile.getTxtEmail() == null || userProfile.getPasswordField() == null ||
                    userProfile.getTxtRegion() == null || userProfile.getTxtUsername() == null) {
                System.out.println("User profile or one of its fields is null.");
                return false;
            }

            try {
                currentUser.setPhoneNum(Integer.parseInt(userProfile.getTxtPhone().getText()));
            } catch (NumberFormatException e) {
                System.out.println("Invalid phone number format.");
                return false;
            }

            currentUser.setFullName(userProfile.getTxtName().getText());
            currentUser.setEmail(userProfile.getTxtEmail().getText());
            currentUser.setPassword(String.valueOf(userProfile.getPasswordField().getPassword()));
            currentUser.setRegion(userProfile.getTxtRegion().getText());
            currentUser.setUserName(userProfile.getTxtUsername().getText());

            System.out.println("Changes applied.");
            ManageDatabase.updateClient(currentUser);
            resetEditButtons();
            return true;
        } catch (ClassCastException e) {
            System.out.println("Error casting current user to Client.");
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean passwordsMatch() {
        JPasswordField pwd = new JPasswordField(10);
        int action = JOptionPane.showConfirmDialog(userProfile, pwd, "Enter Password to Confirm changes",
                JOptionPane.OK_CANCEL_OPTION);
        if (action < 0)
            return false;
        else {
            System.out.println("Checking match...");
            return (String.valueOf(userProfile.getPasswordField().getPassword())
                    .equals(String.valueOf(pwd.getPassword())));
        }

    }

    public boolean checkAllElements() {
        System.out.println("Validating fields...");
        return (FieldUtils.validateEmail(userProfile.getTxtEmail().getText(), userProfile,
                ConnectionDB.getCurrentUser().getDNI()) &&
                FieldUtils.validateName(userProfile.getTxtName().getText(), userProfile)
                && FieldUtils.validatePhone(userProfile.getTxtPhone().getText(), userProfile,
                        ConnectionDB.getCurrentUser().getDNI())
                && FieldUtils.validatePassword(userProfile.getPasswordField().getPassword(), userProfile)
                && FieldUtils.validateUsername(userProfile.getTxtUsername().getText(), userProfile,
                        userProfile.getTxtDNI().getText())
                && FieldUtils.validateRegion(userProfile.getTxtRegion().getText(), userProfile));
    }

    public void resetEditButtons() {
        userProfile.getBtnEdit().setText("Edit");
        userProfile.getBtnApply().setEnabled(false);
        userProfile.getTxtName().setEditable(false);
        userProfile.getTxtUsername().setEditable(false);
        userProfile.getTxtEmail().setEditable(false);
        userProfile.getPasswordField().setEditable(false);
        userProfile.getTxtPhone().setEditable(false);
        userProfile.getTxtRegion().setEditable(false);
        System.out.println("Reset edit buttons.");
    }

    public void cancelEdit() {
        resetEditButtons();
        initializeElements();
        System.out.println("Canceled edit.");
    }

    private void enableEditElements() {
        userProfile.getBtnEdit().setText("Cancel");
        userProfile.getBtnApply().setEnabled(true);
        userProfile.getTxtName().setEditable(true);
        userProfile.getTxtUsername().setEditable(true);
        userProfile.getTxtEmail().setEditable(true);
        userProfile.getPasswordField().setEditable(true);
        userProfile.getTxtPhone().setEditable(true);
        userProfile.getTxtRegion().setEditable(true);
        System.out.println("Enabling edit mode...");
    }

    private void initializeElements() {
        userProfile.getTxtDNI().setText(ConnectionDB.getCurrentUser().getDNI());
        userProfile.getTxtName().setText(ConnectionDB.getCurrentUser().getFullName());
        userProfile.getTxtUsername().setText(ConnectionDB.getCurrentUser().getUserName());
        userProfile.getPasswordField().setText(ConnectionDB.getCurrentUser().getPassword());
        userProfile.getTxtEmail().setText(ConnectionDB.getCurrentUser().getEmail());
        userProfile.getTxtPhone().setText(String.valueOf(ConnectionDB.getCurrentUser().getPhoneNum()));
        userProfile.getTxtRegion().setText(((Client) ConnectionDB.getCurrentUser()).getRegion());
        userProfile.getTxtCreation().setText(((Client) ConnectionDB.getCurrentUser()).getCreationTime().toString());
        userProfile.getLblPhoto().setIcon(new ImageIcon("files/images/users/default.png"));
        System.out.println("Initialized components.");
    }
}