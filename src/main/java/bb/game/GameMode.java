package bb.game;

import bb.common.BBConfig;
import bb.common.BBContext;
import bb.framework.event.ScreenEvent;
import bb.framework.mode.AbstractMode;
import bb.framework.mode.AbstractModeController;
import bb.framework.screen.Screen;
import bb.framework.screen.ScreenManager;
import bb.framework.util.Assert;
import bb.game.arena.scene.ArenaScene;
import bb.game.arena.screen.ArenaScreen;

/**
 * Created by willie on 7/1/17.
 */
public class GameMode extends AbstractMode {

	public GameMode(BBConfig config, BBContext context, ScreenManager screenManager) {
		super(BBConfig.GAME_MODE);
		Assert.notNull(config, "config can't be null");
		Assert.notNull(context, "context can't be null");
		Assert.notNull(screenManager, "screenManager can't be null");
		setStateMachine(new GameController(config, context, screenManager));
	}

	private class GameController extends AbstractModeController {
		private BBConfig config;
		private BBContext context;

		public GameController(BBConfig config, BBContext context, ScreenManager screenManager) {
			super(screenManager);
			this.config = config;
			this.context = context;
		}

		@Override
		public void handleEvent(ScreenEvent event) {
			// TODO
		}

		@Override
		public void start() {
			transitionTo(arenaScreen());
		}

		private Screen arenaScreen() {
			ArenaScreen screen = new ArenaScreen(config, context, new ArenaScene());
			screen.postConstruct();
			return screen;
		}
	}
}
