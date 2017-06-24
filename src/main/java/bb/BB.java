package bb;

import bb.arena.ArenaController;
import bb.arena.model.ArenaModel;
import bb.arena.view.ArenaScreen;
import bb.attract.backstory.BackstoryController;
import bb.attract.backstory.BackstoryModel;
import bb.attract.backstory.BackstoryScreen;
import bb.attract.roster.Actor;
import bb.attract.roster.RosterController;
import bb.attract.roster.RosterModel;
import bb.attract.roster.RosterScreen;
import bb.attract.title.TitleController;
import bb.attract.title.TitleModel;
import bb.attract.title.TitleScreen;
import bb.core.GameController;
import bb.core.audio.AudioFactory;
import bb.core.event.GameEvent;
import bb.core.event.GameListener;
import bb.core.view.FontFactory;
import bb.core.view.ImageFactory;
import bb.core.view.Resizer;
import bb.core.view.SpriteFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.util.LinkedList;
import java.util.List;

// TODO Move attact mode logic into a dedicated controller [WLW]

public class BB extends JFrame {
	private static final Logger log = LoggerFactory.getLogger(BB.class);

	private FontFactory fontFactory;
	private ImageFactory imageFactory;
	private SpriteFactory spriteFactory;
	private AudioFactory audioFactory;
	private GameHandler gameHandler;
	private GameController currentController;

	public BB() {
		super("Battle Balloons");
		this.fontFactory = new FontFactory();
		this.imageFactory = new ImageFactory();
		this.spriteFactory = new SpriteFactory(imageFactory);
		this.audioFactory = new AudioFactory();
		this.gameHandler = new GameHandler();
	}

	public void launch() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Forces correct size
		setContentPane(new Resizer());

		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		startTitle();
//		startRoster();
	}

	private void startTitle() {
		TitleModel model = new TitleModel();
		TitleScreen screen = new TitleScreen(model, fontFactory, imageFactory, spriteFactory);
		setCurrentController(new TitleController(model, screen, gameHandler));
	}

	private void startBackstory() {
		BackstoryModel model = new BackstoryModel();
		BackstoryScreen screen = new BackstoryScreen(model, fontFactory);
		setCurrentController(new BackstoryController(model, screen, gameHandler));
	}

	private void startRoster() {
		RosterModel model = new RosterModel();

		Actor lexi = new Actor(model.getLexiModel());
		lexi.setBlinkingSprites(spriteFactory.getLexiBlinking());
		lexi.setWavingSprites(spriteFactory.getLexiWaving());

		List<Actor> actors = new LinkedList<>();
		actors.add(lexi);

		RosterScreen screen = new RosterScreen(model, actors);
		setCurrentController(new RosterController(model, screen, gameHandler));
	}

	private void startGame(int numPlayers) {
		ArenaModel model = new ArenaModel();
		ArenaScreen screen = new ArenaScreen(model, fontFactory, spriteFactory);
		setCurrentController(new ArenaController(model, screen, audioFactory, gameHandler));
	}

	private void setCurrentController(GameController controller) {
		if (currentController != null) {
			currentController.stop();
			removeKeyListener(currentController.getKeyListener());
		}

		this.currentController = controller;
		setContentPane(new Resizer(controller.getGameScreen()));
		validate();
		addKeyListener(currentController.getKeyListener());
		currentController.start();
	}

	private class GameHandler implements GameListener {

		@Override
		public void handleEvent(GameEvent event) {
			Object source = event.getSource();
			String type = event.getType();

			if (type == GameEvent.START_1P_GAME) {
				startGame(1);
			} else if (type == GameEvent.START_2P_GAME) {
				startGame(2);
			} else if (type == GameEvent.SCREEN_ABORTED) {
				startTitle();
			} else if (type == GameEvent.SCREEN_EXPIRED) {
				if (source instanceof TitleController) {
					startBackstory();
				} else if (source instanceof BackstoryController) {
					startRoster();
				} else if (source instanceof RosterController) {
					startTitle();
				} else {
					throw new IllegalStateException("Unknown source: " + source);
				}
			}
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new BB().launch();
			}
		});
	}
}
