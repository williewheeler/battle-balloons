package bb.common;

import bb.framework.event.ScreenEvent;
import bb.framework.event.ScreenListener;
import bb.framework.scene.Scene;
import bb.framework.util.Assert;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.List;

import static bb.common.BBConfig.SCREEN_SIZE_PX;

/**
 * Created by willie on 6/19/17.
 */
public abstract class BBScreen extends JPanel {
	private BBContext context;
	private Scene scene;
	private KeyListener keyListener;
	private ActionListener tickListener;
	private Timer timer;

	private final List<ScreenListener> screenListeners = new LinkedList<>();

	public BBScreen(BBContext context, Scene scene) {
		Assert.notNull(context, "context can't be null");
		Assert.notNull(scene, "scene can't be null");
		this.context = context;
		this.scene = scene;

		this.keyListener = buildKeyListener();
		this.tickListener = buildTickListener();
		this.timer = new Timer(BBConfig.FRAME_PERIOD_MS, tickListener);

		setBackground(Color.BLACK);
	}

	@Override
	public Dimension getPreferredSize() {
		return SCREEN_SIZE_PX;
	}

	public BBContext getContext() {
		return context;
	}

	public Scene getScene() {
		return scene;
	}

	public boolean isActive() {
		if (scene == null) {
			throw new IllegalStateException("scene can't be null");
		}
		return scene.isActive();
	}

	public abstract KeyListener buildKeyListener();

	public KeyListener getKeyListener() {
		return keyListener;
	}

	public void start() {
		timer.start();
	}

	public void stop() {
		timer.stop();
	}

//	@Override
//	public void paint(Graphics g) {
//		super.paint(g);
//		paintActors(g);
//	}

	public void addScreenListener(ScreenListener listener) {
		screenListeners.add(listener);
	}

	protected void fireScreenEvent(int type) {
		ScreenEvent event = new ScreenEvent(type);
		screenListeners.forEach(listener -> listener.handleEvent(event));
	}

	private ActionListener buildTickListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Use the game screen for lifecycle management, not the model, as the screen manages the actors.
				if (isActive()) {
					scene.update();
					getTopLevelAncestor().repaint();
				} else {
					fireScreenEvent(ScreenEvent.SCREEN_EXPIRED);
				}
			}
		};
	}
}
