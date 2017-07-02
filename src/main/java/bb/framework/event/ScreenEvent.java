package bb.framework.event;

/**
 * Created by willie on 6/18/17.
 */
public class ScreenEvent {
	public static final int START_1P_GAME = 0;
	public static final int START_2P_GAME = 1;
	public static final int SCREEN_ABORTED = 2;
	public static final int SCREEN_EXPIRED = 3;

	private int type;

	public ScreenEvent(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}
}
