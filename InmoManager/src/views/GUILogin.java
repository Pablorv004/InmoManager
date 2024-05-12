package views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controllers.ControllerLogin;
import java.awt.Color;
import javax.swing.JSeparator;

public class GUILogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelLogo;
	private JPanel panelData;
	private JPanel panelRegister;
	private JLabel lblInmo;
	private JLabel lblRegister;
	private JButton btnRegister;
	private JPanel panelLoginExit;
	private JButton btnLogin;
	private JButton btnExit;
	private JPanel panelUserPass;
	private JPanel panelWelcome;
	private JLabel lblWelcome;
	private JPanel panelUsername;
	private JPanel panelPassword;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JPasswordField pwPassword;
	private JTextField txtUsername;
	private JPanel panelError;
	private JLabel lblError;
	private Font fontUserPassErr = new Font("Yu Gothic UI Semibold", Font.PLAIN, 13);
	private JSeparator separator;

	/**
	 * Create the frame.
	 */
	public GUILogin() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelLogo = new JPanel();
		contentPane.add(panelLogo, BorderLayout.NORTH);
		
		lblInmo = new JLabel("InmoManager");
		lblInmo.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		panelLogo.add(lblInmo);
		
		panelData = new JPanel();
		contentPane.add(panelData, BorderLayout.CENTER);
		panelData.setLayout(new BorderLayout(0, 0));
		
		panelLoginExit = new JPanel();
		panelData.add(panelLoginExit, BorderLayout.SOUTH);
		
		btnLogin = new JButton("Login");
		panelLoginExit.add(btnLogin);
		
		btnExit = new JButton("Exit");
		panelLoginExit.add(btnExit);
		
		panelUserPass = new JPanel();
		panelData.add(panelUserPass, BorderLayout.CENTER);
		panelUserPass.setLayout(new BoxLayout(panelUserPass, BoxLayout.Y_AXIS));
		
		separator = new JSeparator();
		panelUserPass.add(separator);
		
		panelUsername = new JPanel();
		panelUserPass.add(panelUsername);
		
		lblUsername = new JLabel("Username:");
		panelUsername.add(lblUsername);
		lblUsername.setFont(fontUserPassErr);
		
		txtUsername = new JTextField();
		panelUsername.add(txtUsername);
		txtUsername.setColumns(15);
		
		panelPassword = new JPanel();
		panelUserPass.add(panelPassword);
		
		lblPassword = new JLabel("Password:");
		panelPassword.add(lblPassword);
		lblPassword.setFont(fontUserPassErr);
		
		pwPassword = new JPasswordField();
		pwPassword.setColumns(15);
		panelPassword.add(pwPassword);
		
		panelError = new JPanel();
		panelUserPass.add(panelError);
		
		lblError = new JLabel("");
		lblError.setForeground(new Color(255, 0, 0));
		panelError.add(lblError);
		lblError.setFont(fontUserPassErr);
		
		panelWelcome = new JPanel();
		panelData.add(panelWelcome, BorderLayout.NORTH);
		
		lblWelcome = new JLabel("Welcome!");
		lblWelcome.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		panelWelcome.add(lblWelcome);
		
		panelRegister = new JPanel();
		contentPane.add(panelRegister, BorderLayout.SOUTH);
		
		lblRegister = new JLabel("Haven't registered yet?");
		lblRegister.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 11));
		panelRegister.add(lblRegister);
		
		btnRegister = new JButton("Register");
		panelRegister.add(btnRegister);
		//Adding the corresponding controller class
		new ControllerLogin(this);
		setVisible(true);
	}
	//Add action listeners
	public void addActListener(ActionListener listener) {
		btnLogin.addActionListener(listener);
		btnRegister.addActionListener(listener);
		btnExit.addActionListener(listener);
	}
	//Getters n setters for the elements
	public JPanel getPanelError() {
		return panelError;
	}
	public void setPanelError(JPanel panelError) {
		this.panelError = panelError;
	}
	public JLabel getLblError() {
		return lblError;
	}
	public void setLblError(JLabel lblError) {
		this.lblError = lblError;
	}
	public Font getFontUserPassErr() {
		return fontUserPassErr;
	}
	public void setFontUserPassErr(Font fontUserPassErr) {
		this.fontUserPassErr = fontUserPassErr;
	}
	
	public JPanel getContentPane() {
		return contentPane;
	}

	public void setContentPane(JPanel contentPane) {
		this.contentPane = contentPane;
	}

	public JPanel getPanelLogo() {
		return panelLogo;
	}

	public void setPanelLogo(JPanel panelLogo) {
		this.panelLogo = panelLogo;
	}

	public JPanel getPanelData() {
		return panelData;
	}

	public void setPanelData(JPanel panelData) {
		this.panelData = panelData;
	}

	public JPanel getPanelRegister() {
		return panelRegister;
	}

	public void setPanelRegister(JPanel panelRegister) {
		this.panelRegister = panelRegister;
	}

	public JLabel getLblInmo() {
		return lblInmo;
	}

	public void setLblInmo(JLabel lblInmo) {
		this.lblInmo = lblInmo;
	}

	public JLabel getLblRegister() {
		return lblRegister;
	}

	public void setLblRegister(JLabel lblRegister) {
		this.lblRegister = lblRegister;
	}

	public JButton getBtnRegister() {
		return btnRegister;
	}

	public void setBtnRegister(JButton btnRegister) {
		this.btnRegister = btnRegister;
	}

	public JPanel getPanelLoginExit() {
		return panelLoginExit;
	}

	public void setPanelLoginExit(JPanel panelLoginExit) {
		this.panelLoginExit = panelLoginExit;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public void setBtnLogin(JButton btnLogin) {
		this.btnLogin = btnLogin;
	}

	public JButton getBtnExit() {
		return btnExit;
	}

	public void setBtnExit(JButton btnExit) {
		this.btnExit = btnExit;
	}

	public JPanel getPanelUserPass() {
		return panelUserPass;
	}

	public void setPanelUserPass(JPanel panelUserPass) {
		this.panelUserPass = panelUserPass;
	}

	public JPanel getPanelWelcome() {
		return panelWelcome;
	}

	public void setPanelWelcome(JPanel panelWelcome) {
		this.panelWelcome = panelWelcome;
	}

	public JLabel getLblWelcome() {
		return lblWelcome;
	}

	public void setLblWelcome(JLabel lblWelcome) {
		this.lblWelcome = lblWelcome;
	}

	public JPanel getPanelUsername() {
		return panelUsername;
	}

	public void setPanelUsername(JPanel panelUsername) {
		this.panelUsername = panelUsername;
	}

	public JPanel getPanelPassword() {
		return panelPassword;
	}

	public void setPanelPassword(JPanel panelPassword) {
		this.panelPassword = panelPassword;
	}

	public JLabel getLblUsername() {
		return lblUsername;
	}

	public void setLblUsername(JLabel lblUsername) {
		this.lblUsername = lblUsername;
	}

	public JLabel getLblPassword() {
		return lblPassword;
	}

	public void setLblPassword(JLabel lblPassword) {
		this.lblPassword = lblPassword;
	}

	public JPasswordField getPwPassword() {
		return pwPassword;
	}

	public void setPwPassword(JPasswordField pwPassword) {
		this.pwPassword = pwPassword;
	}

	public JTextField getTxtUsername() {
		return txtUsername;
	}

	public void setTxtUsername(JTextField txtUsername) {
		this.txtUsername = txtUsername;
	}
	
	

}
