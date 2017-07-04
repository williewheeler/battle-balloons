package bb.attract;

import bb.attract.backstory.BackstoryScene;
import bb.attract.roster.RosterScene;
import bb.attract.title.TitleScreen;
import bb.common.BBConfig;
import bb.common.BBContext;
import bb.common.screen.AbortableSceneScreen;
import bb.common.screen.TransitionScreen;
import bb.framework.event.ScreenEvent;
import bb.framework.mode.AbstractMode;
import bb.framework.mode.AbstractModeController;
import bb.framework.screen.Screen;
import bb.framework.screen.ScreenManager;
import bb.framework.util.Assert;

import static bb.attract.AttractScreenNames.*;

/**
 * Created by willie on 7/1/17.
 */
public class AttractMode extends AbstractMode {

	public AttractMode(BBConfig config, BBContext context, ScreenManager screenManager) {
		super(BBConfig.ATTRACT_MODE);
		Assert.notNull(config, "config can't be null");
		Assert.notNull(context, "context can't be null");
		Assert.notNull(screenManager, "screenManager can't be null");
		setStateMachine(new AttractController(config, context, screenManager));
	}

	private class AttractController extends AbstractModeController {
		private BBConfig config;
		private BBContext context;

		public AttractController(BBConfig config, BBContext context, ScreenManager screenManager) {
			super(screenManager);
			this.config = config;
			this.context = context;
		}

		@Override
		public void handleEvent(ScreenEvent event) {
			final int type = event.getType();
			final String screenName = getCurrentScreen().getName();

			if (type == ScreenEvent.START_1P_GAME || type == ScreenEvent.START_2P_GAME) {
				// TODO Handle choice between 1p and 2p game. [WLW]
				yield();
			} else if (type == ScreenEvent.SCREEN_ABORTED) {
				transitionTo(titleScreen());
			} else if (type == ScreenEvent.SCREEN_EXPIRED) {
				if (TRANSITION_SCREEN.equals(screenName)) {
					transitionTo(titleScreen());
				} else if (TITLE_SCREEN.equals(screenName)) {
					transitionTo(backstoryScreen());
				} else if (BACKSTORY_SCREEN.equals(screenName)) {
					transitionTo(rosterScreen());
				} else if (ROSTER_SCREEN.equals(screenName)) {
					transitionTo(transitionScreen());
				} else {
					throw new IllegalArgumentException("Unexpected screen: " + screenName);
				}
			} else {
				throw new IllegalArgumentException("Unexpected event type: " + type);
			}
		}

		@Override
		public void start() {
			transitionTo(transitionScreen());
		}

		private Screen transitionScreen() {
			return TransitionScreen.create(TRANSITION_SCREEN, config, context);
		}

		private Screen titleScreen() {
			return TitleScreen.create(config, context);
		}

		private Screen backstoryScreen() {
			return AbortableSceneScreen.create(BACKSTORY_SCREEN, config, context, new BackstoryScene());
		}

		private Screen rosterScreen() {
			return AbortableSceneScreen.create(BACKSTORY_SCREEN, config, context, new RosterScene());
		}
	}
}
