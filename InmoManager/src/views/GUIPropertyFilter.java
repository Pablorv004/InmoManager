package views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controllers.ControllerPropertyFilter;
import util.GlobalResources;

public class GUIPropertyFilter extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelButtons;
	private JPanel panelTitle;
	private JPanel panelData;
	private JLabel lblTitle;
	private JLabel lblLogo;
	private JSeparator separator;
	private JLabel lblType;
	private JPanel panelRentSale;
	private JCheckBox cbxRent;
	private JCheckBox cbxSale;
	private GUIUserView userView;
	private JLabel lblCities;
	private JLabel lblAge;
	private JPanel panelAgeRange;
	private JLabel lblTo;
	private JLabel lblYearsOld;
	private JLabel lblPropertySize;
	private JPanel panelPropertyRange;
	private JLabel lblToProperty;
	private JLabel lblsqMetersProperty;
	private JList<String> listCities;
	private JTextField txtPropertyInit;
	private JTextField txtPropertyFinal;
	private JTextField txtAgeInit;
	private JTextField txtAgeFinal;
	private JLabel lblTerrainSize;
	private JPanel panelTerrainRange;
	private JTextField txtTerrainInit;
	private JLabel lblToTerrain;
	private JTextField txtTerrainFinal;
	private JLabel lblsqMetersTerrain;
	private JLabel lblGarageSize;
	private JPanel panelGarageRange;
	private JTextField txtGarageInit;
	private JLabel lblToGarage;
	private JTextField txtGarageFinal;
	private JLabel lblsqMetersGarage;
	private JLabel lblCounts;
	private JPanel panelCounts;
	private JLabel lblFeatures;
	private JLabel lblPriceRange;
	private JPanel panelFeatures;
	private JPanel panelPriceRange;
	private JTextField txtPriceInit;
	private JLabel lblToPrice;
	private JTextField txtPriceFinal;
	private JLabel lblEur;
	private JButton btnReturn;
	private JButton btnApply;
	private JButton btnReset;
	private JCheckBox cbxLift;
	private JCheckBox cbxBasement;
	private JCheckBox cbxGarden;
	private JCheckBox cbxPool;
	private JCheckBox cbxTerrace;
	private JCheckBox cbxAC;
	private JCheckBox cbxGarage;
	private JPanel panelFloors;
	private JPanel panelRooms;
	private JPanel panelBathrooms;
	private JLabel lblFloors;
	private JLabel lblRooms;
	private JLabel lblBathrooms;
	private JScrollPane sPCities;
	private JComboBox<Integer> cbFloors;
	private JComboBox<Integer> cbRooms;
	private JComboBox<Integer> cbBathrooms;

	/**
	 * Create the frame.
	 */
	public GUIPropertyFilter(GUIUserView userView) {
		this.userView = userView;
		setTitle("Filter");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 800);
		setLocationRelativeTo(this.userView);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panelButtons = new JPanel();
		contentPane.add(panelButtons, BorderLayout.SOUTH);

		btnReturn = new JButton("Return");
		btnReturn.setIcon(GlobalResources.getIconReturn());
		panelButtons.add(btnReturn);

		btnApply = new JButton("Apply");
		panelButtons.add(btnApply);

		panelTitle = new JPanel();
		contentPane.add(panelTitle, BorderLayout.NORTH);
		panelTitle.setLayout(new BorderLayout(0, 0));

		lblTitle = new JLabel("Property Filter");
		lblTitle.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		panelTitle.add(lblTitle);

		lblLogo = new JLabel("");
		lblLogo.setIcon(GlobalResources.getIconSmall());
		panelTitle.add(lblLogo, BorderLayout.WEST);

		separator = new JSeparator();
		panelTitle.add(separator, BorderLayout.SOUTH);

		btnReset = new JButton("Reset to Default");
		panelTitle.add(btnReset, BorderLayout.EAST);

		panelData = new JPanel();
		contentPane.add(panelData, BorderLayout.CENTER);
		panelData.setLayout(new GridLayout(9, 2, 0, 0));

		lblType = new JLabel("Type of property:");
		panelData.add(lblType);

		panelRentSale = new JPanel();
		panelData.add(panelRentSale);

		cbxRent = new JCheckBox("To Rent");
		cbxRent.setSelected(true);
		panelRentSale.add(cbxRent);

		cbxSale = new JCheckBox("For Sale");
		cbxSale.setSelected(true);
		panelRentSale.add(cbxSale);

		lblCities = new JLabel("Cities:");
		panelData.add(lblCities);

		listCities = new JList<>();
		sPCities = new JScrollPane(listCities);
		panelData.add(sPCities);

		lblAge = new JLabel("Age Range (blank for default):");
		panelData.add(lblAge);

		panelAgeRange = new JPanel();
		panelData.add(panelAgeRange);

		txtAgeInit = new JTextField();
		panelAgeRange.add(txtAgeInit);
		txtAgeInit.setColumns(5);

		lblTo = new JLabel("To");
		panelAgeRange.add(lblTo);

		txtAgeFinal = new JTextField();
		panelAgeRange.add(txtAgeFinal);
		txtAgeFinal.setColumns(5);

		lblYearsOld = new JLabel("years old");
		panelAgeRange.add(lblYearsOld);

		lblPropertySize = new JLabel("Property Size Range (blank for default):");
		lblPropertySize.setIcon(GlobalResources.getIconPropertySize());
		panelData.add(lblPropertySize);

		panelPropertyRange = new JPanel();
		panelData.add(panelPropertyRange);

		txtPropertyInit = new JTextField();
		panelPropertyRange.add(txtPropertyInit);
		txtPropertyInit.setColumns(5);

		lblToProperty = new JLabel("To");
		panelPropertyRange.add(lblToProperty);

		txtPropertyFinal = new JTextField();
		panelPropertyRange.add(txtPropertyFinal);
		txtPropertyFinal.setColumns(5);

		lblsqMetersProperty = new JLabel("square meters");
		panelPropertyRange.add(lblsqMetersProperty);

		lblTerrainSize = new JLabel("Terrain Size Range (blank for default):");
		lblTerrainSize.setIcon(GlobalResources.getIconTerrainSize());
		panelData.add(lblTerrainSize);

		panelTerrainRange = new JPanel();
		panelData.add(panelTerrainRange);

		txtTerrainInit = new JTextField();
		txtTerrainInit.setColumns(5);
		panelTerrainRange.add(txtTerrainInit);

		lblToTerrain = new JLabel("To");
		panelTerrainRange.add(lblToTerrain);

		txtTerrainFinal = new JTextField();
		txtTerrainFinal.setColumns(5);
		panelTerrainRange.add(txtTerrainFinal);

		lblsqMetersTerrain = new JLabel("square meters");
		panelTerrainRange.add(lblsqMetersTerrain);

		lblGarageSize = new JLabel("Garage Size Range (blank for default):");
		lblGarageSize.setIcon(GlobalResources.getIconGarageSize());
		panelData.add(lblGarageSize);

		panelGarageRange = new JPanel();
		panelData.add(panelGarageRange);

		txtGarageInit = new JTextField();
		txtGarageInit.setEnabled(false);
		txtGarageInit.setColumns(5);
		panelGarageRange.add(txtGarageInit);

		lblToGarage = new JLabel("To");
		panelGarageRange.add(lblToGarage);

		txtGarageFinal = new JTextField();
		txtGarageFinal.setEnabled(false);
		txtGarageFinal.setColumns(5);
		panelGarageRange.add(txtGarageFinal);

		lblsqMetersGarage = new JLabel("square meters");
		panelGarageRange.add(lblsqMetersGarage);

		lblCounts = new JLabel("Counts (blank for default):");
		panelData.add(lblCounts);

		panelCounts = new JPanel();
		panelData.add(panelCounts);
		panelCounts.setLayout(new BoxLayout(panelCounts, BoxLayout.X_AXIS));

		panelFloors = new JPanel();
		panelCounts.add(panelFloors);
		panelFloors.setLayout(new GridLayout(2, 1, 0, 0));

		lblFloors = new JLabel("Floors");
		panelFloors.add(lblFloors);

		cbFloors = new JComboBox<>();
		panelFloors.add(cbFloors);

		panelRooms = new JPanel();
		panelCounts.add(panelRooms);
		panelRooms.setLayout(new GridLayout(2, 1, 0, 0));

		lblRooms = new JLabel("Rooms");
		panelRooms.add(lblRooms);

		cbRooms = new JComboBox<>();
		panelRooms.add(cbRooms);

		panelBathrooms = new JPanel();
		panelCounts.add(panelBathrooms);
		panelBathrooms.setLayout(new GridLayout(2, 1, 0, 0));

		lblBathrooms = new JLabel("Bathrooms");
		panelBathrooms.add(lblBathrooms);

		cbBathrooms = new JComboBox<>();
		panelBathrooms.add(cbBathrooms);

		lblFeatures = new JLabel("Features:");
		panelData.add(lblFeatures);

		panelFeatures = new JPanel();
		panelData.add(panelFeatures);

		cbxLift = new JCheckBox("");
		cbxLift.setIcon(GlobalResources.getIconLift());
		cbxLift.setToolTipText("Lift");
		panelFeatures.add(cbxLift);

		cbxBasement = new JCheckBox("");
		cbxBasement.setIcon(GlobalResources.getIconBasement());
		cbxBasement.setToolTipText("Basement");
		panelFeatures.add(cbxBasement);

		cbxGarden = new JCheckBox("");
		cbxGarden.setIcon(GlobalResources.getIconGarden());
		cbxGarden.setToolTipText("Garden");
		panelFeatures.add(cbxGarden);

		cbxPool = new JCheckBox("");
		cbxPool.setIcon(GlobalResources.getIconPool());
		cbxPool.setToolTipText("Pool");
		panelFeatures.add(cbxPool);

		cbxTerrace = new JCheckBox("");
		cbxTerrace.setIcon(GlobalResources.getIconTerrace());
		cbxTerrace.setToolTipText("Terrace");
		panelFeatures.add(cbxTerrace);

		cbxAC = new JCheckBox("");
		cbxAC.setIcon(GlobalResources.getIconAC());
		cbxAC.setToolTipText("AC");
		panelFeatures.add(cbxAC);

		cbxGarage = new JCheckBox("");
		cbxGarage.setIcon(GlobalResources.getIconGarage());
		cbxGarage.setToolTipText("Garage");
		panelFeatures.add(cbxGarage);

		lblPriceRange = new JLabel("Price Range:");
		panelData.add(lblPriceRange);

		panelPriceRange = new JPanel();
		panelData.add(panelPriceRange);

		txtPriceInit = new JTextField();
		txtPriceInit.setColumns(7);
		panelPriceRange.add(txtPriceInit);

		lblToPrice = new JLabel("To");
		panelPriceRange.add(lblToPrice);

		txtPriceFinal = new JTextField();
		txtPriceFinal.setColumns(7);
		panelPriceRange.add(txtPriceFinal);

		lblEur = new JLabel("EUR");
		panelPriceRange.add(lblEur);
		//Add controller
		new ControllerPropertyFilter(this, userView);
		setVisible(true);
		
	}
	
	public void addItemListeners(ItemListener listener) {
		cbxRent.addItemListener(listener);
		cbxSale.addItemListener(listener);
		cbxGarage.addItemListener(listener);
		cbxAC.addItemListener(listener);
		cbxTerrace.addItemListener(listener);
		cbxPool.addItemListener(listener);
		cbxBasement.addItemListener(listener);
		cbxGarden.addItemListener(listener);
		cbxLift.addItemListener(listener);
	}

	public void addActListeners(ActionListener listener) {
		btnReturn.addActionListener(listener);
		btnApply.addActionListener(listener);
		btnReset.addActionListener(listener);
	}

	public JPanel getPanelButtons() {
		return panelButtons;
	}

	public void setPanelButtons(JPanel panelButtons) {
		this.panelButtons = panelButtons;
	}

	public JPanel getPanelTitle() {
		return panelTitle;
	}

	public void setPanelTitle(JPanel panelTitle) {
		this.panelTitle = panelTitle;
	}

	public JPanel getPanelData() {
		return panelData;
	}

	public void setPanelData(JPanel panelData) {
		this.panelData = panelData;
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

	public JLabel getLblType() {
		return lblType;
	}

	public void setLblType(JLabel lblType) {
		this.lblType = lblType;
	}

	public JPanel getPanelRentSale() {
		return panelRentSale;
	}

	public void setPanelRentSale(JPanel panelRentSale) {
		this.panelRentSale = panelRentSale;
	}

	public JCheckBox getCbxRent() {
		return cbxRent;
	}

	public void setCbxRent(JCheckBox cbxRent) {
		this.cbxRent = cbxRent;
	}

	public JCheckBox getCbxSale() {
		return cbxSale;
	}

	public void setCbxSale(JCheckBox cbxSale) {
		this.cbxSale = cbxSale;
	}

	public GUIUserView getUserView() {
		return userView;
	}

	public void setUserView(GUIUserView userView) {
		this.userView = userView;
	}

	public JLabel getLblCities() {
		return lblCities;
	}

	public void setLblCities(JLabel lblCities) {
		this.lblCities = lblCities;
	}

	public JLabel getLblAge() {
		return lblAge;
	}

	public void setLblAge(JLabel lblAge) {
		this.lblAge = lblAge;
	}

	public JPanel getPanelAgeRange() {
		return panelAgeRange;
	}

	public void setPanelAgeRange(JPanel panelAgeRange) {
		this.panelAgeRange = panelAgeRange;
	}

	public JLabel getLblTo() {
		return lblTo;
	}

	public void setLblTo(JLabel lblTo) {
		this.lblTo = lblTo;
	}

	public JLabel getLblYearsOld() {
		return lblYearsOld;
	}

	public void setLblYearsOld(JLabel lblYearsOld) {
		this.lblYearsOld = lblYearsOld;
	}

	public JLabel getLblPropertySize() {
		return lblPropertySize;
	}

	public void setLblPropertySize(JLabel lblPropertySize) {
		this.lblPropertySize = lblPropertySize;
	}

	public JPanel getPanelPropertyRange() {
		return panelPropertyRange;
	}

	public void setPanelPropertyRange(JPanel panelPropertyRange) {
		this.panelPropertyRange = panelPropertyRange;
	}

	public JLabel getLblToProperty() {
		return lblToProperty;
	}

	public void setLblToProperty(JLabel lblToProperty) {
		this.lblToProperty = lblToProperty;
	}

	public JLabel getLblsqMetersProperty() {
		return lblsqMetersProperty;
	}

	public void setLblsqMetersProperty(JLabel lblsqMetersProperty) {
		this.lblsqMetersProperty = lblsqMetersProperty;
	}

	public JList<String> getListCities() {
		return listCities;
	}

	public void setListCities(JList<String> listCities) {
		this.listCities = listCities;
	}

	public JTextField getTxtPropertyInit() {
		return txtPropertyInit;
	}

	public void setTxtPropertyInit(JTextField txtPropertyInit) {
		this.txtPropertyInit = txtPropertyInit;
	}

	public JTextField getTxtPropertyFinal() {
		return txtPropertyFinal;
	}

	public void setTxtPropertyFinal(JTextField txtPropertyFinal) {
		this.txtPropertyFinal = txtPropertyFinal;
	}

	public JTextField getTxtAgeInit() {
		return txtAgeInit;
	}

	public void setTxtAgeInit(JTextField txtAgeInit) {
		this.txtAgeInit = txtAgeInit;
	}

	public JTextField getTxtAgeFinal() {
		return txtAgeFinal;
	}

	public void setTxtAgeFinal(JTextField txtAgeFinal) {
		this.txtAgeFinal = txtAgeFinal;
	}

	public JLabel getLblTerrainSize() {
		return lblTerrainSize;
	}

	public void setLblTerrainSize(JLabel lblTerrainSize) {
		this.lblTerrainSize = lblTerrainSize;
	}

	public JPanel getPanelTerrainRange() {
		return panelTerrainRange;
	}

	public void setPanelTerrainRange(JPanel panelTerrainRange) {
		this.panelTerrainRange = panelTerrainRange;
	}

	public JTextField getTxtTerrainInit() {
		return txtTerrainInit;
	}

	public void setTxtTerrainInit(JTextField txtTerrainInit) {
		this.txtTerrainInit = txtTerrainInit;
	}

	public JLabel getLblToTerrain() {
		return lblToTerrain;
	}

	public void setLblToTerrain(JLabel lblToTerrain) {
		this.lblToTerrain = lblToTerrain;
	}

	public JTextField getTxtTerrainFinal() {
		return txtTerrainFinal;
	}

	public void setTxtTerrainFinal(JTextField txtTerrainFinal) {
		this.txtTerrainFinal = txtTerrainFinal;
	}

	public JLabel getLblsqMetersTerrain() {
		return lblsqMetersTerrain;
	}

	public void setLblsqMetersTerrain(JLabel lblsqMetersTerrain) {
		this.lblsqMetersTerrain = lblsqMetersTerrain;
	}

	public JLabel getLblGarageSize() {
		return lblGarageSize;
	}

	public void setLblGarageSize(JLabel lblGarageSize) {
		this.lblGarageSize = lblGarageSize;
	}

	public JPanel getPanelGarageRange() {
		return panelGarageRange;
	}

	public void setPanelGarageRange(JPanel panelGarageRange) {
		this.panelGarageRange = panelGarageRange;
	}

	public JTextField getTxtGarageInit() {
		return txtGarageInit;
	}

	public void setTxtGarageInit(JTextField txtGarageInit) {
		this.txtGarageInit = txtGarageInit;
	}

	public JLabel getLblToGarage() {
		return lblToGarage;
	}

	public void setLblToGarage(JLabel lblToGarage) {
		this.lblToGarage = lblToGarage;
	}

	public JTextField getTxtGarageFinal() {
		return txtGarageFinal;
	}

	public void setTxtGarageFinal(JTextField txtGarageFinal) {
		this.txtGarageFinal = txtGarageFinal;
	}

	public JLabel getLblsqMetersGarage() {
		return lblsqMetersGarage;
	}

	public void setLblsqMetersGarage(JLabel lblsqMetersGarage) {
		this.lblsqMetersGarage = lblsqMetersGarage;
	}

	public JLabel getLblCounts() {
		return lblCounts;
	}

	public void setLblCounts(JLabel lblCounts) {
		this.lblCounts = lblCounts;
	}

	public JPanel getPanelCounts() {
		return panelCounts;
	}

	public void setPanelCounts(JPanel panelCounts) {
		this.panelCounts = panelCounts;
	}

	public JLabel getLblFeatures() {
		return lblFeatures;
	}

	public void setLblFeatures(JLabel lblFeatures) {
		this.lblFeatures = lblFeatures;
	}

	public JLabel getLblPriceRange() {
		return lblPriceRange;
	}

	public void setLblPriceRange(JLabel lblPriceRange) {
		this.lblPriceRange = lblPriceRange;
	}

	public JPanel getPanelFeatures() {
		return panelFeatures;
	}

	public void setPanelFeatures(JPanel panelFeatures) {
		this.panelFeatures = panelFeatures;
	}

	public JPanel getPanelPriceRange() {
		return panelPriceRange;
	}

	public void setPanelPriceRange(JPanel panelPriceRange) {
		this.panelPriceRange = panelPriceRange;
	}

	public JTextField getTxtPriceInit() {
		return txtPriceInit;
	}

	public void setTxtPriceInit(JTextField txtPriceInit) {
		this.txtPriceInit = txtPriceInit;
	}

	public JLabel getLblToPrice() {
		return lblToPrice;
	}

	public void setLblToPrice(JLabel lblToPrice) {
		this.lblToPrice = lblToPrice;
	}

	public JTextField getTxtPriceFinal() {
		return txtPriceFinal;
	}

	public void setTxtPriceFinal(JTextField txtPriceFinal) {
		this.txtPriceFinal = txtPriceFinal;
	}

	public JLabel getLblEur() {
		return lblEur;
	}

	public void setLblEur(JLabel lblEur) {
		this.lblEur = lblEur;
	}

	public JButton getBtnReturn() {
		return btnReturn;
	}

	public void setBtnReturn(JButton btnReturn) {
		this.btnReturn = btnReturn;
	}

	public JButton getBtnApply() {
		return btnApply;
	}

	public void setBtnApply(JButton btnApply) {
		this.btnApply = btnApply;
	}

	public JButton getBtnReset() {
		return btnReset;
	}

	public void setBtnReset(JButton btnReset) {
		this.btnReset = btnReset;
	}

	public JCheckBox getCbxLift() {
		return cbxLift;
	}

	public void setCbxLift(JCheckBox cbxLift) {
		this.cbxLift = cbxLift;
	}

	public JCheckBox getCbxBasement() {
		return cbxBasement;
	}

	public void setCbxBasement(JCheckBox cbxBasement) {
		this.cbxBasement = cbxBasement;
	}

	public JCheckBox getCbxGarden() {
		return cbxGarden;
	}

	public void setCbxGarden(JCheckBox cbxGarden) {
		this.cbxGarden = cbxGarden;
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

	public JCheckBox getCbxAC() {
		return cbxAC;
	}

	public void setCbxAC(JCheckBox cbxAC) {
		this.cbxAC = cbxAC;
	}

	public JCheckBox getCbxGarage() {
		return cbxGarage;
	}

	public void setCbxGarage(JCheckBox cbxGarage) {
		this.cbxGarage = cbxGarage;
	}

	public JPanel getPanelFloors() {
		return panelFloors;
	}

	public void setPanelFloors(JPanel panelFloors) {
		this.panelFloors = panelFloors;
	}

	public JPanel getPanelRooms() {
		return panelRooms;
	}

	public void setPanelRooms(JPanel panelRooms) {
		this.panelRooms = panelRooms;
	}

	public JPanel getPanelBathrooms() {
		return panelBathrooms;
	}

	public void setPanelBathrooms(JPanel panelBathrooms) {
		this.panelBathrooms = panelBathrooms;
	}

	public JLabel getLblFloors() {
		return lblFloors;
	}

	public void setLblFloors(JLabel lblFloors) {
		this.lblFloors = lblFloors;
	}

	public JLabel getLblRooms() {
		return lblRooms;
	}

	public void setLblRooms(JLabel lblRooms) {
		this.lblRooms = lblRooms;
	}

	public JLabel getLblBathrooms() {
		return lblBathrooms;
	}

	public void setLblBathrooms(JLabel lblBathrooms) {
		this.lblBathrooms = lblBathrooms;
	}

	public JScrollPane getsPCities() {
		return sPCities;
	}

	public void setsPCities(JScrollPane sPCities) {
		this.sPCities = sPCities;
	}

	public JComboBox<Integer> getCbFloors() {
		return cbFloors;
	}

	public void setCbFloors(JComboBox<Integer> cbFloors) {
		this.cbFloors = cbFloors;
	}

	public JComboBox<Integer> getCbRooms() {
		return cbRooms;
	}

	public void setCbRooms(JComboBox<Integer> cbRooms) {
		this.cbRooms = cbRooms;
	}

	public JComboBox<Integer> getCbBathrooms() {
		return cbBathrooms;
	}

	public void setCbBathrooms(JComboBox<Integer> cbBathrooms) {
		this.cbBathrooms = cbBathrooms;
	}

}
