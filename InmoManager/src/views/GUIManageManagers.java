package views;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import util.GlobalResources;

public class GUIManageManagers extends JFrame {

	private static final long serialVersionUID = 1L;
	private GUIMainManager gManager;
	private JPanel contentPane;
	private JPanel titlePanel;
	private JTable table;
	private JLabel lblLogo;
	private JButton btnReset;
	private JButton btnSearch;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblImage_1;
	private JButton btnNext;
	private JButton btnPrevious;
	private JButton btnEdit;
	private JButton btnApply;
	private JLabel lblID;
	private JTextField fieldID;
	private JLabel lblDNI;
	private JTextField fieldDNI;
	private JPanel panel_2;
	private JLabel lblName;
	private JTextField fieldName;
	private JPanel panelCredentials;
	private JLabel lblCredentials;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JTextField fieldUsername;
	private JTextField fieldPassword;
	private JLabel lblEmail;
	private JTextField fieldEmail;
	private JLabel lblPhone;
	private JTextField fieldPhone;
	private JLabel lblCommission;
	private JTextField fieldCommission;
	private JLabel lblBank;
	private JTextField fieldBank;
	private JLabel lblHireDate;
	private JTextField fieldHireDate;
	private JLabel lblManagerID;
	private JTextField fieldManagerID;
	private JLabel lblSalary;
	private JTextField fieldSalary;
	private JButton btnReturn;
	
	public GUIManageManagers(GUIMainManager gManager) {
		super("Managers Managements");
		this.gManager = gManager;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 716, 630);
		setLocationRelativeTo(gManager);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		titlePanel = new JPanel();
		titlePanel.setBounds(10, 11, 682, 60);
		contentPane.add(titlePanel);
		titlePanel.setLayout(null);
		
