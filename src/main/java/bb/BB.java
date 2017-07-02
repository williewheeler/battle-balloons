package bb;

import bb.arena.ArenaMode;
import bb.attract.AttractMode;
import bb.common.BBConfig;
import bb.common.BBContext;
import bb.framework.event.ModeEvent;
import bb.framework.event.ModeListener;
import bb.framework.mode.BBMode;
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
		log.info("Starting BB");

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

		public BBMode createAttractMode() {
			return new AttractMode(context, screenManager);
		}

		public BBMode createArenaMode() {
			return new ArenaMode(context, screenManager);
		}
	}

	/**
	 * Top-level state machine to transition between modes.
	 */
	private static class BBStateMachine implements ModeListener {
		private BBModeFactory modeFactory;
		private BBMode currentMode;

		public BBStateMachine(BBModeFactory modeFactory) {
			Assert.notNull(modeFactory, "modeFactory can't be null");
			this.modeFactory = modeFactory;
			this.currentMode = null;
		}

		@Override
		public void handleEvent(ModeEvent event) {
			final int type = event.getType();
			final String currModeName = currentMode.getName();

			if (type == ModeEvent.MODE_STOPPED) {
				if (BBConfig.ATTRACT_MODE.equals(currModeName)) {
					transitionTo(modeFactory.createArenaMode());
				} else if (BBConfig.ARENA_MODE.equals(currModeName)) {
					transitionTo(modeFactory.createAttractMode());
				} else {
					throw new IllegalArgumentException("Unexpected mode: " + currModeName);
				}
			} else {
				throw new IllegalArgumentException("Unexpected game event type: " + type);
			}
		}

		public void start() {
			log.trace("Starting BB state machine");
			transitionTo(modeFactory.createAttractMode());
		}

		/**
		 * This method assumes that the current mode has already stopped, and therefore does not re-yield the node.
		 *
		 * @param mode
		 */
		public void transitionTo(BBMode mode) {
			Assert.notNull(mode, "mode can't be null");
			log.trace("BB transitioning to mode={}", mode);
			this.currentMode = mode;
			currentMode.addModeListener(this);
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
