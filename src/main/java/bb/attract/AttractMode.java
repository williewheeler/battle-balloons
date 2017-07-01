package bb.attract;

import bb.BBContext;
import bb.BBFrame;
import bb.framework.GameScreen;
import bb.framework.event.GameEvent;
import bb.framework.event.GameListener;
import bb.framework.util.Assert;

/**
 * Created by willie on 7/1/17.
 */
public class AttractMode {
	private BBContext context;
	private BBFrame frame;
	private GameListener modeTransitionHandler;
	private GameListener screenTransitionHandler;

	public AttractMode(BBFrame frame, BBContext context, GameListener modeTransitionHandler) {
		Assert.notNull(frame, "frame can't be null");
		Assert.notNull(context, "context can't be null");
		Assert.notNull(modeTransitionHandler, "modeTransitionHandler can't be null");

		this.frame = frame;
		this.context = context;
		this.modeTransitionHandler = modeTransitionHandler;
		this.screenTransitionHandler = new StateTransitionHandler();
	}

	public void start() {
		screenTransitionTo(new TitleScreen(context, screenTransitionHandler));
	}

	private void screenTransitionTo(GameScreen screen) {
		frame.startScreen(screen);
	}

	private class StateTransitionHandler implements GameListener {

		@Override
		public void handleEvent(GameEvent event) {
			String type = event.getType();

			// TODO re: two-player game. [WLW]
			if (type == GameEvent.START_1P_GAME || type == GameEvent.START_2P_GAME) {
				GameEvent modeEvent = new GameEvent(AttractMode.this, GameEvent.MODE_EXPIRED);
				modeTransitionHandler.handleEvent(modeEvent);
			} else if (type == GameEvent.SCREEN_ABORTED) {
				screenTransitionTo(new TitleScreen(context, this));
			} else if (type == GameEvent.SCREEN_EXPIRED) {
				GameScreen currentScreen = frame.getCurrentScreen();
				if (currentScreen instanceof TitleScreen) {
					screenTransitionTo(new BackstoryScreen(context, this));
				} else if (currentScreen instanceof BackstoryScreen) {
					screenTransitionTo(new RosterScreen(context, this));
				} else if (currentScreen instanceof RosterScreen) {
					screenTransitionTo(new TitleScreen(context, this));
				} else {
					throw new IllegalArgumentException("Unexpected screen: " + currentScreen);
				}
			} else {
				throw new IllegalArgumentException("Unexpected game event type: " + type);
			}
		}
	}
}
