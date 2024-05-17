package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionListener;

import controllers.ControllerManageManagers;
import util.GlobalResources;

public class GUIManageManagers extends JFrame {

	private static final long serialVersionUID = 1L;
	private GUIMainManager gManager;
	private JScrollPane scrollPane;
	private JPanel contentPane;
	private JPanel panelTitle;
	private JPanel panelEdit;
	private JPanel panelCredentials;
	private JTable table;
	private JButton btnReset;
	private JButton btnSearch;
	private JButton btnNext;
	private JButton btnPrevious;
	private JButton btnEdit;
	private JButton btnApply;
	private JButton btnReturn;
	private JTextField fieldID;
	private JTextField fieldDNI;
	private JTextField fieldName;
	private JTextField fieldUsername;
	private JPasswordField fieldPassword;
	private JTextField fieldEmail;
	private JTextField fieldPhone;
	private JTextField fieldCommission;
	private JTextField fieldBank;
	private JTextField fieldHireDate;
	private JTextField fieldManagerID;
	private JTextField fieldSalary;
	private JLabel lblLogo;
	private JPanel panelForm;
	private JPanel panelImage;
	private JLabel lblImage_1;
	private JLabel lblID;
	private JLabel lblDNI;
	private JLabel lblName;
	private JLabel lblCredentials;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblEmail;
	private JLabel lblPhone;
	private JLabel lblCommission;
	private JLabel lblBank;
	private JLabel lblHireDate;
	private JLabel lblManagerID;
	private JLabel lblSalary;
	private List<JTextField> textFieldList;

	public GUIManageManagers(GUIMainManager gManager) {
		super("Managers Managements");
		this.gManager = gManager;
		this.textFieldList = new ArrayList<>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 716, 630);
		setLocationRelativeTo(gManager);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		panelTitle = new JPanel();
		panelTitle.setBounds(10, 11, 682, 60);
		contentPane.add(panelTitle);
		panelTitle.setLayout(null);

