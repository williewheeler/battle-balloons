package bb.arena;

import bb.BBContext;
import bb.BBFrame;
import bb.framework.GameScreen;
import bb.framework.event.GameEvent;
import bb.framework.event.GameListener;
import bb.framework.util.Assert;

/**
 * Created by willie on 7/1/17.
 */
public class ArenaMode {
	private BBFrame frame;
	private BBContext context;
	private GameListener modeTransitionHandler;
	private GameListener screenTransitionHandler;

	public ArenaMode(BBFrame frame, BBContext context, GameListener modeTransitionHandler) {
		Assert.notNull(frame, "frame can't be null");
		Assert.notNull(context, "context can't be null");
		Assert.notNull(modeTransitionHandler, "modeTransitionHandler can't be null");

		this.frame = frame;
		this.context = context;
		this.modeTransitionHandler = modeTransitionHandler;
		this.screenTransitionHandler = new StateTransitionHandler();
	}

	public void start() {
		screenTransitionTo(new ArenaScreen(context, screenTransitionHandler));
	}

	private void screenTransitionTo(GameScreen screen) {
		frame.startScreen(screen);
	}

	private class StateTransitionHandler implements GameListener {

		@Override
		public void handleEvent(GameEvent event) {
			// TODO
		}
	}
}
