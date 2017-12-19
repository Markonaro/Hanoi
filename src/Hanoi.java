import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import acm.program.Program;

public class Hanoi extends Program {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static Integer nDisks = 0;

	public static final int APPLICATION_WIDTH = 3 * HanoiConstants.BASE_WIDTH
			+ 4 * HanoiConstants.ROD_MID_SPACING;
	public static final int APPLICATION_HEIGHT = HanoiConstants.MAX_NDISKS
			* HanoiConstants.DISK_HEIGHT + HanoiConstants.BASE_HEIGHT + 3
			* HanoiConstants.ROD_TOP_SPACING + HanoiConstants.JFRAME_HEIGHT;

	public static JSlider slider;

	public static HanoiCanvas canvas;

	public static ArrayList<Disk> myDisks;
	
	public static Thread[] myThreads;
	
	public static boolean solve = false;

	public void init() {
		JLabel qtyLabel = new JLabel("Number of Disks: ");
		add(qtyLabel, NORTH);

		qtyText = new JTextField(2);
		qtyText.addActionListener(this);
		add(qtyText, NORTH);

		set = new JButton("Set");
		add(set, NORTH);

		slider = new JSlider();
		slider.setInverted(true);
		slider.setMinimum(HanoiConstants.FASTEST);
		slider.setMaximum(HanoiConstants.SLOWEST);
		slider.setValue(HanoiConstants.INITIAL_SPEED);
		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(2);
		slider.setPaintTicks(true);
		slider.setSnapToTicks(true);
		add(slider, NORTH);

		run = new JButton("Run");
		add(run, SOUTH);

		pause = new JButton("Pause");
		add(pause, SOUTH);

		reset = new JButton("Reset");
		add(reset, SOUTH);

		addActionListeners();

		canvas = new HanoiCanvas();
		add(canvas);

		char ch = '0';
		for (int c = 0; c <= 9; c++) {
			digits[c] = ch;
			ch++;
		}
	}

	public void main() {
		//Because I need this to execute the file,
		//even though idk what to put in here lol.
	}

	public void actionPerformed(ActionEvent e) {
		//"RESET" button action.
		if (e.getSource() == reset) {
			pauseExecution();
			canvas.resetDisks();
			qtyText.setText("");
		}

		//"RUN" button action
		if (e.getSource() == run) {
			//If we're talking about at least 1 disk...
			if (myDisks != null) {
				solve = true;
				//The canvas rod locations are read into
				//the initial function call.
				double source = HanoiCanvas.aRod.getX()
						+ HanoiCanvas.aRod.getWidth() / 2;
				double destination = HanoiCanvas.bRod.getX()
						+ HanoiCanvas.bRod.getWidth() / 2;
				double temporary = HanoiCanvas.cRod.getX()
						+ HanoiCanvas.cRod.getWidth() / 2;
				myThreads = new Thread[nDisks];
//.......... Here's the initial function call.
				moveTower(nDisks, source, destination, temporary);
			} else {
				System.out.println("myDisks contains 0 GRects");
			}
		}

		//"SET" button action or if enter is pressed with 
		//the cursor in the text field.
		if (e.getSource() == set || e.getSource() == qtyText) {
			if (checkText()) {
				canvas.resetDisks();
				nDisks = Character.getNumericValue(nDisksText[0]);
				myDisks = canvas.addDisks();
			} else {
				qtyText.setText("");
				System.out.println("Only enter a single digit (0-9)");
			}
		}
		
		//"PAUSE" button action.
		if (e.getSource() == set || e.getSource() == qtyText) {
			if (checkText()) {
				canvas.resetDisks();
				nDisks = Character.getNumericValue(nDisksText[0]);
				myDisks = canvas.addDisks();
			} else {
				qtyText.setText("");
				System.out.println("Only enter a single digit (0-9)");
			}
		}
	}

	public boolean checkText() {
		boolean isNumber = false;
		//I only want to use numbers 1-9 for simplicity
		if (qtyText.getText().length() == 1) {
			nDisksText = qtyText.getText().toCharArray();
			if (Character.isDigit(nDisksText[0])) {
				if(nDisksText[0] != '0'){
					isNumber = true;
				}
			}
		}
		return isNumber;
	}

	private void moveTower(int n, double src, double dst, double tmp) {
		if (n > 0) {
			moveTower(n-1, src, tmp, dst);
			moveSingleDisk(n, src, dst);
			moveTower(n-1, tmp, dst, src);
		} else {
			moveSingleDisk(n, src, dst);
		}
	}

	public void moveSingleDisk(int n, double src, double dst) {
	//Tell the current Disk what number it is, which rod it's on, and where it's going.
		myDisks.get(n).n = n;
		myDisks.get(n).src = src;
		myDisks.get(n).dst = dst;
/*Two execution options. Comment one out when checking the other:
	*Option 1, the initial func'n call creates a bunch of nested while loops
	*within it, so the animation doesn't occur until it's back at the initial level:*/
		myDisks.get(n).run(); // But then I get stuck in the while loop
	/*Option 2:
	*Create a new thread for the disk's present requested behavior and execute it.*/
//		myThreads[n] = new Thread(myDisks.get(n));
//		myThreads[n].start();
	}

	public void pauseExecution() {
		solve = false;
	}

	private JTextField qtyText;
	private JButton run;
	private JButton pause;
	private JButton reset;
	private JButton set;
	private char[] digits = new char[10];
	private char[] nDisksText = new char[1];
}
