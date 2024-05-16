package views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controllers.ControllerUserView;
import util.GlobalResources;

public class GUIUserView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelControls;
	private JPanel panelTitle;
	private JPanel panelPictureMap;
	private JPanel panelData;
	private JLabel lblType;
	private JLabel lblCity;
	private JLabel lblAddress;
	private JTextField txtAddress;
	private JLabel lblAge;
	private JTextField txtAge;
	private JPanel panelSizes;
	private JPanel panelRooms;
	private JLabel lblSizes;
	private JLabel lblCounts;
	private JLabel lblFeatures;
	private JPanel panelFeatures;
	private JLabel lblDescription;
	private JTextField txtStatus;
	private JLabel lblTitle;
	private JLabel lblLogo;
	private JSeparator separator;
	private JButton btnPrevious;
	private JButton btnNext;
	private JButton btnFilter;
	private JPanel panelPictures;
	private JButton btnInterested;
	private JButton btnReturn;
	private JLabel lblPropertyPhoto;
	private JLabel lblPropertyMap;
	private Font categoriesFont = new Font("Microsoft YaHei UI", Font.PLAIN, 21);
	private JPanel panelInterestedCart;
	private JLabel lblInterestedCount;
	private JLabel lblLift;
	private JLabel lblAC;
	private Font fontPropertyNumbers = new Font("Microsoft JhengHei UI", Font.BOLD, 20);
	private JLabel lblGarden;
	private JLabel lblGarage;
	private JLabel lblTerrace;
	private JLabel lblPool;
	private JLabel lblRooms;
	private JLabel lblBathrooms;
	private JLabel lblFloors;
	private JLabel lblPropertySize;
	private JLabel lblTerrainSize;
	private JLabel lblGarageSize;
	private JButton btnInterestedList;
	private JLabel lblIndex;
	private GUIMainUser mainUser;
	private JLabel lblPrice;
	private JPanel panelPrice;
	private JLabel lblPriceTag;
	private JLabel lblBasement;
	/**
	 * Create the frame.
	 * @param mainUser 
	 */
	public GUIUserView(GUIMainUser mainUser) {
		this.mainUser = mainUser;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 545);
		setTitle("View Properties");
		setLocationRelativeTo(this.mainUser);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelControls = new JPanel();
		contentPane.add(panelControls, BorderLayout.SOUTH);
		
		btnReturn = new JButton("Return");
		btnReturn.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelControls.add(btnReturn);
		btnReturn.setIcon(GlobalResources.getIconReturn());
		
		btnPrevious = new JButton("Previous");
		panelControls.add(btnPrevious);
		
		btnNext = new JButton("Next");
		panelControls.add(btnNext);
		
		panelTitle = new JPanel();
		contentPane.add(panelTitle, BorderLayout.NORTH);
		panelTitle.setLayout(new BorderLayout(0, 0));
		
		lblTitle = new JLabel("Properties");
		lblTitle.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		panelTitle.add(lblTitle);
		
		lblLogo = new JLabel("");
		lblLogo.setIcon(GlobalResources.getIconSmall());
		panelTitle.add(lblLogo, BorderLayout.WEST);
		
		separator = new JSeparator();
		panelTitle.add(separator, BorderLayout.SOUTH);
		
		panelInterestedCart = new JPanel();
		panelTitle.add(panelInterestedCart, BorderLayout.EAST);
		
		lblInterestedCount = new JLabel("0");
		lblInterestedCount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelInterestedCart.add(lblInterestedCount);
		
		btnInterestedList = new JButton("");
		btnInterestedList.setBorderPainted(false);
		btnInterestedList.setContentAreaFilled(false);
		btnInterestedList.setIcon(new ImageIcon("files/images/interested32.png"));
		panelInterestedCart.add(btnInterestedList);
		
		panelPictureMap = new JPanel();
		contentPane.add(panelPictureMap, BorderLayout.EAST);
		panelPictureMap.setLayout(new BorderLayout(0, 0));
		
		btnFilter = new JButton("Filter...");
		panelPictureMap.add(btnFilter, BorderLayout.NORTH);
		
		panelPictures = new JPanel();
		panelPictureMap.add(panelPictures, BorderLayout.CENTER);
		panelPictures.setLayout(new BoxLayout(panelPictures, BoxLayout.Y_AXIS));
		
		lblPropertyPhoto = new JLabel("");
		panelPictures.add(lblPropertyPhoto);
		
		lblIndex = new JLabel("0/0");
		panelPictures.add(lblIndex);
		lblIndex.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lblPropertyMap = new JLabel("");
		panelPictures.add(lblPropertyMap);
		
		btnInterested = new JButton("Interested!");
		btnInterested.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		panelPictureMap.add(btnInterested, BorderLayout.SOUTH);
		
		panelData = new JPanel();
		contentPane.add(panelData, BorderLayout.CENTER);
		panelData.setLayout(new GridLayout(8, 2, 0, 0));
		
		lblType = new JLabel("TypeGoesHere");
		lblType.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 30));
		panelData.add(lblType);
		
		lblCity = new JLabel("CityGoesHere");
		lblCity.setFont(new Font("Rockwell", Font.PLAIN, 21));
		panelData.add(lblCity);
		
		lblDescription = new JLabel("Description");
		lblDescription.setFont(categoriesFont);
		panelData.add(lblDescription);
		
		txtStatus = new JTextField();
		txtStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtStatus.setEditable(false);
		panelData.add(txtStatus);
		txtStatus.setColumns(10);
		
		lblAddress = new JLabel("Address");
		lblAddress.setFont(categoriesFont);
		panelData.add(lblAddress);
		
		txtAddress = new JTextField();
		txtAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtAddress.setEditable(false);
		panelData.add(txtAddress);
		txtAddress.setColumns(10);
		
		lblAge = new JLabel("Age");
		lblAge.setFont(categoriesFont);
		panelData.add(lblAge);
		
		txtAge = new JTextField();
		txtAge.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		txtAge.setEditable(false);
		panelData.add(txtAge);
		txtAge.setColumns(10);
		
		lblSizes = new JLabel("Size");
		lblSizes.setFont(categoriesFont);
		panelData.add(lblSizes);
		
		panelSizes = new JPanel();
		panelData.add(panelSizes);
		
		lblPropertySize = new JLabel("100");
		lblPropertySize.setIcon(new ImageIcon("files/images/propertySize40.png"));
		lblPropertySize.setToolTipText("Has " + lblPropertySize.getText() + " square meters of terrain size.");
		lblPropertySize.setFont(fontPropertyNumbers);
		panelSizes.add(lblPropertySize);
		
		lblTerrainSize = new JLabel("150");
		lblTerrainSize.setIcon(new ImageIcon("files/images/terrainSize40.png"));
		lblTerrainSize.setToolTipText("Has " + lblTerrainSize.getText() + " square meters of terrain size.");
		lblTerrainSize.setFont(fontPropertyNumbers);
		
		lblGarageSize = new JLabel("20");
		lblGarageSize.setIcon(new ImageIcon("files/images/garageSize40.png"));
		lblGarageSize.setToolTipText("Has " + lblGarageSize.getText() + " square meters of garage size.");
		lblGarageSize.setFont(fontPropertyNumbers);
		
		lblCounts = new JLabel("Rooms");
		lblCounts.setFont(categoriesFont);
		panelData.add(lblCounts);
		
		panelRooms = new JPanel();
		panelData.add(panelRooms);
		
		lblFloors = new JLabel("1");
		lblFloors.setIcon(new ImageIcon("files/images/floors40.png"));
		lblFloors.setToolTipText("Has " + lblFloors.getText() + " floors.");
		lblFloors.setFont(fontPropertyNumbers);
		panelRooms.add(lblFloors);
		
		lblRooms = new JLabel("1");
		lblRooms.setIcon(new ImageIcon("files/images/rooms40.png"));
		lblRooms.setToolTipText("Has " + lblRooms.getText() + " rooms.");
		lblRooms.setFont(fontPropertyNumbers);
		panelRooms.add(lblRooms);
		
		lblBathrooms = new JLabel("1");
		lblBathrooms.setIcon(new ImageIcon("files/images/bathrooms40.png"));
		lblBathrooms.setToolTipText("Has " + lblBathrooms.getText() + " bathrooms.");
		lblBathrooms.setFont(fontPropertyNumbers);
		panelRooms.add(lblBathrooms);
		
		lblFeatures = new JLabel("Features");
		lblFeatures.setFont(categoriesFont);
		panelData.add(lblFeatures);
		
		panelFeatures = new JPanel();
		panelData.add(panelFeatures);
		
		lblLift = new JLabel("");
		lblLift.setFont(fontPropertyNumbers);
		lblLift.setToolTipText("Has a lift.");
		lblLift.setIcon(new ImageIcon("files/images/lift40.png"));
		panelFeatures.add(lblLift);
		
		lblAC = new JLabel("");
		lblAC.setFont(fontPropertyNumbers);
		lblAC.setToolTipText("Has an Air Conditioning unit.");
		lblAC.setIcon(new ImageIcon("files/Images/ac40.png"));
		panelFeatures.add(lblAC);
		
		lblGarden = new JLabel("");
		lblGarden.setFont(fontPropertyNumbers);
		lblGarden.setToolTipText("Has a garden.");
		lblGarden.setIcon(new ImageIcon("files/Images/garden40.png"));
		panelFeatures.add(lblGarden);
		
		lblGarage = new JLabel("");
		lblGarage.setFont(fontPropertyNumbers);
		lblGarage.setToolTipText("Has a garage.");
		lblGarage.setIcon(new ImageIcon("files/Images/garage40.png"));
		panelFeatures.add(lblGarage);
		
		lblTerrace = new JLabel("");
		lblTerrace.setFont(fontPropertyNumbers);
		lblTerrace.setToolTipText("Has a terrace.");
		lblTerrace.setIcon(new ImageIcon("files/Images/terrace40.png"));
		panelFeatures.add(lblTerrace);
		
		lblPool = new JLabel("");
		lblPool.setFont(fontPropertyNumbers);
		lblPool.setToolTipText("Has a pool.");
		lblPool.setIcon(new ImageIcon("files/Images/pool40.png"));
		panelFeatures.add(lblPool);
		
		lblBasement = new JLabel("");
		lblBasement.setFont(fontPropertyNumbers);
		lblBasement.setToolTipText("Has a basement.");
		lblBasement.setIcon(new ImageIcon("files/Images/basement40.png"));
		panelFeatures.add(lblBasement);
		
		lblPrice = new JLabel("Price");
		panelData.add(lblPrice);
		
		panelPrice = new JPanel();
		panelData.add(panelPrice);
		
		lblPriceTag = new JLabel("20 EUR");
		panelPrice.add(lblPriceTag);
		
		setVisible(true);
		//Adding controllers...
		new ControllerUserView(this);
	}
	public void addActListeners(ActionListener listener) {
		btnNext.addActionListener(listener);
		btnPrevious.addActionListener(listener);
		btnReturn.addActionListener(listener);
		btnFilter.addActionListener(listener);
		btnInterested.addActionListener(listener);
		btnInterestedList.addActionListener(listener);
	}
	
	
	public JLabel getLblBasement() {
		return lblBasement;
	}
	public void setLblBasement(JLabel lblBasement) {
		this.lblBasement = lblBasement;
	}
	public JPanel getPanelControls() {
		return panelControls;
	}
	public void setPanelControls(JPanel panelControls) {
		this.panelControls = panelControls;
	}
	public JPanel getPanelTitle() {
		return panelTitle;
	}
	public void setPanelTitle(JPanel panelTitle) {
		this.panelTitle = panelTitle;
	}
	public JPanel getPanelPictureMap() {
		return panelPictureMap;
	}
	public void setPanelPictureMap(JPanel panelPictureMap) {
		this.panelPictureMap = panelPictureMap;
	}
	public JPanel getPanelData() {
		return panelData;
	}
	public void setPanelData(JPanel panelData) {
		this.panelData = panelData;
	}
	public JLabel getLblType() {
		return lblType;
	}
	public void setLblType(JLabel lblType) {
		this.lblType = lblType;
	}
	public JLabel getLblCity() {
		return lblCity;
	}
	public void setLblCity(JLabel lblCity) {
		this.lblCity = lblCity;
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
	public JLabel getLblAge() {
		return lblAge;
	}
	public void setLblAge(JLabel lblAge) {
		this.lblAge = lblAge;
	}
	public JTextField getTxtAge() {
		return txtAge;
	}
	public void setTxtAge(JTextField textField) {
		this.txtAge = textField;
	}
	public JPanel getPanelSizes() {
		return panelSizes;
	}
	public void setPanelSizes(JPanel panelSizes) {
		this.panelSizes = panelSizes;
	}
	public JPanel getPanelRooms() {
		return panelRooms;
	}
	public void setPanelRooms(JPanel panelRooms) {
		this.panelRooms = panelRooms;
	}
	public JLabel getLblSizes() {
		return lblSizes;
	}
	
	public JLabel getLblPrice() {
		return lblPrice;
	}
	public void setLblPrice(JLabel lblPrice) {
		this.lblPrice = lblPrice;
	}
	public JPanel getPanelPrice() {
		return panelPrice;
	}
	public void setPanelPrice(JPanel panelPrice) {
		this.panelPrice = panelPrice;
	}
	public JLabel getLblPriceTag() {
		return lblPriceTag;
	}
	public void setLblPriceTag(JLabel lblPriceTag) {
		this.lblPriceTag = lblPriceTag;
	}
	public void setLblSizes(JLabel lblSizes) {
		this.lblSizes = lblSizes;
	}
	public JLabel getLblCounts() {
		return lblCounts;
	}
	public void setLblCounts(JLabel lblCounts) {
		this.lblCounts = lblCounts;
	}
	public JLabel getLblFeatures() {
		return lblFeatures;
	}
	public void setLblFeatures(JLabel lblFeatures) {
		this.lblFeatures = lblFeatures;
	}
	public JPanel getPanelFeatures() {
		return panelFeatures;
	}
	public void setPanelFeatures(JPanel panelFeatures) {
		this.panelFeatures = panelFeatures;
	}
	public JLabel getLblDescription() {
		return lblDescription;
	}
	public void setLblDescription(JLabel lblDescription) {
		this.lblDescription = lblDescription;
	}
	public JTextField getTxtStatus() {
		return txtStatus;
	}
	public void setTxtStatus(JTextField txtStatus) {
		this.txtStatus = txtStatus;
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
	public JButton getBtnPrevious() {
		return btnPrevious;
	}
	public void setBtnPrevious(JButton btnPrevious) {
		this.btnPrevious = btnPrevious;
	}
	public JButton getBtnNext() {
		return btnNext;
	}
	public void setBtnNext(JButton btnNext) {
		this.btnNext = btnNext;
	}
	public JButton getBtnFilter() {
		return btnFilter;
	}
	public void setBtnFilter(JButton btnFilter) {
		this.btnFilter = btnFilter;
	}
	public JPanel getPanelPictures() {
		return panelPictures;
	}
	public void setPanelPictures(JPanel panelPictures) {
		this.panelPictures = panelPictures;
	}
	public JButton getBtnInterested() {
		return btnInterested;
	}
	public void setBtnInterested(JButton btnInterested) {
		this.btnInterested = btnInterested;
	}
	public JButton getBtnReturn() {
		return btnReturn;
	}
	public void setBtnReturn(JButton btnReturn) {
		this.btnReturn = btnReturn;
	}
	public JLabel getLblPropertyPhoto() {
		return lblPropertyPhoto;
	}
	public void setLblPropertyPhoto(JLabel lblPropertyPhoto) {
		this.lblPropertyPhoto = lblPropertyPhoto;
	}
	public JLabel getLblPropertyMap() {
		return lblPropertyMap;
	}
	public void setLblPropertyMap(JLabel lblPropertyMap) {
		this.lblPropertyMap = lblPropertyMap;
	}
	public Font getCategoriesFont() {
		return categoriesFont;
	}
	public void setCategoriesFont(Font categoriesFont) {
		this.categoriesFont = categoriesFont;
	}
	public JPanel getPanelInterestedCart() {
		return panelInterestedCart;
	}
	public void setPanelInterestedCart(JPanel panelInterestedCart) {
		this.panelInterestedCart = panelInterestedCart;
	}
	public JLabel getLblInterestedCount() {
		return lblInterestedCount;
	}
	public void setLblInterestedCount(JLabel lblInterestedCount) {
		this.lblInterestedCount = lblInterestedCount;
	}
	public JLabel getLblLift() {
		return lblLift;
	}
	public void setLblLift(JLabel lblLift) {
		this.lblLift = lblLift;
	}
	public JLabel getLblAC() {
		return lblAC;
	}
	public void setLblAC(JLabel lblAC) {
		this.lblAC = lblAC;
	}
	public Font getFontPropertyNumbers() {
		return fontPropertyNumbers;
	}
	public void setFontPropertyNumbers(Font fontPropertyNumbers) {
		this.fontPropertyNumbers = fontPropertyNumbers;
	}
	public JLabel getLblGarden() {
		return lblGarden;
	}
	public void setLblGarden(JLabel lblGarden) {
		this.lblGarden = lblGarden;
	}
	public JLabel getLblGarage() {
		return lblGarage;
	}
	public void setLblGarage(JLabel lblGarage) {
		this.lblGarage = lblGarage;
	}
	public JLabel getLblTerrace() {
		return lblTerrace;
	}
	public void setLblTerrace(JLabel lblTerrace) {
		this.lblTerrace = lblTerrace;
	}
	public JLabel getLblPool() {
		return lblPool;
	}
	public void setLblPool(JLabel lblPool) {
		this.lblPool = lblPool;
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
	public JLabel getLblFloors() {
		return lblFloors;
	}
	public void setLblFloors(JLabel lblFloors) {
		this.lblFloors = lblFloors;
	}
	public JLabel getLblPropertySize() {
		return lblPropertySize;
	}
	public void setLblPropertySize(JLabel lblPropertySize) {
		this.lblPropertySize = lblPropertySize;
	}
	public JLabel getLblTerrainSize() {
		return lblTerrainSize;
	}
	public void setLblTerrainSize(JLabel lblTerrainSize) {
		this.lblTerrainSize = lblTerrainSize;
	}
	public JLabel getLblGarageSize() {
		return lblGarageSize;
	}
	public void setLblGarageSize(JLabel lblGarageSize) {
		this.lblGarageSize = lblGarageSize;
	}
	public JButton getBtnInterestedList() {
		return btnInterestedList;
	}
	public void setBtnInterestedList(JButton btnInterestedList) {
		this.btnInterestedList = btnInterestedList;
	}
	public JLabel getLblIndex() {
		return lblIndex;
	}
	public void setLblIndex(JLabel lblIndex) {
		this.lblIndex = lblIndex;
	}
	public GUIMainUser getMainUser() {
		return mainUser;
	}
	public void setMainUser(GUIMainUser mainUser) {
		this.mainUser = mainUser;
	}
	
	

}
