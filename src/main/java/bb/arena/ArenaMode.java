package bb.arena;

import bb.arena.view.ArenaScreen;
import bb.framework.mode.AbstractModeStateMachine;
import bb.common.BBConfig;
import bb.common.BBContext;
import bb.framework.event.ScreenEvent;
import bb.framework.event.ScreenListener;
import bb.framework.mode.AbstractMode;
import bb.framework.util.Assert;
import bb.framework.view.BBScreenManager;

/**
 * Created by willie on 7/1/17.
 */
public class ArenaMode extends AbstractMode {
	private ArenaScreenFactory screenFactory;

	public ArenaMode(BBContext context, BBScreenManager screenManager) {
		super(BBConfig.ARENA_MODE);

		this.screenFactory = new ArenaScreenFactory(context);
		setStateMachine(new ArenaStateMachine(screenManager));
	}

	private class ArenaScreenFactory {
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

	private class ArenaStateMachine extends AbstractModeStateMachine {

		public ArenaStateMachine(BBScreenManager screenManager) {
			super(screenManager);
		}

		@Override
		public void handleEvent(ScreenEvent event) {
			// TODO
		}

		@Override
		public void start() {
			transitionTo(screenFactory.createArenaScreen());
		}
	}
}
