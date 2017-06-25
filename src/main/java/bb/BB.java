package bb;

import bb.arena.ArenaController;
import bb.arena.model.ArenaModel;
import bb.arena.view.ArenaScreen;
import bb.attract.backstory.BackstoryController;
import bb.attract.backstory.BackstoryModel;
import bb.attract.backstory.BackstoryScreen;
import bb.attract.roster.RosterController;
import bb.attract.roster.RosterScreen;
import bb.attract.title.TitleController;
import bb.attract.title.TitleModel;
import bb.attract.title.TitleScreen;
import bb.common.audio.AudioFactory;
import bb.common.model.LexiModel;
import bb.common.view.actor.ActorViewFactory;
import bb.common.view.FontFactory;
import bb.common.view.actor.LexiView;
import bb.common.view.SpriteFactory;
import bb.framework.GameController;
import bb.framework.event.GameEvent;
import bb.framework.event.GameListener;
import bb.framework.model.actor.Actor;
import bb.framework.view.FontLoader;
import bb.framework.view.ImageLoader;
import bb.framework.view.Resizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

// TODO Move attact mode logic into a dedicated controller [WLW]

public class BB extends JFrame {
	private static final Logger log = LoggerFactory.getLogger(BB.class);

	private FontLoader fontLoader;
	private ImageLoader imageLoader;

	private FontFactory fontFactory;
	private SpriteFactory spriteFactory;
	private AudioFactory audioFactory;
	private ActorViewFactory actorViewFactory;

	private GameHandler gameHandler;
	private GameController currentController;

	public BB() {
		super("Battle Balloons");

		this.fontLoader = new FontLoader();
		this.imageLoader = new ImageLoader();

		this.fontFactory = new FontFactory(fontLoader);
		this.spriteFactory = new SpriteFactory(imageLoader);
		this.audioFactory = new AudioFactory();
		this.actorViewFactory = new ActorViewFactory(spriteFactory);

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
		TitleScreen screen = new TitleScreen(model, fontFactory, imageLoader, spriteFactory);
		setCurrentController(new TitleController(model, screen, gameHandler));
	}

	private void startBackstory() {
		BackstoryModel model = new BackstoryModel();
		BackstoryScreen screen = new BackstoryScreen(model, fontFactory);
		setCurrentController(new BackstoryController(model, screen, gameHandler));
	}

	private void startRoster() {
		LexiModel lexiModel = new LexiModel(80, 150);
		LexiView lexiView = actorViewFactory.createLexiView(lexiModel);
		Actor lexi = new Actor(lexiModel, lexiView);

		RosterScreen screen = new RosterScreen();
		screen.addActor(lexi);

		setCurrentController(new RosterController(screen, gameHandler));
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
