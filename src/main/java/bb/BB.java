package bb;

import bb.arena.ArenaController;
import bb.arena.ArenaScreen;
import bb.arena.model.ArenaModel;
import bb.attract.BackstoryController;
import bb.attract.BackstoryScreen;
import bb.attract.RosterScreen;
import bb.attract.TitleScreen;
import bb.attract.RosterController;
import bb.attract.TitleController;
import bb.framework.GameController;
import bb.framework.event.GameEvent;
import bb.framework.event.GameListener;
import bb.framework.view.Resizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

// TODO Move attact mode logic into a dedicated controller [WLW]

public class BB extends JFrame {
	private static final Logger log = LoggerFactory.getLogger(BB.class);

	private BBContext context;
	private GameListener gameListener;
	private GameController currentController;

	public BB() {
		super("Battle Balloons");
		this.context = new BBContext();
		this.gameListener = new GameHandler();
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
		TitleScreen screen = new TitleScreen(context, gameListener);
		setCurrentController(new TitleController(screen, gameListener));
	}

	private void startBackstory() {
		BackstoryScreen screen = new BackstoryScreen(context, gameListener);
		setCurrentController(new BackstoryController(screen, gameListener));
	}

	private void startRoster() {
		RosterScreen screen = new RosterScreen(context, gameListener);
		setCurrentController(new RosterController(screen, gameListener));
	}

	private void startGame(int numPlayers) {
		ArenaModel model = new ArenaModel();
		ArenaScreen screen = new ArenaScreen(context, model);
		setCurrentController(new ArenaController(context, model, screen, gameListener));
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

	public class GameHandler implements GameListener {

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
