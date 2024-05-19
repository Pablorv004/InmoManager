package views;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controllers.ControllerUserProfile;
import util.GlobalResources;
import javax.swing.JPasswordField;

public class GUIUserProfile extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelTitle;
	private JPanel panelButtonsInfo;
	private JPanel panelData;
	private Container lblTitle;
	private JLabel lblLogo;
	private JSeparator separator;
	private JButton btnReturn;
	private JButton btnEdit;
	private JButton btnApply;
	private JButton btnHomes;
	private JLabel lblPhoto;
	private JLabel lblName;
	private JTextField txtName;
	private JLabel lblDni;
	private JTextField txtDNI;
	private JPanel panelControls;
	private JPanel panelInfo;
	private JLabel lblWarning;
	private JLabel lblUsername;
	private JTextField txtUsername;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JLabel lblPhoneNum;
	private JTextField txtPhone;
	private JLabel lblRegion;
	private JTextField txtRegion;
	private JLabel lblCreation;
	private JTextField txtCreation;
	private GUIMainUser mainUser;
	private JLabel lblPassword;
	private JPasswordField passwordField;

	/**
	 * Create the frame.
	 */
	public GUIUserProfile(GUIMainUser mainUser) {
		setTitle("Profile");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 597, 368);
		setLocationRelativeTo(mainUser);
		this.mainUser = mainUser;
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panelTitle = new JPanel();
		contentPane.add(panelTitle, BorderLayout.NORTH);
		panelTitle.setLayout(new BorderLayout(0, 0));

		panelButtonsInfo = new JPanel();
		contentPane.add(panelButtonsInfo, BorderLayout.SOUTH);
		panelButtonsInfo.setLayout(new BoxLayout(panelButtonsInfo, BoxLayout.Y_AXIS));

		panelInfo = new JPanel();
		panelButtonsInfo.add(panelInfo);

		lblWarning = new JLabel(
				"There are certain fields you cannot edit due to security reasons. Contact an administrator for help.");
		panelInfo.add(lblWarning);

		panelControls = new JPanel();
		panelButtonsInfo.add(panelControls);

		btnReturn = new JButton("Return");
		panelControls.add(btnReturn);

		btnEdit = new JButton("Edit");
		panelControls.add(btnEdit);

		btnApply = new JButton("Apply");
		btnApply.setEnabled(false);
		panelControls.add(btnApply);

		btnHomes = new JButton("View Purchased Homes");
		panelControls.add(btnHomes);

		panelData = new JPanel();
		contentPane.add(panelData, BorderLayout.CENTER);
		panelData.setLayout(new GridLayout(8, 2, 0, 0));

		lblName = new JLabel("Name");
		panelData.add(lblName);

		txtName = new JTextField();
		txtName.setEditable(false);
		panelData.add(txtName);
		txtName.setColumns(10);

		lblDni = new JLabel("DNI");
		panelData.add(lblDni);

		txtDNI = new JTextField();
		txtDNI.setEditable(false);
		panelData.add(txtDNI);
		txtDNI.setColumns(10);

		lblUsername = new JLabel("Username");
		panelData.add(lblUsername);

		txtUsername = new JTextField();
		txtUsername.setEditable(false);
		panelData.add(txtUsername);
		txtUsername.setColumns(10);

		lblEmail = new JLabel("Email");
		panelData.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setEditable(false);
		panelData.add(txtEmail);
		txtEmail.setColumns(10);
		
		lblPassword = new JLabel("Password");
		panelData.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setEditable(false);
		panelData.add(passwordField);

		lblPhoneNum = new JLabel("Phone Number");
		panelData.add(lblPhoneNum);

		txtPhone = new JTextField();
		txtPhone.setEditable(false);
		panelData.add(txtPhone);
		txtPhone.setColumns(10);

		lblRegion = new JLabel("Region");
		panelData.add(lblRegion);

		txtRegion = new JTextField();
		txtRegion.setEditable(false);
		panelData.add(txtRegion);
		txtRegion.setColumns(10);

		lblCreation = new JLabel("Creation Time");
		panelData.add(lblCreation);

		txtCreation = new JTextField();
		txtCreation.setEditable(false);
		panelData.add(txtCreation);
		txtCreation.setColumns(10);

		lblTitle = new JLabel("Your Profile");
		lblTitle.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		panelTitle.add(lblTitle);

		lblLogo = new JLabel("");
		lblLogo.setIcon(GlobalResources.getIconSmall());
		panelTitle.add(lblLogo, BorderLayout.WEST);

		separator = new JSeparator();
		panelTitle.add(separator, BorderLayout.SOUTH);

		lblPhoto = new JLabel("");
		contentPane.add(lblPhoto, BorderLayout.EAST);
		
		new ControllerUserProfile(this);
		setVisible(true);
	}

	public void addActListeners(ActionListener listener) {
		btnReturn.addActionListener(listener);
		btnEdit.addActionListener(listener);
		btnApply.addActionListener(listener);
		btnHomes.addActionListener(listener);
	}

	public GUIMainUser getMainUser() {
		return mainUser;
	}

	public void setMainUser(GUIMainUser mainUser) {
		this.mainUser = mainUser;
	}

	public JPanel getPanelTitle() {
		return panelTitle;
	}

	public void setPanelTitle(JPanel panelTitle) {
		this.panelTitle = panelTitle;
	}

	public JPanel getPanelButtonsInfo() {
		return panelButtonsInfo;
	}

	public void setPanelButtonsInfo(JPanel panelButtonsInfo) {
		this.panelButtonsInfo = panelButtonsInfo;
	}

	public JPanel getPanelData() {
		return panelData;
	}

	public void setPanelData(JPanel panelData) {
		this.panelData = panelData;
	}

	public Container getLblTitle() {
		return lblTitle;
	}

	public void setLblTitle(Container lblTitle) {
		this.lblTitle = lblTitle;
	}

	public JLabel getLblLogo() {
		return lblLogo;
	}

	public void setLblLogo(JLabel lblLogo) {
		this.lblLogo = lblLogo;
	}

	public JSeparator getSeparator() {
		return separator;
	}

	public void setSeparator(JSeparator separator) {
		this.separator = separator;
	}

	public JButton getBtnReturn() {
		return btnReturn;
	}

	public void setBtnReturn(JButton btnReturn) {
		this.btnReturn = btnReturn;
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

	public JButton getBtnHomes() {
		return btnHomes;
	}

	public void setBtnHomes(JButton btnHomes) {
		this.btnHomes = btnHomes;
	}

	public JLabel getLblPhoto() {
		return lblPhoto;
	}

	public void setLblPhoto(JLabel lblPhoto) {
		this.lblPhoto = lblPhoto;
	}

	public JLabel getLblName() {
		return lblName;
	}

	public void setLblName(JLabel lblName) {
		this.lblName = lblName;
	}

	public JTextField getTxtName() {
		return txtName;
	}

	public void setTxtName(JTextField txtName) {
		this.txtName = txtName;
	}

	public JLabel getLblDni() {
		return lblDni;
	}

	public void setLblDni(JLabel lblDni) {
		this.lblDni = lblDni;
	}

	public JTextField getTxtDNI() {
		return txtDNI;
	}

	public void setTxtDNI(JTextField txtDNI) {
		this.txtDNI = txtDNI;
	}

	public JPanel getPanelControls() {
		return panelControls;
	}

	public void setPanelControls(JPanel panelControls) {
		this.panelControls = panelControls;
	}

	public JPanel getPanelInfo() {
		return panelInfo;
	}

	public void setPanelInfo(JPanel panelInfo) {
		this.panelInfo = panelInfo;
	}

	public JLabel getLblWarning() {
		return lblWarning;
	}

	public void setLblWarning(JLabel lblWarning) {
		this.lblWarning = lblWarning;
	}

	public JLabel getLblUsername() {
		return lblUsername;
	}

	public void setLblUsername(JLabel lblUsername) {
		this.lblUsername = lblUsername;
	}

	public JTextField getTxtUsername() {
		return txtUsername;
	}

	public void setTxtUsername(JTextField txtUsername) {
		this.txtUsername = txtUsername;
	}

	public JLabel getLblEmail() {
		return lblEmail;
	}

	public void setLblEmail(JLabel lblEmail) {
		this.lblEmail = lblEmail;
	}

	public JTextField getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(JTextField txtEmail) {
		this.txtEmail = txtEmail;
	}

	public JLabel getLblPhoneNum() {
		return lblPhoneNum;
	}

	public void setLblPhoneNum(JLabel lblPhoneNum) {
		this.lblPhoneNum = lblPhoneNum;
	}

	public JTextField getTxtPhone() {
		return txtPhone;
	}

	public void setTxtPhone(JTextField txtPhone) {
		this.txtPhone = txtPhone;
	}

	public JLabel getLblRegion() {
		return lblRegion;
	}

	public void setLblRegion(JLabel lblRegion) {
		this.lblRegion = lblRegion;
	}

	public JTextField getTxtRegion() {
		return txtRegion;
	}

	public void setTxtRegion(JTextField txtRegion) {
		this.txtRegion = txtRegion;
	}

	public JLabel getLblCreation() {
		return lblCreation;
	}

	public void setLblCreation(JLabel lblCreation) {
		this.lblCreation = lblCreation;
	}

	public JTextField getTxtCreation() {
		return txtCreation;
	}

	public void setTxtCreation(JTextField txtCreation) {
		this.txtCreation = txtCreation;
	}

	public JLabel getLblPassword() {
		return lblPassword;
	}

	public void setLblPassword(JLabel lblPassword) {
		this.lblPassword = lblPassword;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

}
