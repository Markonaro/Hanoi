import java.awt.Color;
import java.util.ArrayList;

import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.graphics.GRect;

public class HanoiCanvas extends GCanvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static int WIDTH = Hanoi.APPLICATION_WIDTH;
	public static int HEIGHT = Hanoi.APPLICATION_HEIGHT;

	public static GRect aRod, bRod, cRod, aBase, bBase, cBase;

	public HanoiCanvas() {
		resetDisks();
	}

	public void resetDisks() {
		removeAll();

		aRod = new GRect(HanoiConstants.ROD_WIDTH, HanoiConstants.MAX_NDISKS
				* HanoiConstants.DISK_HEIGHT + HanoiConstants.ROD_TOP_SPACING);
		bRod = new GRect(HanoiConstants.ROD_WIDTH, HanoiConstants.MAX_NDISKS
				* HanoiConstants.DISK_HEIGHT + HanoiConstants.ROD_TOP_SPACING);
		cRod = new GRect(HanoiConstants.ROD_WIDTH, HanoiConstants.MAX_NDISKS
				* HanoiConstants.DISK_HEIGHT + HanoiConstants.ROD_TOP_SPACING);
		aBase = new GRect(HanoiConstants.BASE_WIDTH, HanoiConstants.BASE_HEIGHT);
		bBase = new GRect(HanoiConstants.BASE_WIDTH, HanoiConstants.BASE_HEIGHT);
		cBase = new GRect(HanoiConstants.BASE_WIDTH, HanoiConstants.BASE_HEIGHT);
		GLabel aLabel = new GLabel("A");
		GLabel bLabel = new GLabel("B");
		GLabel cLabel = new GLabel("C");

		aRod.setColor(HanoiConstants.ROD_AND_BASE_COLOR);
		bRod.setColor(HanoiConstants.ROD_AND_BASE_COLOR);
		cRod.setColor(HanoiConstants.ROD_AND_BASE_COLOR);
		aRod.setColor(HanoiConstants.ROD_AND_BASE_COLOR);
		bBase.setColor(HanoiConstants.ROD_AND_BASE_COLOR);
		cBase.setColor(HanoiConstants.ROD_AND_BASE_COLOR);
		aLabel.setFont(HanoiConstants.FONT_SIZE);
		bLabel.setFont(HanoiConstants.FONT_SIZE);
		cLabel.setFont(HanoiConstants.FONT_SIZE);

		aRod.setFillColor(HanoiConstants.ROD_AND_BASE_COLOR);
		bRod.setFillColor(HanoiConstants.ROD_AND_BASE_COLOR);
		cRod.setFillColor(HanoiConstants.ROD_AND_BASE_COLOR);
		aBase.setFillColor(HanoiConstants.ROD_AND_BASE_COLOR);
		bBase.setFillColor(HanoiConstants.ROD_AND_BASE_COLOR);
		cBase.setFillColor(HanoiConstants.ROD_AND_BASE_COLOR);

		aRod.setFilled(true);
		bRod.setFilled(true);
		cRod.setFilled(true);
		aBase.setFilled(true);
		bBase.setFilled(true);
		cBase.setFilled(true);

		aRod.setLocation(WIDTH / 4 - HanoiConstants.ROD_WIDTH / 2, (HEIGHT
				- aRod.getHeight() - HanoiConstants.JFRAME_HEIGHT) / 2);
		bRod.setLocation(WIDTH / 2 - HanoiConstants.ROD_WIDTH / 2, (HEIGHT
				- bRod.getHeight() - HanoiConstants.JFRAME_HEIGHT) / 2);
		cRod.setLocation(3 * WIDTH / 4 - HanoiConstants.ROD_WIDTH / 2, (HEIGHT
				- cRod.getHeight() - HanoiConstants.JFRAME_HEIGHT) / 2);

		aBase.setLocation(aRod.getX() + (aRod.getWidth() - aBase.getWidth())
				/ 2, aRod.getHeight() + aRod.getY());
		bBase.setLocation(bRod.getX() + (bRod.getWidth() - bBase.getWidth())
				/ 2, aRod.getHeight() + aRod.getY());
		cBase.setLocation(cRod.getX() + (cRod.getWidth() - cBase.getWidth())
				/ 2, aRod.getHeight() + aRod.getY());
		aLabel.setLocation(aBase.getX()
				+ (aBase.getWidth() - aLabel.getWidth()) / 2, aBase.getY()
				+ aBase.getHeight() + aLabel.getHeight());
		bLabel.setLocation(bBase.getX()
				+ (bBase.getWidth() - bLabel.getWidth()) / 2, bBase.getY()
				+ bBase.getHeight() + bLabel.getHeight());
		cLabel.setLocation(cBase.getX()
				+ (cBase.getWidth() - cLabel.getWidth()) / 2, cBase.getY()
				+ cBase.getHeight() + cLabel.getHeight());

		add(aRod);
		add(bRod);
		add(cRod);
		add(aBase);
		add(bBase);
		add(cBase);
		add(aLabel);
		add(bLabel);
		add(cLabel);
	}

	public ArrayList<Disk> addDisks() {
		ArrayList<Disk> myDisks = new ArrayList<Disk>();
		int minWidth = HanoiConstants.MIN_DISK_WIDTH;
		for (int i = 0; i < Hanoi.nDisks; i++) {
			Disk myDisk = new Disk(minWidth, HanoiConstants.DISK_HEIGHT, i);
			myDisk.setFilled(true);
			myDisk.setFillColor(diskColor(i));
			myDisks.add(myDisk);
			minWidth += HanoiConstants.DISK_WIDTH_INCREMENT;
		}
		double initHeight = aBase.getY() - aBase.getHeight();
		for (int i = myDisks.size() - 1; i >= 0; i--) {
			myDisks.get(i).setLocation(
					aRod.getX() + (aRod.getWidth() - myDisks.get(i).getWidth())
							/ 2, initHeight);
			add(myDisks.get(i));
			initHeight -= HanoiConstants.DISK_HEIGHT;
		}
		return myDisks;
	}

	public Color diskColor(int disk) {
		Color diskColor = Color.BLACK;
		switch (disk) {
		case 0:
			diskColor = Color.RED;
			break;
		case 1:
			diskColor = Color.ORANGE;
			break;
		case 2:
			diskColor = Color.YELLOW;
			break;
		case 3:
			diskColor = Color.GREEN;
			break;
		case 4:
			diskColor = Color.BLUE;
			break;
		case 5:
			diskColor = Color.BLUE.darker().darker();
			break;
		case 6:
			diskColor = Color.MAGENTA.darker().darker();
			break;
		case 7:
			diskColor = Color.RED.darker().darker();
			break;
		case 8:
			diskColor = Color.ORANGE.darker().darker();
			break;
		default:
			diskColor = Color.WHITE;
		}
		return diskColor;
	}

}
