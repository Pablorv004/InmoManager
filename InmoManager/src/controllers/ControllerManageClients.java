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

import models.Client;
import util.ConnectionDB;
import util.FieldUtils;
import views.GUIMainAdmin;
import views.GUIManageClients;

public class ControllerManageClients {
	private GUIManageClients gClients;
	private List<Client> clientList;

	public ControllerManageClients(GUIManageClients gClients) {
		this.gClients = gClients;
		this.clientList = getClients();
		updateTable();

		showPassword();

		gClients.addActListener(new ActListener());
	}

	// PRIVATE CLASSES

	private class ActListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();

			if (obj == gClients.getBtnReturn()) {
				handleBtnReturn();
			} else if (obj == gClients.getBtnEdit()) {
				enableDisableComponents(true);
			} else if (obj == gClients.getBtnApply()) {
				checkChanges();
			} else if (obj == gClients.getBtnReset()) {
				clientList = getClients();
				updateTable();
			} else if (obj == gClients.getBtnDelete()) {
				handleDeleteBtn();
			}
		}

		private void handleDeleteBtn() {
			int selectedRow = gClients.getTable().getSelectedRow();
			if(selectedRow != -1)
				deleteClient(selectedRow);
			else
				JOptionPane.showMessageDialog(gClients, "You must select a client from the table","Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	private class TableListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			// Disables editable textFields each time a row is clicked
			enableDisableComponents(false);
			gClients.getBtnEdit().setEnabled(true);
			int selectedRow = gClients.getTable().getSelectedRow();
			Client client = findClient(String.valueOf(gClients.getTable().getValueAt(selectedRow, 1)));
			if (client != null)
				fillFields(client);
		}
	}

	// METHODS
	
	private void deleteClient(int selectedRow) {
		int id = Integer.parseInt(String.valueOf(gClients.getTable().getValueAt(selectedRow, 0)));
		try {
			Connection conn = ConnectionDB.connect();
			PreparedStatement pst = conn.prepareStatement("DELETE FROM inmomanager.Clients WHERE id = ?");
			pst.setInt(1, id);
			int success = pst.executeUpdate();
			if(success != 0) {
				JOptionPane.showMessageDialog(gClients, "Client deleted from the system");
				clientList = getClients();
				updateTable();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void handleBtnReturn() {
		gClients.dispose();
		new GUIMainAdmin(gClients.getgLogin());
	}

	// If the current user is an Administrator the password will not be hidden
	private void showPassword() {
		gClients.getFieldPassword().setEchoChar((char) 0);
	}

	// Checks if the values from the fields are valid
	private void checkChanges() {
		int selectedRow = gClients.getTable().getSelectedRow();
		String dni = String.valueOf(gClients.getTable().getValueAt(selectedRow,1));
		boolean validID = FieldUtils.validateUserID(gClients.getFieldID().getText().strip(), "Clients", gClients,
				dni);
		boolean validDNI = FieldUtils.validateDNI(gClients.getFieldDNI().getText(), gClients, dni);
		boolean validName = FieldUtils.validateName(gClients.getFieldName().getText().strip().replaceAll("\\s+", " "),
				gClients);
		boolean validUsername = FieldUtils.validateUsername(gClients.getFieldUsername().getText().strip(), gClients,
				dni);
		boolean validPassword = FieldUtils.validatePassword(gClients.getFieldPassword().getPassword(), gClients);
		boolean validEmail = FieldUtils.validateEmail(gClients.getFieldEmail().getText().strip(), gClients,
				dni);
		boolean validPhone = FieldUtils.validatePhone(gClients.getFieldPhone().getText().strip(), gClients,
				dni);
		boolean validRegion = FieldUtils.validateRegion(gClients.getFieldRegion().getText().strip().replaceAll("\\s+", " "), gClients);
		boolean validBankAccount = FieldUtils.validateBankAccount(gClients.getFieldBank().getText().strip(), gClients);

		if (validID && validDNI && validName && validUsername && validPassword && validBankAccount && validEmail
				&& validPhone && validRegion) {
			applyChanges(dni);
			clientList = getClients();
			updateTable();
		}

	}

	// Applies the changes updating the client information searching by its DNI
	private void applyChanges(String oldDNI) {
		try {
			Connection conn = ConnectionDB.connect();
			PreparedStatement pst = conn.prepareStatement("UPDATE inmomanager.Clients " + "SET id = ?, " + "DNI = ?, "
					+ "fullName = ?, " + "userName = ?, " + "password = ?, " + "email = ?, " + "phoneNum = ?, "
					+ "region = ?, " + "bankAccountNum = ? " + "WHERE DNI = ?");
			pst.setObject(1, gClients.getFieldID().getText().strip());
			pst.setObject(2, gClients.getFieldDNI().getText().strip());
			pst.setObject(3, gClients.getFieldName().getText().strip());
			pst.setObject(4, gClients.getFieldUsername().getText().strip());
			pst.setObject(5, String.valueOf(gClients.getFieldPassword().getPassword()));
			pst.setObject(6, gClients.getFieldEmail().getText().strip());
			pst.setObject(7, gClients.getFieldPhone().getText().strip());
			pst.setObject(8, gClients.getFieldRegion().getText().strip());
			pst.setObject(9, gClients.getFieldBank().getText().strip());
			pst.setObject(10, oldDNI);

			int result = pst.executeUpdate();

			if (result != 0)
				JOptionPane.showMessageDialog(gClients, "The client data has been updated", "Client updated",
						JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(gClients, "There has been an unexpected error", "Error",
						JOptionPane.ERROR_MESSAGE);

			pst.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		enableDisableComponents(false);
		gClients.getBtnEdit().setEnabled(false);
	}

	// Search for a client by it's DNI and returns it
	private Client findClient(String DNI) {
		for (Client c : clientList)
			if (c.getDNI().equalsIgnoreCase(DNI))
				return c;

		return null;
	}

	// Fills JTextFields with the client information
	private void fillFields(Client client) {
		gClients.getFieldID().setText(String.valueOf(client.getID()));
		gClients.getFieldDNI().setText(client.getDNI());
		gClients.getFieldName().setText(client.getFullName());
		gClients.getFieldUsername().setText(client.getUserName());
		gClients.getFieldPassword().setText(client.getPassword());
		gClients.getFieldEmail().setText(client.getEmail());
		gClients.getFieldRegion().setText(client.getRegion());
		gClients.getFieldPhone().setText(String.valueOf(client.getPhoneNum()));
		gClients.getFieldBank().setText(client.getBankAccountNum());
		gClients.getFieldSignDate().setText(getDate(client));
	}

	// Gets only the date from timestamp
	private String getDate(Client client) {
		String[] timestampArray = String.valueOf(client.getCreationTime()).split("T");
		return timestampArray[0];
	}

	// Enables or disables the components
	// on -> Activates // off -> Deactivates
	private void enableDisableComponents(Boolean onOff) {
		for (JTextField textField : gClients.getTextFieldList()) {
			if (textField != gClients.getFieldSignDate())
				textField.setEditable(onOff);
		}
		gClients.getBtnApply().setEnabled(onOff);
		gClients.getFieldBank().setEditable(onOff);
		gClients.getFieldEmail().setEditable(onOff);
		gClients.getFieldPhone().setEditable(onOff);
	}


	// Updates table with the information about the clients
	public void updateTable() {
		String[] headers = { "ID", "DNI", "Name", "Email", "Phone Number", "Region"};
		DefaultTableModel model = new DefaultTableModel(toDataList(), headers) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JTable table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.gClients.setTable(table);
		this.gClients.getScrollPane().setViewportView(table);
		this.gClients.addTableListener(new TableListener());
	}

	// Converts the List of clients into a bidimensional object array so it can be
	// received into DefaultTableModel constructor
	private Object[][] toDataList() {
		Object[][] datos = new Object[clientList.size()][];
		for (int i = 0; i < clientList.size(); i++) {
			datos[i] = clientList.get(i).toArray();
		}
		return datos;
	}

	// Gets all the clients from the database and returns an ArrayList
	private List<Client> getClients() {
		List<Client> mList = new ArrayList<>();

		try {
			Connection conn = ConnectionDB.connect();
			
			ResultSet result = conn.createStatement().executeQuery("SELECT * FROM inmomanager.Clients");

			while (result.next()) {
				addClientToList(mList, result);
			}
			
			result.close();
			return mList;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return mList;
	}

	// Adds the current client cointained in the ResultSet to the clients list
	private void addClientToList(List<Client> mList, ResultSet result) {
		try {
			int id = result.getInt("id");
			String dni = result.getString("DNI");
			String fullName = result.getString("fullName");
			String userName = result.getString("userName");
			String password = result.getString("password");
			String email = result.getString("email");
			int phoneNum = result.getInt("phoneNum");
			String region = result.getString("region");
			String bankAccount = checkBankAccountValue(result);
			LocalDateTime signDate = result.getTimestamp("creationTime").toLocalDateTime();

			mList.add(new Client(id, dni, fullName, userName, password, email, phoneNum, region, bankAccount, signDate));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Checks the bankAccount value of the current client contained in the
	// ResultSet
	private String checkBankAccountValue(ResultSet result) throws SQLException {
		String bankAccount = result.getString("bankAccountNum");
		if (bankAccount == null) {
			bankAccount = "";
		}
		return bankAccount;
	}

	public GUIManageClients getgClients() {
		return gClients;
	}

	public List<Client> getManagerList() {
		return clientList;
	}

	public void setgManage(GUIManageClients gClients) {
		this.gClients = gClients;
	}

	public void setClientsList(List<Client> clientList) {
		this.clientList = clientList;
	}

}
