import acm.graphics.GRect;

public class Disk extends GRect implements Runnable {

	public int n;
	public double src, dst;

	public Disk(double width, double height, int num) {
		super(width, height);
	}

	public void run() {
		//Double checking that the source and destination numbers are read in
		if (src != 0.0 && dst != 0.0) {
			//Translate the x-coordinates into the rod letters for the printout
			String source = "", destination = "";
			if (src == HanoiCanvas.aRod.getX() + HanoiCanvas.aRod.getWidth()
					/ 2) {
				source = "A";
			} else if (src == HanoiCanvas.bRod.getX()
					+ HanoiCanvas.bRod.getWidth() / 2) {
				source = "B";
			} else if (src == HanoiCanvas.cRod.getX()
					+ HanoiCanvas.cRod.getWidth() / 2) {
				source = "C";
			}

			if (dst == HanoiCanvas.aRod.getX() + HanoiCanvas.aRod.getWidth()
					/ 2) {
				destination = "A";
			} else if (dst == HanoiCanvas.bRod.getX()
					+ HanoiCanvas.bRod.getWidth() / 2) {
				destination = "B";
			} else if (dst == HanoiCanvas.cRod.getX()
					+ HanoiCanvas.cRod.getWidth() / 2) {
				destination = "C";
			}
			//Ensuring there's at least 1 disk in the array of disks
			if (Hanoi.myDisks.size() > 0) {
				//Move up from the current stack to tip of the rod
				while (true) {
					if (Hanoi.myDisks.get(n).getY() <= HanoiCanvas.aRod
							.getY()
							- Hanoi.myDisks.get(n).getHeight()) {
						break;
					}
					Hanoi.myDisks.get(n).move(0, -1);
					pause(Hanoi.slider.getValue());
				}
				//Move left or right depending on where it has to go
				if (src < dst) {
					while (Hanoi.myDisks.get(n).getX() < dst
							- Hanoi.myDisks.get(n).getWidth() / 2) {
						Hanoi.myDisks.get(n).move(1, 0);
						pause(Hanoi.slider.getValue());
					}
				} else {
					while (Hanoi.myDisks.get(n).getX() > dst
							- Hanoi.myDisks.get(n).getWidth() / 2) {
						Hanoi.myDisks.get(n).move(-1, 0);
						pause(Hanoi.slider.getValue());
					}
				}
				//Drop it down onto the destination rod's existing stack
				while (true) {
					if (Hanoi.canvas.getElementAt(
							Hanoi.myDisks.get(n).getX(),
							Hanoi.myDisks.get(n).getY()
									+ Hanoi.myDisks.get(n)
											.getHeight() + 1) != null) {
						break;
					}
					Hanoi.myDisks.get(n).move(0, 1);
					pause(Hanoi.slider.getValue());
				}
				Hanoi.myDisks.get(n).move(0, 1);
			}
			//Print out what happened
			System.out.println("Plate " + (n + 1) + " moved from " + source
					+ " to " + destination);
		}
	}
}
