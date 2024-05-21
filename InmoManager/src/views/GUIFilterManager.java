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
import controllers.ControllerManageManagers;
import util.GlobalResources;

public class GUIFilterManager extends JFrame {
	ControllerManageManagers gManageController;
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
	private JButton btnReset;
	private JPanel panelWarnings;
	private JLabel lblWarning;
	private JLabel lblThisMeansThat;

	public GUIFilterManager(ControllerManageManagers gManageController) {
		super("Filter Managers");
		this.gManageController = gManageController;
		setSize(451, 338);
		setResizable(false);
		setLocationRelativeTo(gManageController.getgManage());
		getContentPane().setLayout(null);
		
		GlobalResources.setFrameIcon(this);

		lblTitle = new JLabel("Manager Filters");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitle.setBounds(137, 5, 160, 19);
		getContentPane().add(lblTitle);

		panelForm = new JPanel();
		panelForm.setBorder(new LineBorder(new Color(105, 105, 105)));
		panelForm.setBounds(10, 104, 414, 151);
		getContentPane().add(panelForm);
		panelForm.setLayout(null);

		cbxManagerID = new JCheckBox("Manager ID");
		cbxManagerID.setBounds(6, 20, 97, 23);
		panelForm.add(cbxManagerID);

		fieldManagerID = new JTextField();
		fieldManagerID.setEditable(false);
		fieldManagerID.setColumns(10);
		fieldManagerID.setBounds(109, 21, 48, 20);
		panelForm.add(fieldManagerID);

		cbxSalary = new JCheckBox("Salary");
		cbxSalary.setBounds(6, 63, 97, 23);
		panelForm.add(cbxSalary);

		panelSalary = new JPanel();
		panelSalary.setBorder(new LineBorder(new Color(192, 192, 192)));
		panelSalary.setBounds(116, 57, 288, 32);
		panelForm.add(panelSalary);
		panelSalary.setLayout(null);

		lblMinSalary = new JLabel("Min:");
		lblMinSalary.setBounds(10, 9, 34, 14);
		panelSalary.add(lblMinSalary);

		fieldMinSalary = new JTextField();
		fieldMinSalary.setEditable(false);
		fieldMinSalary.setColumns(10);
		fieldMinSalary.setBounds(43, 6, 86, 20);
		panelSalary.add(fieldMinSalary);

		lblMaxSalary = new JLabel("Max:");
		lblMaxSalary.setBounds(139, 9, 41, 14);
		panelSalary.add(lblMaxSalary);

		fieldMaxSalary = new JTextField();
		fieldMaxSalary.setEditable(false);
		fieldMaxSalary.setColumns(10);
		fieldMaxSalary.setBounds(183, 6, 102, 20);
		panelSalary.add(fieldMaxSalary);

		panelCommission = new JPanel();
		panelCommission.setLayout(null);
		panelCommission.setBorder(new LineBorder(new Color(192, 192, 192)));
		panelCommission.setBounds(116, 100, 288, 32);
		panelForm.add(panelCommission);

		lblMinCom = new JLabel("Min:");
		lblMinCom.setBounds(10, 9, 34, 14);
		panelCommission.add(lblMinCom);

		fieldMinCom = new JTextField();
		fieldMinCom.setEditable(false);
		fieldMinCom.setColumns(10);
		fieldMinCom.setBounds(43, 6, 86, 20);
		panelCommission.add(fieldMinCom);

		lblMaxCom = new JLabel("Max:");
		lblMaxCom.setBounds(139, 9, 41, 14);
		panelCommission.add(lblMaxCom);

		fieldMaxCom = new JTextField();
		fieldMaxCom.setEditable(false);
		fieldMaxCom.setColumns(10);
		fieldMaxCom.setBounds(183, 6, 102, 20);
		panelCommission.add(fieldMaxCom);

		cbxCommission = new JCheckBox("Commission");
		cbxCommission.setBounds(6, 106, 106, 23);
		panelForm.add(cbxCommission);

		separator = new JSeparator();
		separator.setBounds(0, 28, 434, 8);
		getContentPane().add(separator);

		fieldDNI = new JTextField();
		fieldDNI.setEditable(false);
		fieldDNI.setBounds(136, 79, 160, 20);
		getContentPane().add(fieldDNI);
		fieldDNI.setColumns(10);

		panelButton = new JPanel();
		panelButton.setBounds(10, 256, 414, 38);
		getContentPane().add(panelButton);
		panelButton.setLayout(null);

		btnApply = new JButton("Apply Filters");
		btnApply.setBounds(13, 7, 120, 23);
		panelButton.add(btnApply);

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(279, 7, 120, 23);
		panelButton.add(btnCancel);

		btnReset = new JButton("Reset");
		btnReset.setBounds(146, 8, 120, 23);
		panelButton.add(btnReset);

		cbxDNI = new JCheckBox("Search by DNI");
		cbxDNI.setBounds(16, 78, 114, 23);
		getContentPane().add(cbxDNI);
		
		panelWarnings = new JPanel();
		panelWarnings.setBounds(10, 32, 415, 40);
		getContentPane().add(panelWarnings);
		
		lblWarning = new JLabel("¡The filter will be applied to the current managers shown in the table!");
		panelWarnings.add(lblWarning);
		
		lblThisMeansThat = new JLabel("This means that you can accumulate filters on top of another");
		panelWarnings.add(lblThisMeansThat);

		new ControllerFilterManager(this);

		setVisible(true);
	}

	public void addActListener(ActionListener listener) {
		this.btnApply.addActionListener(listener);
		this.btnCancel.addActionListener(listener);
		this.btnReset.addActionListener(listener);
	}

	public void addItmListener(ItemListener listener) {
		this.cbxDNI.addItemListener(listener);
		this.cbxManagerID.addItemListener(listener);
		this.cbxSalary.addItemListener(listener);
		this.cbxCommission.addItemListener(listener);
	}

	

	public ControllerManageManagers getgManageController() {
		return gManageController;
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

	public JButton getBtnReset() {
		return btnReset;
	}

	public void setBtnReset(JButton btnReset) {
		this.btnReset = btnReset;
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
