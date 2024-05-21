package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import models.Property;
import util.ConnectionDB;
import util.ManageDatabase;
import views.GUIMainUser;
import views.GUIStopRent;

public class ControllerStopRent {
    private GUIStopRent stopRent;

    public ControllerStopRent(GUIStopRent stopRent) {
        this.stopRent = stopRent;
        initializeList();
        stopRent.addActListeners(new ButtonListeners());
    }

    private class ButtonListeners implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            if (source == stopRent.getBtnConfirm()) {

                if (stopRent.getList().getSelectedIndex() != -1) {
                    int choose = JOptionPane.showConfirmDialog(stopRent,
                            "Are you sure you want to stop renting this property?", "Confirmation",
                            JOptionPane.INFORMATION_MESSAGE);
                    if (choose == JOptionPane.YES_OPTION) {
                        ManageDatabase.stopRent(stopRent.getList().getSelectedValue());
                        JOptionPane.showMessageDialog(stopRent, "You have canceled your rent.", "Thank you",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(stopRent, "Please choose a property to stop renting.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            else {
                stopRent.dispose();
                new GUIMainUser(null);
            }
        }

    }

    public void initializeList() {
        List<Property> properties = ManageDatabase.getUserCurrentHomes(ConnectionDB.getCurrentUser().getDNI());
        DefaultListModel<Property> dlm = new DefaultListModel<>();
        for (Property p : properties)
            dlm.addElement(p);
        stopRent.getList().setModel(dlm);
        stopRent.getPanelData().revalidate();
        stopRent.getPanelData().repaint();
    }
}
