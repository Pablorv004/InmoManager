package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import controllers.ControllerFilterManager;

public class GUIFilterManager extends JFrame {
	GUIManageManagers gManage;
	private JLabel lblTitle;
	private JPanel panelForm;
	private JSeparator separator;
	private JCheckBox cbxDNI;
	private JTextField fieldDNI;
	private JPanel panelButton;
	private JButton btnApply;
	private JButton btnCancel;
	private JCheckBox cbxManagerID;
	private JTextField fieldManagerID;
	private JCheckBox cbxSalary;
	private JPanel panelSalary;
	private JLabel lblMinSalary;
	private JTextField fieldMinSalary;
	private JLabel lblMaxSalary;
	private JTextField fieldMaxSalary;
	private JCheckBox cbxCommission;
	private JPanel panelCommission;
	private JLabel lblMinCom;
	private JTextField fieldMinCom;
	private JLabel lblMaxCom;
	private JTextField fieldMaxCom;
	
	public GUIFilterManager(GUIManageManagers gManage) {
		super("Filter Managers");
		this.gManage = gManage;
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(gManage);
		getContentPane().setLayout(null);
		
		lblTitle = new JLabel("Manager Filters");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitle.setBounds(137, 5, 160, 19);
		getContentPane().add(lblTitle);
		
		panelForm = new JPanel();
		panelForm.setBorder(new LineBorder(new Color(105, 105, 105)));
		panelForm.setBounds(10, 71, 414, 151);
		getContentPane().add(panelForm);
		panelForm.setLayout(null);
		
		cbxManagerID = new JCheckBox("Manager ID");
		cbxManagerID.setBounds(6, 20, 97, 23);
		panelForm.add(cbxManagerID);
		
		fieldManagerID = new JTextField();
		fieldManagerID.setColumns(10);
		fieldManagerID.setBounds(109, 21, 48, 20);
		panelForm.add(fieldManagerID);
		
		cbxSalary = new JCheckBox("Salary");
		cbxSalary.setBounds(6, 63, 97, 23);
		panelForm.add(cbxSalary);
		
		panelSalary = new JPanel();
		panelSalary.setBorder(new LineBorder(new Color(192, 192, 192)));
		panelSalary.setBounds(109, 57, 295, 32);
		panelForm.add(panelSalary);
		panelSalary.setLayout(null);
		
		lblMinSalary = new JLabel("Min:");
		lblMinSalary.setBounds(10, 9, 34, 14);
		panelSalary.add(lblMinSalary);
		
		fieldMinSalary = new JTextField();
		fieldMinSalary.setColumns(10);
		fieldMinSalary.setBounds(43, 6, 86, 20);
		panelSalary.add(fieldMinSalary);
		
		lblMaxSalary = new JLabel("Max:");
		lblMaxSalary.setBounds(139, 9, 41, 14);
		panelSalary.add(lblMaxSalary);
		
		fieldMaxSalary = new JTextField();
		fieldMaxSalary.setColumns(10);
		fieldMaxSalary.setBounds(183, 6, 102, 20);
		panelSalary.add(fieldMaxSalary);
		
		cbxCommission = new JCheckBox("Commission");
		cbxCommission.setBounds(6, 106, 97, 23);
		panelForm.add(cbxCommission);
		
		panelCommission = new JPanel();
		panelCommission.setLayout(null);
		panelCommission.setBorder(new LineBorder(new Color(192, 192, 192)));
		panelCommission.setBounds(109, 100, 295, 32);
		panelForm.add(panelCommission);
		
		lblMinCom = new JLabel("Min:");
		lblMinCom.setBounds(10, 9, 34, 14);
		panelCommission.add(lblMinCom);
		
		fieldMinCom = new JTextField();
		fieldMinCom.setColumns(10);
		fieldMinCom.setBounds(43, 6, 86, 20);
		panelCommission.add(fieldMinCom);
		
		lblMaxCom = new JLabel("Max:");
		lblMaxCom.setBounds(139, 9, 41, 14);
		panelCommission.add(lblMaxCom);
		
		fieldMaxCom = new JTextField();
		fieldMaxCom.setColumns(10);
		fieldMaxCom.setBounds(183, 6, 102, 20);
		panelCommission.add(fieldMaxCom);
		
		separator = new JSeparator();
		separator.setBounds(0, 28, 434, 8);
		getContentPane().add(separator);
		
		cbxDNI = new JCheckBox("Filter by DNI");
		cbxDNI.setBounds(9, 41, 104, 23);
		getContentPane().add(cbxDNI);
		
		fieldDNI = new JTextField();
		fieldDNI.setBounds(119, 40, 160, 20);
		getContentPane().add(fieldDNI);
		fieldDNI.setColumns(10);
		
		panelButton = new JPanel();
		panelButton.setBounds(10, 226, 414, 38);
		getContentPane().add(panelButton);
		panelButton.setLayout(null);
		
		btnApply = new JButton("Apply Filters");
		btnApply.setBounds(58, 7, 120, 23);
		panelButton.add(btnApply);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(236, 7, 120, 23);
		panelButton.add(btnCancel);
		
		new ControllerFilterManager(this);
		
		setVisible(true);
	}
	
