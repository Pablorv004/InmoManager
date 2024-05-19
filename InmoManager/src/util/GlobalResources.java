package util;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GlobalResources {
	private static Icon iconSmall;
	private static Icon iconReturn;
	private static Icon iconUser;
	private static Icon iconGarage;
	private static Icon iconPool;
	private static Icon iconLift;
	private static Icon iconTerrace;
	private static Icon iconBasement;
	private static Icon iconAC;
	private static Icon iconGarden;
	private static Icon iconGarageSize;
	private static Icon iconTerrainSize;
	private static Icon iconPropertySize;
	private static Icon iconFloors;
	private static Icon iconRooms;
	private static Icon iconBathrooms;

	public GlobalResources() {
		iconSmall = new ImageIcon("files/images/InmoManagerSmol64.png");
		iconReturn = new ImageIcon("files/images/Return25.png");
		iconUser = new ImageIcon("files/images/user.png");
		iconPropertySize = new ImageIcon("files/images/propertySize40.png");
		iconGarage = new ImageIcon("files/images/garage40.png");
		iconTerrainSize = new ImageIcon("files/images/terrainSize40.png");
		iconGarageSize = new ImageIcon("files/images/garageSize40.png");
		iconFloors = new ImageIcon("files/images/floors40.png");
		iconRooms = new ImageIcon("files/images/rooms40.png");
		iconBathrooms = new ImageIcon("files/images/bathrooms40.png");
		iconLift = new ImageIcon("files/images/lift40.png");
		iconAC = new ImageIcon("files/Images/ac40.png");
		iconGarden = new ImageIcon("files/Images/garden40.png");
		iconTerrace = new ImageIcon("files/Images/terrace40.png");
		iconPool = new ImageIcon("files/Images/pool40.png");
		iconBasement = new ImageIcon("files/Images/basement40.png");
	}

	public static void setFrameIcon(JFrame frame) {
		ImageIcon icon = new ImageIcon("files/images/InmoManager32-32.png");
		frame.setIconImage(icon.getImage());
	}

	public static Icon getIconReturn() {
		return iconReturn;
	}

	public static Icon getIconUser() {
		return iconUser;
	}

	public static Icon getIconSmall() {
		return iconSmall;
	}

	public static void setIconSmall(Icon iconSmall) {
		GlobalResources.iconSmall = iconSmall;
	}

	public static Icon getIconGarage() {
		return iconGarage;
	}

	public static void setIconGarage(Icon iconGarage) {
		GlobalResources.iconGarage = iconGarage;
	}

	public static Icon getIconPool() {
		return iconPool;
	}

	public static void setIconPool(Icon iconPool) {
		GlobalResources.iconPool = iconPool;
	}

	public static Icon getIconLift() {
		return iconLift;
	}

	public static void setIconLift(Icon iconLift) {
		GlobalResources.iconLift = iconLift;
	}

	public static Icon getIconTerrace() {
		return iconTerrace;
	}

	public static void setIconTerrace(Icon iconTerrace) {
		GlobalResources.iconTerrace = iconTerrace;
	}

	public static Icon getIconBasement() {
		return iconBasement;
	}

	public static void setIconBasement(Icon iconBasement) {
		GlobalResources.iconBasement = iconBasement;
	}

	public static Icon getIconAC() {
		return iconAC;
	}

	public static void setIconAC(Icon iconAC) {
		GlobalResources.iconAC = iconAC;
	}

	public static Icon getIconGarden() {
		return iconGarden;
	}

	public static void setIconGarden(Icon iconGarden) {
		GlobalResources.iconGarden = iconGarden;
	}

	public static Icon getIconGarageSize() {
		return iconGarageSize;
	}

	public static void setIconGarageSize(Icon iconGarageSize) {
		GlobalResources.iconGarageSize = iconGarageSize;
	}

	public static Icon getIconTerrainSize() {
		return iconTerrainSize;
	}

	public static void setIconTerrainSize(Icon iconTerrainSize) {
		GlobalResources.iconTerrainSize = iconTerrainSize;
	}

	public static Icon getIconPropertySize() {
		return iconPropertySize;
	}

	public static void setIconPropertySize(Icon iconPropertySize) {
		GlobalResources.iconPropertySize = iconPropertySize;
	}

	public static Icon getIconFloors() {
		return iconFloors;
	}

	public static void setIconFloors(Icon iconFloors) {
		GlobalResources.iconFloors = iconFloors;
	}

	public static Icon getIconRooms() {
		return iconRooms;
	}

	public static void setIconRooms(Icon iconRooms) {
		GlobalResources.iconRooms = iconRooms;
	}

	public static Icon getIconBathrooms() {
		return iconBathrooms;
	}

	public static void setIconBathrooms(Icon iconBathrooms) {
		GlobalResources.iconBathrooms = iconBathrooms;
	}

}
