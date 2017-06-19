package bb.backstory;

import bb.core.GameController;
import bb.core.event.GameEvent;
import bb.core.event.GameListener;
import bb.core.view.FontFactory;

import javax.swing.JComponent;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static bb.BBConfig.FRAME_PERIOD_MS;

/**
 * Created by willie on 6/18/17.
 */
public class BackstoryController implements GameController {
	private BackstoryModel backstoryModel;
	private BackstoryScreen backstoryScreen;
	private TickHandler tickHandler;
	private KeyHandler keyHandler;
	private Timer timer;
	private GameListener gameListener;

	public BackstoryController(FontFactory fontFactory, GameListener gameListener) {
		this.backstoryModel = new BackstoryModel();
		this.backstoryScreen = new BackstoryScreen(backstoryModel, fontFactory);
		this.tickHandler = new TickHandler();
		this.keyHandler = new KeyHandler();
		this.timer = new Timer(FRAME_PERIOD_MS, tickHandler);
		this.gameListener = gameListener;
	}

	@Override
	public void start() {
		timer.start();
	}

	@Override
	public void stop() {
		timer.stop();
	}

	@Override
	public JComponent getScreen() {
		return backstoryScreen;
	}

	@Override
	public KeyListener getKeyListener() {
		return keyHandler;
	}

	private void fireGameEvent(String type) {
		gameListener.handleEvent(new GameEvent(this, type));
	}

	private class TickHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (backstoryModel.isActive()) {
				backstoryModel.update();
				backstoryScreen.getTopLevelAncestor().repaint();
			} else {
				fireGameEvent(GameEvent.SCREEN_EXPIRED);
			}
		}
	}

	private class KeyHandler extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			super.keyPressed(e);
			fireGameEvent(GameEvent.SCREEN_ABORTED);
		}
	}
}
