package bb.arena;

import bb.common.BBContext;
import bb.framework.event.ScreenListener;
import bb.framework.util.Assert;

/**
 * Created by willie on 7/1/17.
 */
public class ArenaScreenFactory {
	private BBContext context;
	private ScreenListener screenListener;

	public ArenaScreenFactory(BBContext context) {
		Assert.notNull(context, "context can't be null");
		this.context = context;
	}

	public ArenaScreen createArenaScreen() {
		return new ArenaScreen(context);
	}
}
