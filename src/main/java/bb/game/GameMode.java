package bb.game;

import bb.common.BBConfig;
import bb.common.BBContext;
import bb.common.event.GameEvents;
import bb.common.resource.AudioFactory;
import bb.common.screen.TransitionScreen;
import bb.game.screen.ArenaScreen;
import retroge.event.GameEvent;
import retroge.event.GameListener;
import retroge.event.ScreenEvent;
import retroge.event.ScreenListener;
import retroge.mode.AbstractMode;
import retroge.screen.ScreenManager;
import retroge.util.Assert;

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
		AudioFactory audioFactory = context.getAudioFactory();
		game.incrementPlayerLevel();
		game.getScene().addGameListener(gameHandler);
		game.getScene().addGameListener(new AudioHandler(audioFactory));
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
	
	private class AudioHandler implements GameListener {
		private AudioFactory audioFactory;
		
		public AudioHandler(AudioFactory audioFactory) {
			this.audioFactory = audioFactory;
		}
		
		@Override
		public void handleEvent(GameEvent event) {
			
			// TODO Refactor to avoid if/else? [WLW]
			if (event == GameEvents.PLAYER_WALKED) {
				audioFactory.playerWalks();
			} else if (event == GameEvents.PLAYER_THREW_BALLOON) {
				audioFactory.playerThrowsBalloon();
			} else if (event == GameEvents.PLAYER_DIED) {
				audioFactory.playerDies();
			} else if (event == GameEvents.ANIMAL_RESCUED) {
				audioFactory.animalRescued();
			} else if (event == GameEvents.ANIMAL_DIED) {
				audioFactory.animalDies();
			} else if (event == GameEvents.NEXT_LEVEL) {
				audioFactory.playerNextLevel();
			} else if (event == GameEvents.OBSTACLE_DESTROYED) {
				// TODO Yeah, repurposing the sound...
//				audioFactory.startSound();
			} else if (event == GameEvents.JUDO_DIED) {
				audioFactory.judoHit();
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
