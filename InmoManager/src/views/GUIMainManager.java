package views;

import javax.swing.JFrame;

public class GUIMainManager extends JFrame {
	GUILogin gLogin;
	public GUIMainManager() {
		//this.gLogin
		setTitle("Manager View");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 450, 300);
	}

}
