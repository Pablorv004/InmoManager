package views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;

import controllers.ControllerAddProperty;
import util.GlobalResources;

public class GUIAddProperty extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelTitleCard;
	private JPanel panelData;
	private JPanel panelButtons;
	private JButton btnAdd;
	private JButton btnCancel;
	private JPanel panelTitle;
	private JLabel lblLogo;
	private JLabel lblTitle;
	private JSeparator separator;
	private JLabel lblRentSell;
	private JPanel panelSellRent;
	private JRadioButton rdBtnSell;
	private JRadioButton rdBtnRent;
	private JLabel lblAddress;
	private JTextField txtAddress;
	private JLabel lblCity;
	private JTextField txtCity;
	private JLabel lblPropertySize;
	private JTextField txtPropertySize;
	private JLabel lblType;
	private JComboBox<String> cBType;
	private JLabel lblRooms;
	private JComboBox<Integer> cBRooms;
	private JLabel lblFloors;
	private JSlider sliderFloors;
	private JLabel lblBathrooms;
	private JComboBox<Integer> cBBathrooms;
	private JCheckBox cbxGarage;
	private JLabel lblGarageSize;
	private JTextField txtGarage;
	private final ButtonGroup btgRentSell = new ButtonGroup();
	private JLabel lblTerrain;
	private JTextField txtTerrain;
	private JCheckBox cbxGarden;
	private JCheckBox cbxBasement;
	private JCheckBox cbxPool;
	private JCheckBox cbxTerrace;
	private JCheckBox cbxLift;
	private JCheckBox cbxAC;
	private JLabel lblStatus;
	private JTextField txtStatus;
	private JLabel lblValue;
	private JTextField txtValue;
	private JLabel lblAge;
	private JTextField txtAge;
	private GUIMainUser mainUser;
	private GUIManageProperties gProperties;
	
	public GUIAddProperty(JFrame frame) {
		setTitle("Add Property");
		setBounds(100, 100, 597, 728);
		
		if(frame instanceof GUIMainUser) {
			this.mainUser = (GUIMainUser) frame;
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLocationRelativeTo(mainUser);
		}else {
			this.gProperties = (GUIManageProperties) frame;
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setLocationRelativeTo(gProperties);
		}
		
		GlobalResources.setFrameIcon(this);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelTitleCard = new JPanel();
		contentPane.add(panelTitleCard, BorderLayout.NORTH);
		panelTitleCard.setLayout(new BorderLayout(0, 0));
		
		panelTitle = new JPanel();
		panelTitleCard.add(panelTitle, BorderLayout.CENTER);
		
		lblTitle = new JLabel("Add Property");
		lblTitle.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		panelTitle.add(lblTitle);
		
		lblLogo = new JLabel("");
		lblLogo.setIcon(GlobalResources.getIconSmall());
		panelTitleCard.add(lblLogo, BorderLayout.WEST);
		
		separator = new JSeparator();
		panelTitleCard.add(separator, BorderLayout.SOUTH);
		
		panelData = new JPanel();
		contentPane.add(panelData, BorderLayout.CENTER);
		panelData.setLayout(new GridLayout(17, 2, 0, 0));
		
		lblRentSell = new JLabel("What're you aiming to do?(*):");
		panelData.add(lblRentSell);
		
		panelSellRent = new JPanel();
		panelData.add(panelSellRent);
		
		rdBtnSell = new JRadioButton("Sell");
		btgRentSell.add(rdBtnSell);
		panelSellRent.add(rdBtnSell);
		
		rdBtnRent = new JRadioButton("Rent");
		btgRentSell.add(rdBtnRent);
		panelSellRent.add(rdBtnRent);
		
		lblType = new JLabel("Select the type of housing:");
		panelData.add(lblType);
		
		cBType = new JComboBox<>();
		panelData.add(cBType);
		cBType.addItem("Flat");
		cBType.addItem("Detached House");
		cBType.addItem("Apartment");
		cBType.addItem("House");
		cBType.setSelectedIndex(0);
		lblStatus = new JLabel("Describe the status of the home(*):");
		panelData.add(lblStatus);
		
		txtStatus = new JTextField();
		panelData.add(txtStatus);
		txtStatus.setColumns(10);
		
		lblAddress = new JLabel("Address(*):");
		panelData.add(lblAddress);
		
		txtAddress = new JTextField();
		panelData.add(txtAddress);
		txtAddress.setColumns(10);
		
		lblCity = new JLabel("City(*):");
		panelData.add(lblCity);
		
		txtCity = new JTextField();
		panelData.add(txtCity);
		txtCity.setColumns(10);
		
		lblAge = new JLabel("Age(Years)(*):");
		panelData.add(lblAge);
		
		txtAge = new JTextField();
		panelData.add(txtAge);
		txtAge.setColumns(10);
		
		lblPropertySize = new JLabel("Property size (m2)(*):");
		panelData.add(lblPropertySize);
		
		txtPropertySize = new JTextField();
		panelData.add(txtPropertySize);
		txtPropertySize.setColumns(10);
		
		lblTerrain = new JLabel("Terrain size (m2):");
		panelData.add(lblTerrain);
		
		txtTerrain = new JTextField();
		panelData.add(txtTerrain);
		txtTerrain.setColumns(10);
		
		lblRooms = new JLabel("How many rooms?:");
		panelData.add(lblRooms);
		
		cBRooms = new JComboBox<>();
		panelData.add(cBRooms);

		lblBathrooms = new JLabel("How many bathrooms?:");
		panelData.add(lblBathrooms);
		
		cBBathrooms = new JComboBox<>();
		panelData.add(cBBathrooms);
		
		for(int i = 0; i <= 20; i++) {
			cBRooms.addItem(i);
			cBBathrooms.addItem(i);
		}
		
		cBBathrooms.setSelectedIndex(0);
		cBRooms.setSelectedIndex(0);
		
		sliderFloors = new JSlider();
		sliderFloors.setValue(1);
		sliderFloors.setMinimum(1);
		sliderFloors.setMaximum(10);
		
		lblFloors = new JLabel("Floors: " + sliderFloors.getValue());
		panelData.add(lblFloors);
		
		
		panelData.add(sliderFloors);
		
		lblValue = new JLabel("Value(*):");
		panelData.add(lblValue);
		
		txtValue = new JTextField();
		panelData.add(txtValue);
		txtValue.setColumns(10);
		
		cbxGarden = new JCheckBox("Garden?");
		panelData.add(cbxGarden);
		
		cbxGarage = new JCheckBox("Garage?");
		panelData.add(cbxGarage);
		
		lblGarageSize = new JLabel("Garage size:");
		panelData.add(lblGarageSize);
		
		txtGarage = new JTextField();
		txtGarage.setEnabled(false);
		panelData.add(txtGarage);
		txtGarage.setColumns(10);
		
		cbxBasement = new JCheckBox("Basement?");
		panelData.add(cbxBasement);
		
		cbxPool = new JCheckBox("Pool?");
		panelData.add(cbxPool);
		
		cbxTerrace = new JCheckBox("Terrace?");
		panelData.add(cbxTerrace);
		
		cbxLift = new JCheckBox("Lift?");
		panelData.add(cbxLift);
		
		cbxAC = new JCheckBox("Air Conditioning?");
		panelData.add(cbxAC);
		
		panelButtons = new JPanel();
		contentPane.add(panelButtons, BorderLayout.SOUTH);
		
		btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
		panelButtons.add(btnAdd);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
		panelButtons.add(btnCancel);
		//Add the controller
		new ControllerAddProperty(this);
		setVisible(true);
	}
	
	public void addActListeners(ActionListener listener) {
		btnAdd.addActionListener(listener);
		btnCancel.addActionListener(listener);
	}
	
	public void addChangeListeners(ChangeListener changelistener) {
		sliderFloors.addChangeListener(changelistener);
	}

	public void addRadioButtonListeners (ItemListener listener){
		rdBtnSell.addItemListener(listener);
		rdBtnRent.addItemListener(listener);
	}
	
	public void addCheckBoxListeners (ItemListener listener) {
		cbxGarage.addItemListener(listener);
	}

	public JLabel getLblAge() {
		return lblAge;
	}

	public void setLblAge(JLabel lblAge) {
		this.lblAge = lblAge;
	}

	public JTextField getTxtAge() {
		return txtAge;
	}

	public void setTxtAge(JTextField txtAge) {
		this.txtAge = txtAge;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public void setBtnAdd(JButton btnAdd) {
		this.btnAdd = btnAdd;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}

	public JLabel getLblLogo() {
		return lblLogo;
	}

	public void setLblLogo(JLabel lblLogo) {
		this.lblLogo = lblLogo;
	}

	public JLabel getLblTitle() {
		return lblTitle;
	}

	public void setLblTitle(JLabel lblTitle) {
		this.lblTitle = lblTitle;
	}

	public JLabel getLblRentSell() {
		return lblRentSell;
	}

	public void setLblRentSell(JLabel lblRentSell) {
		this.lblRentSell = lblRentSell;
	}

	public JRadioButton getRdBtnSell() {
		return rdBtnSell;
	}

	public void setRdBtnSell(JRadioButton rdBtnSell) {
		this.rdBtnSell = rdBtnSell;
	}

	public JRadioButton getRdBtnRent() {
		return rdBtnRent;
	}

	public void setRdBtnRent(JRadioButton rdBtnRent) {
		this.rdBtnRent = rdBtnRent;
	}

	public JLabel getLblAddress() {
		return lblAddress;
	}

	public void setLblAddress(JLabel lblAddress) {
		this.lblAddress = lblAddress;
	}

	public JTextField getTxtAddress() {
		return txtAddress;
	}

	public void setTxtAddress(JTextField txtAddress) {
		this.txtAddress = txtAddress;
	}

	public JLabel getLblCity() {
		return lblCity;
	}

	public void setLblCity(JLabel lblCity) {
		this.lblCity = lblCity;
	}

	public JTextField getTxtCity() {
		return txtCity;
	}

	public void setTxtCity(JTextField txtCity) {
		this.txtCity = txtCity;
	}

	public JLabel getLblPropertySize() {
		return lblPropertySize;
	}

	public void setLblPropertySize(JLabel lblPropertySize) {
		this.lblPropertySize = lblPropertySize;
	}

	public JTextField getTxtPropertySize() {
		return txtPropertySize;
	}

	public void setTxtPropertySize(JTextField txtPropertySize) {
		this.txtPropertySize = txtPropertySize;
	}

	public JLabel getLblType() {
		return lblType;
	}

	public void setLblType(JLabel lblType) {
		this.lblType = lblType;
	}

	public JComboBox<String> getcBType() {
		return cBType;
	}

	public void setcBType(JComboBox<String> cBType) {
		this.cBType = cBType;
	}

	public JLabel getLblRooms() {
		return lblRooms;
	}

	public void setLblRooms(JLabel lblRooms) {
		this.lblRooms = lblRooms;
	}

	public JComboBox<Integer> getcBRooms() {
		return cBRooms;
	}

	public void setcBRooms(JComboBox<Integer> cBRooms) {
		this.cBRooms = cBRooms;
	}

	public JLabel getLblFloors() {
		return lblFloors;
	}

	public void setLblFloors(JLabel lblFloors) {
		this.lblFloors = lblFloors;
	}

	public JSlider getSliderFloors() {
		return sliderFloors;
	}

	public void setSliderFloors(JSlider sliderFloors) {
		this.sliderFloors = sliderFloors;
	}

	public JLabel getLblBathrooms() {
		return lblBathrooms;
	}

	public void setLblBathrooms(JLabel lblBathrooms) {
		this.lblBathrooms = lblBathrooms;
	}

	public JComboBox<Integer> getcBBathrooms() {
		return cBBathrooms;
	}

	public void setcBBathrooms(JComboBox<Integer> cBBathrooms) {
		this.cBBathrooms = cBBathrooms;
	}

	public JCheckBox getCbxGarage() {
		return cbxGarage;
	}

	public void setCbxGarage(JCheckBox cbxGarage) {
		this.cbxGarage = cbxGarage;
	}

	public JLabel getLblGarageSize() {
		return lblGarageSize;
	}

	public void setLblGarageSize(JLabel lblGarageSize) {
		this.lblGarageSize = lblGarageSize;
	}

	public JTextField getTxtGarage() {
		return txtGarage;
	}

	public void setTxtGarage(JTextField txtGarage) {
		this.txtGarage = txtGarage;
	}

	public JLabel getLblTerrain() {
		return lblTerrain;
	}

	public void setLblTerrain(JLabel lblTerrain) {
		this.lblTerrain = lblTerrain;
	}

	public JTextField getTxtTerrain() {
		return txtTerrain;
	}

	public void setTxtTerrain(JTextField txtTerrain) {
		this.txtTerrain = txtTerrain;
	}

	public JCheckBox getCbxGarden() {
		return cbxGarden;
	}

	public void setCbxGarden(JCheckBox cbxGarden) {
		this.cbxGarden = cbxGarden;
	}

	public JCheckBox getCbxBasement() {
		return cbxBasement;
	}

	public void setCbxBasement(JCheckBox cbxBasement) {
		this.cbxBasement = cbxBasement;
	}

	public JCheckBox getCbxPool() {
		return cbxPool;
	}

	public void setCbxPool(JCheckBox cbxPool) {
		this.cbxPool = cbxPool;
	}

	public JCheckBox getCbxTerrace() {
		return cbxTerrace;
	}

	public void setCbxTerrace(JCheckBox cbxTerrace) {
		this.cbxTerrace = cbxTerrace;
	}

	public JCheckBox getCbxLift() {
		return cbxLift;
	}

	public void setCbxLift(JCheckBox cbxLift) {
		this.cbxLift = cbxLift;
	}

	public JCheckBox getCbxAC() {
		return cbxAC;
	}

	public void setCbxAC(JCheckBox cbxAC) {
		this.cbxAC = cbxAC;
	}

	public JLabel getLblStatus() {
		return lblStatus;
	}

	public void setLblStatus(JLabel lblStatus) {
		this.lblStatus = lblStatus;
	}

	public JTextField getTxtStatus() {
		return txtStatus;
	}

	public void setTxtStatus(JTextField txtStatus) {
		this.txtStatus = txtStatus;
	}

	public JLabel getLblValue() {
		return lblValue;
	}

	public void setLblValue(JLabel lblValue) {
		this.lblValue = lblValue;
	}

	public JTextField getTxtValue() {
		return txtValue;
	}

	public void setTxtValue(JTextField txtValue) {
		this.txtValue = txtValue;
	}

	public GUIManageProperties getgProperties() {
		return gProperties;
	}

	public void setgProperties(GUIManageProperties gProperties) {
		this.gProperties = gProperties;
	}
}
