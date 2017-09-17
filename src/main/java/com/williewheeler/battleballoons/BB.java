package com.williewheeler.battleballoons;

import com.williewheeler.battleballoons.attract.AttractMode;
import com.williewheeler.battleballoons.common.BBConfig;
import com.williewheeler.battleballoons.common.view.BBViewContext;
import com.williewheeler.battleballoons.game.GameMode;
import io.halfling.mode.event.ModeEvent;
import io.halfling.mode.event.ModeListener;
import io.halfling.mode.Mode;
import io.halfling.view.Resizer;
import io.halfling.view.Screen;
import io.halfling.view.ScreenManager;
import io.halfling.core.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;

/**
 * Battle Ballons top-level class.
 */
public class BB extends JFrame {
	private static final Logger log = LoggerFactory.getLogger(BB.class);

	private ModeHandler modeHandler;

	public BB() {
		super("Battle Balloons");
		BBConfig config = new BBConfig();
		BBViewContext context = new BBViewContext();
		ScreenManager screenManager = new ScreenManagerImpl();
		BBModeFactory modeFactory = new BBModeFactory(config, context, screenManager);
		this.modeHandler = new ModeHandler(modeFactory);
	}

	public void start() {
		log.info("Starting BB");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Resizer resizer = new Resizer(BBConfig.SCREEN_SIZE_PX, BBConfig.SCREEN_SCALE_BY);
		getContentPane().add(resizer, BorderLayout.CENTER);
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		modeHandler.start();
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
			doStartScreen(screen);
		}
		
		@Override
		public void stopCurrentScreen() {
			if (currentScreen != null) {
				log.trace("Stopping screen: {}", currentScreen);
				currentScreen.stop();
				removeKeyListener(currentScreen.getKeyHandler());
				getContentPane().removeAll();
				this.currentScreen = null;
			}
		}
		
		private void doStartScreen(Screen screen) {
			log.trace("Starting screen: {}", screen);
			this.currentScreen = screen;
			Resizer resizer = new Resizer(BBConfig.SCREEN_SIZE_PX, BBConfig.SCREEN_SCALE_BY);
			resizer.add(currentScreen.getJComponent());
			getContentPane().add(resizer);
			validate();
			addKeyListener(currentScreen.getKeyHandler());
			currentScreen.start();
		}
	}

	/**
	 * Creates new mode instances.
	 */
	private static class BBModeFactory {
		private BBConfig config;
		private BBViewContext context;
		private ScreenManager screenManager;

		public BBModeFactory(BBConfig config, BBViewContext context, ScreenManager screenManager) {
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
	
	// TODO Refactor this along the lines of ScreenHandler. [WLW]
	private static class ModeHandler implements ModeListener {
		private BBModeFactory modeFactory;
		private Mode currentMode;

		public ModeHandler(BBModeFactory modeFactory) {
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
