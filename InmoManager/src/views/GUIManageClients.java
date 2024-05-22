package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
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

import controllers.ControllerManageClients;
import util.GlobalResources;

public class GUIManageClients extends JFrame {

	private static final long serialVersionUID = 1L;
	private GUILogin gLogin;
	private JScrollPane scrollPane;
	private JPanel contentPane;
	private JPanel panelTitle;
	private JPanel panelEdit;
	private JPanel panelCredentials;
	private JPanel panelImage;
	private JTable table;
	private JButton btnReset;
	private JButton btnFilter;
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
	private JTextField fieldBank;
	private JTextField fieldSignDate;
	private JLabel lblLogo;
	private JPanel panelForm;
	private JLabel lblID;
	private JLabel lblDNI;
	private JLabel lblName;
	private JLabel lblCredentials;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblEmail;
	private JLabel lblPhone;
	private JLabel lblBank;
	private JLabel lblHireDate;
	private JLabel lblImage;
	private List<JTextField> textFieldList;
	private JLabel lblRegion;
	private JTextField fieldRegion;
	private JButton btnDelete;

	public GUIManageClients(GUILogin gLogin) {
		super("Managers Management");
		setTitle("Clients Management");
		this.gLogin = gLogin;
		this.textFieldList = new ArrayList<>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 716, 599);
		setResizable(false);
		setLocationRelativeTo(gLogin);

		GlobalResources.setFrameIcon(this);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		panelTitle = new JPanel();
		panelTitle.setBounds(10, 11, 682, 60);
		contentPane.add(panelTitle);
		panelTitle.setLayout(null);

		JLabel lblTitle = new JLabel("Clients Management");
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

		btnFilter = new JButton("Filter");
		btnFilter.setBounds(601, 253, 88, 23);
		contentPane.add(btnFilter);

		btnReset = new JButton("Reset");
		btnReset.setBounds(506, 254, 88, 23);
		contentPane.add(btnReset);

		panelForm = new JPanel();
		panelForm.setBorder(new LineBorder(new Color(192, 192, 192)));
		panelForm.setBounds(10, 302, 479, 241);
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
		panelEdit.setBounds(280, 194, 189, 35);
		panelForm.add(panelEdit);
		panelEdit.setLayout(null);

		btnEdit = new JButton("Edit");
		btnEdit.setEnabled(false);
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
		panelCredentials.setBounds(280, 107, 189, 76);
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
		lblCredentials.setBounds(332, 88, 75, 14);
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

		lblBank = new JLabel("Bank Account");
		lblBank.setBounds(10, 11, 90, 20);
		panelForm.add(lblBank);

		fieldBank = new JTextField();
		fieldBank.setHorizontalAlignment(SwingConstants.LEFT);
		fieldBank.setEditable(false);
		fieldBank.setColumns(10);
		fieldBank.setBounds(96, 11, 307, 20);
		panelForm.add(fieldBank);

		lblHireDate = new JLabel("Sign Up Date");
		lblHireDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblHireDate.setBounds(10, 209, 96, 20);
		panelForm.add(lblHireDate);

		fieldSignDate = new JTextField();
		fieldSignDate.setHorizontalAlignment(SwingConstants.LEFT);
		fieldSignDate.setEditable(false);
		fieldSignDate.setColumns(10);
		fieldSignDate.setBounds(110, 209, 131, 20);
		panelForm.add(fieldSignDate);

		lblRegion = new JLabel("Region");
		lblRegion.setBounds(10, 178, 62, 20);
		panelForm.add(lblRegion);

		fieldRegion = new JTextField();
		fieldRegion.setHorizontalAlignment(SwingConstants.LEFT);
		fieldRegion.setEditable(false);
		fieldRegion.setColumns(10);
		fieldRegion.setBounds(75, 178, 106, 20);
		panelForm.add(fieldRegion);

		panelImage = new JPanel();
		panelImage.setBounds(503, 316, 186, 210);
		contentPane.add(panelImage);
		panelImage.setLayout(new BorderLayout(0, 0));

		lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon("files/images/InmoManager186-210.png"));
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		panelImage.add(lblImage, BorderLayout.CENTER);

		btnReturn = new JButton("");
		btnReturn.setBounds(642, 11, 30, 30);
		btnReturn.setIcon(GlobalResources.getIconReturn());
		btnReturn.setContentAreaFilled(false);
		btnReturn.setBorderPainted(false);
		panelTitle.add(btnReturn);

		btnDelete = new JButton("Delete");
		btnDelete.setBounds(14, 255, 88, 23);
		contentPane.add(btnDelete);

		new ControllerManageClients(this);
		fillTextFieldList();
		setVisible(true);
	}

	public void addActListener(ActionListener listener) {
		this.btnApply.addActionListener(listener);
		this.btnEdit.addActionListener(listener);
		this.btnReset.addActionListener(listener);
		this.btnReturn.addActionListener(listener);
		this.btnFilter.addActionListener(listener);
		this.btnDelete.addActionListener(listener);
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
		textFieldList.add(fieldBank);
		textFieldList.add(fieldSignDate);
		textFieldList.add(fieldRegion);
	}

	// GETTERS AND SETTERS

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public GUILogin getgLogin() {
		return gLogin;
	}

	public JPanel getPanelTitle() {
		return panelTitle;
	}

	public JPanel getPanelEdit() {
		return panelEdit;
	}

	public JPanel getPanelCredentials() {
		return panelCredentials;
	}

	public JPanel getPanelImage() {
		return panelImage;
	}

	public JTable getTable() {
		return table;
	}

	public JButton getBtnReset() {
		return btnReset;
	}

	public JButton getBtnFilter() {
		return btnFilter;
	}

	public JButton getBtnEdit() {
		return btnEdit;
	}

	public JButton getBtnApply() {
		return btnApply;
	}

	public JButton getBtnReturn() {
		return btnReturn;
	}

	public JTextField getFieldID() {
		return fieldID;
	}

	public JTextField getFieldDNI() {
		return fieldDNI;
	}

	public JTextField getFieldName() {
		return fieldName;
	}

	public JTextField getFieldUsername() {
		return fieldUsername;
	}

	public JPasswordField getFieldPassword() {
		return fieldPassword;
	}

	public JTextField getFieldEmail() {
		return fieldEmail;
	}

	public JTextField getFieldPhone() {
		return fieldPhone;
	}

	public JTextField getFieldBank() {
		return fieldBank;
	}

	public JTextField getFieldSignDate() {
		return fieldSignDate;
	}

	public JPanel getPanelForm() {
		return panelForm;
	}

	public List<JTextField> getTextFieldList() {
		return textFieldList;
	}

	public JTextField getFieldRegion() {
		return fieldRegion;
	}

	public void setgLogin(GUILogin gLogin) {
		this.gLogin = gLogin;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public void setPanelTitle(JPanel panelTitle) {
		this.panelTitle = panelTitle;
	}

	public void setPanelEdit(JPanel panelEdit) {
		this.panelEdit = panelEdit;
	}

	public void setPanelCredentials(JPanel panelCredentials) {
		this.panelCredentials = panelCredentials;
	}

	public void setPanelImage(JPanel panelImage) {
		this.panelImage = panelImage;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public void setBtnReset(JButton btnReset) {
		this.btnReset = btnReset;
	}

	public void setBtnFilter(JButton btnFilter) {
		this.btnFilter = btnFilter;
	}

	public void setBtnEdit(JButton btnEdit) {
		this.btnEdit = btnEdit;
	}

	public void setBtnApply(JButton btnApply) {
		this.btnApply = btnApply;
	}

	public void setBtnReturn(JButton btnReturn) {
		this.btnReturn = btnReturn;
	}

	public void setFieldID(JTextField fieldID) {
		this.fieldID = fieldID;
	}

	public void setFieldDNI(JTextField fieldDNI) {
		this.fieldDNI = fieldDNI;
	}

	public void setFieldName(JTextField fieldName) {
		this.fieldName = fieldName;
	}

	public void setFieldUsername(JTextField fieldUsername) {
		this.fieldUsername = fieldUsername;
	}

	public void setFieldPassword(JPasswordField fieldPassword) {
		this.fieldPassword = fieldPassword;
	}

	public void setFieldEmail(JTextField fieldEmail) {
		this.fieldEmail = fieldEmail;
	}

	public void setFieldPhone(JTextField fieldPhone) {
		this.fieldPhone = fieldPhone;
	}

	public void setFieldBank(JTextField fieldBank) {
		this.fieldBank = fieldBank;
	}

	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public void setFieldSignDate(JTextField fieldSignDate) {
		this.fieldSignDate = fieldSignDate;
	}

	public void setPanelForm(JPanel panelForm) {
		this.panelForm = panelForm;
	}

	public void setTextFieldList(List<JTextField> textFieldList) {
		this.textFieldList = textFieldList;
	}

	public void setFieldRegion(JTextField fieldRegion) {
		this.fieldRegion = fieldRegion;
	}
}
