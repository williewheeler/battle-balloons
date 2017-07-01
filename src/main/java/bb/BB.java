package bb;

import bb.arena.ArenaMode;
import bb.attract.AttractMode;
import bb.framework.GameScreen;
import bb.framework.Resizer;
import bb.framework.event.GameEvent;
import bb.framework.event.GameListener;
import bb.framework.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class BB extends JFrame implements BBFrame {
	private static final Logger log = LoggerFactory.getLogger(BB.class);

	private BBContext context;
	private GameListener modeTransitionHandler;
	private GameScreen currentScreen;

	public BB() {
		super("Battle Balloons");
		this.context = new BBContext();
		this.modeTransitionHandler = new ModeTransitionHandler();
	}

	@Override
	public GameScreen getCurrentScreen() {
		return currentScreen;
	}

	public void start() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Forces correct size
		setContentPane(new Resizer());

		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		startAttractMode();
	}

	@Override
	public void startScreen(GameScreen screen) {
		Assert.notNull(screen, "screen can't be null");

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

	private void startAttractMode() {
		new AttractMode(this, context, modeTransitionHandler).start();
	}

	private void startArenaMode() {
		new ArenaMode(this, context, modeTransitionHandler).start();
	}

	public class ModeTransitionHandler implements GameListener {

		@Override
		public void handleEvent(GameEvent event) {
			Object source = event.getSource();
			String type = event.getType();
			if (type == GameEvent.MODE_EXPIRED) {
				if (source instanceof AttractMode) {
					startArenaMode();
				} else if (source instanceof ArenaMode) {
					startAttractMode();
				} else {
					throw new IllegalArgumentException("Unexpected game event source: " + source);
				}
			} else {
				throw new IllegalArgumentException("Unexpected game event type: " + type);
			}
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new BB().start();
			}
		});
	}
}
