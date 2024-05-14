package util;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class GlobalResources {
	private static Icon iconSmall;

	public GlobalResources() {
		iconSmall = new ImageIcon("files/images/InmoManagerSmol64.png");
	}

	public static Icon getIconSmall() {
		return iconSmall;
	}

	public static void setIconSmall(Icon iconSmall) {
		GlobalResources.iconSmall = iconSmall;
	}
	
	
}
