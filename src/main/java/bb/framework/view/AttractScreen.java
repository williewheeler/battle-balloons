package bb.framework.view;

import bb.BBConfig;
import bb.BBContext;
import bb.framework.event.GameEvent;
import bb.framework.event.GameListener;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by willie on 6/25/17.
 */
public class AttractScreen extends GameScreen {
	private BBContext context;
	private GameListener gameListener;
	private int ttl;
	private TickHandler tickHandler;
	private Timer timer;

	public AttractScreen(BBContext context, GameListener gameListener, int ttl) {
		this.context = context;
		this.gameListener = gameListener;
		this.ttl = ttl;
		this.tickHandler = new TickHandler();
		this.timer = new Timer(BBConfig.FRAME_PERIOD_MS, tickHandler);
	}

	public BBContext getContext() {
		return context;
	}

	public int getTtl() {
		return ttl;
	}

	@Override
	public void update() {
		super.update();
		this.ttl--;
	}

	@Override
	public boolean isActive() {
		return ttl > 0;
	}

	protected void fireGameEvent(String type) {
		gameListener.handleEvent(new GameEvent(this, type));
	}

	private class TickHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// Use the game screen for lifecycle management, not the model, as the screen manages the actors.
			if (isActive()) {
				update();
				getTopLevelAncestor().repaint();
			} else {
				fireGameEvent(GameEvent.SCREEN_EXPIRED);
			}
		}
	}
}
