package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import models.Admin;
import models.Manager;
import models.User;
import util.ConnectionDB;

public class ControllerManageManagers {
	GUIManageManagers gManageManagers;
	List<Manager> managerList;
	User currentUser;

	public ControllerManageManagers(GUIManageManagers gManageManagers) {
		this.gManageManagers = gManageManagers;
		this.currentUser = ConnectionDB.getCurrentUser();
		this.managerList = getManagers();
		updateTable();
		
		
		this.gManageManagers.revalidate();
		this.gManageManagers.repaint();

		gManageManagers.addActListener(new ActListener());
	}

	// Updates table with the information about the managers that the current manager has under it's orders
	private void updateTable() {
		String [] headers = {"ID","DNI", "Name", "Email","Phone Number", "hireDate", "Manager ID", "Salary"};
		DefaultTableModel model = new DefaultTableModel(toDataList(), headers) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JTable table = new JTable(model);
		this.gManageManagers.setTable(table);
	}

	// Converts the List of managers into a bidimensional object array so it can be received into DefaultTableModel constructor
	private Object[][] toDataList() {
		Object[][] datos = new Object[managerList.size()][];
        for (int i = 0; i < managerList.size(); i++) {
            datos[i] = managerList.get(i).toArray();
        }
        return datos;
	}

	// Gets all the managers that the current manager has under it's orders, then returns it
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

	// Checks the bankAccount value of the current manager contained in the ResultSet
	private String checkBankAccountValue(ResultSet result) throws SQLException {
		String bankAccount = result.getString("bankAccountNum");
		if (bankAccount == null) {
			bankAccount = "";
		}
		return bankAccount;
	}

	private class ActListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();

			if (obj == gManageManagers.getBtnReturn()) {
				gManageManagers.dispose();
				new GUIMainManager(gManageManagers.getgManager().getgLogin());
			}
		}
	}
}
