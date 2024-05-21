package views;

import java.awt.BorderLayout;
import java.awt.Container;
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
import javax.swing.event.ListSelectionListener;

import controllers.ControllerFreeChart;
import util.GlobalResources;

public class GUIFreeChart extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private GUIMainAdmin mainAdmin;
	private JPanel panelTitle;
	private Container lblTitle;
	private JLabel lblLogo;
	private JSeparator separator;
	private JPanel panelChart;
	private JPanel panelControls;
	private JButton btnReturn;
	private JButton btnPrevious;
	private JButton btnNext;
	private JPanel panelIdx;
	private JLabel lblIndex;
	private JScrollPane scrollPane;
	private JList<String> list;

	/**
	 * Create the frame.
	 */
	public GUIFreeChart(GUIMainAdmin mainAdmin) {
		setTitle("Stats");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 781, 383);
		this.setMainAdmin(mainAdmin);
		setLocationRelativeTo(mainAdmin);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		GlobalResources.setFrameIcon(this);

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelTitle = new JPanel();
		contentPane.add(panelTitle, BorderLayout.NORTH);
		panelTitle.setLayout(new BorderLayout(0, 0));

		lblTitle = new JLabel("Statistics");
		lblTitle.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		panelTitle.add(lblTitle);

		lblLogo = new JLabel("");
		lblLogo.setIcon(GlobalResources.getIconSmall());
		panelTitle.add(lblLogo, BorderLayout.WEST);

		separator = new JSeparator();
		panelTitle.add(separator, BorderLayout.SOUTH);
		
		panelChart = new JPanel();
		contentPane.add(panelChart, BorderLayout.CENTER);
		
		panelControls = new JPanel();
		contentPane.add(panelControls, BorderLayout.SOUTH);
		
		btnReturn = new JButton("Return");
		panelControls.add(btnReturn);
		
		btnPrevious = new JButton("Previous");
		panelControls.add(btnPrevious);
		
		btnNext = new JButton("Next");
		panelControls.add(btnNext);
		
		panelIdx = new JPanel();
		contentPane.add(panelIdx, BorderLayout.EAST);
		panelIdx.setLayout(new BorderLayout(0, 0));
		
		lblIndex = new JLabel("0/0");
		panelIdx.add(lblIndex, BorderLayout.NORTH);
		list = new JList<>();
		scrollPane = new JScrollPane(list);
		panelIdx.add(scrollPane, BorderLayout.CENTER);
		
		
		new ControllerFreeChart(this);
		
		setVisible(true);
		
	}
	
	public void addListListeners(ListSelectionListener listener) {
		list.addListSelectionListener(listener);
	}
	
	public void addActListeners(ActionListener listener) {
		btnReturn.addActionListener(listener);
		btnPrevious.addActionListener(listener);
		btnNext.addActionListener(listener);
	}
	

	public JList<String> getList() {
		return list;
	}

	public void setList(JList<String> list) {
		this.list = list;
	}

	public GUIMainAdmin getMainAdmin() {
		return mainAdmin;
	}

	public void setMainAdmin(GUIMainAdmin mainAdmin) {
		this.mainAdmin = mainAdmin;
	}

	public JPanel getPanelTitle() {
		return panelTitle;
	}

	public void setPanelTitle(JPanel panelTitle) {
		this.panelTitle = panelTitle;
	}

	public Container getLblTitle() {
		return lblTitle;
	}

	public void setLblTitle(Container lblTitle) {
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

	public JPanel getPanelChart() {
		return panelChart;
	}

	public void setPanelChart(JPanel panelChart) {
		this.panelChart = panelChart;
	}

	public JPanel getPanelControls() {
		return panelControls;
	}

	public void setPanelControls(JPanel panelControls) {
		this.panelControls = panelControls;
	}

	public JButton getBtnReturn() {
		return btnReturn;
	}

	public void setBtnReturn(JButton btnReturn) {
		this.btnReturn = btnReturn;
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

	public JPanel getPanelIdx() {
		return panelIdx;
	}

	public void setPanelIdx(JPanel panelIdx) {
		this.panelIdx = panelIdx;
	}

	public JLabel getLblIndex() {
		return lblIndex;
	}

	public void setLblIndex(JLabel lblIndex) {
		this.lblIndex = lblIndex;
	}
	
	

}
