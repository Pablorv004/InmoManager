package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import models.Admin;
import models.Manager;
import models.User;
import util.ConnectionDB;
import util.FieldUtils;
import views.GUIMainManager;
import views.GUIManageManagers;

public class ControllerManageManagers {
	private GUIManageManagers gManageManagers;
	private List<Manager> managerList;
	private User currentUser;

	public ControllerManageManagers(GUIManageManagers gManageManagers) {
		this.gManageManagers = gManageManagers;
		this.currentUser = ConnectionDB.getCurrentUser();
		this.managerList = getManagers();
		updateTable();

		gManageManagers.addActListener(new ActListener());
		gManageManagers.addTableListener(new TableListener());
	}
	
	// PRIVATE CLASSES

	private class ActListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
	
			if (obj == gManageManagers.getBtnReturn()) {
				gManageManagers.dispose();
				new GUIMainManager(gManageManagers.getgManager().getgLogin());
			} else if (obj == gManageManagers.getBtnEdit()) {
				int selectedRow = gManageManagers.getTable().getSelectedRow();
				if(selectedRow != -1) {
					enableDisableComponents(true);
				}else
					JOptionPane.showMessageDialog(gManageManagers, "There's no manager selected in the table.\nPlease select one row","Error",JOptionPane.ERROR_MESSAGE);
			} else if (obj == gManageManagers.getBtnApply()) {
				applyChanges();
			}
		}
	}
	
	private class TableListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			// Disables editable textFields each time a row is clicked
			enableDisableComponents(false);
			int selectedRow = gManageManagers.getTable().getSelectedRow();
			Manager manager = findManager(String.valueOf(gManageManagers.getTable().getValueAt(selectedRow, 1)));
			if(manager != null)
				fillFields(manager);
		}
	}
	
	// METHODS
	
	private void applyChanges() {
//		gManageManagers.getFieldBank().setEditable(onOff);
//		gManageManagers.getFieldEmail().setEditable(onOff);
//		gManageManagers.getFieldPhone().setEditable(onOff);
//		gManageManagers.getFieldSalary().setEditable(onOff);
//		gManageManagers.getFieldCommission().setEditable(onOff);
//		gManageManagers.getBtnApply().setEnabled(onOff);
		boolean validBankAccount = FieldUtils.validateBankAccount(gManageManagers.getFieldBank().getText().strip(), gManageManagers);
		boolean validEmail = FieldUtils.validateEmail(gManageManagers.getFieldEmail().getText().strip(), gManageManagers);
		
		if(validEmail)
			System.out.println("BIEN!");
	}

	// Search for a manager by it's DNI and returns it
	private Manager findManager(String DNI) {
		for(Manager m : managerList)
			if(m.getDNI().equalsIgnoreCase(DNI))
				return m;
		
		return null;
	}
	
	// Fills JTextFields with the manager information
	private void fillFields(Manager manager) {
		gManageManagers.getFieldID().setText(String.valueOf(manager.getID()));
		gManageManagers.getFieldDNI().setText(manager.getDNI());
		gManageManagers.getFieldName().setText(manager.getFullName());
		gManageManagers.getFieldUsername().setText(manager.getUserName());
		gManageManagers.getFieldPassword().setText(manager.getPassword());
		gManageManagers.getFieldEmail().setText(manager.getEmail());
		gManageManagers.getFieldPhone().setText(String.valueOf(manager.getPhoneNum()));
		gManageManagers.getFieldCommission().setText(String.valueOf(manager.getComission()));
		gManageManagers.getFieldBank().setText(manager.getBankAccountNum());
		gManageManagers.getFieldHireDate().setText(String.valueOf(manager.getHireDate()));
		gManageManagers.getFieldManagerID().setText(String.valueOf(manager.getManagerId()));
		gManageManagers.getFieldSalary().setText(String.valueOf(manager.getSalary()));
	}
	
	// Enables or disables the components depending of the current user being an Admin or a Manager
	// on -> Activates  // off -> Deactivates 
	private void enableDisableComponents(Boolean onOff) {
		if (currentUser instanceof Admin) {
			for (JTextField textField : gManageManagers.getTextFieldList())
				textField.setEditable(onOff);
			gManageManagers.getBtnApply().setEnabled(onOff);
		}else if (currentUser instanceof Manager) {
			gManageManagers.getFieldBank().setEditable(onOff);
			gManageManagers.getFieldEmail().setEditable(onOff);
			gManageManagers.getFieldPhone().setEditable(onOff);
			gManageManagers.getFieldSalary().setEditable(onOff);
			gManageManagers.getFieldCommission().setEditable(onOff);
			gManageManagers.getBtnApply().setEnabled(onOff);
		}
		
		gManageManagers.getPanelForm().revalidate();
		gManageManagers.getPanelForm().repaint();
	}

	// Updates table with the information about the managers that the current
	// manager has under it's orders
	private void updateTable() {
		String[] headers = { "ID", "DNI", "Name", "Email", "Phone Number", "hireDate", "Manager ID", "Salary" };
		DefaultTableModel model = new DefaultTableModel(toDataList(), headers) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JTable table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.gManageManagers.setTable(table);
	}

	// Converts the List of managers into a bidimensional object array so it can be
	// received into DefaultTableModel constructor
	private Object[][] toDataList() {
		Object[][] datos = new Object[managerList.size()][];
		for (int i = 0; i < managerList.size(); i++) {
			datos[i] = managerList.get(i).toArray();
		}
		return datos;
	}

	// Gets all the managers that the current manager has under it's orders, then
	// returns it
	private List<Manager> getManagers() {
		List<Manager> mList = new ArrayList<>();

		try {
			Connection conn = ConnectionDB.connect();
			ResultSet result = null;

			if (currentUser instanceof Admin || ((Manager) currentUser).getManagerId() == 0) {
				result = conn.createStatement().executeQuery("SELECT * FROM inmomanager.Managers");
			} else if (currentUser instanceof Manager) {
				PreparedStatement pst = conn.prepareStatement("SELECT * FROM inmomanager.Managers WHERE managerId = ?");
				pst.setInt(1, currentUser.getID());
				result = pst.executeQuery();
			}

			while (result != null && result.next()) {
				addManagerToList(mList, result);
			}
			result.close();
			return mList;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return mList;
	}

	// Adds the current manager cointained in the ResultSet to the managers list
	private void addManagerToList(List<Manager> mList, ResultSet result) {
		try {
			int id = result.getInt("id");
			String dni = result.getString("DNI");
			String fullName = result.getString("fullName");
			String userName = result.getString("userName");
			String password = result.getString("password");
			String email = result.getString("email");
			int phoneNum = result.getInt("phoneNum");
			double comission = result.getDouble("commission");
			String bankAccount = checkBankAccountValue(result);
			LocalDateTime hireDate = result.getTimestamp("hireDate").toLocalDateTime();
			int managerID = checkManagerIdValue(result);
			double salary = result.getDouble("salary");

			mList.add(new Manager(id, dni, fullName, userName, password, email, phoneNum, comission, bankAccount,
					hireDate, managerID, salary));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Checks the managerId value of the current manager contained in the ResultSet
	private int checkManagerIdValue(ResultSet result) throws SQLException {
		int managerId = result.getInt("managerId");
		if (result.wasNull())
			managerId = 0;

		return managerId;
	}

	// Checks the bankAccount value of the current manager contained in the
	// ResultSet
	private String checkBankAccountValue(ResultSet result) throws SQLException {
		String bankAccount = result.getString("bankAccountNum");
		if (bankAccount == null) {
			bankAccount = "";
		}
		return bankAccount;
	}
}
