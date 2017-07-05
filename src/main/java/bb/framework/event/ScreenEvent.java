package bb.framework.event;

import bb.framework.screen.Screen;
import bb.framework.util.Assert;

/**
 * Created by willie on 6/18/17.
 */
public class ScreenEvent {

	// FIXME Shouldn't 1P/2P be mode events? [WLW]
	public static final int START_1P_GAME = 0;
	public static final int START_2P_GAME = 1;

	public static final int SCREEN_ABORTED = 2;
	public static final int SCREEN_EXPIRED = 3;

	private Screen source;
	private int type;

	public ScreenEvent(Screen source, int type) {
		Assert.notNull(source, "source can't be null");
		this.source = source;
		this.type = type;
	}

	public Screen getSource() {
		return source;
	}

	public int getType() {
		return type;
	}
}
