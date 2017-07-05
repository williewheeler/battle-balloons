package bb.game;

import bb.common.BBConfig;
import bb.common.BBContext;
import bb.common.actor.model.Lexi;
import bb.common.event.GameEvents;
import bb.common.screen.TransitionScreen;
import bb.framework.actor.Player;
import bb.framework.actor.brain.BasicActorBrain;
import bb.framework.event.GameEvent;
import bb.framework.event.GameListener;
import bb.framework.event.ScreenEvent;
import bb.framework.event.ScreenListener;
import bb.framework.mode.AbstractMode;
import bb.framework.mode.AbstractModeController;
import bb.framework.screen.Screen;
import bb.framework.screen.ScreenManager;
import bb.framework.util.Assert;
import bb.game.arena.level.Levels;
import bb.game.arena.scene.ArenaScene;
import bb.game.arena.screen.ArenaScreen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static bb.game.GameScreenNames.TRANSITION_SCREEN;

/**
 * Created by willie on 7/1/17.
 */
public class GameMode extends AbstractMode {
	private static final Logger log = LoggerFactory.getLogger(GameMode.class);

	private final Levels levels = new Levels();
	private Player player;
	private GameController gameController;

	public GameMode(BBConfig config, BBContext context, ScreenManager screenManager) {
		super(BBConfig.GAME_MODE);
		Assert.notNull(config, "config can't be null");
		Assert.notNull(context, "context can't be null");
		Assert.notNull(screenManager, "screenManager can't be null");

		// FIXME This Lexi code duplicates code in ArenaScene. [WLW]
		this.player = new Player(new Lexi(new BasicActorBrain(), 0, 0));

		this.gameController = new GameController(config, context, screenManager);
		setModeController(gameController);
	}

	private class GameController extends AbstractModeController implements GameListener, ScreenListener {
		private BBConfig config;
		private BBContext context;

		public GameController(BBConfig config, BBContext context, ScreenManager screenManager) {
			super(screenManager);
			this.config = config;
			this.context = context;
		}

		@Override
		public void handleEvent(GameEvent event) {
			if (event == GameEvents.NEXT_LEVEL) {
				transitionTo(transitionScreen());
			} else if (event == GameEvents.GAME_OVER) {
				yield();
			}
		}

		@Override
		public void handleEvent(ScreenEvent event) {
			Screen source = event.getSource();
			int type = event.getType();

			if (type == ScreenEvent.SCREEN_EXPIRED) {
				if (source instanceof TransitionScreen) {
					transitionTo(arenaScreen());
				}
			}
		}

		public void start() {
			transitionTo(arenaScreen());
		}

		private Screen arenaScreen() {
			Screen screen = ArenaScreen.create(config, context, arenaScene());
			screen.addScreenListener(gameController);
			return screen;
		}

		private ArenaScene arenaScene() {
			ArenaScene scene = new ArenaScene(levels);
			scene.init(player);
			scene.addGameListener(gameController);
			return scene;
		}

		private Screen transitionScreen() {
			Screen screen = TransitionScreen.create(TRANSITION_SCREEN, config, context);
			screen.addScreenListener(gameController);
			return screen;
		}
	}
}
