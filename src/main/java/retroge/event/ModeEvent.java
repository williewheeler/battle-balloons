package retroge.event;

/**
 * Created by willie on 7/1/17.
 */
public class ModeEvent {
	public static final int MODE_STOPPED = 1;

	private int type;

	public ModeEvent(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}
}
