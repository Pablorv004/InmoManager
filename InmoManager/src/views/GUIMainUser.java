package views;



import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import controllers.ControllerMainUser;
import util.GlobalResources;

public class GUIMainUser extends JFrame {

	private static final long serialVersionUID = 1L;
	private GUILogin gLogin;
	private JPanel contentPane;
	private JPanel panelButtons;
	private JLabel lblTitle;
	private JButton btnView;
	private JButton btnAdd;
	private JSeparator separator;
	private JButton btnReturn;
	private JButton btnUser;
	public GUIMainUser(GUILogin login) {
		this.gLogin = login;
		setTitle("InmoManager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 496, 303);
		setLocationRelativeTo(gLogin);
		
		GlobalResources.setFrameIcon(this);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelButtons = new JPanel();
		panelButtons.setBounds(38, 100, 389, 135);
		contentPane.add(panelButtons);
		
		btnView = new JButton("View");
		btnView.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		panelButtons.add(btnView);
		
		btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		panelButtons.add(btnAdd);
		
		separator = new JSeparator();
		separator.setBounds(0, 75, 482, 2);
		contentPane.add(separator);
		
		btnReturn = new JButton("");
		btnReturn.setBounds(8, 6, 30, 30);
		btnReturn.setIcon(GlobalResources.getIconReturn());
		btnReturn.setBorderPainted(false);
		btnReturn.setContentAreaFilled(false);
		contentPane.add(btnReturn);
		
		lblTitle = new JLabel("InmoManager");
		lblTitle.setBounds(150, 21, 180, 38);
		contentPane.add(lblTitle);
		lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTitle.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 28));
		
		btnUser = new JButton("");
		btnUser.setContentAreaFilled(false);
		btnUser.setBorderPainted(false);
		btnUser.setBounds(439, 6, 33, 30);
		btnUser.setIcon(GlobalResources.getIconUser());
		contentPane.add(btnUser);
		//Adding controller
		new ControllerMainUser(this);
		setVisible(true);
	}
	
	public void addActListeners(ActionListener listener) {
		btnView.addActionListener(listener);
		btnAdd.addActionListener(listener);
		btnReturn.addActionListener(listener);
		btnUser.addActionListener(listener);
	}

	public JButton getBtnView() {
		return btnView;
	}

	public void setBtnView(JButton btnView) {
		this.btnView = btnView;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public void setBtnAdd(JButton btnAdd) {
		this.btnAdd = btnAdd;
	}

	public JButton getBtnReturn() {
		return btnReturn;
	}

	public void setBtnReturn(JButton btnReturn) {
		this.btnReturn = btnReturn;
	}

	public JButton getBtnUser() {
		return btnUser;
	}

	public void setBtnUser(JButton btnUser) {
		this.btnUser = btnUser;
	}
}
