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

import controllers.ControllerManageProperties;
import util.GlobalResources;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;

public class GUIManageProperties extends JFrame {

	private static final long serialVersionUID = 1L;
	private GUILogin gLogin;
	private JScrollPane scrollPane;
	private JPanel contentPane;
	private JPanel panelTitle;
	private JPanel panelEdit;
	private JPanel panelExtras;
	private JPanel panelImage;
	private JTable table;
	private JButton btnReset;
	private JButton btnFilter;
	private JButton btnEdit;
	private JButton btnApply;
	private JButton btnReturn;
	private JTextField fieldID;
	private JTextField fieldCity;
	private JTextField fieldRooms;
	private JTextField fieldPropertySize;
	private JTextField fieldAddress;
	private JLabel lblLogo;
	private JPanel panelForm;
	private JLabel lblID;
	private JLabel lblCity;
	private JLabel lblRooms;
	private JLabel lblExtras;
	private JLabel lblPropertySize;
	private JLabel lblAddress;
	private JLabel lblImage;
	private List<JTextField> textFieldList;
	private JLabel lblType;
	private JTextField fieldType;
	private JLabel lblAge;
	private JTextField fieldAge;
	private JLabel lblFloors;
	private JTextField fieldFloors;
	private JLabel lblBathrooms;
	private JTextField fieldBathrooms;
	private JLabel lblTerrainSize;
	private JTextField fieldTerrainSize;
	private JLabel lblGarageSize;
	private JTextField fieldGarageSize;
	private JTextArea fieldStatus;
	private JScrollPane scrollPane_1;
	private JLabel lblStatus;
	private JCheckBox cbxGarden;
	private JCheckBox cbxBasement;
	private JCheckBox cbxGarage;
	private JCheckBox cbxPool;
	private JCheckBox cbxLift;
	private JCheckBox cbxTerrace;
	private JCheckBox cbxAC;
	private JLabel lblPrice;
	private JTextField fieldPrice;

	public GUIManageProperties(GUILogin gLogin) {
		super("Managers Managements");
		this.gLogin = gLogin;
		this.textFieldList = new ArrayList<>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 716, 656);
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

		JLabel lblTitle = new JLabel("Property Management");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTitle.setBounds(176, 11, 329, 37);
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

		panelImage = new JPanel();
		panelImage.setBounds(506, 349, 186, 210);
		contentPane.add(panelImage);
		panelImage.setLayout(new BorderLayout(0, 0));

		lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon("files/images/InmoManager186-210Properties.png"));
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		panelImage.add(lblImage, BorderLayout.CENTER);

		btnReturn = new JButton("");
		btnReturn.setBounds(642, 11, 30, 30);
		btnReturn.setIcon(GlobalResources.getIconReturn());
		btnReturn.setContentAreaFilled(false);
		btnReturn.setBorderPainted(false);
		panelTitle.add(btnReturn);

		panelForm = new JPanel();
		panelForm.setBorder(new LineBorder(new Color(192, 192, 192)));
		panelForm.setBounds(10, 302, 479, 304);
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

		lblCity = new JLabel("City");
		lblCity.setBounds(10, 45, 25, 20);
		panelForm.add(lblCity);

		fieldCity = new JTextField();
		fieldCity.setEditable(false);
		fieldCity.setHorizontalAlignment(SwingConstants.LEFT);
		fieldCity.setColumns(10);
		fieldCity.setBounds(52, 45, 106, 20);
		panelForm.add(fieldCity);

		panelEdit = new JPanel();
		panelEdit.setBorder(new LineBorder(new Color(192, 192, 192)));
		panelEdit.setBounds(10, 258, 189, 35);
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

		lblRooms = new JLabel("Rooms");
		lblRooms.setBounds(10, 79, 43, 20);
		panelForm.add(lblRooms);

		fieldRooms = new JTextField();
		fieldRooms.setEditable(false);
		fieldRooms.setHorizontalAlignment(SwingConstants.LEFT);
		fieldRooms.setColumns(10);
		fieldRooms.setBounds(62, 79, 36, 20);
		panelForm.add(fieldRooms);

		panelExtras = new JPanel();
		panelExtras.setBorder(new LineBorder(new Color(192, 192, 192)));
		panelExtras.setBounds(280, 177, 189, 116);
		panelForm.add(panelExtras);
		panelExtras.setLayout(null);
		
		cbxGarden = new JCheckBox("Garden");
		cbxGarden.setEnabled(false);
		cbxGarden.setBounds(1, 4, 85, 23);
		panelExtras.add(cbxGarden);
		
		cbxBasement = new JCheckBox("Basement");
		cbxBasement.setEnabled(false);
		cbxBasement.setBounds(1, 31, 99, 23);
		panelExtras.add(cbxBasement);
		
		cbxGarage = new JCheckBox("Garage");
		cbxGarage.setEnabled(false);
		cbxGarage.setBounds(1, 58, 99, 23);
		panelExtras.add(cbxGarage);
		
		cbxPool = new JCheckBox("Pool");
		cbxPool.setEnabled(false);
		cbxPool.setBounds(96, 4, 79, 23);
		panelExtras.add(cbxPool);
		
		cbxLift = new JCheckBox("Lift");
		cbxLift.setEnabled(false);
		cbxLift.setBounds(96, 31, 79, 23);
		panelExtras.add(cbxLift);
		
		cbxTerrace = new JCheckBox("Terrace");
		cbxTerrace.setEnabled(false);
		cbxTerrace.setBounds(96, 58, 79, 23);
		panelExtras.add(cbxTerrace);
		
		cbxAC = new JCheckBox("Air-Conditioner");
		cbxAC.setEnabled(false);
		cbxAC.setBounds(1, 85, 139, 23);
		panelExtras.add(cbxAC);

		lblExtras = new JLabel("Extras");
		lblExtras.setHorizontalAlignment(SwingConstants.CENTER);
		lblExtras.setBounds(332, 158, 75, 14);
		panelForm.add(lblExtras);

		lblPropertySize = new JLabel("Property Size");
		lblPropertySize.setBounds(10, 113, 85, 20);
		panelForm.add(lblPropertySize);

		fieldPropertySize = new JTextField();
		fieldPropertySize.setHorizontalAlignment(SwingConstants.LEFT);
		fieldPropertySize.setEditable(false);
		fieldPropertySize.setColumns(10);
		fieldPropertySize.setBounds(99, 113, 43, 20);
		panelForm.add(fieldPropertySize);

		lblAddress = new JLabel("Address");
		lblAddress.setBounds(10, 11, 78, 20);
		panelForm.add(lblAddress);

		fieldAddress = new JTextField();
		fieldAddress.setHorizontalAlignment(SwingConstants.LEFT);
		fieldAddress.setEditable(false);
		fieldAddress.setColumns(10);
		fieldAddress.setBounds(88, 11, 315, 20);
		panelForm.add(fieldAddress);

		lblType = new JLabel("Type");
		lblType.setBounds(193, 45, 43, 20);
		panelForm.add(lblType);

		fieldType = new JTextField();
		fieldType.setHorizontalAlignment(SwingConstants.LEFT);
		fieldType.setEditable(false);
		fieldType.setColumns(10);
		fieldType.setBounds(235, 45, 106, 20);
		panelForm.add(fieldType);

		lblAge = new JLabel("Age");
		lblAge.setBounds(385, 45, 25, 20);
		panelForm.add(lblAge);

		fieldAge = new JTextField();
		fieldAge.setHorizontalAlignment(SwingConstants.LEFT);
		fieldAge.setEditable(false);
		fieldAge.setColumns(10);
		fieldAge.setBounds(423, 45, 35, 20);
		panelForm.add(fieldAge);

		lblFloors = new JLabel("Floors");
		lblFloors.setBounds(110, 79, 43, 20);
		panelForm.add(lblFloors);

		fieldFloors = new JTextField();
		fieldFloors.setHorizontalAlignment(SwingConstants.LEFT);
		fieldFloors.setEditable(false);
		fieldFloors.setColumns(10);
		fieldFloors.setBounds(152, 79, 36, 20);
		panelForm.add(fieldFloors);

		lblBathrooms = new JLabel("Bathrooms");
		lblBathrooms.setBounds(215, 79, 68, 20);
		panelForm.add(lblBathrooms);

		fieldBathrooms = new JTextField();
		fieldBathrooms.setHorizontalAlignment(SwingConstants.LEFT);
		fieldBathrooms.setEditable(false);
		fieldBathrooms.setColumns(10);
		fieldBathrooms.setBounds(287, 79, 36, 20);
		panelForm.add(fieldBathrooms);

		lblTerrainSize = new JLabel("Terrain Size");
		lblTerrainSize.setBounds(162, 113, 85, 20);
		panelForm.add(lblTerrainSize);

		fieldTerrainSize = new JTextField();
		fieldTerrainSize.setHorizontalAlignment(SwingConstants.LEFT);
		fieldTerrainSize.setEditable(false);
		fieldTerrainSize.setColumns(10);
		fieldTerrainSize.setBounds(240, 113, 43, 20);
		panelForm.add(fieldTerrainSize);

		lblGarageSize = new JLabel("Garage Size");
		lblGarageSize.setBounds(319, 113, 85, 20);
		panelForm.add(lblGarageSize);

		fieldGarageSize = new JTextField();
		fieldGarageSize.setHorizontalAlignment(SwingConstants.LEFT);
		fieldGarageSize.setEditable(false);
		fieldGarageSize.setColumns(10);
		fieldGarageSize.setBounds(397, 113, 43, 20);
		panelForm.add(fieldGarageSize);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 166, 226, 57);
		panelForm.add(scrollPane_1);
		
		fieldStatus = new JTextArea();
		fieldStatus.setEnabled(false);
		scrollPane_1.setViewportView(fieldStatus);
		fieldStatus.setLineWrap(true);
		fieldStatus.setEditable(false);
		
		lblStatus = new JLabel("Property Status");
		lblStatus.setBounds(10, 146, 121, 20);
		panelForm.add(lblStatus);
		
		lblPrice = new JLabel("Price");
		lblPrice.setBounds(10, 231, 43, 20);
		panelForm.add(lblPrice);
		
		fieldPrice = new JTextField();
		fieldPrice.setHorizontalAlignment(SwingConstants.LEFT);
		fieldPrice.setEditable(false);
		fieldPrice.setColumns(10);
		fieldPrice.setBounds(52, 231, 68, 20);
		panelForm.add(fieldPrice);

		new ControllerManageProperties(this);
		fillTextFieldList();
		setVisible(true);
	}

	public void addActListener(ActionListener listener) {
		this.btnApply.addActionListener(listener);
		this.btnEdit.addActionListener(listener);
		this.btnReset.addActionListener(listener);
		this.btnReturn.addActionListener(listener);
		this.btnFilter.addActionListener(listener);
	}

	public void addTableListener(ListSelectionListener listener) {
		this.table.getSelectionModel().addListSelectionListener(listener);
	}

	// Fills the JTextField list with all JTextFields of the view
	private void fillTextFieldList() {
		textFieldList.add(fieldID);
		textFieldList.add(fieldCity);
		textFieldList.add(fieldRooms);
		textFieldList.add(fieldPropertySize);
		textFieldList.add(fieldAddress);
	}
}

