package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import models.Admin;
import models.Manager;
import util.ConnectionDB;
import util.FieldUtils;
import views.GUIFilterManager;

public class ControllerFilterManager {
	GUIFilterManager gFilter;

	public ControllerFilterManager(GUIFilterManager gFilter) {
		this.gFilter = gFilter;

		distinguishUser();

		// Enable gManage when "X" window button is clicked
		gFilter.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				gFilter.getgManageController().getgManage().setEnabled(true);
			}
		});

		gFilter.addActListener(new ActListener());
		gFilter.addItmListener(new ItmListener());
	}

	private class ActListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();

			if (btn == gFilter.getBtnCancel()) {
				gFilter.getgManageController().getgManage().setEnabled(true);
				gFilter.dispose();
			} else if (btn == gFilter.getBtnApply()) {
				checkSelected();
			} else if (btn == gFilter.getBtnReset()) {
				int option = JOptionPane.showConfirmDialog(gFilter, "Are you sure you want to reset all?", "Warning",
						JOptionPane.INFORMATION_MESSAGE);
				if (option == JOptionPane.OK_OPTION)
					resetAll();
			}
		}

		private void checkSelected() {
		    if (gFilter.getCbxDNI().isSelected()) {
		        filterByDNI();
		    } else {
		        boolean managerIDSelected = gFilter.getCbxManagerID().isSelected();
		        boolean salarySelected = gFilter.getCbxSalary().isSelected();
		        boolean commissionSelected = gFilter.getCbxCommission().isSelected();
		        
		        String managerID = gFilter.getFieldManagerID().getText().strip();
		        String minSalary = gFilter.getFieldMinSalary().getText().strip();
		        String maxSalary = gFilter.getFieldMaxSalary().getText().strip();
		        String minCom = gFilter.getFieldMinCom().getText().strip();
		        String maxCom = gFilter.getFieldMaxCom().getText().strip();

		        boolean isManagerIDValid = managerIDSelected && FieldUtils.validateManagerID(managerID, gFilter);
		        boolean isMinSalaryValid = salarySelected && validateMinSalary(minSalary);
		        boolean isMaxSalaryValid = salarySelected && validateMaxSalary(maxSalary);
		        boolean isMinComValid = commissionSelected && validateMinCommission(minCom);
		        boolean isMaxComValid = commissionSelected && validateMaxCommission(maxCom);

		        if (isManagerIDValid && !salarySelected && !commissionSelected) {
		            filterByManagerID();
		        } else if (isManagerIDValid && isMinSalaryValid && isMaxSalaryValid && !commissionSelected) {
		            filterBySalaryManagerID(minSalary, maxSalary, managerID);
		        } else if (isManagerIDValid && isMinSalaryValid && isMaxSalaryValid && isMinComValid && isMaxComValid) {
		            filterBySalaryManagerIDCommission(minSalary, maxSalary, minCom, maxCom, managerID);
		        } else if (!managerIDSelected && isMinSalaryValid && isMaxSalaryValid && isMinComValid && isMaxComValid) {
		            filterBySalaryCommission(minSalary, maxSalary, minCom, maxCom);
		        } else if (!managerIDSelected && !salarySelected && isMinComValid && isMaxComValid) {
		            filterByCommission(minCom, maxCom);
		        } else if (!managerIDSelected && isMinSalaryValid && isMaxSalaryValid && !commissionSelected) {
		            filterBySalary(minSalary, maxSalary);
		        }
		    }
		}

		
		
		private void filterBySalary(String mSalary, String mxSalary) {
		    List<Manager> filteredList = new ArrayList<>();
		    double minSalary = mSalary.isBlank() ? 0 : Double.parseDouble(mSalary);
		    double maxSalary = mxSalary.isBlank() ? Double.MAX_VALUE : Double.parseDouble(mxSalary);
		    
		    for (Manager m : gFilter.getgManageController().getManagerList()) {
		        if (matchesSalary(m, minSalary, maxSalary)) {
		            filteredList.add(m);
		        }
		    }
		    
		    gFilter.getgManageController().setManagerList(filteredList);
		    gFilter.getgManageController().updateTable();
		}

		private boolean matchesSalary(Manager manager, double minSalary, double maxSalary) {
		    return manager.getSalary() >= minSalary && manager.getSalary() <= maxSalary;
		}

		private void filterByCommission(String mCom, String mxCom) {
		    List<Manager> filteredList = new ArrayList<>();
		    double minCom = mCom.isBlank() ? 0 : Double.parseDouble(mCom);
		    double maxCom = mxCom.isBlank() ? Double.MAX_VALUE : Double.parseDouble(mxCom);
		    
		    for (Manager m : gFilter.getgManageController().getManagerList()) {
		        if (matchesCommission(m, minCom, maxCom)) {
		            filteredList.add(m);
		        }
		    }
		    
		    gFilter.getgManageController().setManagerList(filteredList);
		    gFilter.getgManageController().updateTable();
		}

		private boolean matchesCommission(Manager manager, double minCom, double maxCom) {
		    return manager.getComission() >= minCom && manager.getComission() <= maxCom;
		}

		private void filterBySalaryCommission(String mSalary, String mxSalary, String mCom, String mxCom) {
		    List<Manager> filteredList = new ArrayList<>();
		    double minSalary = mSalary.isBlank() ? 0 : Double.parseDouble(mSalary);
		    double maxSalary = mxSalary.isBlank() ? Double.MAX_VALUE : Double.parseDouble(mxSalary);
		    double minCom = mCom.isBlank() ? 0 : Double.parseDouble(mCom);
		    double maxCom = mxCom.isBlank() ? Double.MAX_VALUE : Double.parseDouble(mxCom);
		    
		    for (Manager m : gFilter.getgManageController().getManagerList()) {
		        if (matchesSalaryCommission(m, minSalary, maxSalary, minCom, maxCom)) {
		            filteredList.add(m);
		        }
		    }
		    
		    gFilter.getgManageController().setManagerList(filteredList);
		    gFilter.getgManageController().updateTable();
		}

		private boolean matchesSalaryCommission(Manager manager, double minSalary, double maxSalary, double minCom, double maxCom) {
		    return manager.getSalary() >= minSalary && manager.getSalary() <= maxSalary &&
		           manager.getComission() >= minCom && manager.getComission() <= maxCom;
		}

		private void filterBySalaryManagerIDCommission(String mSalary, String mxSalary, String mCom, String mxCom, String manID) {
		    List<Manager> filteredList = new ArrayList<>();
		    int managerID = Integer.parseInt(manID);
		    double minSalary = mSalary.isBlank() ? 0 : Double.parseDouble(mSalary);
		    double maxSalary = mxSalary.isBlank() ? Double.MAX_VALUE : Double.parseDouble(mxSalary);
		    double minCom = mCom.isBlank() ? 0 : Double.parseDouble(mCom);
		    double maxCom = mxCom.isBlank() ? Double.MAX_VALUE : Double.parseDouble(mxCom);
		    
		    for (Manager m : gFilter.getgManageController().getManagerList()) {
		        if (matchesSalaryManagerIDCommission(m, managerID, minSalary, maxSalary, minCom, maxCom)) {
		            filteredList.add(m);
		        }
		    }
		    
		    gFilter.getgManageController().setManagerList(filteredList);
		    gFilter.getgManageController().updateTable();
		}

		private boolean matchesSalaryManagerIDCommission(Manager manager, int managerID, double minSalary, double maxSalary, double minCom, double maxCom) {
		    return manager.getManagerId() == managerID && 
		           manager.getSalary() >= minSalary && manager.getSalary() <= maxSalary &&
		           manager.getComission() >= minCom && manager.getComission() <= maxCom;
		}

		private void filterBySalaryManagerID(String mSalary, String mxSalary, String manID) {
		    List<Manager> filteredList = new ArrayList<>();
		    double minSalary = mSalary.isBlank() ? 0 : Double.parseDouble(mSalary);
		    double maxSalary = mxSalary.isBlank() ? Double.MAX_VALUE : Double.parseDouble(mxSalary);
		    int managerID = Integer.parseInt(manID);
		    
		    for (Manager m : gFilter.getgManageController().getManagerList()) {
		        if (matchesSalaryManagerID(m, managerID, minSalary, maxSalary)) {
		            filteredList.add(m);
		        }
		    }
		    
		    gFilter.getgManageController().setManagerList(filteredList);
		    gFilter.getgManageController().updateTable();
		}

		private boolean matchesSalaryManagerID(Manager manager, int managerID, double minSalary, double maxSalary) {
		    return manager.getManagerId() == managerID && manager.getSalary() >= minSalary && manager.getSalary() <= maxSalary;
		}

		private void filterByManagerID() {
			int managerID = Integer.parseInt(gFilter.getFieldManagerID().getText().strip());
			List<Manager> filteredList = new ArrayList<>();
			for(Manager m : gFilter.getgManageController().getManagerList()) {
				if(m.getManagerId() == managerID)
					filteredList.add(m);
			}
			gFilter.getgManageController().setManagerList(filteredList);
			gFilter.getgManageController().updateTable();
		}

		private void filterByDNI() {
			String DNI = gFilter.getFieldDNI().getText().strip();
			if (FieldUtils.validateDNI(DNI, gFilter,DNI)) {
				List<Manager> filteredList = new ArrayList<>();
				for (Manager m : gFilter.getgManageController().getManagerList()) {
					if (m.getDNI().equalsIgnoreCase(DNI))
						filteredList.add(m);
				}
				gFilter.getgManageController().setManagerList(filteredList);
				gFilter.getgManageController().updateTable();
			}
		}
		
		private boolean validateMinSalary(String minSalary) {
			if(minSalary.matches("\\d{1,4}(\\.\\d{2})?") || minSalary.isBlank())
				return true;
			
			return false;
		}
		
		private boolean validateMaxSalary(String maxSalary) {
			if(maxSalary.matches("\\d{1,4}(\\.\\d{2})?") || maxSalary.isBlank())
				return true;
			
			return false;
		}
		
		private boolean validateMinCommission(String minCommission) {
			if(minCommission.matches("\\d{1,2}\\.\\d{1,2}") || minCommission.isBlank())
				return true;
			
			return false;
		}
		
		private boolean validateMaxCommission(String minCommission) {
			if(minCommission.matches("\\d{1,2}\\.\\d{1,2}") || minCommission.isBlank())
				return true;
			
			return false;
		}
	}

	private class ItmListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			JCheckBox cbx = (JCheckBox) e.getSource();

			if (cbx == gFilter.getCbxDNI()) {
				if (gFilter.getCbxDNI().isSelected()) {
					toggleComponents(cbx, false);
					gFilter.getFieldDNI().setEditable(true);
					if (ConnectionDB.getCurrentUser() instanceof Admin) {
						gFilter.getCbxManagerID().setSelected(false);
					}
					gFilter.getCbxSalary().setSelected(false);
					gFilter.getCbxCommission().setSelected(false);
				} else {
					toggleComponents(cbx, true);
					gFilter.getFieldDNI().setEditable(false);
				}
			} else if (cbx == gFilter.getCbxManagerID()) {
				if (gFilter.getCbxManagerID().isSelected())
					toggleComponents(cbx, true);
				else
					toggleComponents(cbx, false);
			} else if (cbx == gFilter.getCbxSalary()) {
				if (gFilter.getCbxSalary().isSelected())
					toggleComponents(cbx, true);
				else
					toggleComponents(cbx, false);
			} else if (cbx == gFilter.getCbxCommission()) {
				if (gFilter.getCbxCommission().isSelected())
					toggleComponents(cbx, true);
				else
					toggleComponents(cbx, false);
			}
		}
	}

	private void distinguishUser() {
		if (ConnectionDB.getCurrentUser() instanceof Manager) {
			gFilter.getCbxManagerID().setEnabled(false);
		}
	}

	private void toggleComponents(JCheckBox cbx, boolean onOff) {
		if (cbx == gFilter.getCbxDNI()) {
			if (ConnectionDB.getCurrentUser() instanceof Admin)
				gFilter.getCbxManagerID().setEnabled(onOff);
			gFilter.getCbxSalary().setEnabled(onOff);
			gFilter.getCbxCommission().setEnabled(onOff);
		} else if (cbx == gFilter.getCbxManagerID()) {
			gFilter.getFieldManagerID().setEditable(onOff);
		} else if (cbx == gFilter.getCbxSalary()) {
			gFilter.getFieldMinSalary().setEditable(onOff);
			gFilter.getFieldMaxSalary().setEditable(onOff);
		} else if (cbx == gFilter.getCbxCommission()) {
			gFilter.getFieldMinCom().setEditable(onOff);
			gFilter.getFieldMaxCom().setEditable(onOff);
		}
	}

	private void resetAll() {
		gFilter.getCbxDNI().setSelected(false);
		gFilter.getFieldDNI().setText("");
		gFilter.getCbxManagerID().setSelected(false);
		gFilter.getFieldManagerID().setText("");
		gFilter.getCbxSalary().setSelected(false);
		gFilter.getFieldMinSalary().setText("");
		gFilter.getFieldMaxSalary().setText("");
		gFilter.getCbxCommission().setSelected(false);
		gFilter.getFieldMinCom().setText("");
		gFilter.getFieldMaxCom().setText("");
	}
}
