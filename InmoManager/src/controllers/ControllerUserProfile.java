package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import models.Client;
import util.ConnectionDB;
import views.GUIUserProfile;

public class ControllerUserProfile {

	private GUIUserProfile userProfile;
    private boolean editMode = false;

	public ControllerUserProfile(GUIUserProfile userProfile) {
		this.userProfile = userProfile;
		initializeElements();
		userProfile.addActListeners(new ButtonListeners());
	}
	
	private class ButtonListeners implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
            JButton source = (JButton)e.getSource();
            if(source == userProfile.getBtnReturn()){
                userProfile.dispose();
            }
            else if(source == userProfile.getBtnEdit()){
                editMode = !editMode;
                if(editMode){
                    enableEditElements();
                }
                else
                applyEditElements();
            }
            else if (source == userProfile.getBtnApply()){
                //TODO: Finish apply
            }
            else if (source == userProfile.getBtnHomes()){
                //TODO: Special UserView with buttons removed and filters applied.
            }
			
		}
		
	}

    private void applyEditElements(){
        userProfile.getBtnEdit().setText("Edit");
        userProfile.getTxtName().setEditable(false);
        userProfile.getTxtUsername().setEditable(false);
        userProfile.getTxtEmail().setEditable(false);
        userProfile.getTxtPhone().setEditable(false);
        userProfile.getTxtRegion().setEditable(false);

    }

    private void enableEditElements(){
        userProfile.getBtnEdit().setText("Cancel");
        userProfile.getTxtName().setEditable(true);
        userProfile.getTxtUsername().setEditable(true);
        userProfile.getTxtEmail().setEditable(true);
        userProfile.getTxtPhone().setEditable(true);
        userProfile.getTxtRegion().setEditable(true);
    }

    private void initializeElements(){
        userProfile.getTxtDNI().setText(ConnectionDB.getCurrentUser().getDNI()); 
        userProfile.getTxtName().setText(ConnectionDB.getCurrentUser().getFullName());
        userProfile.getTxtUsername().setText(ConnectionDB.getCurrentUser().getUserName());
        userProfile.getTxtEmail().setText(ConnectionDB.getCurrentUser().getEmail());
        userProfile.getTxtPhone().setText(String.valueOf(ConnectionDB.getCurrentUser().getPhoneNum()));
        userProfile.getTxtRegion().setText(((Client)ConnectionDB.getCurrentUser()).getRegion());
        userProfile.getTxtCreation().setText(((Client)ConnectionDB.getCurrentUser()).getCreationTime().toString());
        userProfile.getLblPhoto().setIcon(new ImageIcon("files/images/users/default.png"));
        }
}