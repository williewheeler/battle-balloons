package bb.attract;

import bb.attract.backstory.BackstoryScreen;
import bb.attract.roster.RosterScreen;
import bb.attract.title.TitleScreen;
import bb.common.mode.AbstractModeStateMachine;
import bb.common.BBConfig;
import bb.common.BBContext;
import bb.framework.event.ScreenEvent;
import bb.common.mode.AbstractMode;
import bb.framework.util.Assert;
import bb.common.view.BBScreen;
import bb.common.view.BBScreenManager;

/**
 * Created by willie on 7/1/17.
 */
public class AttractMode extends AbstractMode {

	public AttractMode(BBContext context, BBScreenManager screenManager) {
		super(BBConfig.ATTRACT_MODE);
		Assert.notNull(context, "context can't be null");
		Assert.notNull(screenManager, "screenManager can't be null");
		setStateMachine(new AttractStateMachine(context, screenManager));
	}

	private class AttractStateMachine extends AbstractModeStateMachine {
		private BBContext context;

		public AttractStateMachine(BBContext context, BBScreenManager screenManager) {
			super(screenManager);
			this.context = context;
		}

		@Override
		public void handleEvent(ScreenEvent event) {
			BBScreen currentScreen = getCurrentScreen();

			int type = event.getType();
			if (type == ScreenEvent.START_1P_GAME || type == ScreenEvent.START_2P_GAME) {
				// TODO Handle choice between 1p and 2p game. [WLW]
				yield();
			} else if (type == ScreenEvent.SCREEN_ABORTED) {
				transitionTo(new TitleScreen(context));
			} else if (type == ScreenEvent.SCREEN_EXPIRED) {
				if (currentScreen instanceof TitleScreen) {
					transitionTo(new BackstoryScreen(context));
				} else if (currentScreen instanceof BackstoryScreen) {
					transitionTo(new RosterScreen(context));
				} else if (currentScreen instanceof RosterScreen) {
					transitionTo(new TitleScreen(context));
				} else {
					throw new IllegalArgumentException("Unexpected screen: " + currentScreen);
				}
			} else {
				throw new IllegalArgumentException("Unexpected game event type: " + type);
			}
		}

		@Override
		public void start() {
			transitionTo(new TitleScreen(context));
		}
	}
}
