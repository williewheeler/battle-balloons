package bb.core;

import bb.core.event.GameEvent;
import bb.core.event.GameListener;
import bb.core.model.GameModel;

import javax.swing.JComponent;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import static bb.BBConfig.FRAME_PERIOD_MS;

/**
 * Created by willie on 6/19/17.
 */
public abstract class AbstractGameController implements GameController {
	private GameModel gameModel;
	private JComponent gameScreen;
	private GameListener gameListener;
	private KeyListener keyListener;
	private TickHandler tickHandler;
	private Timer timer;

	public AbstractGameController(GameModel gameModel, JComponent gameScreen, GameListener gameListener) {
		this.gameModel = gameModel;
		this.gameScreen = gameScreen;
		this.gameListener = gameListener;
		this.tickHandler = new TickHandler();
		this.timer = new Timer(FRAME_PERIOD_MS, tickHandler);
	}

	@Override
	public GameModel getGameModel() {
		return gameModel;
	}

	@Override
	public JComponent getGameScreen() {
		return gameScreen;
	}

	@Override
	public KeyListener getKeyListener() {
		return keyListener;
	}

	protected void setKeyListener(KeyListener keyListener) {
		this.keyListener = keyListener;
	}

	@Override
	public void start() {
		timer.start();
	}

	@Override
	public void stop() {
		timer.stop();
	}

	protected void fireGameEvent(String type) {
		gameListener.handleEvent(new GameEvent(this, type));
	}

	private class TickHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (gameModel.isActive()) {
				gameModel.update();
				gameScreen.getTopLevelAncestor().repaint();
			} else {
				fireGameEvent(GameEvent.SCREEN_EXPIRED);
			}
		}
	}
}
