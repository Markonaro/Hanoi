import java.awt.Color;

public class HanoiConstants {

	public static final int DISK_HEIGHT = 10;
	public static final int MIN_DISK_WIDTH = 30;
	public static final int DISK_WIDTH_INCREMENT = 2 * DISK_HEIGHT;
	public static final int ROD_WIDTH = DISK_HEIGHT;
	public static final int ROD_TOP_SPACING = 50;
	public static final int ROD_MID_SPACING = 20;
	public static final int BASE_HEIGHT = DISK_HEIGHT;
	public static final int MAX_NDISKS = 9;
	public static final int BASE_WIDTH = MIN_DISK_WIDTH + (MAX_NDISKS + 1)
			* DISK_WIDTH_INCREMENT;
	public static final int JFRAME_HEIGHT = 130;
	public static final int FASTEST = 1;
	public static final int SLOWEST = 21;
	public static final int INITIAL_SPEED = 1;

	public static String FONT_SIZE = setFont();

	public static final Color ROD_AND_BASE_COLOR = Color.BLACK;

	public static String setFont() {
		String font = "Arial-";
		Integer size = BASE_WIDTH % 100;
		font += size.toString();
		return font;
	}

}
