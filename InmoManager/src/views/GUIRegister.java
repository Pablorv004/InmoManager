package views;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controllers.ControllerRegister;

public class GUIRegister extends JFrame {

	private GUILogin gLogin;
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
	private JTextField fieldEmail;
	private JTextField fieldPhone;
	private JTextField fieldRegion;
	private JPasswordField fieldPassword;
	private JPasswordField fieldRepeatPass;
	private JButton btnRegister;
	private JButton btnCancel;

	// GUIRegisters receive in its contructor a GUILogin class as a parameter
	// Right now it doesnt have it because it's in test phase
	public GUIRegister() {
		super("Register");
		//this.gLogin = gLogin;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 336, 322);
		setLocationRelativeTo(gLogin);
		setResizable(false);
		getContentPane().setLayout(null);

		lblDNI = new JLabel("DNI: ");
		lblDNI.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDNI.setBounds(29, 22, 46, 14);
		getContentPane().add(lblDNI);

		lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblName.setBounds(29, 47, 46, 14);
		getContentPane().add(lblName);

		lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUsername.setBounds(29, 72, 81, 14);
		getContentPane().add(lblUsername);

		lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPassword.setBounds(29, 97, 81, 14);
		getContentPane().add(lblPassword);

		lblRepeatPassword = new JLabel("Repeat Password:");
		lblRepeatPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRepeatPassword.setBounds(29, 122, 113, 14);
		getContentPane().add(lblRepeatPassword);

		lblEmail = new JLabel("Email Adress:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail.setBounds(29, 147, 113, 14);
		getContentPane().add(lblEmail);

		lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPhoneNumber.setBounds(29, 172, 113, 14);
		getContentPane().add(lblPhoneNumber);
		
		lblRegion = new JLabel("Region:");
		lblRegion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRegion.setBounds(29, 197, 113, 18);
		getContentPane().add(lblRegion);

		fieldDNI = new JTextField();
		fieldDNI.setToolTipText("Your DNI number (Ex: 00000000A)");
		fieldDNI.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldDNI.setHorizontalAlignment(SwingConstants.CENTER);
		fieldDNI.setBounds(153, 20, 136, 20);
		getContentPane().add(fieldDNI);
		fieldDNI.setColumns(10);

		fieldName = new JTextField();
		fieldName.setToolTipText("Your full name");
		fieldName.setHorizontalAlignment(SwingConstants.CENTER);
		fieldName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldName.setColumns(10);
		fieldName.setBounds(153, 45, 136, 20);
		getContentPane().add(fieldName);

		fieldUsername = new JTextField();
		fieldUsername.setToolTipText("Your username (Only alphanumeric characters allowed)");
		fieldUsername.setHorizontalAlignment(SwingConstants.CENTER);
		fieldUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldUsername.setColumns(10);
		fieldUsername.setBounds(153, 70, 136, 20);
		getContentPane().add(fieldUsername);

		fieldPassword = new JPasswordField();
		fieldPassword.setToolTipText("Your password (Must contain at least one number and one special character)");
		fieldPassword.setHorizontalAlignment(SwingConstants.CENTER);
		fieldPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldPassword.setBounds(153, 95, 136, 20);
		getContentPane().add(fieldPassword);

		fieldRepeatPass = new JPasswordField();
		fieldRepeatPass.setToolTipText("Repeat your password");
		fieldRepeatPass.setHorizontalAlignment(SwingConstants.CENTER);
		fieldRepeatPass.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldRepeatPass.setBounds(153, 120, 136, 20);
		getContentPane().add(fieldRepeatPass);

		fieldEmail = new JTextField();
		fieldEmail.setToolTipText("Your Email adress");
		fieldEmail.setHorizontalAlignment(SwingConstants.CENTER);
		fieldEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldEmail.setColumns(10);
		fieldEmail.setBounds(153, 145, 136, 20);
		getContentPane().add(fieldEmail);

		fieldPhone = new JTextField();
		fieldPhone.setToolTipText("Your personal phone number");
		fieldPhone.setHorizontalAlignment(SwingConstants.CENTER);
		fieldPhone.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldPhone.setColumns(10);
		fieldPhone.setBounds(153, 170, 136, 20);
		getContentPane().add(fieldPhone);
		
		fieldRegion = new JTextField();
		fieldRegion.setToolTipText("Your personal phone number");
		fieldRegion.setHorizontalAlignment(SwingConstants.CENTER);
		fieldRegion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldRegion.setColumns(10);
		fieldRegion.setBounds(153, 195, 136, 20);
		getContentPane().add(fieldRegion);

		btnRegister = new JButton("Register");
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnRegister.setBounds(41, 232, 96, 32);
		getContentPane().add(btnRegister);

		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCancel.setBounds(179, 233, 96, 32);
		getContentPane().add(btnCancel);

		ControllerRegister rController = new ControllerRegister(this);
		
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
