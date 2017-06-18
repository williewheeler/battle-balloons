package bb.title;

import bb.BB;
import bb.core.GameController;
import bb.title.model.TitleModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.JComponent;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static bb.BBConfig.FRAME_PERIOD_MS;

/**
 * Created by willie on 6/17/17.
 */
public class TitleController implements GameController {
	private static final Logger log = LoggerFactory.getLogger(TitleController.class);

	private BB bb;
	private TitleModel titleModel;
	private TitleScreen titleScreen;
	private TickHandler tickHandler;
	private KeyHandler keyHandler;
	private Timer timer;

	public TitleController(BB bb) {
		this.bb = bb;
		this.titleModel = new TitleModel();
		this.titleScreen =
				new TitleScreen(titleModel, bb.getFontFactory(), bb.getImageFactory(), bb.getSpriteFactory());
		this.tickHandler = new TickHandler();
		this.keyHandler = new KeyHandler();
		this.timer = new Timer(FRAME_PERIOD_MS, tickHandler);
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
		return titleScreen;
	}

	@Override
	public KeyListener getKeyListener() {
		return keyHandler;
	}

	private class TickHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			titleModel.update();
			bb.repaint();
		}
	}

	private class KeyHandler extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			super.keyPressed(e);
			switch (e.getKeyCode()) {
				case KeyEvent.VK_1:
					bb.startGame(1);
					break;
				case KeyEvent.VK_2:
					bb.startGame(2);
					break;
			}
		}
	}
}
