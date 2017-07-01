package bb;

import bb.arena.ArenaScreen;
import bb.attract.BackstoryScreen;
import bb.attract.RosterScreen;
import bb.attract.TitleScreen;
import bb.framework.event.GameEvent;
import bb.framework.event.GameListener;
import bb.framework.GameScreen;
import bb.framework.Resizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

// TODO Move attact mode logic into a dedicated controller [WLW]

public class BB extends JFrame {
	private static final Logger log = LoggerFactory.getLogger(BB.class);

	private BBContext context;
	private GameListener gameListener;
	private GameScreen currentScreen;

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
		setCurrentScreen(new TitleScreen(context, gameListener));
	}

	private void startBackstory() {
		setCurrentScreen(new BackstoryScreen(context, gameListener));
	}

	private void startRoster() {
		setCurrentScreen(new RosterScreen(context, gameListener));
	}

	private void startGame(int numPlayers) {
		setCurrentScreen(new ArenaScreen(context, gameListener));
	}

	private void setCurrentScreen(GameScreen screen) {
		if (currentScreen != null) {
			currentScreen.stop();
			removeKeyListener(currentScreen.getKeyListener());
		}

		this.currentScreen = screen;
		setContentPane(new Resizer(currentScreen));
		validate();
		addKeyListener(currentScreen.getKeyListener());
		currentScreen.start();
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
				if (source instanceof TitleScreen) {
					startBackstory();
				} else if (source instanceof BackstoryScreen) {
					startRoster();
				} else if (source instanceof RosterScreen) {
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