		JLabel lblTitle = new JLabel("Manager Management");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTitle.setBounds(192, 11, 297, 37);
		titlePanel.add(lblTitle);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblLogo = new JLabel("");
		lblLogo.setBounds(0, 2, 64, 55);
		titlePanel.add(lblLogo);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 71, 714, 8);
		contentPane.add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
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
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(192, 192, 192)));
		panel.setBounds(10, 302, 479, 280);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblID = new JLabel("ID");
		lblID.setBounds(413, 11, 25, 20);
		panel.add(lblID);
		
		fieldID = new JTextField();
		fieldID.setEditable(false);
		fieldID.setHorizontalAlignment(SwingConstants.LEFT);
		fieldID.setBounds(434, 11, 35, 20);
		panel.add(fieldID);
		fieldID.setColumns(10);
		
		lblDNI = new JLabel("DNI");
		lblDNI.setBounds(10, 45, 25, 20);
		panel.add(lblDNI);
		
		fieldDNI = new JTextField();
		fieldDNI.setEditable(false);
		fieldDNI.setHorizontalAlignment(SwingConstants.LEFT);
		fieldDNI.setColumns(10);
		fieldDNI.setBounds(52, 45, 83, 20);
		panel.add(fieldDNI);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(192, 192, 192)));
		panel_2.setBounds(285, 240, 189, 35);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		btnEdit = new JButton("Edit");
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnEdit.setBounds(4, 6, 60, 23);
		panel_2.add(btnEdit);
		
		btnApply = new JButton("Apply Changes");
		btnApply.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnApply.setEnabled(false);
		btnApply.setBounds(68, 6, 117, 23);
		panel_2.add(btnApply);
		
		lblName = new JLabel("Name");
		lblName.setBounds(10, 79, 43, 20);
		panel.add(lblName);
		
		fieldName = new JTextField();
		fieldName.setEditable(false);
		fieldName.setHorizontalAlignment(SwingConstants.LEFT);
		fieldName.setColumns(10);
		fieldName.setBounds(52, 79, 210, 20);
		panel.add(fieldName);
		
		panelCredentials = new JPanel();
		panelCredentials.setBorder(new LineBorder(new Color(192, 192, 192)));
		panelCredentials.setBounds(285, 153, 189, 76);
		panel.add(panelCredentials);
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
		
		fieldPassword = new JTextField();
		fieldPassword.setEditable(false);
		fieldPassword.setHorizontalAlignment(SwingConstants.LEFT);
		fieldPassword.setColumns(10);
		fieldPassword.setBounds(80, 44, 103, 20);
		panelCredentials.add(fieldPassword);
		
		lblCredentials = new JLabel("Credentials");
		lblCredentials.setHorizontalAlignment(SwingConstants.CENTER);
		lblCredentials.setBounds(337, 134, 75, 14);
		panel.add(lblCredentials);
		
		lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 113, 43, 20);
		panel.add(lblEmail);
		
		fieldEmail = new JTextField();
		fieldEmail.setHorizontalAlignment(SwingConstants.LEFT);
		fieldEmail.setEditable(false);
		fieldEmail.setColumns(10);
		fieldEmail.setBounds(52, 113, 210, 20);
		panel.add(fieldEmail);
		
		lblPhone = new JLabel("Phone");
		lblPhone.setBounds(10, 147, 43, 20);
		panel.add(lblPhone);
		
		fieldPhone = new JTextField();
		fieldPhone.setHorizontalAlignment(SwingConstants.LEFT);
		fieldPhone.setEditable(false);
		fieldPhone.setColumns(10);
		fieldPhone.setBounds(52, 147, 106, 20);
		panel.add(fieldPhone);
		
		lblCommission = new JLabel("Commission");
		lblCommission.setBounds(10, 249, 78, 20);
		panel.add(lblCommission);
		
		fieldCommission = new JTextField();
		fieldCommission.setHorizontalAlignment(SwingConstants.LEFT);
		fieldCommission.setEditable(false);
		fieldCommission.setColumns(10);
		fieldCommission.setBounds(88, 249, 35, 20);
		panel.add(fieldCommission);
		
		lblBank = new JLabel("Bank Account");
		lblBank.setBounds(10, 11, 90, 20);
		panel.add(lblBank);
		
		fieldBank = new JTextField();
		fieldBank.setHorizontalAlignment(SwingConstants.LEFT);
		fieldBank.setEditable(false);
		fieldBank.setColumns(10);
		fieldBank.setBounds(96, 11, 307, 20);
		panel.add(fieldBank);
		
		lblHireDate = new JLabel("Hire Date");
		lblHireDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblHireDate.setBounds(10, 181, 68, 20);
		panel.add(lblHireDate);
		
		fieldHireDate = new JTextField();
		fieldHireDate.setHorizontalAlignment(SwingConstants.LEFT);
		fieldHireDate.setEditable(false);
		fieldHireDate.setColumns(10);
		fieldHireDate.setBounds(72, 181, 131, 20);
		panel.add(fieldHireDate);
		
		lblManagerID = new JLabel("Manager ID");
		lblManagerID.setBounds(142, 249, 78, 20);
		panel.add(lblManagerID);
		
		fieldManagerID = new JTextField();
		fieldManagerID.setHorizontalAlignment(SwingConstants.LEFT);
		fieldManagerID.setEditable(false);
		fieldManagerID.setColumns(10);
		fieldManagerID.setBounds(223, 249, 35, 20);
		panel.add(fieldManagerID);
		
		lblSalary = new JLabel("Salary");
		lblSalary.setBounds(10, 215, 43, 20);
		panel.add(lblSalary);
		
		fieldSalary = new JTextField();
		fieldSalary.setHorizontalAlignment(SwingConstants.LEFT);
		fieldSalary.setEditable(false);
		fieldSalary.setColumns(10);
		fieldSalary.setBounds(63, 215, 61, 20);
		panel.add(fieldSalary);
		
		panel_1 = new JPanel();
		panel_1.setBounds(506, 302, 186, 210);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		lblImage_1 = new JLabel("Image");
		lblImage_1.setBounds(7, 0, 171, 167);
		lblImage_1.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblImage_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblImage_1);
		
		btnNext = new JButton("Next");
		btnNext.setBounds(6, 178, 77, 23);
		panel_1.add(btnNext);
		
		btnPrevious = new JButton("Previous");
		btnPrevious.setBounds(89, 178, 89, 23);
		panel_1.add(btnPrevious);
		
		btnReturn = new JButton("");
		btnReturn.setBounds(642, 11, 30, 30);
		btnReturn.setIcon(GlobalResources.getIconReturn());
		btnReturn.setContentAreaFilled(false);
		btnReturn.setBorderPainted(false);
		titlePanel.add(btnReturn);
		
		setVisible(true);
	}
}
