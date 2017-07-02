package bb.game;

import bb.common.BBConfig;
import bb.common.BBContext;
import bb.common.mode.AbstractMode;
import bb.common.mode.AbstractModeStateMachine;
import bb.common.BBScreenManager;
import bb.framework.event.ScreenEvent;
import bb.framework.util.Assert;
import bb.game.arena.view.ArenaScreen;

/**
 * Created by willie on 7/1/17.
 */
public class GameMode extends AbstractMode {

	public GameMode(BBContext context, BBScreenManager screenManager) {
		super(BBConfig.GAME_MODE);
		Assert.notNull(context, "context can't be null");
		Assert.notNull(screenManager, "screenManager can't be null");
		setStateMachine(new GameStateMachine(context, screenManager));
	}

	private class GameStateMachine extends AbstractModeStateMachine {
		private BBContext context;

		public GameStateMachine(BBContext context, BBScreenManager screenManager) {
			super(screenManager);
			this.context = context;
		}

		@Override
		public void handleEvent(ScreenEvent event) {
			// TODO
		}

		@Override
		public void start() {
			transitionTo(new ArenaScreen(context));
		}
	}
}
