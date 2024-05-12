package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import controllers.ControllerRegister;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class GUIRegister extends JFrame {

	private GUILogin gLogin;
	private JPanel formPanel;
	private JPanel titlePanel;
	private JPanel btnPanel;
	private JLabel lblTitle1;
	private JLabel lblDNI;
	private JLabel lblName;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblRepeatPassword;
	private JLabel lblEmail;
	private JLabel lblPhoneNumber;
	private JLabel lblRegion;
	private JTextField fieldDNI;
	private JTextField fieldName;
	private JTextField fieldUsername;
	private JPasswordField fieldPassword;
	private JPasswordField fieldRepeatPass;
	private JTextField fieldEmail;
	private JTextField fieldPhone;
	private JTextField fieldRegion;
	private JButton btnRegister;
	private JButton btnCancel;
	private Font fontRegister = new Font("Consolas", Font.PLAIN, 13);
	
	public GUIRegister(GUILogin gLogin) {
		super("Register");
		this.gLogin = gLogin;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 337, 536);
		setLocationRelativeTo(gLogin);
		setResizable(false);
		getContentPane().setLayout(null);

		formPanel = new JPanel();
		formPanel.setBounds(19, 49, 283, 392);
		getContentPane().add(formPanel);
		formPanel.setLayout(null);

		titlePanel = new JPanel();
		titlePanel.setBounds(10, 11, 301, 39);
		getContentPane().add(titlePanel);
		titlePanel.setLayout(null);

		btnPanel = new JPanel();
		btnPanel.setBounds(19, 440, 283, 42);
		getContentPane().add(btnPanel);
		btnPanel.setLayout(null);

		lblDNI = new JLabel("DNI: ");
		lblDNI.setFont(fontRegister);
		lblDNI.setBounds(21, 11, 46, 14);
		formPanel.add(lblDNI);

		lblName = new JLabel("Name:");
		lblName.setFont(fontRegister);
		lblName.setBounds(21, 58, 46, 14);
		formPanel.add(lblName);

		lblUsername = new JLabel("Username:");
		lblUsername.setFont(fontRegister);
		lblUsername.setBounds(21, 105, 81, 14);
		formPanel.add(lblUsername);

		lblPassword = new JLabel("Password:");
		lblPassword.setFont(fontRegister);
		lblPassword.setBounds(21, 152, 81, 14);
		formPanel.add(lblPassword);

		lblRepeatPassword = new JLabel("Repeat Password:");
		lblRepeatPassword.setFont(fontRegister);
		lblRepeatPassword.setBounds(21, 198, 113, 14);
		formPanel.add(lblRepeatPassword);

		lblEmail = new JLabel("Email Address:");
		lblEmail.setFont(fontRegister);
		lblEmail.setBounds(21, 245, 113, 18);
		formPanel.add(lblEmail);

		lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setFont(fontRegister);
		lblPhoneNumber.setBounds(21, 291, 113, 14);
		formPanel.add(lblPhoneNumber);

		lblRegion = new JLabel("Region:");
		lblRegion.setFont(fontRegister);
		lblRegion.setBounds(21, 336, 113, 18);
		formPanel.add(lblRegion);

		fieldDNI = new JTextField();
		fieldDNI.setToolTipText("Your DNI number (Ex: 00000000A)");
		fieldDNI.setHorizontalAlignment(SwingConstants.LEFT);
		fieldDNI.setFont(fontRegister);
		fieldDNI.setBorder(new LineBorder(new Color(100, 149, 237)));
		fieldDNI.setColumns(10);
		fieldDNI.setBounds(22, 27, 240, 20);
		formPanel.add(fieldDNI);

		fieldName = new JTextField();
		fieldName.setBorder(new LineBorder(new Color(100, 149, 237)));
		fieldName.setToolTipText("Your full name");
		fieldName.setHorizontalAlignment(SwingConstants.LEFT);
		fieldName.setFont(fontRegister);
		fieldName.setColumns(10);
		fieldName.setBounds(22, 74, 240, 20);
		formPanel.add(fieldName);

		fieldUsername = new JTextField();
		fieldUsername.setBorder(new LineBorder(new Color(100, 149, 237)));
		fieldUsername.setToolTipText("Your username (Only alphanumeric characters allowed)");
		fieldUsername.setHorizontalAlignment(SwingConstants.LEFT);
		fieldUsername.setFont(fontRegister);
		fieldUsername.setColumns(10);
		fieldUsername.setBounds(21, 121, 241, 20);
		formPanel.add(fieldUsername);

		fieldPassword = new JPasswordField();
		fieldPassword.setBorder(new LineBorder(new Color(100, 149, 237)));
		fieldPassword.setToolTipText("Your password (Must contain at least one number and one special character)");
		fieldPassword.setHorizontalAlignment(SwingConstants.LEFT);
		fieldPassword.setFont(fontRegister);
		fieldPassword.setBounds(21, 167, 241, 20);
		formPanel.add(fieldPassword);

		fieldRepeatPass = new JPasswordField();
		fieldRepeatPass.setBorder(new LineBorder(new Color(100, 149, 237)));
		fieldRepeatPass.setToolTipText("Repeat your password");
		fieldRepeatPass.setHorizontalAlignment(SwingConstants.LEFT);
		fieldRepeatPass.setFont(fontRegister);
		fieldRepeatPass.setBounds(22, 214, 240, 20);
		formPanel.add(fieldRepeatPass);

		fieldEmail = new JTextField();
		fieldEmail.setBorder(new LineBorder(new Color(100, 149, 237)));
		fieldEmail.setToolTipText("Your Email adress");
		fieldEmail.setHorizontalAlignment(SwingConstants.LEFT);
		fieldEmail.setFont(fontRegister);
		fieldEmail.setColumns(10);
		fieldEmail.setBounds(21, 260, 241, 20);
		formPanel.add(fieldEmail);

		fieldPhone = new JTextField();
		fieldPhone.setBorder(new LineBorder(new Color(100, 149, 237)));
		fieldPhone.setToolTipText("Your personal phone number");
		fieldPhone.setHorizontalAlignment(SwingConstants.LEFT);
		fieldPhone.setFont(fontRegister);
		fieldPhone.setColumns(10);
		fieldPhone.setBounds(21, 305, 241, 20);
		formPanel.add(fieldPhone);

		fieldRegion = new JTextField();
		fieldRegion.setBorder(new LineBorder(new Color(100, 149, 237)));
		fieldRegion.setToolTipText("Your region");
		fieldRegion.setHorizontalAlignment(SwingConstants.LEFT);
		fieldRegion.setFont(fontRegister);
		fieldRegion.setColumns(10);
		fieldRegion.setBounds(21, 354, 241, 20);
		formPanel.add(fieldRegion);

		lblTitle1 = new JLabel("REGISTER");
		lblTitle1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle1.setFont(new Font("Consolas", Font.BOLD, 30));
		lblTitle1.setBounds(27, 4, 246, 39);
		titlePanel.add(lblTitle1);

		btnRegister = new JButton("Register");
		btnRegister.setBounds(34, 5, 90, 32);
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnPanel.add(btnRegister);

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(158, 5, 90, 32);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnPanel.add(btnCancel);

		new ControllerRegister(this);

		setVisible(true);
	}

	// Add ActionListeners to the buttons of the view
	public void addActListener(ActionListener listener) {
		btnRegister.addActionListener(listener);
		btnCancel.addActionListener(listener);
	}

	// GETTERS - SETTERS

	public GUILogin getgLogin() {
		return gLogin;
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

	public JTextField getFieldEmail() {
		return fieldEmail;
	}

	public JTextField getFieldPhone() {
		return fieldPhone;
	}

	public JPasswordField getFieldPassword() {
		return fieldPassword;
	}

	public JPasswordField getFieldRepeatPass() {
		return fieldRepeatPass;
	}

	public JTextField getFieldRegion() {
		return fieldRegion;
	}

	public JButton getBtnRegister() {
		return btnRegister;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public void setgLogin(GUILogin gLogin) {
		this.gLogin = gLogin;
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

	public void setFieldEmail(JTextField fieldEmail) {
		this.fieldEmail = fieldEmail;
	}

	public void setFieldPhone(JTextField fieldPhone) {
		this.fieldPhone = fieldPhone;
	}

	public void setFieldPassword(JPasswordField fieldPassword) {
		this.fieldPassword = fieldPassword;
	}

	public void setFieldRepeatPass(JPasswordField fieldRepeatPass) {
		this.fieldRepeatPass = fieldRepeatPass;
	}

	public void setFieldRegion(JTextField fieldRegion) {
		this.fieldRegion = fieldRegion;
	}

	public void setBtnRegister(JButton btnRegister) {
		this.btnRegister = btnRegister;
	}

	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}
}