		JLabel lblTitle = new JLabel("Manager Management");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTitle.setBounds(192, 11, 329, 37);
		panelTitle.add(lblTitle);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);

		lblLogo = new JLabel("");
		lblLogo.setIcon(GlobalResources.getIconSmall());
		lblLogo.setBounds(0, 2, 64, 55);
		panelTitle.add(lblLogo);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 71, 714, 8);
		contentPane.add(separator);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 82, 682, 168);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 283, 714, 8);
		contentPane.add(separator_1);

		btnSearch = new JButton("Search");
		btnSearch.setBounds(601, 253, 88, 23);
		contentPane.add(btnSearch);

		btnReset = new JButton("Reset");
		btnReset.setBounds(506, 254, 88, 23);
		contentPane.add(btnReset);

		panelForm = new JPanel();
		panelForm.setBorder(new LineBorder(new Color(192, 192, 192)));
		panelForm.setBounds(10, 302, 479, 280);
		contentPane.add(panelForm);
		panelForm.setLayout(null);

		lblID = new JLabel("ID");
		lblID.setBounds(413, 11, 25, 20);
		panelForm.add(lblID);

		fieldID = new JTextField();
		fieldID.setEditable(false);
		fieldID.setHorizontalAlignment(SwingConstants.LEFT);
		fieldID.setBounds(434, 11, 35, 20);
		panelForm.add(fieldID);
		fieldID.setColumns(10);

		lblDNI = new JLabel("DNI");
		lblDNI.setBounds(10, 45, 25, 20);
		panelForm.add(lblDNI);

		fieldDNI = new JTextField();
		fieldDNI.setEditable(false);
		fieldDNI.setHorizontalAlignment(SwingConstants.LEFT);
		fieldDNI.setColumns(10);
		fieldDNI.setBounds(52, 45, 83, 20);
		panelForm.add(fieldDNI);

		panelEdit = new JPanel();
		panelEdit.setBorder(new LineBorder(new Color(192, 192, 192)));
		panelEdit.setBounds(285, 240, 189, 35);
		panelForm.add(panelEdit);
		panelEdit.setLayout(null);

		btnEdit = new JButton("Edit");
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnEdit.setBounds(4, 6, 60, 23);
		panelEdit.add(btnEdit);

		btnApply = new JButton("Apply Changes");
		btnApply.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnApply.setEnabled(false);
		btnApply.setBounds(68, 6, 117, 23);
		panelEdit.add(btnApply);

		lblName = new JLabel("Name");
		lblName.setBounds(10, 79, 43, 20);
		panelForm.add(lblName);

		fieldName = new JTextField();
		fieldName.setEditable(false);
		fieldName.setHorizontalAlignment(SwingConstants.LEFT);
		fieldName.setColumns(10);
		fieldName.setBounds(52, 79, 210, 20);
		panelForm.add(fieldName);

		panelCredentials = new JPanel();
		panelCredentials.setBorder(new LineBorder(new Color(192, 192, 192)));
		panelCredentials.setBounds(285, 153, 189, 76);
		panelForm.add(panelCredentials);
		panelCredentials.setLayout(null);

		lblUsername = new JLabel("Username");
		lblUsername.setBounds(6, 12, 68, 20);
		panelCredentials.add(lblUsername);

		lblPassword = new JLabel("Password");
		lblPassword.setBounds(6, 44, 68, 20);
		panelCredentials.add(lblPassword);

		fieldUsername = new JTextField();
		fieldUsername.setEditable(false);
		fieldUsername.setHorizontalAlignment(SwingConstants.LEFT);
		fieldUsername.setColumns(10);
		fieldUsername.setBounds(80, 12, 103, 20);
		panelCredentials.add(fieldUsername);

		fieldPassword = new JPasswordField();
		fieldPassword.setEditable(false);
		fieldPassword.setHorizontalAlignment(SwingConstants.LEFT);
		fieldPassword.setColumns(10);
		fieldPassword.setBounds(80, 44, 103, 20);
		panelCredentials.add(fieldPassword);

		lblCredentials = new JLabel("Credentials");
		lblCredentials.setHorizontalAlignment(SwingConstants.CENTER);
		lblCredentials.setBounds(337, 134, 75, 14);
		panelForm.add(lblCredentials);

		lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 113, 43, 20);
		panelForm.add(lblEmail);

		fieldEmail = new JTextField();
		fieldEmail.setHorizontalAlignment(SwingConstants.LEFT);
		fieldEmail.setEditable(false);
		fieldEmail.setColumns(10);
		fieldEmail.setBounds(52, 113, 210, 20);
		panelForm.add(fieldEmail);

		lblPhone = new JLabel("Phone");
		lblPhone.setBounds(10, 147, 43, 20);
		panelForm.add(lblPhone);

		fieldPhone = new JTextField();
		fieldPhone.setHorizontalAlignment(SwingConstants.LEFT);
		fieldPhone.setEditable(false);
		fieldPhone.setColumns(10);
		fieldPhone.setBounds(52, 147, 106, 20);
		panelForm.add(fieldPhone);

		lblCommission = new JLabel("Commission");
		lblCommission.setBounds(10, 249, 78, 20);
		panelForm.add(lblCommission);

		fieldCommission = new JTextField();
		fieldCommission.setHorizontalAlignment(SwingConstants.LEFT);
		fieldCommission.setEditable(false);
		fieldCommission.setColumns(10);
		fieldCommission.setBounds(88, 249, 35, 20);
		panelForm.add(fieldCommission);

		lblBank = new JLabel("Bank Account");
		lblBank.setBounds(10, 11, 90, 20);
		panelForm.add(lblBank);

		fieldBank = new JTextField();
		fieldBank.setHorizontalAlignment(SwingConstants.LEFT);
		fieldBank.setEditable(false);
		fieldBank.setColumns(10);
		fieldBank.setBounds(96, 11, 307, 20);
		panelForm.add(fieldBank);

		lblHireDate = new JLabel("Hire Date");
		lblHireDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblHireDate.setBounds(10, 181, 68, 20);
		panelForm.add(lblHireDate);

		fieldHireDate = new JTextField();
		fieldHireDate.setHorizontalAlignment(SwingConstants.LEFT);
		fieldHireDate.setEditable(false);
		fieldHireDate.setColumns(10);
		fieldHireDate.setBounds(72, 181, 131, 20);
		panelForm.add(fieldHireDate);

		lblManagerID = new JLabel("Manager ID");
		lblManagerID.setBounds(142, 249, 78, 20);
		panelForm.add(lblManagerID);

		fieldManagerID = new JTextField();
		fieldManagerID.setHorizontalAlignment(SwingConstants.LEFT);
		fieldManagerID.setEditable(false);
		fieldManagerID.setColumns(10);
		fieldManagerID.setBounds(223, 249, 35, 20);
		panelForm.add(fieldManagerID);

		lblSalary = new JLabel("Salary");
		lblSalary.setBounds(10, 215, 43, 20);
		panelForm.add(lblSalary);

		fieldSalary = new JTextField();
		fieldSalary.setHorizontalAlignment(SwingConstants.LEFT);
		fieldSalary.setEditable(false);
		fieldSalary.setColumns(10);
		fieldSalary.setBounds(63, 215, 61, 20);
		panelForm.add(fieldSalary);

		panelImage = new JPanel();
		panelImage.setBounds(506, 302, 186, 210);
		contentPane.add(panelImage);
		panelImage.setLayout(null);

		lblImage_1 = new JLabel("Image");
		lblImage_1.setBounds(7, 0, 171, 167);
		lblImage_1.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblImage_1.setHorizontalAlignment(SwingConstants.CENTER);
		panelImage.add(lblImage_1);

		btnNext = new JButton("Next");
		btnNext.setBounds(6, 178, 77, 23);
		panelImage.add(btnNext);

		btnPrevious = new JButton("Previous");
		btnPrevious.setBounds(89, 178, 89, 23);
		panelImage.add(btnPrevious);

		btnReturn = new JButton("");
		btnReturn.setBounds(642, 11, 30, 30);
		btnReturn.setIcon(GlobalResources.getIconReturn());
		btnReturn.setContentAreaFilled(false);
		btnReturn.setBorderPainted(false);
		panelTitle.add(btnReturn);

		new ControllerManageManagers(this);
		fillTextFieldList();
		setVisible(true);
	}

	public void addActListener(ActionListener listener) {
		this.btnApply.addActionListener(listener);
		this.btnEdit.addActionListener(listener);
		this.btnNext.addActionListener(listener);
		this.btnPrevious.addActionListener(listener);
		this.btnReset.addActionListener(listener);
		this.btnReturn.addActionListener(listener);
		this.btnSearch.addActionListener(listener);
	}

	public void addTableListener(ListSelectionListener listener) {
		this.table.getSelectionModel().addListSelectionListener(listener);
	}

	// Fills the JTextField list with all JTextFields of the view
	private void fillTextFieldList() {
		textFieldList.add(fieldID);
		textFieldList.add(fieldDNI);
		textFieldList.add(fieldName);
		textFieldList.add(fieldUsername);
		textFieldList.add(fieldPassword);
		textFieldList.add(fieldEmail);
		textFieldList.add(fieldPhone);
		textFieldList.add(fieldCommission);
		textFieldList.add(fieldBank);
		textFieldList.add(fieldHireDate);
		textFieldList.add(fieldManagerID);
		textFieldList.add(fieldSalary);
	}

	// GETTERS AND SETTERS

	public GUIMainManager getgManager() {
		return gManager;
	}

	public void setgManager(GUIMainManager gManager) {
		this.gManager = gManager;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
		scrollPane.setViewportView(table);
		scrollPane.revalidate();
		scrollPane.repaint();
	}

	public JButton getBtnReset() {
		return btnReset;
	}

	public void setBtnReset(JButton btnReset) {
		this.btnReset = btnReset;
	}

	public JButton getBtnSearch() {
		return btnSearch;
	}

	public void setBtnSearch(JButton btnSearch) {
		this.btnSearch = btnSearch;
	}

	public JLabel getLblImage_1() {
		return lblImage_1;
	}

	public void setLblImage_1(JLabel lblImage_1) {
		this.lblImage_1 = lblImage_1;
	}

	public JButton getBtnNext() {
		return btnNext;
	}

	public void setBtnNext(JButton btnNext) {
		this.btnNext = btnNext;
	}

	public JButton getBtnPrevious() {
		return btnPrevious;
	}

	public void setBtnPrevious(JButton btnPrevious) {
		this.btnPrevious = btnPrevious;
	}

	public JButton getBtnEdit() {
		return btnEdit;
	}

	public void setBtnEdit(JButton btnEdit) {
		this.btnEdit = btnEdit;
	}

	public JButton getBtnApply() {
		return btnApply;
	}

	public void setBtnApply(JButton btnApply) {
		this.btnApply = btnApply;
	}

	public JTextField getFieldID() {
		return fieldID;
	}

	public void setFieldID(JTextField fieldID) {
		this.fieldID = fieldID;
	}

	public JTextField getFieldDNI() {
		return fieldDNI;
	}

	public void setFieldDNI(JTextField fieldDNI) {
		this.fieldDNI = fieldDNI;
	}

	public JTextField getFieldName() {
		return fieldName;
	}

	public void setFieldName(JTextField fieldName) {
		this.fieldName = fieldName;
	}

	public JTextField getFieldUsername() {
		return fieldUsername;
	}

	public void setFieldUsername(JTextField fieldUsername) {
		this.fieldUsername = fieldUsername;
	}

	public JPasswordField getFieldPassword() {
		return fieldPassword;
	}

	public void setFieldPassword(JPasswordField fieldPassword) {
		this.fieldPassword = fieldPassword;
	}

	public JTextField getFieldEmail() {
		return fieldEmail;
	}

	public void setFieldEmail(JTextField fieldEmail) {
		this.fieldEmail = fieldEmail;
	}

	public JTextField getFieldPhone() {
		return fieldPhone;
	}

	public void setFieldPhone(JTextField fieldPhone) {
		this.fieldPhone = fieldPhone;
	}

	public JTextField getFieldCommission() {
		return fieldCommission;
	}

	public void setFieldCommission(JTextField fieldCommission) {
		this.fieldCommission = fieldCommission;
	}

	public JTextField getFieldBank() {
		return fieldBank;
	}

	public void setFieldBank(JTextField fieldBank) {
		this.fieldBank = fieldBank;
	}

	public JTextField getFieldHireDate() {
		return fieldHireDate;
	}

	public void setFieldHireDate(JTextField fieldHireDate) {
		this.fieldHireDate = fieldHireDate;
	}

	public JTextField getFieldManagerID() {
		return fieldManagerID;
	}

	public void setFieldManagerID(JTextField fieldManagerID) {
		this.fieldManagerID = fieldManagerID;
	}

	public JTextField getFieldSalary() {
		return fieldSalary;
	}

	public void setFieldSalary(JTextField fieldSalary) {
		this.fieldSalary = fieldSalary;
	}

	public JButton getBtnReturn() {
		return btnReturn;
	}

	public void setBtnReturn(JButton btnReturn) {
		this.btnReturn = btnReturn;
	}

	public List<JTextField> getTextFieldList() {
		return textFieldList;
	}

	public void setTextFieldList(List<JTextField> textFieldList) {
		this.textFieldList = textFieldList;
	}

	public JPanel getPanelEdit() {
		return panelEdit;
	}

	public JPanel getPanelCredentials() {
		return panelCredentials;
	}

	public JPanel getPanelForm() {
		return panelForm;
	}

	public JPanel getPanelImage() {
		return panelImage;
	}

	public void setPanelEdit(JPanel panelEdit) {
		this.panelEdit = panelEdit;
	}

	public void setPanelCredentials(JPanel panelCredentials) {
		this.panelCredentials = panelCredentials;
	}

	public void setPanelForm(JPanel panelForm) {
		this.panelForm = panelForm;
	}

	public void setPanelImage(JPanel panelImage) {
		this.panelImage = panelImage;
	}

}
