package bb;

import bb.attract.AttractMode;
import bb.common.BBConfig;
import bb.common.BBContext;
import bb.framework.event.ModeEvent;
import bb.framework.event.ModeListener;
import bb.framework.mode.Mode;
import bb.framework.screen.Resizer;
import bb.framework.screen.Screen;
import bb.framework.screen.ScreenManager;
import bb.framework.util.Assert;
import bb.game.GameMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;

import static bb.common.BBConfig.SCREEN_SCALE_BY;
import static bb.common.BBConfig.SCREEN_SIZE_PX;

/**
 * Battle Ballons top-level class.
 */
public class BB extends JFrame {
	private static final Logger log = LoggerFactory.getLogger(BB.class);

	private BBStateMachine stateMachine;

	public BB() {
		super("Battle Balloons");

		BBConfig config = new BBConfig();
		BBContext context = new BBContext();
		ScreenManager screenManager = new ScreenManagerImpl();
		BBModeFactory modeFactory = new BBModeFactory(config, context, screenManager);

		this.stateMachine = new BBStateMachine(modeFactory);
	}

	public void start() {
		log.info("Starting BB");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Resizer resizer = new Resizer(SCREEN_SIZE_PX, SCREEN_SCALE_BY);
		getContentPane().add(resizer, BorderLayout.CENTER);
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);

		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		stateMachine.start();
	}

	/**
	 * Manages the current screen.
	 */
	private class ScreenManagerImpl implements ScreenManager {
		private Screen currentScreen;

		@Override
		public void startScreen(Screen screen) {
			Assert.notNull(screen, "screen can't be null");

			stopCurrentScreen();

			this.currentScreen = screen;
			Resizer resizer = new Resizer(SCREEN_SIZE_PX, SCREEN_SCALE_BY);
			resizer.add(currentScreen.getJComponent());
			getContentPane().add(resizer);
			validate();
			addKeyListener(currentScreen.getKeyHandler());
			currentScreen.start();
		}

		@Override
		public void stopCurrentScreen() {
			if (currentScreen != null) {
				currentScreen.stop();
				removeKeyListener(currentScreen.getKeyHandler());
				getContentPane().removeAll();
			}
		}
	}

	/**
	 * Creates new mode instances.
	 */
	private static class BBModeFactory {
		private BBConfig config;
		private BBContext context;
		private ScreenManager screenManager;

		public BBModeFactory(BBConfig config, BBContext context, ScreenManager screenManager) {
			Assert.notNull(config, "config can't be null");
			Assert.notNull(context, "context can't be null");
			Assert.notNull(screenManager, "screenManager can't be null");

			this.config = config;
			this.context = context;
			this.screenManager = screenManager;
		}

		public Mode createAttractMode() {
			return new AttractMode(config, context, screenManager);
		}

		public Mode createGameMode() {
			return new GameMode(config, context, screenManager);
		}
	}

	/**
	 * Top-level state machine to transition between modes.
	 */
	private static class BBStateMachine implements ModeListener {
		private BBModeFactory modeFactory;
		private Mode currentMode;

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
					transitionTo(modeFactory.createGameMode());
				} else if (BBConfig.GAME_MODE.equals(currModeName)) {
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
		public void transitionTo(Mode mode) {
			Assert.notNull(mode, "mode can't be null");
			log.trace("Mode transition: {}", mode.getClass().getSimpleName());
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
