package bb;

import bb.arena.ArenaModeStateMachine;
import bb.attract.AttractModeStateMachine;
import bb.common.BBConfig;
import bb.common.BBContext;
import bb.framework.BBMode;
import bb.framework.event.GameEvent;
import bb.framework.event.GameListener;
import bb.framework.util.Assert;
import bb.framework.view.BBScreen;
import bb.framework.view.BBScreenManager;
import bb.framework.view.Resizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Battle Ballons top-level class.
 */
public class BB extends JFrame {
	private static final Logger log = LoggerFactory.getLogger(BB.class);

	private BBStateMachine stateMachine;

	public BB() {
		super("Battle Balloons");
		BBContext context = new BBContext();
		BBScreenManager screenManager = new BBScreenManagerImpl();
		BBModeFactory modeFactory = new BBModeFactory(context, screenManager);
		this.stateMachine = new BBStateMachine(modeFactory);
	}

	public void start() {
		log.info("Starting Battle Balloons");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Forces correct size
		setContentPane(new Resizer());

		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);

		stateMachine.start();
	}

	/**
	 * Manages the current screen.
	 */
	private class BBScreenManagerImpl implements BBScreenManager {
		private BBScreen currentScreen;

		@Override
		public void startScreen(BBScreen screen) {
			Assert.notNull(screen, "screen can't be null");

			stopCurrentScreen();

			this.currentScreen = screen;
			setContentPane(new Resizer(currentScreen));
			validate();
			addKeyListener(currentScreen.getKeyListener());
			currentScreen.start();
		}

		@Override
		public BBScreen getCurrentScreen() {
			return currentScreen;
		}

		@Override
		public void stopCurrentScreen() {
			if (currentScreen != null) {
				currentScreen.stop();
				removeKeyListener(currentScreen.getKeyListener());
			}
		}
	}

	/**
	 * Creates new mode instances.
	 */
	private static class BBModeFactory {
		private BBContext context;
		private BBScreenManager screenManager;

		public BBModeFactory(BBContext context, BBScreenManager screenManager) {
			Assert.notNull(context, "context can't be null");
			Assert.notNull(screenManager, "screenManager can't be null");
			this.context = context;
			this.screenManager = screenManager;
		}

		public BBMode createAttractMode(GameListener gameListener) {
			AttractModeStateMachine sm = new AttractModeStateMachine(context, screenManager, gameListener);
			return new BBMode(BBConfig.ATTRACT_MODE, sm);
		}

		public BBMode createArenaMode(GameListener gameListener) {
			ArenaModeStateMachine sm = new ArenaModeStateMachine(context, screenManager, gameListener);
			return new BBMode(BBConfig.ARENA_MODE, sm);
		}
	}

	/**
	 * Top-level state machine to transition between modes.
	 */
	private static class BBStateMachine implements GameListener {
		private BBModeFactory modeFactory;
		private BBMode currentMode;

		public BBStateMachine(BBModeFactory modeFactory) {
			Assert.notNull(modeFactory, "modeFactory can't be null");
			this.modeFactory = modeFactory;
			this.currentMode = null;
		}

		@Override
		public void handleEvent(GameEvent event) {
			String type = event.getType();
			String currModeName = currentMode.getName();

			if (type == GameEvent.MODE_EXPIRED) {
				if (BBConfig.ATTRACT_MODE.equals(currModeName)) {
					transitionTo(modeFactory.createArenaMode(this));
				} else if (BBConfig.ARENA_MODE.equals(currModeName)) {
					transitionTo(modeFactory.createAttractMode(this));
				} else {
					throw new IllegalArgumentException("Unexpected mode: " + currModeName);
				}
			} else {
				throw new IllegalArgumentException("Unexpected game event type: " + type);
			}
		}

		public void start() {
			transitionTo(modeFactory.createAttractMode(this));
		}

		public void transitionTo(BBMode mode) {
			Assert.notNull(mode, "mode can't be null");

			// Don't re-stop the current mode.
			// It has already stopped and that's why we're transitioning to a new mode.
//			if (currentMode != null) {
//				currentMode.stop();
//			}

			this.currentMode = mode;
			currentMode.start();
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
