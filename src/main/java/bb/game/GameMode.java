package bb.game;

import bb.common.BBConfig;
import bb.common.BBContext;
import bb.common.event.GameEvents;
import bb.common.screen.TransitionScreen;
import bb.framework.event.GameEvent;
import bb.framework.event.GameListener;
import bb.framework.event.ScreenEvent;
import bb.framework.event.ScreenListener;
import bb.framework.mode.AbstractMode;
import bb.framework.screen.ScreenManager;
import bb.framework.util.Assert;
import bb.game.arena.screen.ArenaScreen;

import static bb.game.GameScreenNames.TRANSITION_SCREEN;

/**
 * Created by willie on 7/1/17.
 */
public class GameMode extends AbstractMode {
	private BBConfig config;
	private BBContext context;
	
	private Game game;
	private GameHandler gameHandler;
	private ScreenHandler screenHandler;

	public GameMode(BBConfig config, BBContext context, ScreenManager screenManager) {
		super(BBConfig.GAME_MODE, screenManager);
		Assert.notNull(config, "config can't be null");
		Assert.notNull(context, "context can't be null");
		this.config = config;
		this.context = context;
		initListeners();
	}
	
	@Override
	public void start() {
		this.game = new Game();
		advancePlayerLevel();
		context.getAudioFactory().playerFirstLevel();
		transitionTo(transitionScreen());
	}
	
	private void advancePlayerLevel() {
		game.incrementPlayerLevel();
		game.getScene().addGameListener(gameHandler);
	}
	
	private TransitionScreen transitionScreen() {
		TransitionScreen screen = TransitionScreen.create(TRANSITION_SCREEN, config, context);
		screen.addScreenListener(screenHandler);
		return screen;
	}
	
	private ArenaScreen arenaScreen() {
		ArenaScreen screen = ArenaScreen.create(config, context, game.getScene());
		screen.addScreenListener(screenHandler);
		return screen;
	}
	
	private void initListeners() {
		this.gameHandler = new GameHandler();
		this.screenHandler = new ScreenHandler();
	}
	
	private class GameHandler implements GameListener {

		@Override
		public void handleEvent(GameEvent event) {
			// TODO Can we use enum event types? [WLW]
			if (event == GameEvents.NEXT_LEVEL) {
				advancePlayerLevel();
				transitionTo(transitionScreen());
			} else if (event == GameEvents.PLAYER_GONE) {
				transitionTo(transitionScreen());
			} else if (event == GameEvents.GAME_OVER) {
				stop();
			}
		}
	}
	
	private class ScreenHandler implements ScreenListener {
		
		@Override
		public void handleEvent(ScreenEvent event) {
			if (event.getSource() instanceof TransitionScreen) {
				switch (event.getType()) {
					case SCREEN_EXPIRED:
						game.spawnPlayer();
						game.getScene().setActive(true);
						transitionTo(arenaScreen());
						break;
				}
			}
		}
	}
}
