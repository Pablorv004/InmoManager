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
import javax.swing.border.EmptyBorder;

import controllers.ControllerStopRent;
import models.Property;
import util.GlobalResources;

public class GUIStopRent extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private GUIMainUser mainUser;
	private JPanel panelTitle;
	private JPanel panelData;
	private JPanel panelButtons;
	private JLabel lblTitle;
	private JLabel lblLogo;
	private JSeparator separator;
	private JScrollPane scrollPane;
	private JList<Property> list;
	private JLabel lblInstructions;
	private JButton btnConfirm;
	private JButton btnReturn;

	/**
	 * Create the frame.
	 */
	public GUIStopRent(GUIMainUser mainUser) {
		this.mainUser = mainUser;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(this.mainUser);
		setTitle("Stop Rent");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panelTitle = new JPanel();
		contentPane.add(panelTitle, BorderLayout.NORTH);
		panelTitle.setLayout(new BorderLayout(0, 0));

		lblTitle = new JLabel("Stop Rent");
		lblTitle.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		panelTitle.add(lblTitle);

		lblLogo = new JLabel("");
		lblLogo.setIcon(GlobalResources.getIconSmall());
		panelTitle.add(lblLogo, BorderLayout.WEST);

		separator = new JSeparator();
		panelTitle.add(separator, BorderLayout.SOUTH);

		panelData = new JPanel();
		contentPane.add(panelData, BorderLayout.CENTER);
		panelData.setLayout(new BorderLayout(0, 0));

		list = new JList<>();
		scrollPane = new JScrollPane(list);
		panelData.add(scrollPane, BorderLayout.CENTER);

		lblInstructions = new JLabel("Please, select the desired home to stop renting:");
		scrollPane.setColumnHeaderView(lblInstructions);

		panelButtons = new JPanel();
		contentPane.add(panelButtons, BorderLayout.SOUTH);

		btnConfirm = new JButton("Confirm");
		panelButtons.add(btnConfirm);

		btnReturn = new JButton("Return");
		panelButtons.add(btnReturn);

		new ControllerStopRent(this);

		setVisible(true);
	}

	public void addActListeners(ActionListener listener) {
		btnConfirm.addActionListener(listener);
		btnReturn.addActionListener(listener);
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

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public JList<Property> getList() {
		return list;
	}

	public void setList(JList<Property> list) {
		this.list = list;
	}

	public JLabel getLblInstructions() {
		return lblInstructions;
	}

	public void setLblInstructions(JLabel lblInstructions) {
		this.lblInstructions = lblInstructions;
	}

	public JButton getBtnConfirm() {
		return btnConfirm;
	}

	public void setBtnConfirm(JButton btnConfirm) {
		this.btnConfirm = btnConfirm;
	}

	public JButton getBtnReturn() {
		return btnReturn;
	}

	public void setBtnReturn(JButton btnReturn) {
		this.btnReturn = btnReturn;
	}

}