	public void addActListener (ActionListener listener) {
		this.btnApply.addActionListener(listener);
		this.btnCancel.addActionListener(listener);
	}
	
	public void addItmListener (ItemListener listener) {
		this.cbxDNI.addItemListener(listener);
		this.cbxManagerID.addItemListener(listener);
		this.cbxSalary.addItemListener(listener);
		this.cbxCommission.addItemListener(listener);
	}

	public GUIManageManagers getgManage() {
		return gManage;
	}

	public JPanel getPanelForm() {
		return panelForm;
	}

	public JCheckBox getCbxDNI() {
		return cbxDNI;
	}

	public JTextField getFieldDNI() {
		return fieldDNI;
	}

	public JPanel getPanelButton() {
		return panelButton;
	}

	public JButton getBtnApply() {
		return btnApply;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public JCheckBox getCbxManagerID() {
		return cbxManagerID;
	}

	public JTextField getFieldManagerID() {
		return fieldManagerID;
	}

	public JCheckBox getCbxSalary() {
		return cbxSalary;
	}

	public JPanel getPanelSalary() {
		return panelSalary;
	}

	public JTextField getFieldMinSalary() {
		return fieldMinSalary;
	}

	public JTextField getFieldMaxSalary() {
		return fieldMaxSalary;
	}

	public JCheckBox getCbxCommission() {
		return cbxCommission;
	}

	public JPanel getPanelCommission() {
		return panelCommission;
	}

	public JTextField getFieldMinCom() {
		return fieldMinCom;
	}

	public JTextField getFieldMaxCom() {
		return fieldMaxCom;
	}

	public void setgManage(GUIManageManagers gManage) {
		this.gManage = gManage;
	}

	public void setPanelForm(JPanel panelForm) {
		this.panelForm = panelForm;
	}

	public void setCbxDNI(JCheckBox cbxDNI) {
		this.cbxDNI = cbxDNI;
	}

	public void setFieldDNI(JTextField fieldDNI) {
		this.fieldDNI = fieldDNI;
	}

	public void setPanelButton(JPanel panelButton) {
		this.panelButton = panelButton;
	}

	public void setBtnApply(JButton btnApply) {
		this.btnApply = btnApply;
	}

	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}

	public void setCbxManagerID(JCheckBox cbxManagerID) {
		this.cbxManagerID = cbxManagerID;
	}

	public void setFieldManagerID(JTextField fieldManagerID) {
		this.fieldManagerID = fieldManagerID;
	}

	public void setCbxSalary(JCheckBox cbxSalary) {
		this.cbxSalary = cbxSalary;
	}

	public void setPanelSalary(JPanel panelSalary) {
		this.panelSalary = panelSalary;
	}

	public void setFieldMinSalary(JTextField fieldMinSalary) {
		this.fieldMinSalary = fieldMinSalary;
	}

	public void setFieldMaxSalary(JTextField fieldMaxSalary) {
		this.fieldMaxSalary = fieldMaxSalary;
	}

	public void setCbxCommission(JCheckBox cbxCommission) {
		this.cbxCommission = cbxCommission;
	}

	public void setPanelCommission(JPanel panelCommission) {
		this.panelCommission = panelCommission;
	}

	public void setFieldMinCom(JTextField fieldMinCom) {
		this.fieldMinCom = fieldMinCom;
	}

	public void setFieldMaxCom(JTextField fieldMaxCom) {
		this.fieldMaxCom = fieldMaxCom;
	}
	
	
}
