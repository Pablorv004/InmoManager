package util;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class GlobalResources {
	private static Icon iconSmall;
	private static Icon iconReturn;

	public static Icon getIconReturn() {
		return iconReturn;
	}

	private static Icon iconUser;

	public static Icon getIconUser() {
		return iconUser;
	}

	public GlobalResources() {
		iconSmall = new ImageIcon("files/images/InmoManagerSmol64.png");
		iconReturn = new ImageIcon("files/images/Return25.png");
		iconUser = new ImageIcon("files/images/user.png");
	}

	public static Icon getIconSmall() {
		return iconSmall;
	}

	public static void setIconSmall(Icon iconSmall) {
		GlobalResources.iconSmall = iconSmall;
	}
	
	
}
