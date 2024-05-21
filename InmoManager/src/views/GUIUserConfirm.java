package views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controllers.ControllerUserConfirm;
import util.GlobalResources;

public class GUIUserConfirm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelTitle;
	private JPanel panelButtons;
	private JPanel panelData;
	private JPanel panelUser;
	private JPanel panelDataProperty;
	private JButton btnConfirm;
	private JButton btnCancel;
	private JLabel LblPropertyConfirm;
	private JLabel lblPropertyPhoto;
	private JTextArea txtAreaProperty;
	private JLabel lblTotalPrice;
	private JLabel lblTitle;
	private JLabel lblLogo;
	private JSeparator separator;
	private JLabel lblUserConfirm;
	private GUIUserInterested userInterested;
	private JPanel panelManagerTOS;
	private JLabel lblManager;
	private JPanel panelDataUser;
	private JPanel panelManager;
	private JPanel panelTOS;
	private JLabel lblTOS;
	private JLabel lblDNI;
	private JTextField txtDNI;
	private JLabel lblFullName;
	private JTextField txtFullName;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JLabel lblPhoneNum;
	private JTextField txtPhoneNum;
	private JLabel lblRegion;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JTextField txtRegion;
	private JLabel lblBankAccNum;
	private JTextField txtBankAccountNum;
	private JLabel lblBankWould;
	private JCheckBox cbxSaveBankInfo;
	private JSeparator separator_3;
	private JScrollPane scrollPane;
	private JSeparator separator_4;
	/**
	 * Create the frame.
	 */
	public GUIUserConfirm(GUIUserInterested userInterested) {
		setTitle("Confirm");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 826, 509);
		this.setUserInterested(userInterested);
		setLocationRelativeTo(userInterested);
		
		GlobalResources.setFrameIcon(this);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelTitle = new JPanel();
		contentPane.add(panelTitle, BorderLayout.NORTH);
		panelTitle.setLayout(new BorderLayout(0, 0));
		lblTitle = new JLabel("Confirm Action");
		lblTitle.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		panelTitle.add(lblTitle);

		lblLogo = new JLabel("");
		lblLogo.setIcon(GlobalResources.getIconSmall());
		panelTitle.add(lblLogo, BorderLayout.WEST);

		separator = new JSeparator();
		panelTitle.add(separator, BorderLayout.SOUTH);
		panelButtons = new JPanel();
		contentPane.add(panelButtons, BorderLayout.SOUTH);
		
		btnConfirm = new JButton("Confirm and Purchase");
		panelButtons.add(btnConfirm);
		
		btnCancel = new JButton("Cancel");
		panelButtons.add(btnCancel);
		
		panelData = new JPanel();
		contentPane.add(panelData, BorderLayout.CENTER);
		panelData.setLayout(new BorderLayout(0, 0));
		
		panelUser = new JPanel();
		panelData.add(panelUser, BorderLayout.NORTH);
		panelUser.setLayout(new BorderLayout(0, 0));
		
		lblUserConfirm = new JLabel("Please, confirm that the data that will be sent is correct:");
		panelUser.add(lblUserConfirm, BorderLayout.NORTH);
		
		panelDataUser = new JPanel();
		panelUser.add(panelDataUser, BorderLayout.CENTER);
		panelDataUser.setLayout(new GridLayout(7, 2, 0, 0));
		
		lblDNI = new JLabel("DNI:");
		panelDataUser.add(lblDNI);
		
		txtDNI = new JTextField();
		txtDNI.setEditable(false);
		panelDataUser.add(txtDNI);
		txtDNI.setColumns(10);
		
		lblFullName = new JLabel("Full Name:");
		panelDataUser.add(lblFullName);
		
		txtFullName = new JTextField();
		txtFullName.setEditable(false);
		panelDataUser.add(txtFullName);
		txtFullName.setColumns(10);
		
		lblEmail = new JLabel("Email:");
		panelDataUser.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setEditable(false);
		panelDataUser.add(txtEmail);
		txtEmail.setColumns(10);
		
		lblPhoneNum = new JLabel("Phone Number:");
		panelDataUser.add(lblPhoneNum);
		
		txtPhoneNum = new JTextField();
		txtPhoneNum.setEditable(false);
		panelDataUser.add(txtPhoneNum);
		txtPhoneNum.setColumns(10);
		
		lblRegion = new JLabel("Region:");
		panelDataUser.add(lblRegion);
		
		txtRegion = new JTextField();
		txtRegion.setEditable(false);
		panelDataUser.add(txtRegion);
		txtRegion.setColumns(10);
		
		lblBankAccNum = new JLabel("Bank Account Number to pay with:");
		panelDataUser.add(lblBankAccNum);
		
		txtBankAccountNum = new JTextField();
		panelDataUser.add(txtBankAccountNum);
		txtBankAccountNum.setColumns(10);
		
		lblBankWould = new JLabel("Would you like to save your banking number for next time?:");
		panelDataUser.add(lblBankWould);
		
		cbxSaveBankInfo = new JCheckBox("Check if you'd like to save your banking information.");
		panelDataUser.add(cbxSaveBankInfo);
		
		separator_2 = new JSeparator();
		panelUser.add(separator_2, BorderLayout.SOUTH);
		
		panelDataProperty = new JPanel();
		panelData.add(panelDataProperty, BorderLayout.SOUTH);
		panelDataProperty.setLayout(new BorderLayout(0, 0));
		
		lblPropertyPhoto = new JLabel("");
		panelDataProperty.add(lblPropertyPhoto, BorderLayout.WEST);
		
		txtAreaProperty = new JTextArea();
		txtAreaProperty.setRows(5);
		txtAreaProperty.setEditable(false);
		
		scrollPane = new JScrollPane(txtAreaProperty);
		panelDataProperty.add(scrollPane, BorderLayout.CENTER);
		
		panelManagerTOS = new JPanel();
		panelDataProperty.add(panelManagerTOS, BorderLayout.SOUTH);
		panelManagerTOS.setLayout(new BoxLayout(panelManagerTOS, BoxLayout.Y_AXIS));
		
		panelManager = new JPanel();
		panelManagerTOS.add(panelManager);
		panelManager.setLayout(new BoxLayout(panelManager, BoxLayout.X_AXIS));
		
		lblTotalPrice = new JLabel("Price to Pay: ");
		panelManager.add(lblTotalPrice);
		
		separator_4 = new JSeparator();
		separator_4.setOrientation(SwingConstants.VERTICAL);
		panelManager.add(separator_4);
		
		lblManager = new JLabel("The manager you'll get assigned to is:");
		panelManager.add(lblManager);
		
		separator_3 = new JSeparator();
		panelManagerTOS.add(separator_3);
		
		panelTOS = new JPanel();
		panelManagerTOS.add(panelTOS);
		
		lblTOS = new JLabel("By clicking \"Confirm and Purchase\", you'll be agreeing to our Terms of Service and Privacy Policy. View our documentation for more information.");
		panelTOS.add(lblTOS);
		
		
		
		LblPropertyConfirm = new JLabel("This is the property you've selected:");
		panelData.add(LblPropertyConfirm, BorderLayout.CENTER);
		
		separator_1 = new JSeparator();
		panelData.add(separator_1, BorderLayout.WEST);
		
		//Adding controller
		
		new ControllerUserConfirm(this);
		setVisible(true);
	}
	
	public void addActListeners(ActionListener listener) {
		btnConfirm.addActionListener(listener);
		btnCancel.addActionListener(listener);
	}

	public GUIUserInterested getUserInterested() {
		return userInterested;
	}

	public void setUserInterested(GUIUserInterested userInterested) {
		this.userInterested = userInterested;
	}

	public JPanel getPanelTitle() {
		return panelTitle;
	}

	public void setPanelTitle(JPanel panelTitle) {
		this.panelTitle = panelTitle;
	}

	public JPanel getPanelButtons() {
		return panelButtons;
	}

	public void setPanelButtons(JPanel panelButtons) {
		this.panelButtons = panelButtons;
	}

	public JPanel getPanelData() {
		return panelData;
	}

	public void setPanelData(JPanel panelData) {
		this.panelData = panelData;
	}

	public JPanel getPanelUser() {
		return panelUser;
	}

	public void setPanelUser(JPanel panelUser) {
		this.panelUser = panelUser;
	}

	public JPanel getPanelDataProperty() {
		return panelDataProperty;
	}

	public void setPanelDataProperty(JPanel panelDataProperty) {
		this.panelDataProperty = panelDataProperty;
	}

	public JButton getBtnConfirm() {
		return btnConfirm;
	}

	public void setBtnConfirm(JButton btnConfirm) {
		this.btnConfirm = btnConfirm;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}

	public JLabel getLblPropertyConfirm() {
		return LblPropertyConfirm;
	}

	public void setLblPropertyConfirm(JLabel lblPropertyConfirm) {
		LblPropertyConfirm = lblPropertyConfirm;
	}

	public JLabel getLblPropertyPhoto() {
		return lblPropertyPhoto;
	}

	public void setLblPropertyPhoto(JLabel lblPropertyPhoto) {
		this.lblPropertyPhoto = lblPropertyPhoto;
	}

	public JTextArea getTxtAreaProperty() {
		return txtAreaProperty;
	}

	public void setTxtAreaProperty(JTextArea txtAreaProperty) {
		this.txtAreaProperty = txtAreaProperty;
	}

	public JLabel getLblTotalPrice() {
		return lblTotalPrice;
	}

	public void setLblTotalPrice(JLabel lblTotalPrice) {
		this.lblTotalPrice = lblTotalPrice;
	}

	public JLabel getLblTitle() {
		return lblTitle;
	}

	public void setLblTitle(JLabel lblTitle) {
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

	public JLabel getLblUserConfirm() {
		return lblUserConfirm;
	}

	public void setLblUserConfirm(JLabel lblUserConfirm) {
		this.lblUserConfirm = lblUserConfirm;
	}

	public JPanel getPanelManagerTOS() {
		return panelManagerTOS;
	}

	public void setPanelManagerTOS(JPanel panelManagerTOS) {
		this.panelManagerTOS = panelManagerTOS;
	}

	public JLabel getLblManager() {
		return lblManager;
	}

	public void setLblManager(JLabel lblManager) {
		this.lblManager = lblManager;
	}

	public JPanel getPanelDataUser() {
		return panelDataUser;
	}

	public void setPanelDataUser(JPanel panelDataUser) {
		this.panelDataUser = panelDataUser;
	}

	public JPanel getPanelManager() {
		return panelManager;
	}

	public void setPanelManager(JPanel panelManager) {
		this.panelManager = panelManager;
	}

	public JPanel getPanelTOS() {
		return panelTOS;
	}

	public void setPanelTOS(JPanel panelTOS) {
		this.panelTOS = panelTOS;
	}

	public JLabel getLblTOS() {
		return lblTOS;
	}

	public void setLblTOS(JLabel lblTOS) {
		this.lblTOS = lblTOS;
	}

	public JLabel getLblDNI() {
		return lblDNI;
	}

	public void setLblDNI(JLabel lblDNI) {
		this.lblDNI = lblDNI;
	}

	public JTextField getTxtDNI() {
		return txtDNI;
	}

	public void setTxtDNI(JTextField txtDNI) {
		this.txtDNI = txtDNI;
	}

	public JLabel getLblFullName() {
		return lblFullName;
	}

	public void setLblFullName(JLabel lblFullName) {
		this.lblFullName = lblFullName;
	}

	public JTextField getTxtFullName() {
		return txtFullName;
	}

	public void setTxtFullName(JTextField txtFullName) {
		this.txtFullName = txtFullName;
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

	public JTextField getTxtPhoneNum() {
		return txtPhoneNum;
	}

	public void setTxtPhoneNum(JTextField txtPhoneNum) {
		this.txtPhoneNum = txtPhoneNum;
	}

	public JLabel getLblRegion() {
		return lblRegion;
	}

	public void setLblRegion(JLabel lblRegion) {
		this.lblRegion = lblRegion;
	}

	public JSeparator getSeparator_1() {
		return separator_1;
	}

	public void setSeparator_1(JSeparator separator_1) {
		this.separator_1 = separator_1;
	}

	public JSeparator getSeparator_2() {
		return separator_2;
	}

	public void setSeparator_2(JSeparator separator_2) {
		this.separator_2 = separator_2;
	}

	public JTextField getTxtRegion() {
		return txtRegion;
	}

	public void setTxtRegion(JTextField txtRegion) {
		this.txtRegion = txtRegion;
	}

	public JLabel getLblBankAccNum() {
		return lblBankAccNum;
	}

	public void setLblBankAccNum(JLabel lblBankAccNum) {
		this.lblBankAccNum = lblBankAccNum;
	}

	public JTextField getTxtBankAccountNum() {
		return txtBankAccountNum;
	}

	public void setTxtBankAccountNum(JTextField txtBankAccountNum) {
		this.txtBankAccountNum = txtBankAccountNum;
	}

	public JLabel getLblBankWould() {
		return lblBankWould;
	}

	public void setLblBankWould(JLabel lblBankWould) {
		this.lblBankWould = lblBankWould;
	}

	public JCheckBox getCbxSaveBankInfo() {
		return cbxSaveBankInfo;
	}

	public void setCbxSaveBankInfo(JCheckBox cbxSaveBankInfo) {
		this.cbxSaveBankInfo = cbxSaveBankInfo;
	}

	public JSeparator getSeparator_3() {
		return separator_3;
	}

	public void setSeparator_3(JSeparator separator_3) {
		this.separator_3 = separator_3;
	}
	

}
