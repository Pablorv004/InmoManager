package views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;

import controllers.ControllerUserInterested;
import models.Property;
import util.GlobalResources;

public class GUIUserInterested extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelData;
	private JPanel panelButtons;
	private JPanel panelTitle;
	private JLabel lblTitle;
	private JLabel lblLogo;
	private JSeparator separator;
	private JScrollPane sPProperties;
	private JList<Property> listProperties;
	private JButton btnReturn;
	private JButton btnConfirm;
	private JLabel lblLabelDown;
	private JLabel lblLabelUp;
	private JButton btnDelete;
	private GUIUserView userView;
	private ControllerUserInterested userInterested;

	/**
	 * Create the frame.
	 */
	public GUIUserInterested(GUIUserView userView) {
		setTitle("Interested List");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 565, 299);
		setLocationRelativeTo(userView);
		this.userView = userView;
		
		GlobalResources.setFrameIcon(this);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panelData = new JPanel();
		contentPane.add(panelData, BorderLayout.CENTER);
		panelData.setLayout(new BorderLayout(0, 0));
		listProperties = new JList<>();
		listProperties.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sPProperties = new JScrollPane(listProperties);
		panelData.add(sPProperties);

		lblLabelDown = new JLabel("Please, review carefully and choose the one you'd like to make your purchase on.");
		panelData.add(lblLabelDown, BorderLayout.SOUTH);

		lblLabelUp = new JLabel("You're currently interested in the following properties:");
		panelData.add(lblLabelUp, BorderLayout.NORTH);

		panelButtons = new JPanel();
		contentPane.add(panelButtons, BorderLayout.SOUTH);

		btnConfirm = new JButton("Proceed to Purchase");
		panelButtons.add(btnConfirm);
		
		btnDelete = new JButton("Not Interested");
		panelButtons.add(btnDelete);

		btnReturn = new JButton("Return");
		panelButtons.add(btnReturn);

		panelTitle = new JPanel();
		contentPane.add(panelTitle, BorderLayout.NORTH);
		panelTitle.setLayout(new BorderLayout(0, 0));

		lblTitle = new JLabel("Choose Property");
		lblTitle.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		panelTitle.add(lblTitle);

		lblLogo = new JLabel("");
		lblLogo.setIcon(GlobalResources.getIconSmall());
		panelTitle.add(lblLogo, BorderLayout.WEST);

		separator = new JSeparator();
		panelTitle.add(separator, BorderLayout.SOUTH);
		
		//Adding controller
		userInterested = new ControllerUserInterested(this);
		setVisible(true);
	}
	public void addActListeners(ActionListener listener) {
		btnConfirm.addActionListener(listener);
		btnReturn.addActionListener(listener);
		btnDelete.addActionListener(listener);
	}
	
	public void addStateListeners(ListSelectionListener listener) {
		listProperties.addListSelectionListener(listener);
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public JPanel getPanelData() {
		return panelData;
	}

	public void setPanelData(JPanel panelData) {
		this.panelData = panelData;
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

	public JScrollPane getsPProperties() {
		return sPProperties;
	}

	public void setsPProperties(JScrollPane sPProperties) {
		this.sPProperties = sPProperties;
	}

	public JList<Property> getListProperties() {
		return listProperties;
	}

	public void setListProperties(JList<Property> listProperties) {
		this.listProperties = listProperties;
	}

	public JButton getBtnReturn() {
		return btnReturn;
	}

	public void setBtnReturn(JButton btnReturn) {
		this.btnReturn = btnReturn;
	}

	public JButton getBtnConfirm() {
		return btnConfirm;
	}

	public void setBtnConfirm(JButton btnConfirm) {
		this.btnConfirm = btnConfirm;
	}

	public JLabel getLblLabelDown() {
		return lblLabelDown;
	}

	public void setLblLabelDown(JLabel lblLabelDown) {
		this.lblLabelDown = lblLabelDown;
	}

	public JLabel getLblLabelUp() {
		return lblLabelUp;
	}

	public void setLblLabelUp(JLabel lblLabelUp) {
		this.lblLabelUp = lblLabelUp;
	}

	public GUIUserView getUserView() {
		return userView;
	}

	public void setUserView(GUIUserView userView) {
		this.userView = userView;
	}

	public ControllerUserInterested getUserInterested() {
		return userInterested;
	}

	public void setUserInterested(ControllerUserInterested userInterested) {
		this.userInterested = userInterested;
	}

}
