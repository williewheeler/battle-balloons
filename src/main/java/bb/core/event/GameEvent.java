package bb.core.event;

/**
 * Created by willie on 6/18/17.
 */
public class GameEvent {
	public static final String START_1P_GAME = "START_1P_GAME";
	public static final String START_2P_GAME = "START_2P_GAME";
	public static final String SCREEN_ABORTED = "SCREEN_ABORTED";
	public static final String SCREEN_EXPIRED = "SCREEN_EXPIRED";

	private Object source;
	private String type;

	public GameEvent(Object source, String type) {
		this.source = source;
		this.type = type;
	}

	public Object getSource() {
		return source;
	}

	public String getType() {
		return type;
	}
}
