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
import views.GUIFilterManager;
import views.GUIMainAdmin;
import views.GUIMainManager;
import views.GUIManageManagers;

public class ControllerManageManagers {
	private GUIManageManagers gManage;
	private List<Manager> managerList;
	private User currentUser;

	public ControllerManageManagers(GUIManageManagers gManageManagers) {
		this.gManage = gManageManagers;
		this.currentUser = ConnectionDB.getCurrentUser();
		this.managerList = getManagers();
		updateTable();
		
		showPassword();

		gManageManagers.addActListener(new ActListener());
	}
	
	

	// PRIVATE CLASSES

	private class ActListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
	
			if (obj == gManage.getBtnReturn()) {
				handleBtnReturn();
			} else if (obj == gManage.getBtnEdit()) {
				enableDisableComponents(true);
			} else if (obj == gManage.getBtnApply()) {
				checkChanges();
			} else if (obj == gManage.getBtnFilter()) {
				gManage.setEnabled(false);
				new GUIFilterManager(ControllerManageManagers.this);
			} else if (obj == gManage.getBtnReset()) {
				managerList = getManagers();
				updateTable();
			}
		}
	}
	
	private class TableListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			// Disables editable textFields each time a row is clicked
			enableDisableComponents(false);
			gManage.getBtnEdit().setEnabled(true);
			int selectedRow = gManage.getTable().getSelectedRow();
			Manager manager = findManager(String.valueOf(gManage.getTable().getValueAt(selectedRow, 1)));
			if(manager != null)
				fillFields(manager);
		}
	}
	
	// METHODS
	
	private void handleBtnReturn() {
		gManage.dispose();
		if(currentUser instanceof Manager)
			new GUIMainManager(gManage.getgLogin());
		else if(currentUser instanceof Admin)
			new GUIMainAdmin(gManage.getgLogin());
	}

	// If the current user is an Administrator the password will not be hidden
	private void showPassword() {
		if(currentUser instanceof Admin) {
			gManage.getFieldPassword().setEchoChar((char)0);
		}
	}

	// Checks if the values from the fields are valid
	private void checkChanges() {
		int selectedRow = gManage.getTable().getSelectedRow();
		String dni = String.valueOf(gManage.getTable().getValueAt(selectedRow,1));
		boolean validID = FieldUtils.validateUserID(gManage.getFieldID().getText().strip(), "Managers", gManage,dni);
		boolean validDNI = FieldUtils.validateDNI(gManage.getFieldDNI().getText(), gManage, dni);
		boolean validName = FieldUtils.validateName(gManage.getFieldName().getText().strip().replaceAll("\\s+", " "),gManage);
		boolean validUsername = FieldUtils.validateUsername(gManage.getFieldUsername().getText().strip(), gManage,dni);
		boolean validPassword = FieldUtils.validatePassword(gManage.getFieldPassword().getPassword(), gManage);
		boolean validEmail = FieldUtils.validateEmail(gManage.getFieldEmail().getText().strip(), gManage,dni);
		boolean validPhone = FieldUtils.validatePhone(gManage.getFieldPhone().getText().strip(), gManage,dni);
		boolean validCommission = FieldUtils.validateComission(gManage.getFieldCommission().getText().strip(), gManage);
		boolean validBankAccount = FieldUtils.validateBankAccount(gManage.getFieldBank().getText().strip(), gManage);
		boolean validManagerID = FieldUtils.validateManagerID(gManage.getFieldManagerID().getText().strip(), gManage);
		boolean validSalary = FieldUtils.validateSalary(gManage.getFieldSalary().getText().strip(), gManage);

		if (validID && validDNI && validName && validUsername && validPassword && validBankAccount && validEmail
				&& validPhone && validSalary && validCommission && validManagerID) {
			applyChanges(dni);
			managerList = getManagers();
			updateTable();
		}

	}

	// Applies the changes updating the manager information searching by its DNI
	private void applyChanges(String oldDNI) {
		try {
			Connection conn = ConnectionDB.connect();
			PreparedStatement pst = conn.prepareStatement("UPDATE inmomanager.Managers "
														+ "SET id = ?, "
															+ "DNI = ?, "
															+ "fullName = ?, "
															+ "userName = ?, "
															+ "password = ?, "
															+ "email = ?, "
															+ "phoneNum = ?, "
															+ "commission = ?, "
															+ "bankAccountNum = ?, "
															+ "managerID = ?, "
															+ "salary = ?"
														+ "WHERE DNI = ?");
			pst.setObject(1, gManage.getFieldID().getText().strip());
			pst.setObject(2, gManage.getFieldDNI().getText().strip());
			pst.setObject(3, gManage.getFieldName().getText().strip());
			pst.setObject(4, gManage.getFieldUsername().getText().strip());
			pst.setObject(5, String.valueOf(gManage.getFieldPassword().getPassword()));
			pst.setObject(6, gManage.getFieldEmail().getText().strip());
			pst.setObject(7, gManage.getFieldPhone().getText().strip());
			pst.setObject(8, gManage.getFieldCommission().getText().strip());
			pst.setObject(9, gManage.getFieldBank().getText().strip());
			if(gManage.getFieldManagerID().getText().strip().equals("0"))
				pst.setObject(10, null);
			else
				pst.setObject(10, gManage.getFieldManagerID().getText().strip());
			pst.setObject(11, gManage.getFieldSalary().getText().strip());
			pst.setObject(12, oldDNI);
			
			int result = pst.executeUpdate();
			
			if(result != 0)
				JOptionPane.showMessageDialog(gManage, "The manager data has been updated", "Manager updated",
						JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(gManage, "There has been an unexpected error", "Error",
						JOptionPane.ERROR_MESSAGE);
			
			pst.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
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
		gManage.getFieldID().setText(String.valueOf(manager.getID()));
		gManage.getFieldDNI().setText(manager.getDNI());
		gManage.getFieldName().setText(manager.getFullName());
		gManage.getFieldUsername().setText(manager.getUserName());
		gManage.getFieldPassword().setText(manager.getPassword());
		gManage.getFieldEmail().setText(manager.getEmail());
		gManage.getFieldPhone().setText(String.valueOf(manager.getPhoneNum()));
		gManage.getFieldCommission().setText(String.valueOf(manager.getComission()));
		gManage.getFieldBank().setText(manager.getBankAccountNum());
		gManage.getFieldHireDate().setText(getDate(manager));
		gManage.getFieldManagerID().setText(String.valueOf(manager.getManagerId()));
		gManage.getFieldSalary().setText(String.valueOf(manager.getSalary()));
	}
	
	// Gets only the date from timestamp
	private String getDate(Manager manager) {
		String [] timestampArray = String.valueOf(manager.getHireDate()).split("T");
		return timestampArray[0];
	}

	// Enables or disables the components depending of the current user being an Admin or a Manager
	// on -> Activates  // off -> Deactivates 
	private void enableDisableComponents(Boolean onOff) {
		if (currentUser instanceof Admin) {
			for (JTextField textField : gManage.getTextFieldList()) {
				if(textField != gManage.getFieldHireDate())
					textField.setEditable(onOff);
			}
			gManage.getBtnApply().setEnabled(onOff);
		}else if (currentUser instanceof Manager) {
			gManage.getFieldBank().setEditable(onOff);
			gManage.getFieldEmail().setEditable(onOff);
			gManage.getFieldPhone().setEditable(onOff);
			gManage.getFieldSalary().setEditable(onOff);
			gManage.getFieldCommission().setEditable(onOff);
			gManage.getBtnApply().setEnabled(onOff);
		}
	}

	// Updates table with the information about the managers that the current
	// manager has under it's orders
	public void updateTable() {
		String[] headers = { "ID", "DNI", "Name", "Email", "Phone Number", "hireDate", "Manager ID", "Salary" };
		DefaultTableModel model = new DefaultTableModel(toDataList(), headers) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JTable table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.gManage.setTable(table);
		this.gManage.addTableListener(new TableListener());
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

	public GUIManageManagers getgManage() {
		return gManage;
	}

	public List<Manager> getManagerList() {
		return managerList;
	}

	public void setgManage(GUIManageManagers gManage) {
		this.gManage = gManage;
	}

	public void setManagerList(List<Manager> managerList) {
		this.managerList = managerList;
	}
	
	
}
