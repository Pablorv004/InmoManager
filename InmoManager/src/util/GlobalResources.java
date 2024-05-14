package util;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class GlobalResources {
	private Icon iconSmall;

	public GlobalResources() {
		iconSmall = new ImageIcon("files/images/InmoManagerSmol.png");
	}

	public Icon getIconSmall() {
		return iconSmall;
	}

	public void setIconSmall(Icon iconSmall) {
		this.iconSmall = iconSmall;
	}
	
	
}
